package org.olr.admin

import grails.core.GrailsApplication

class BootStrap {
    GrailsApplication grailsApplication

    def init = { servletContext ->
        // show some environment variables
        dumpEnv()
        // populate an admin user - ROLE_ADMIN
        User adminUser = populateAdmin()
        // populate an regular user - ROLE_USER
        User userUser = populateUserRock()
    }
    def destroy = {
    }

    private dumpEnv() {
        // print selective environment variables
        println "LOG_LEVEL: ${System.getenv('LOG_LEVEL')}"
        println "PORT: ${System.getenv('PORT')}"
        println "OLR_USER_USERNAME: ${System.getenv('OLR_USER_USERNAME')}"
        // tomcat - expect 'null' in development
        println  "tomcat: ${grailsApplication.config.getProperty('server.tomcat.remote-ip-header')}"
        println  "tomcat: ${grailsApplication.config.getProperty('server.tomcat.protocol-header')}"
    }
    /**
     * populateAdmin - create an OLR administrator with ROLE_ADMIN
     * @return User object.
     */
    private User populateAdmin() {
        def username = grailsApplication.config.getProperty('olr.admin.username')
        println "Creating user: ${username}"
        def adminUser = User.findByUsername(username)
        if (!adminUser) {
            println "Fresh Database. Creating ADMIN user."
            def adminRole = new Role(authority: "ROLE_ADMIN").save(failOnError: true)
            assert adminRole
            def password = grailsApplication.config.getProperty('olr.admin.password')
            adminUser = new User(username: username, password: password, email: "rrodini@hotmail.com", firstName: "Bob", lastName: "Rodini").save(failOnError: true)
            assert adminUser
            UserRole.create adminUser, adminRole
            UserRole.withSession {
                it.flush()
                it.clear()
            }
            return adminUser
        }
        else {
            println "Existing admin user, skipping creation"
        }
        return adminUser
    }
    /**
     * populateUserRock - create an OLR user with ROLE_USER
     * @return User object
     */
    private User populateUserRock() {
        def username = grailsApplication.config.getProperty('olr.user.username')
        println "Creating regular user"
        def userRock = User.findByUsername(username)
        if (!userRock) {
            println "Fresh Database. Creating REGULAR user."
            def userRole = new Role(authority: "ROLE_USER").save(failOnError: true)
            assert userRole
            def password = grailsApplication.config.getProperty('olr.user.password')
            userRock = new User(username: username, password: password, email: "rrodini@hotmail.com", firstName: "Bob", lastName: "Rodini").save(failOnError: true)
            UserRole.create userRock, userRole
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }
        else {
            println "Existing regular user, skipping creation"
        }
        return userRock
    }
}

