package org.olr.nonadmin
import groovy.json.JsonSlurper

class JsonFieldService {

    static JsonSlurper jsonSlurper = new JsonSlurper()

    def getField(jsonString, fieldName) {
        //def jsonSlurper = new JsonSlurper()
        def json = jsonSlurper.parseText(jsonString)
        // Groovy dynamic indexing below
        def field = json[fieldName]
        return field
    }
}
