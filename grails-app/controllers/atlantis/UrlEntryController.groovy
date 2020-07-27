package atlantis

import grails.rest.*
import grails.converters.*

class UrlEntryController extends RestfulController {
    static responseFormats = ['json']
    static allowedMethods  =  [redirect : 'GET', save : 'POST'];

    UrlEntryService urlEntryService;
    UrlEntryController() {
        super(UrlEntry)
    }

    def redirect(int id){

        def originalUrl = urlEntryService.getRedirectUrl(id);

        if(originalUrl == null)
            response.sendError 404

        redirect(url: shortUrl);

    }

    def save() {
        def obj = new UrlEntry(params);
        def res = urlEntryService.createOrGetUrl(obj);

        withFormat {
            json { render res as JSON }
        }
    }
}
