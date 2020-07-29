package atlantis

import groovy.util.logging.Slf4j

@Slf4j
class ExpiredUrlPurgerJob {
    static triggers = {
      simple repeatInterval: 5000l // every day
    }

    def execute() {
        def timeOfTheDay = new Date();
        def entries =
                UrlEntry.findAllByExpiryDateLessThan(timeOfTheDay);
        log.info "cleaning up {} expired url's", entries.size()
        UrlEntry.deleteAll(entries);
    }

}
