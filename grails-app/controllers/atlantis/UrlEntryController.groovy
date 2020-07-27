package atlantis


import grails.rest.*
import grails.converters.*

class UrlEntryController extends RestfulController {
    static responseFormats = ['json']
    def urlEntryService;
    UrlEntryController() {
        super(UrlEntry)
    }

    def save() {
        def obj = new UrlEntry(params);
        def res = urlEntryService.createURL(obj);

        if(res == null)
            response.sendError 500
        else
            withFormat {
                json { render res as JSON }
            }
    }
}
