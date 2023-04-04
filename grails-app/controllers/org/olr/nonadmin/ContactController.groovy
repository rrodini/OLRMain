package org.olr.nonadmin

import grails.core.GrailsApplication
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ContactController {

    ContactService contactService
    SpringSecurityService springSecurityService
    def mailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def contactList = contactService.list(params)
        def contactCount = contactList.size()
        respond contactList, model:[contactCount: contactCount]
    }

    def show(Long id) {
        respond contactService.get(id)
    }

    def create() {
        respond new Contact(params)
    }

    def save(Contact contact) {
        def user = springSecurityService.currentUser
        String admin = grailsApplication.config.getProperty('grails.mail.username')

//        println "Send mail credentials:"
//        println admin

        // this is the user submitting the contact
        contact.owner = user
        if (contact == null) {
            notFound()
            return
        }

        // closure here, not method parameters
        mailService.sendMail {
            to admin
            from admin
            subject "OLR Contact"
            text "from: ${contact.owner.email}\nsubject: ${contact.subject}\ntext: ${contact.feedback}"
        }

        try {
            contactService.save(contact)
        } catch (ValidationException e) {
            respond contact.errors, view:'create'
            return
        }
/*
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contact.label', default: 'Contact'), contact.id])
                redirect contact
            }
            '*' { respond contact, [status: CREATED] }
        }
 */
        redirect(url: '/')
    }

    def edit(Long id) {
        respond contactService.get(id)
    }

    def update(Contact contact) {
        if (contact == null) {
            notFound()
            return
        }

        try {
            contactService.save(contact)
        } catch (ValidationException e) {
            respond contact.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contact.label', default: 'Contact'), contact.id])
                redirect contact
            }
            '*'{ respond contact, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        contactService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
