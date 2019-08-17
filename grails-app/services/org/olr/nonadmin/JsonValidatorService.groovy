package org.olr.nonadmin

import org.grails.io.support.GrailsResourceUtils

/**
 * JsonValidatorService - validates json instance files against a schema file.
 * Used to validate player files and question files before they are stored in the database.
 *
 * Usage:  validateWithSchema{"questionFileSchema.json", "string of questionFile contents"}
 *
 * ATTENTION: tried many way to get the schema file loaded by resource.getFile() API combination
 * Nothing worked, possibly due to a Tomcat bug where the resource filea are not expaned in the war
 * file.  Finally find this API sequence that works.
 *
 */

import org.leadpony.justify.api.JsonSchema
import org.leadpony.justify.api.JsonValidationService
import org.springframework.beans.factory.annotation.Autowired

import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.*
import org.leadpony.justify.api.ProblemHandler
import org.leadpony.justify.api.Problem

class JsonValidatorService {
//  The only instance of JSON validation service.
    static JsonValidationService service = JsonValidationService.newInstance()
//  D.I. grailsApplication
    @Autowired
    static def grailsApplication
/**
 * validate - validates the json instance against the given schema.
 * Any errors are returned to the caller as list of Strings.  Otherwise the list is empty.
 */
    static def List validate(JsonSchema schema, String instance) throws Exception {
        List probList = []
        // the handler will collect Problem objects (sorta like Exceptions)
        ProblemHandler handler = ProblemHandler.collectingTo(probList)
        def instanceStream = new StringReader(instance)
        // create parser here.  Will throw error is schema is bad
        JsonParser parser = service.createParser(instanceStream, schema, handler)
        try {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
            }
        } finally {
            parser.close()
        }
        return probList.collect{ p -> p.getContextualMessage()}
    }
/**
 * validateWithSchema - give a schema file name (resource file under /src/main/resources) and
 *                      the contents of a instance json file, perform validation.
 * @param schemaName name of schema file e.g. 'playerFileSchema.json'
 * @param instance String contents of a json file to be validated.
 * @return List of errors (Strings)
 */
    static def validateWithSchema(String schemaName, String instance) {
        // setup - read the schema
        String uri = 'classpath:' + schemaName
        def ctx = grailsApplication.mainContext
        def schemaResource = ctx.getResource(uri)
        def schemaInputStream  = schemaResource.getInputStream()
        String schemaString = schemaInputStream.text
        JsonSchema schema = service.readSchema(new StringReader(schemaString))
        schema.text
        List probList = []
        try {
            probList = validate(schema, instance)
        } catch (Exception e) {
            // this is a serious problem
            // TODO: log the exception
            return probList.add(e.getMessage())
        }
        return probList
    }

}
