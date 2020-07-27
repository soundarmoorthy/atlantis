package atlantis

import grails.gorm.transactions.Transactional

@Transactional
class UrlEntryService {

    def createURL(UrlEntry obj) {

        def url = obj.getOriginalUrl();
        UrlEntry entry =  UrlEntry.findByOriginalUrl(url) ;

        if(entry !=null){
            return existing(entry);
        }

        return createNewUrlEntry(url)
    }

    def createNewUrlEntry(String url) {
        def entry = new UrlEntry();
        entry.setOriginalUrl(url);
        entry.setId(getNewId());
        entry.setShortUrl("https://atlant.is/${entry.getId()}");
        entry.setDateCreated(new Date());
        entry.refreshExpiryDate();
        entry.save();
        return entry;
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

    def existing(UrlEntry entry) {
        entry.refreshExpiryDate();
        entry.save();
        return entry;
    }
}
