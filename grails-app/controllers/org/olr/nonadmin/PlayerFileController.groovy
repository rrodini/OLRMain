package org.olr.nonadmin

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class PlayerFileController {

    PlayerFileService playerFileService
    OwnerFileService ownerFileService
    JsonValidatorService jsonValidatorService
    SpringSecurityService springSecurityService
    JsonFieldService jsonFieldService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def rowList
        def playerFileCount
        params.max = Math.min(max ?: 10, 10)

        def user = springSecurityService.currentUser
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            rowList = playerFileService.list(params)
            playerFileCount = playerFileService.count()
        } else {
            params.ownerId = user.id
            rowList = ownerFileService.playerListByOwner(params)
            playerFileCount = ownerFileService.playerListByOwnerCount(params)
        }
        // KLUDGE - but so be it.
        rowList.each{ row ->
            def desc = jsonFieldService.getField(row.playersJson, 'description')
            // replace the file with just the description field
            row.playersJson = desc
        }

        respond rowList, model:[playerFileCount: playerFileCount]
    }


    def show(Long id) {
        def playerFile = PlayerFile.get(id)
        playerFile.playersJson = jsonFieldService.getField(playerFile.playersJson, 'description')
        respond playerFile
    }

    def create() {
        def probList = []
        respond new QuestionFile(), view: 'create', model: [problems: probList]
    }
// Called by Create screen.
    def save(FileCommand cmd) {
        def playerFile = new PlayerFile(publik: false)  // dummy needed by bean ref in gsp.
        // Command object just validates the file name, not the contents of the file.
        if (cmd.hasErrors()) {
            //println cmd.errors
            respond playerFile, view: 'create', model: [command:cmd, problems: []]
            return
        }
        // Now get the contents of the json file.
        InputStream input = cmd.jsonFile.getInputStream()
        String playersJson = input?.text
        // Now validate the file.
        def probList = []
        probList = jsonValidatorService.validateWithSchema('playerFileSchema.json', playersJson)
        if (probList) {
            respond playerFile, view: 'create', model: [problems: probList]
            return
        }
        // TODO: scan for <script> tags within file. Reject if present.
        // Passed file checks and validation checks, ready for the database.
        def user = springSecurityService.currentUser
        playerFile = new PlayerFile(playersJson: playersJson, owner: user)
        try {
            playerFileService.save(playerFile)
        } catch (ValidationException e) {
            respond playerFile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'playerFile.label', default: 'PlayerFile'), playerFile.id])
                redirect playerFile
            }
            '*' { respond playerFile, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond playerFileService.get(id), view: 'edit', model: [problems: []]
    }
/*  update - allows editing of playerFile text (blob) */
    def update(PlayerFile playerFile) {
        if (playerFile == null) {
            notFound()
            return
        }
        def user = springSecurityService.currentUser
        if (playerFile.ownerId != user.id) {
            flash.error = message(code: 'user.edit.anothers.file')
            respond playerFile, view: 'edit'
            return
        }

        try {
            playerFileService.save(playerFile)
        } catch (ValidationException e) {
            respond playerFile.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'playerFile.label', default: 'PlayerFile'), playerFile.id])
                redirect playerFile
            }
            '*'{ respond playerFile, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        def playerFile = playerFileService.get(id)
        def user = springSecurityService.currentUser
        if (playerFile.ownerId != user.id) {
            flash.error = message(code: 'user.delete.anothers.file')
            respond playerFile, view: 'edit'
            return
        }

        playerFileService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'playerFile.label', default: 'PlayerFile'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerFile.label', default: 'PlayerFile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
