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
        def id = urlEntryService.createURL(params.originalUrl);
        def urlEntry = UrlEntry.get(id);
        if (!urlEntry)
            response.sendError 500
        else
            withFormat {
                json { render urlEntry as JSON }
            }
    }


}
