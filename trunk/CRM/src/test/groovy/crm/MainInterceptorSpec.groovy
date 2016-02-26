package crm


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MainInterceptor)
class MainInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test main interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"main")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
