package org.olr.nonadmin
import org.olr.admin.User
import static grails.gorm.hibernate.mapping.MappingBuilder.orm
import grails.compiler.GrailsCompileStatic
@GrailsCompileStatic
class Question {
//  Integer id // autogenerated by db
//  Integer version - autogenerated integer
    Date dateCreated  // auto filled
    Date lastUpdated  // auto filled
    String qText        // question text - can contain markup!
    String aText        // answer text - can contain markup!
    boolean publik      // public to all users
    //byte [] figure    original image bytes not needed
    String figureBase64 // image in BASE64 format, used in data-URI for figure
    String figureName   // figure name (for documentation)
    Integer format      // how to format for projector display
    Integer difficulty  // e.g. 1 (easy), 2 (medium), 3 (hard)
    Integer gradeLevel  // grade: 5,6,7,8,9

    static belongsTo = [owner: User]
    User owner;         // filled by controller save() method

    static constraints = {
        id generator: 'identity'
        owner nullable: false, editable: false
        qText sqlType: 'text', nullable: false, widget: 'textarea'
        aText sqlType: 'text', nullable: false
        figureBase64 sqlType: 'text', nullable: true, blank: true, maxSize: 1024*12
        figureName nullable: true, blank: true
        format min: 0, max: 5, sqlType: 'smallInt', nullable: true, blank: true
        difficulty min: 1, max: 4, sqlType: 'smallInt', nullable: true, blank: true
        gradeLevel min: 6, max: 8, sqlType: 'smallInt', nullable: true, blank: true
    }
//  Below should work but doesn't due to Intellij bug regarding @GrailsCompileStatic
//    static mapping = {
//        sort id: "asc"
//    }
//  Workaround found on StackOverflow
    static final mapping = orm {
        sort dateCreated: "asc"
    }

    @Override
    String toString() {
        return "${id}"
    }

}
