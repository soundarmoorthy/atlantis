package atlantis

import grails.core.GrailsApplication
import grails.plugins.*
import grails.rest.*
import grails.converters.*

class UrlEntryController extends RestfulController implements PluginManagerAware {
    static responseFormats = ['json']

    UrlEntryService urlEntryService;

    GrailsApplication grailsApplication;
    GrailsPluginManager pluginManager;

    UrlEntryController() {
        super(UrlEntry)
    }

    def index(){
        [grailsApplication: grailsApplication, pluginManager: pluginManager]

    }

    def redirect(int id) {
        def url = urlEntryService.getRedirectUrl(id);

        if(url == null) {
            response.sendError 404
        }

        redirect url: url, permanent:true
        return;

    }

    def save() {
        def obj = new UrlEntry();
        bindData(obj, params, [include: ['originalUrl', 'expiryDate']])
        def res = urlEntryService.createOrGetUrl(obj);

        withFormat {
            json { render res as JSON }
        }
    }
}
