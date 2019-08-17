package org.olr.nonadmin

import org.springframework.beans.factory.annotation.Value
class CorsInterceptor {
    String urlAllowOrigin// = grailsApplication.config.getProperty('url.allowOrigin')


    CorsInterceptor() {
        matchAll()
    }

    boolean before() {
        log.error("${params.toString()}")  // log all urls and params
        if (request.method == "OPTIONS") {
            response.setHeader("Access-Control-Allow-Origin", "${grailsApplication.config.getProperty('url.allowOrigin')}")
// below should only be enabled in DEVELOPMENT env for debugging
//            response.setHeader("Access-Control-Allow-Origin", "*')}")
            response.setHeader("Access-Control-Allow-Credentials", "true")
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET")
            response.setHeader("Access-Control-Max-Age", "3600")
            response.status = 200
        }
        return true
    }


    boolean after() { true }
}
