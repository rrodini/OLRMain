package olrmainproxy

import grails.testing.web.interceptor.InterceptorUnitTest
import org.olr.nonadmin.CorsInterceptor
import spock.lang.Specification

class CorsInterceptorSpec extends Specification implements InterceptorUnitTest<CorsInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test cors interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"cors")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
