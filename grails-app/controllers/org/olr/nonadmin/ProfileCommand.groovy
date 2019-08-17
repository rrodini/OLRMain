package org.olr.nonadmin

import grails.plugin.springsecurity.ui.CommandObject

class ProfileCommand implements CommandObject, grails.validation.Validateable {

//    protected static Class<?> User
//    protected static String usernamePropertyName
    Integer version
    String username
    String email
    String password
    String password2
//  OLR fields added.
    String firstName
    String lastName
    String org
    String orgAddress
    String orgCity
    String orgState
    String orgZip

    static constraints = {
//        username cannot be updated.
//        username validator: { value, command ->
//            if (!value) {
//                return
//            }
//
//            if (User.findWhere((usernamePropertyName): value)) {  //usernamePropertyName
//                return 'registerCommand.username.unique'
//            }
//        }
        email email: true, blank:false
        password validator: RegisterController.passwordValidator
        password2 nullable: true, validator: RegisterController.password2Validator
// OLR fields added
        firstName nullable:false, size: 1..40
        lastName nullable:false, size: 1..40
        org  size: 3..40
        orgAddress size: 5..40
        orgCity size: 5..40
        orgState size: 2..20
        orgZip size: 5..5
    }
}