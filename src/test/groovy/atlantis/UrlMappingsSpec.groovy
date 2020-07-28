package atlantis


import grails.testing.web.UrlMappingsUnitTest
import spock.lang.Specification

class UrlMappingsSpec extends Specification implements UrlMappingsUnitTest<UrlMappings> {

    void setup() {
        mockController(UrlEntryController)
    }

    void "test conference mappings"() {

        expect: "Calls to create URI"
        verifyUrlMapping("/urlentry", controller: 'urlentry', action: 'save', method: 'POST')
        verifyUrlMapping("/u/1", controller: 'urlentry', action: 'fckgw', method: 'POST')

        when: "calling /urlentry/"
        assertUrlMapping("/urlentry", controller: 'urlentry', action: 'save', method: 'POST')
        assertUrlMapping("/u/1", controller: 'urlentry', action: 'fckgw', method: 'POST')

        then: "no exception is thrown"
        noExceptionThrown()

    }

}