package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.webcrawler;

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

    public void refreshMapWithDatabaseData(List<TrackedCharacter> trackedCharacterList){
        List<TrackedCharacterDto> dtoList = TrackedCharacterDtoMapper.mapToDtoList(trackedCharacterList);

        dtoList.forEach(trackedCharacterDto -> addCharacterToMap(trackedCharacterDto));

        List<String> nickList = new ArrayList<>();

        dtoList.forEach(trackedCharacterDto -> {
            if (!nickList.contains(trackedCharacterDto.getNick())) {
                nickList.add(trackedCharacterDto.getNick());
            }
        });

        webScrapperMap.forEach((key, trackedCharacterDto) -> {
            if(!nickList.contains(key)){
                webScrapperMap.remove(key);
                logger.info("Uzytkownik o nicku: " + trackedCharacterDto.getNick() + " zostal usuniety z cache application");
            }
        });
    }
    public void addCharacterToMap(TrackedCharacterDto trackedCharacterDto){
        if(!webScrapperMap.containsKey(trackedCharacterDto.getNick())){
            webScrapperMap.put(trackedCharacterDto.getNick(),trackedCharacterDto);
            logger.info("Dodano postac do cache aplikacji o nicku: "+ trackedCharacterDto.getNick());
        }
    }
    public TrackedCharacterDto updateCharacterInWebMap(TrackedCharacterDto scrapedCharacterDto){
        TrackedCharacterDto existingCharacterDto = webScrapperMap.get(scrapedCharacterDto.getNick());
        if(existingCharacterDto==null) return null;
        existingCharacterDto.setOnline(scrapedCharacterDto.isOnline());
        existingCharacterDto.setExp(calculateExperience(existingCharacterDto.getExperience(),scrapedCharacterDto.getExperience()));
        existingCharacterDto.setExperience(scrapedCharacterDto.getExperience());
        return webScrapperMap.put(existingCharacterDto.getNick(),existingCharacterDto);
    }
    private boolean calculateExperience(int actualExp,int scrappedExp){
        return Math.abs(actualExp - scrappedExp) > actualExp;
    }





}
