package org.olr.nonadmin

import java.time.Instant
import java.sql.Timestamp

class EndGameController {
    static allowedMethods = [update: ['PUT']]

    def update() {
        def method = request.getMethod()
        println ">>endGame/update ${method}"
        // update game record here.
        def gameId = Long.parseLong(params.game_id)
        def endTimeISO = params.end_time // starts as ISO string
        def questionCount = Integer.parseInt(params.question_count)
        Instant endInstant = Instant.parse(endTimeISO)
        def endTime = Timestamp.from(endInstant) // java.sql.timestamp
        def game = Game.findById(gameId)
        def startTime = game.startTime  // java.sql.timestamp
        // compute seconds
        def duration = ((int) (endTime.getTime() - startTime.getTime())).intdiv(1000)
        println "update game set endTime = ${endTime}, questionCount = ${questionCount} where id = ${gameId}"
        org.olr.nonadmin.Game.withTransaction {
            Game.executeUpdate("update Game set endTime = :endTime, duration = :duration, questionCount = :questionCount where id = :gameId",
                    [endTime: endTime, duration: duration, questionCount: questionCount, gameId: gameId])
        }
        println "Game record updated."
        render(text:"", status:200)
    }

}
