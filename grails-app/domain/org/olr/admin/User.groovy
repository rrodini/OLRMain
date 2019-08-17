package org.olr.admin

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import org.olr.nonadmin.QuestionFile
import org.olr.nonadmin.PlayerFile
import org.olr.nonadmin.Game

//@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
//  OLR fields added
    Date lastLoginTime
    String firstName
    String lastName
    String org
    String orgAddress
    String orgCity
    String orgState
    String orgZip

    static hasMany = [questionFiles: QuestionFile, playerFiles: PlayerFile, games: Game]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        email nullable: false, blank: false, email: true
// OLR fields added
        lastLoginTime nullable:true
        firstName nullable:false, size: 1..40
        lastName nullable:false, size: 1..40
        org  nullable: true, size: 3..40
        orgAddress nullable: true, size: 5..40
        orgCity nullable: true, size: 5..40
        orgState nullable: true, size: 2..20
        orgZip nullable: true, size: 5..5
    }

    static mapping = {
        table 'user_olr'
	    password column: '`password`'
    }

    @Override
    String toString() {
        return "${username} (id: ${id})"
    }
}
