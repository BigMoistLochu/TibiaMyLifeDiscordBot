package bot.webscrapper.webscrapperfordiscordbotmodule.services;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.repositories.TrackedCharacterRepository;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication.CacheWebCrawlerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackedCharacterService {
    private final TrackedCharacterRepository trackedCharacterRepository;

    private static final CacheWebCrawlerApplication cacheWebCrawlerApplication = CacheWebCrawlerApplication.getINSTANCE();
    @Autowired
    public TrackedCharacterService(TrackedCharacterRepository trackedCharacterRepository){
        this.trackedCharacterRepository = trackedCharacterRepository;
    }

    public void synchronizeWebCrawlerCacheData(){
        List<TrackedCharacter> trackedCharacters = (List<TrackedCharacter>) trackedCharacterRepository.findAll();
        if(!trackedCharacters.isEmpty()) cacheWebCrawlerApplication.refreshMapWithDatabaseData(trackedCharacters);
    }


    //funkcja ktora zaciaga TrackedCharacters
    //oraz Zaciaga mape z Cache,
    //i zaciaga Liste trackedDto ktore maja taki sam nick jak TrackedCharacters
    //wybieramy server i sprawdzamy webhooka i na ten weebhook wysylamy liste sledzonych postaci
}
