package org.olr.nonadmin
/**
 * JsonValidator - validates json instance files against a schema file.
 * Used to validate player files and question files before they are stored in th database.
 *
 * Usage:  validateWithSchema{"questionFileSchema.json", "string of questionFile contents"}
 *
 */

import org.leadpony.justify.api.JsonSchema
import org.leadpony.justify.api.JsonValidationService
import org.springframework.beans.factory.annotation.Autowired

import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.*
import org.leadpony.justify.api.ProblemHandler
import org.leadpony.justify.api.Problem

import org.springframework.beans.factory.annotation.Autowired

class JsonValidator {
//  The only instance of JSON validation service.
    static JsonValidationService service = JsonValidationService.newInstance()
//  D.I. grailsResourceLocator
    @Autowired()
    static def grailsResourceLocator
/**
 * validate - validates the json instance against the given schema.
 * Any errors (Problems) are returned to the caller.  Otherwise the list is empty.
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

    static def validateWithSchema(String schemaName, String instance) {
        // setup - read the schema
        String schemaFile
        schemaFile = grailsResourceLocator.findResourceForURI('classpath:/src/main/resources/' + schemaName).getFile()
        String schemaString
        schemaString = schemaFile.text
//        String schemaString = new File(getClass().getClassLoader().getResource(schemaName)).text
        JsonSchema schema = service.readSchema(new StringReader(schemaString))
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
