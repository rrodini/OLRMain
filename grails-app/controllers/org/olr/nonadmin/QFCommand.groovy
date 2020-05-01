package org.olr.nonadmin

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class QFCommand implements Validateable {
    String description
    Integer questionCount
    // need to revalidate these?
//    String idFilter
//    String idStart
//    String idEnd
//    String difficultyFilter
//    String difficulty
//    String gradeLevelFilter
//    String gradeLevel

    static constraints = {
        description (nullable: false, blank: false)
        questionCount (min: 1)
//        idFilter (nullable: true, blank: true)
//        idStart (nullable: true, blank: true)
//        idEnd (nullable: true, blank: true)
//        difficultyFilter (nullable: true, blank: true)
//        difficulty (nullable: true, blank: true)
//        gradeLevelFilter (nullable: true, blank: true)
//        gradeLevel (nullable: true, blank: true)

    }
}