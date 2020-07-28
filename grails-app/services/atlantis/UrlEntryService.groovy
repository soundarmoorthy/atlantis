package atlantis

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class UrlEntryService {

    GrailsApplication grailsApplication;

    def createOrGetUrl(UrlEntry source) {
        def url = source.getOriginalUrl();
        def created =  UrlEntry.findByOriginalUrl(url);

        if(created !=null)
            return created;

        return create(source);
    }

    def getRedirectUrl(int id) {
        def obj = UrlEntry.get(id);
        if(obj !=null)
            return obj.getOriginalUrl();

        return null;
    }

    def create(UrlEntry fromRequest ) {
        UrlEntry newEntry = new UrlEntry();
        def id = getNewId();
        newEntry.setId(id)
        def baseUrl = grailsApplication.config.getProperty('grails.serverURL');
        newEntry.setShortUrl("$baseUrl/u/$id");
        newEntry.setOriginalUrl(fromRequest.getOriginalUrl());
        newEntry.setDateCreated(new Date());

        if(newEntry.getExpiryDate() == null)
            newEntry.updateExpiryDate();

        def res = newEntry.save();
        return newEntry;
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
