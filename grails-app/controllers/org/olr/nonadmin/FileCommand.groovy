package org.olr.nonadmin

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class FileCommand implements Validateable {
    MultipartFile jsonFile
    boolean publik


    static constraints = {
        jsonFile validator: { val, obj ->
            if (val.isEmpty()) {
                return ["validation.customRuntimeMessage", "File name must have some content."]
            }


            def fileName = val.originalFilename
            println "file name: ${fileName}"


            if (!fileName.toLowerCase().endsWith(".json")) {
                return ["validation.customRuntimeMessage", "file name must end with .json"]
            }
            return true
        }
    }

}