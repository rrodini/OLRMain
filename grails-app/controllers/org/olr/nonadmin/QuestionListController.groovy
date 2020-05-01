package org.olr.nonadmin

import grails.plugin.springsecurity.SpringSecurityService
import groovy.json.JsonOutput
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class QuestionListController {
//  D.I. here
    QuestionFilterService questionFilterService
    SpringSecurityService springSecurityService
    JsonValidatorService jsonValidatorService
    QuestionFileService questionFileService
    JsonFieldService jsonFieldService

    // For debugging
    def showParams(params) {
        println "-" * 10
        println "count: ${params.size()}"
        params.each { key, val ->
            println "${key}: ${val}"
        }
    }
    // Index returns an unfiltered list of questions.
    def index(Integer max) {
        def rowList
        def questionCount
        //showParams()
        params.max = Math.min(max?: 8, 8)

        def user = springSecurityService.currentUser
        params.ownerId = user.id
        rowList = questionFilterService.questionListByFilter(params)
        questionCount = questionFilterService.questionListByFilterCount(params)
        respond rowList, model:[questionCount: questionCount]
     }
    // Filter returns a filtered list of question.
    def filter(FilterCommand filterCmd) {
        def rowList
        def questionCount
        //showParams()
        // If there are any filter errors show them now with an empty list.
        if (filterCmd.hasErrors()) {
            rowList = []
            respond rowList, view: 'index', model: [filterCommand:filterCmd]
            return
        }
        // Otherwise proceed with the filtering.
        def user = springSecurityService.currentUser
        params.ownerId = user.id
        rowList = questionFilterService.questionListByFilter(params)

        questionCount = questionFilterService.questionListByFilterCount(params)
        respond rowList, view: 'index', model:[command:filterCmd, questionCount: questionCount]
    }

    def save(QFCommand qfCmd, FilterCommand fltrCmd) {
        showParams()
        def rowList
        def user = springSecurityService.currentUser
        params.ownerId = user.id
        rowList = questionFilterService.questionListByFilter(params)
        if (qfCmd.hasErrors()) {
            respond rowList, view: 'index', model: [qfCommand:qfCmd]
            return
        }
        // Otherwise process the filtered questions into QuestionFile format.

        // Language wise we are going from Groovy maps/arrays -> JSON
        def groovyMap = [description: "${params.description}"]
        def groovyQandAs = []
        def groovyFigures = []
        int questionCount = 0
        // Lots of grooviness below
        rowList.each { q ->
            questionCount++
            String question = "${q.qText + (q.figureName!=null ? '`figure-' + questionCount + '`':'')}"
            groovyQandAs << [q: "${question}",
                                a:"${q.aText}",
                                f:q.format]
            // Does question have a figure?
            if (q.figureName) {
                groovyFigures << [name: "figure-${questionCount}",
                                  src: "${q.figureName}",
                                  val: "<div><img src=data:image/png;base64,${q.figureBase64}></div>" ]
            }
        }
//        println "Description"
//        println groovyMap
//        println "Q and A"
//        println groovyQandAs
//        println "Figures"
//        println groovyFigures
        // Now add two additional properties to the map.
        groovyMap.questions_and_answers = groovyQandAs
        groovyMap.figures = groovyFigures
        def json = JsonOutput.toJson(groovyMap)
        String questionsJson = JsonOutput.prettyPrint(json)
        println questionsJson
        // Now validate the file.
        def probList = []
        probList = jsonValidatorService.validateWithSchema('questionFileSchema.json', questionsJson)
        if (probList) {
            respond questionFile, view: 'edit', model: [problems: probList]
            return
        }
        // TODO: scan for <script> tags within file. Reject if present.
        // Passed file checks and validation checks, ready for the database.
        def questionFile = new QuestionFile(questionsJson: questionsJson, publik: false, owner: user)
        try {
            questionFileService.save(questionFile)
        } catch (ValidationException e) {
            render bean:questionFile.errors, view:'/questionFile/edit'
            return
        }

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'questionFile.label', default: 'QuestionFile'), questionFile.id])
//                redirect questionFile
//            }
//            '*' { respond questionFile, [status: CREATED, view: "create"]  }
//        }

//        render bean: questionFile, view: "/questionFile/show", status: CREATED
        // Kludge: just show the description field
        questionFile.questionsJson = jsonFieldService.getField(questionFile.questionsJson, 'description')
        flash.message = message(code: 'default.created.message', args: [message(code: 'questionFile.label', default: 'QuestionFile'), questionFile.id])
        respond questionFile, view: "/questionFile/show", status: CREATED
    }
}
