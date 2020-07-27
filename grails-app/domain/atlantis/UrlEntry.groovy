package atlantis

import org.grails.datastore.gorm.GormEntity

class UrlEntry implements  GormEntity<UrlEntry>
{
    int id;
    String originalUrl;
    String shortUrl;
    Date dateCreated;
    Date expiryDate;

    static mapping = {
    }

    static constraints = {
        id blank:false, nullable:false, unique : true
        //originalUrl blank:false, nullable:false , unique : true, url : true
        expiryDate blank:false, nullable : false
        shortUrl blank:false, nullable: false, url : true
    }
}
