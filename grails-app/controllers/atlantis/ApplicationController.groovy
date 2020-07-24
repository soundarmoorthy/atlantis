package atlantis

import grails.core.GrailsApplication
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }

    def createURL(String original_url, String custom_alias="",String user_name="", String expiry_date= null) {
        []

        //Returns: (string)
        //A successful insertion returns the shortened URL; otherwise, it returns an error code.
    }

    def deleteURL(String url_key) {
        []
        //A successful deletion returns ‘URL Removed’.
    }
}
