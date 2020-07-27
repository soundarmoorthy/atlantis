package atlantis

import grails.gorm.transactions.Transactional

@Transactional
class UrlEntryService {

    def createURL(String originalUrl) {
        def entry = new UrlEntry();
        entry.setOriginalUrl(originalUrl);
        def id = this.getNewId();
        entry.setId(id);
        entry.setShortUrl("http://atlant.is/$id");
        entry.setDateCreated(new Date());
        entry.setExpiryDate(new Date().plus(30));
        entry.save();
        return entry.getId();
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
            return 0;
    }
}
