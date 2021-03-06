package org.olr.nonadmin


import org.olr.admin.User
import org.olr.nonadmin.PlayerFile
import org.olr.nonadmin.QuestionFile

class Game {

    private static final long serialVersionUID = 1

//  Integer id // autogenerated
//  Integer version // autogenerated
    Date startTime  // start timestamp
    Date endTime // end timestamp
    QuestionFile questionFile // id of question file
    PlayerFile playerFile // id of player file (optional)
    Integer roomNo  // "room" in which game was played
    Integer gameType // 0 => Presentation Round, 1 => Open Round, 2 => Countdown Round
    Integer questionCount // number of questions asked
    Integer duration // duration in seconds
    String description // emcee's summary of game

    static belongsTo = [emcee: User]
    User emcee
//  static mapping doesn't work. So table gets a version column.
//    static mapping = {
//        version: false
//    }

    static constraints = {
        id generator: 'identity'  // added
        questionFile column: 'QUESTION_FILE_ID', nullable: false // NOT QUESTION_FILE_ID_ID
        playerFile column: 'PLAYER_FILE_ID', nullable:true
        startTime nullable: false
        endTime nullable: true
        emcee column: 'EMCEE_ID'
        roomNo sqlType: 'smallint', nullable: true
        gameType  sqlType: 'smallint', widget: 'text'  // don't display: 0,1,2 display PR,OLR,CR
        questionCount sqlType: 'smallint', nullable: true
        duration nullable:true
        description nullable:true, widget: 'textarea'
    }

    @Override
    String toString() {
        return "${id}"
    }

}
