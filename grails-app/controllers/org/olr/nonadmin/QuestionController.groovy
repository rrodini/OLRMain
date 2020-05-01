package org.olr.nonadmin

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuestionController {

    QuestionService questionService
    OwnerFileService ownerFileService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def rowList
        def questionCount
        params.max = Math.min(max?: 10, 10)

        def user = springSecurityService.currentUser
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            rowList = questionService.list(params)
            questionCount = questionService.count()
        } else {
            params.ownerId = user.id
            rowList = ownerFileService.questionListByOwner(params)
            questionCount = ownerFileService.questionListByOwnerCount(params)
        }
        respond rowList, model:[questionCount: questionCount]
    }

    def show(Long id) {
        respond questionService.get(id)
    }

    def create() {
        respond new Question(params)
    }

    def save(QuestionCommand cmd) {
        Question question = new Question()
        question.properties = cmd
        if (cmd.hasErrors()) {
            respond question, view: 'create', model: [command:cmd]
            return
        }
        if (cmd.imgFile.filename.length() > 0) {
            // read the image
            InputStream image = cmd.imgFile.getInputStream()
            question.figureBase64 = image.bytes.encodeBase64().toString()
            question.figureName = cmd.imgFile.getOriginalFilename()
        } else {
            question.figureBase64 = null
        }
        // set the owner
        def user = springSecurityService.currentUser
        question.owner = user

        // Now get the contents of the image file.
        //InputStream input = question.figure.getInputStream()
        //byte[] figure = input?.text

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect question
            }
            '*' { respond question, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questionService.get(id)
    }

    def update(Question question) {
        if (question == null) {
            notFound()
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect question
            }
            '*'{ respond question, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        questionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
