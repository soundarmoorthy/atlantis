package atlantis

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class UrlEntryService {

    GrailsApplication grailsApplication;

    def createOrGetUrl(UrlEntry requested) {
        def url = requested.getOriginalUrl();
        def created =  UrlEntry.findByOriginalUrl(url);

        if(created !=null)
            return created;

        return create(requested);
    }

    def getRedirectUrl(int id) {
        def obj = UrlEntry.get(id);
        if(obj !=null)
            return obj.getOriginalUrl();

        return null;
    }

    def create(UrlEntry requested) {
        def id = getNewId();
        requested.setId(id)
        def baseUrl = grailsApplication.config.getProperty('grails.serverURL');
        requested.setShortUrl("$baseUrl/u/$id");
        requested.setDateCreated(new Date());

        if(requested.getExpiryDate() == null)
            requested.updateExpiryDate();

        if(requested.save())
            return requested;
        else {
            def errors = requested.errors.allErrors;
            errors.each { it.defaultMessage }
        }
    }

    def getNewId(){
        def items = UrlEntry.withCriteria {
            projections {
                max("id")
            }
        }

        if(items?.any())
            return items.get(0) + 1;
        else
            return 1;
    }
}
