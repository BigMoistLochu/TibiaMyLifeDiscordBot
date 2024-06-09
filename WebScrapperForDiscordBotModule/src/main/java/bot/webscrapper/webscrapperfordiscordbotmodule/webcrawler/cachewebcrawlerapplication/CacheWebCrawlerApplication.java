package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.mappers.TrackedCharacterDtoMapper;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public final class CacheWebCrawlerApplication {

    private static CacheWebCrawlerApplication INSTANCE = null;

    @Getter
    private final Map<String,TrackedCharacterDto> webScrapperMap = new ConcurrentHashMap<>();
    private final Logger logger = Logger.getLogger(CacheWebCrawlerApplication.class.getName());
    public synchronized static CacheWebCrawlerApplication getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new CacheWebCrawlerApplication();
        }
        return INSTANCE;
    }

    public TrackedCharacterDto getTrackedCharacterDtoByNick(String nick){
        if(!webScrapperMap.containsKey(nick)) throw new IllegalArgumentException();
        return webScrapperMap.get(nick);
    }
    //potrzeba nam funkcje dla CrawleraKtory bedzie aktualizowal


    /**
     * Dodaje postać do mapy, jeśli nie istnieje, i loguje operację.
     *
     * @param trackedCharacterDto DTO postaci do dodania.
     */
    public void addCharacterToMap(TrackedCharacterDto trackedCharacterDto){
        TrackedCharacterDto dto = webScrapperMap.putIfAbsent(trackedCharacterDto.getNick(),trackedCharacterDto);
        if(dto!=null) logger.info("Dodano postac do mapy o nicku: "+trackedCharacterDto.getNick());
    }

    /**
     * Odświeża mapę danymi z bazy danych.Funkcja jest uzywana tylko dla RefreshMapWithDatabaseDataScheduler
     *
     * @param trackedCharacterList Lista postaci do odświeżenia mapy.
     */
    public void refreshMapWithDatabaseData(List<TrackedCharacter> trackedCharacterList){
        trackedCharacterList.stream()
                .map(trackedCharacter -> TrackedCharacterDtoMapper.mapTrackedCharacterToDto(trackedCharacter))
                .forEach(trackedCharacterDto -> addCharacterToMap(trackedCharacterDto));
    }





}
