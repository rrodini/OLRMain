package org.olr.nonadmin

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.olr.nonadmin.GameTypeEnum
import org.olr.admin.User

class GameController {
    //static scaffold = GameController

    GameService gameService
    OwnerFileService ownerFileService
    SpringSecurityService springSecurityService

    def show() {
        // only gameId passed as part of url
        println(">>gameSummary/show/${params.id}")
        def game = Game.findById(params.id)
        if (!game) {
            println("Game Summary error: game record ${params.id} not found.")
            response.sendError(404)
        }
        def gameTypeName = GameTypeEnum.PR.getLongNameFromNumber(game.gameType)
        def emcee = User.findById(game.emcee.id)
        def emceeName = emcee? emcee.toString() : "Unknown emcee"
 //       [game: game]
        [gameId: game.id,
         startTime: game.startTime,
         endTime: game.endTime,
         emceeName: emceeName,
         roomNo: game.roomNo,
         gameType: gameTypeName,
         questionFildId: game.questionFile,
         playerFildId: game.playerFile,
         questionCount: game.questionCount,
         duration: game.duration,
         description: game.description
        ]
    }

    def update() {
        def gameId = params.id
        def description = params.description
        if (gameId && description?.length() > 0) {
            org.olr.nonadmin.Game.withTransaction {
                gameService.updateGame(gameId, description)
            }
        }
        redirect(url: '/')
    }

    def index(Integer max) {
        def rowList
        def gameCount
        params.max = Math.min(max?: 10, 10)

        def user = springSecurityService.currentUser
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            rowList = gameService.list(params)
            gameCount = gameService.count()
        } else {
            params.ownerId = user.id
            rowList = ownerFileService.gameListByOwner(params)
            gameCount = ownerFileService.gameListByOwnerCount(params)
        }
        render view: "index" , model:[gameList: rowList, gameCount: gameCount]
    }

}
