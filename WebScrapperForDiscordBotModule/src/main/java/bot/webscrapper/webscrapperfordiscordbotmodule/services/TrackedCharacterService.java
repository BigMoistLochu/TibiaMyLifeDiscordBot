package bot.webscrapper.webscrapperfordiscordbotmodule.services;

import bot.webscrapper.webscrapperfordiscordbotmodule.exceptions.restcontrollerexceptions.AlreadyFollowedTrackedCharacterException;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.DiscordServer;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import bot.webscrapper.webscrapperfordiscordbotmodule.repositories.DiscordServerRepository;
import bot.webscrapper.webscrapperfordiscordbotmodule.repositories.TrackedCharacterRepository;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication.CacheWebCrawlerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.util.Arrays;
import java.util.List;

@Service
public class TrackedCharacterService {
    private final TrackedCharacterRepository trackedCharacterRepository;
    private final DiscordServerRepository discordServerRepository;

    private static final CacheWebCrawlerApplication cacheWebCrawlerApplication = CacheWebCrawlerApplication.getINSTANCE();
    @Autowired
    public TrackedCharacterService(TrackedCharacterRepository trackedCharacterRepository, DiscordServerRepository discordServerRepository){
        this.trackedCharacterRepository = trackedCharacterRepository;
        this.discordServerRepository = discordServerRepository;
    }

    public TrackedCharacter createTrackedCharacter(String serverName, String nick, String supportServers){
        DiscordServer discordServer = discordServerRepository.findFirstByServerName(serverName);
        if(trackedCharacterRepository.existsTrackedCharacterByNick(nick)){
            throw new AlreadyFollowedTrackedCharacterException("Character With This Nick is Already Tracked");
        }
        if(Arrays.stream(SupportServers.values())
                .anyMatch(supportServer -> supportServer.name().equals(supportServers))){
            throw new IllegalArgumentException("This Server is not supported");
        }
        TrackedCharacter newCharacter = new TrackedCharacter(nick,SupportServers.valueOf(supportServers),discordServer);
        return trackedCharacterRepository.save(newCharacter);
    }


    /**
     * Funkcja tylko dla RefreshMapWithDatabaseDataScheduler, aktualizuje konfiguracje uzytkownikow
     * CacheMapApplication dla webscrappera
     */
    public void synchronizeWebCrawlerCacheData(){
        List<TrackedCharacter> trackedCharacters = (List<TrackedCharacter>) trackedCharacterRepository.findAll();
        if(!trackedCharacters.isEmpty()) cacheWebCrawlerApplication.refreshMapWithDatabaseData(trackedCharacters);
    }


    //funkcja ktora zaciaga TrackedCharacters
    //oraz Zaciaga mape z Cache,
    //i zaciaga Liste trackedDto ktore maja taki sam nick jak TrackedCharacters
    //wybieramy server i sprawdzamy webhooka i na ten weebhook wysylamy liste sledzonych postaci
}
