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

    public void addCharacterToDataBase(TrackedCharacter trackedCharacter){
        trackedCharacterRepository.save(trackedCharacter);
    }


    public List<TrackedCharacter> getAllTrackedCharacters(){
        return (List<TrackedCharacter>) trackedCharacterRepository.findAll();
    }

}
