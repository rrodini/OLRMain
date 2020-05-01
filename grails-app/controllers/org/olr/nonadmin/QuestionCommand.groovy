package org.olr.nonadmin

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class QuestionCommand implements Validateable {
    String qText
    String aText
    boolean publik
    // Don't expose these values.  Messes things up, but don't know why.
    //byte [] figure
    //String figureBase64
    //String figureName
    Integer format
    Integer difficulty
    Integer gradeLevel


    MultipartFile imgFile

    static constraints = {
        qText validator: { val, obj ->
            if (val.indexOf("<script") >= 0 ) {
                return ["validation.customRuntimeMessage", "Question cannot contain <script> tag."]
            }
        }
        aText validator: { val, obj ->
            if (val.indexOf("<script") >= 0 ) {
                return ["validation.customRuntimeMessage", "Answer cannot contain <script> tag."]
            }
        }
        imgFile validator: { val, obj ->
            // val is StandardMultipartHttpServletRequest
            String figureName = val.originalFilename
            if (figureName.isEmpty()) {
                return true
            }
            println "figure name: ${figureName}"
            if (!figureName.toLowerCase().endsWith(".png") ) {
                return ["validation.customRuntimeMessage", "file name must end with .png"]
            }
            // is this needed?
            return true
        }
    }

}