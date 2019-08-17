package org.olr.nonadmin

import grails.plugin.springsecurity.SpringSecurityService

/**
 * The implementation is inspired by a blog article ().
 * The technique was made to work with grails.plugin.springsecurity v. 4.0Mxyz
 * - protect all controllers provided by the plugin with the exception of login/logout/register
 * - put all secure controllers under the /admin/... url
 * - secure url can only be accessed by ROLE_ADMIN
 *
 * Basically w. ROLE_USER you either get a 404 error or a "not authorized" by forced browsing to a secure url
 */


import grails.util.Holders


class UrlMappings {
    // these controllers (logical name here) must remain public
    static exclusions = ["login","logout","register"]

    private static def initGrailsPluginS2Package() {
        def controllers = []
        def grailsApplication = Holders.grailsApplication
        grailsApplication.controllerClasses.findAll { c ->
            c.packageName.startsWith("grails.plugin.springsecurity")
        }.each { c ->
                    def logicalName = c.logicalPropertyName
                    if (!exclusions.contains(logicalName)) {
                        controllers.add(c.logicalPropertyName)
                    }
                }
        return controllers
    }


    private static def findControllersInPackage(String packageName) {
        //println ">>findControllersInPackage()"
        def controllers = initGrailsPluginS2Package()
        def grailsApplication = Holders.grailsApplication
        grailsApplication.controllerClasses.findAll { c ->
            c.packageName.startsWith(packageName)
        }
        .each { c ->
            controllers.add(c.logicalPropertyName)
        }
        return controllers
    }

    static mappings = {
        // collect controllers
        def secureControllers = findControllersInPackage("org.olr.admin")
        // disallow secured controllers on normal url
        "/$controller/$action?/$id?"{
            constraints {
                controller(validator: {
                    //println "non-secure: " + it.toString()
                    return !secureControllers.contains(it.toString())
                })
            }
        }
        // allow secured controllers on secured url
        secureControllers.each { controllerName ->
            //println "secure: ${controllerName}"
            "/admin/${controllerName}/$action?/$id?" {
                controller = controllerName
            }
        }
        "/admin"(view:"/index_admin")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
