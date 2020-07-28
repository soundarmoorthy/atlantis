package atlantis

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UrlEntrySpec extends Specification implements DomainUnitTest<UrlEntry> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        def entry = new UrlEntry();

        expect:"fix me"
            true == false
    }
}
