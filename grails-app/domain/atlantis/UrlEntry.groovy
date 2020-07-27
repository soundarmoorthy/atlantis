package atlantis

import org.grails.datastore.gorm.GormEntity

class UrlEntry implements  GormEntity<UrlEntry>
{
    int id;
    String originalUrl;
    String shortUrl;
    Date dateCreated;
    Date expiryDate;

    public void refreshExpiryDate() {
        Date base = dateCreated ? dateCreated : new Date();
        expiryDate = base.plus(30);
    }


    static mapping = {
    }

    static constraints = {
        id blank:false, nullable:false, unique : true
        expiryDate blank:false, nullable : false
        shortUrl blank:false, nullable: false, url : true
    }
}
