package olrmainproxy

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import org.olr.nonadmin.Game

class GameSpec extends Specification implements DomainUnitTest<Game> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
