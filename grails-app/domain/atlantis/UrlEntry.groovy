package atlantis

import org.grails.datastore.gorm.GormEntity

class UrlEntry implements GormEntity<UrlEntry>
{
    int id;
    String originalUrl;
    String shortUrl;
    Date dateCreated;
    Date expiryDate;

    public void updateExpiryDate() {
        Date base = dateCreated ? dateCreated : new Date();
        expiryDate = base.plus(30);
    }


    static mapping = {
    }

    static constraints = {
        originalUrl nullable:false, blank:false, unique:true
        shortUrl nullable:false, blank:false, unique:true
        dateCreated nullable:false, blank:false
        expiryDate nullable:false, blank : false
    }
}
