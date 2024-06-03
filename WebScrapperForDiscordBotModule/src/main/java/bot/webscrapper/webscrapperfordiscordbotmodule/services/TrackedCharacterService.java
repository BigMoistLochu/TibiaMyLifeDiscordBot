package bot.webscrapper.webscrapperfordiscordbotmodule.services;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.repositories.TrackedCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackedCharacterService {
    private final TrackedCharacterRepository trackedCharacterRepository;
    @Autowired
    public TrackedCharacterService(TrackedCharacterRepository trackedCharacterRepository){
        this.trackedCharacterRepository = trackedCharacterRepository;
    }

    public void addCharacterToDataBase(TrackedCharacter trackedCharacter){
        trackedCharacterRepository.save(trackedCharacter);
    }






}
