package org.olr.nonadmin

import grails.validation.Validateable

/**
 * Tricky Grails command object.
 * If a "filter" value is "on" then a companion value must be present
 * e.g. difficultyFilter => difficulty variable must be valued.
 * Otherwise, the value can be null and thereby ignored.
 */
class FilterCommand implements Validateable {
    // field values submitted by form
    String idFilter
    String idStart
    String idEnd
    String difficultyFilter
    String difficulty
    String gradeLevelFilter
    String gradeLevel

    static constraints = {

        idFilter ( nullable: true, blank: true,
            validator: { val, obj ->
            if (val == null) {
                return true
            } else {
                if (val.equals("on")) {
                    def startVal = obj.idStart ?: null
                    startVal = startVal ? FilterCommand.convertLongIfPresent(startVal) : null
                    if (startVal == null) {
                        return ["validation.customRuntimeMessage", "Id start value is not valid."]
                    }
                    def endVal = obj.idEnd ?: null
                    endVal = endVal? FilterCommand.convertLongIfPresent(endVal) : null
                    if (endVal == null) {
                        return ["validation.customRuntimeMessage", "Id end value is not valid."]
                    }
                }
            }
        })
        idStart (nullable: true, blank: true)
        idEnd (nullable: true, blank: true)
        difficultyFilter( nullable: true, blank: true,
            validator: { val, obj ->
            if (val == null) {
                return true
            } else {
                if (val.equals("on")) {
                    String difficulty = obj.difficulty ?: ""
                    if (difficulty.isEmpty()) {
                        return ["validation.customRuntimeMessage", "No difficulty level was selected."]
                    }
                    return true
                }
            }
         })
         difficulty (nullable: true, blank: true)
         gradeLevelFilter(  nullable: true, blank: true,
            validator: { val, obj ->
            if (val == null) {
                return true
            } else {
                if (val.equals("on")) {
                    String gradeLevel = obj.gradeLevel ?: ""
                    if (gradeLevel.isEmpty()) {
                        return ["validation.customRuntimeMessage", "No grade level was selected."]
                    }
                    return true
                }
            }
        })
        gradeLevel( nullable: true, blank: true)
    }

    static Long convertLongIfPresent(String val) {
        Long result = null;
        try {
            if (val) {
                result = Long.valueOf(val)
            }
        } catch (NumberFormatException) {
            // nothing to do since null will be returned.
        }
        return result
    }
}