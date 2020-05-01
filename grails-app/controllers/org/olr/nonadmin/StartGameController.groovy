package org.olr.nonadmin


import grails.plugin.springsecurity.SpringSecurityService

class StartGameController {

//  D.I. here
    OwnerFileService ownerFileService
    JsonFieldService jsonFieldService
    SpringSecurityService springSecurityService

    def index() {

        def user = springSecurityService.currentUser
        def params = [max: 1000, offset: 0, ownerId: user.id]
        def questionFileList = ownerFileService.questionFileListByOwner(params)
        questionFileList.each{ row ->
            def desc = jsonFieldService.getField(row.questionsJson, 'description')
            // KLUDGE - replace the file with just the description field
            row.questionsJson = desc
        }
        def playerFileList = ownerFileService.playerFileListByOwner(params);
        playerFileList.each{ row ->
            def desc = jsonFieldService.getField(row.playersJson, 'description')
            // KLUDGE - replace the file with just the description field
            row.playersJson = desc
        }
        println grailsApplication.config.getProperty('url.startGame')
        render view: 'startGameParams', model: [questionFileList: questionFileList, questionColumnList: ["Id", "Description"] ,
                                                playerFileList: playerFileList, playerColumnList: ["Id", "Description"],
                                                emceeId: user.id, urlStartGame: grailsApplication.config.getProperty('url.startGame'),
                                                urlGameEmcee: grailsApplication.config.getProperty('url.gameEmcee')]
    }


// DSL fof the defunct grails webflow plugin
//    def selectFlow = {
//        selectGameType {
//            // get list of question files
//            [qfList: []]
//            on("cancel").to "cancel"
//            on("next").to "selectQuestionFile"
//        }
//        selectQuestionFile {
//            // if flow.gameType == 2
//            //   get list of player file
//            on("previous").to "selectGameType"
//            on("next").to "decision"
//        }
//        decisionAction {
//            action {
//                if (flow.gameType == 3) {
//                    // get list of player files
//                    [plList: []]
//                    "selectPlayerFile"
//                } else {
//                    "startGameAJAX"
//                }
//            }
//        }
//        selectPlayerFile {
//            on("previous").to ("selectQuestionFile")
//            on("next").to ("startGameAJAX")
//        }
//        startGameAJAX {
//            // rendered page has game parameters
//            // and activates node.js application
//            gameStarted()
//        }
//        cancel (redirect: "/")
//        gameStarted()
//    }


}
