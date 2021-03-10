package org.olr.nonadmin

import grails.gorm.transactions.Transactional

/**
 * QuestionFilterService performs the filtering select queries on
 * the user's Question rows in the DB.  Questions can be filtered
 * by ID range, difficulty, and grade level.  These queries are all
 * AND conditions such each condition narrows the list of questions
 * that are returned.
 */
@Transactional
class QuestionFilterService {
    // Just for debugging
    def showParams(params) {
        println "-" * 10
        println "count: ${params.size()}"
        params.each { key, val ->
            println "${key}: ${val}"
        }
    }
    // Construct the SQL condition base on the params.
    // Note: errors in the params have been eliminated already.
    String filterConditions(params) {
        String conditions = ""
        if (params.idFilter?.equals("on")) {
            conditions += " and id between " + params.idStart + " and " + params.idEnd
        }
        if (params.difficultyFilter?.equals("on")) {
            // by design the first character of difficulty is numeric
            conditions += " and difficulty <= " + params.difficulty.substring(0,1)
        }
        if (params.gradeLevelFilter?.equals("on")) {
            // by design the first character of gradeLevel is numeric
            conditions += " and grade_level <= " + params.gradeLevel.substring(0,1)
        }
        return conditions
    }
    // Return the count needed for pagination.
    def questionListByFilterCount(params) {
        //showParams(params)
        String conditions = filterConditions(params)
        //println "conditions: ${conditions}"
        String query = "select count(*) from Question where owner_id = ?0 " + conditions
        //println "query: ${query}"
        def count = Question.executeQuery(query,
                [params.ownerId])
        // more params to be processed into and conditions
        return count[0]
    }
    // Return the list of questions.
    def questionListByFilter(params) {
        //showParams(params)
        String conditions = filterConditions(params)
        println conditions
//        String query = "from Question where owner_id = ? " + conditions
//        println query
//        def rowList = Question.executeQuery(query,
//                [params.ownerId], [offset: params.offset, max: params.max])
        String query = "from Question where owner_id = ${params.ownerId}" + conditions + "order by id ASC"
//        println query
        def rowList = Question.executeQuery(query,
                 [offset: params.offset, max: params.max])
        // more params to be processed into and conditions
        return rowList
    }


}
