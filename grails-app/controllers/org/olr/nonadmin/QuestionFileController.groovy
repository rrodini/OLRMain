package org.olr.nonadmin

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*


class QuestionFileController {
//  D.I. here
    QuestionFileService questionFileService
    OwnerFileService ownerFileService
    JsonValidatorService jsonValidatorService
    SpringSecurityService springSecurityService
    JsonFieldService jsonFieldService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def rowList
        def questionFileCount
        params.max = Math.min(max?: 10, 10)

        def user = springSecurityService.currentUser
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            rowList = questionFileService.list(params)
            questionFileCount = questionFileService.count()
        } else {
            params.ownerId = user.id
            rowList = ownerFileService.questionListByOwner(params)
            questionFileCount = ownerFileService.questionListByOwnerCount(params)
        }
        // KLUDGE - but so be it.
        rowList.each{ row ->
            def desc = jsonFieldService.getField(row.questionsJson, 'description')
            // replace the file with just the description field
            row.questionsJson = desc
        }
        respond rowList, model:[questionFileCount: questionFileCount]
    }

    def show(Long id) {
        def questionFile = QuestionFile.get(id)
        questionFile.questionsJson = jsonFieldService.getField(questionFile.questionsJson, 'description')
        respond questionFile
    }

    def create() {
        def probList = []
        respond new QuestionFile(), view: 'create', model: [problems: probList]
    }
//  called by Create screen.
    def save(FileCommand cmd) {
        def questionFile = new QuestionFile(publik: false)  // dummy needed by bean ref in gsp.
        // Command object just validates the file name, not the contents of the file.
        if (cmd.hasErrors()) {
            respond questionFile, view: 'create', model: [command:cmd, problems: []]
            return
        }
        // Now get the contents of the json file.
        InputStream input = cmd.jsonFile.getInputStream()
        String questionsJson = input?.text
        // Now validate the file.
        def probList = []
        probList = jsonValidatorService.validateWithSchema('questionFileSchema.json', questionsJson)
        if (probList) {
            respond questionFile, view: 'create', model: [command:cmd, problems: probList]
            return
        }
        // TODO: scan for <script> tags within file. Reject if present.
        // Passed file checks and validation checks, ready for the database.
        def user = springSecurityService.currentUser
        questionFile = new QuestionFile(questionsJson: questionsJson, publik: cmd.publik, owner: user)
        try {
            questionFileService.save(questionFile)
        } catch (ValidationException e) {
            respond questionFile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionFile.label', default: 'QuestionFile'), questionFile.id])
                redirect questionFile
            }
            '*' { respond questionFile, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questionFileService.get(id), view: 'edit', model: [problems: []]
    }

/*  update - allows editing of questionFile text (blob) */
    def update(QuestionFile questionFile) {
        if (questionFile == null) {
            notFound()
            return
        }
        def user = springSecurityService.currentUser
        if (questionFile.ownerId != user.id) {
            flash.error = message(code: 'user.edit.anothers.file')
            respond questionFile, view: 'edit'
            return
        }
        try {
            questionFileService.save(questionFile)
        } catch (ValidationException e) {
            respond questionFile.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionFile.label', default: 'QuestionFile'), questionFile.id])
                redirect questionFile
            }
            '*'{ respond questionFile, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        def questionFile = questionFileService.get(id)
        def user = springSecurityService.currentUser
        if (questionFile.ownerId != user.id) {
            flash.error = message(code: 'user.delete.anothers.file')
            respond questionFile, view: 'edit'
            return
        }
        questionFileService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionFile.label', default: 'QuestionFile'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionFile.label', default: 'QuestionFile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
