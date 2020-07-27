package atlantis

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class UrlEntryService {

    GrailsApplication grailsApplication;

    def createOrGetUrl(UrlEntry source) {
        def url = source.getOriginalUrl();
        UrlEntry entry =  UrlEntry.findByOriginalUrl(url) ;

        if(entry !=null){
            return entry;
        }

        return create(source)
    }

    def getRedirectUrl(int id) {
        def obj = UrlEntry.get(id);
        if(obj !=null)
            return obj.getOriginalUrl();

        return null;
    }

    def create(UrlEntry source) {
        def id = getNewId();
        source.setId(id)
        def baseUrl = grailsApplication.config.getProperty('grails.serverURL');
        source.setShortUrl("$baseUrl/$id");

        if(source.getExpiryDate() != null)
            source.refreshExpiryDate();

        source.save();
        return source;
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
