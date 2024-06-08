package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
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

    //zwroc cala mape done
    //zwroc uzytkownika z mapy
    public TrackedCharacterDto getTrackedCharacterDtoByNick(String nick){
        if(!webScrapperMap.containsKey(nick)) throw new IllegalArgumentException();
        return webScrapperMap.get(nick);
    }

    //dodaj uzytkownika do mapy
    public void addCharacterToMap(TrackedCharacterDto trackedCharacterDto){
        webScrapperMap.putIfAbsent(trackedCharacterDto.getNick(),trackedCharacterDto);
    }

    //zaktualizuj,jesli nie ma to dodaj...


    //wyslij aktualny stan mapy do klienta


    //potrzebujemy posrednika miedzy baza danych a webcrawlerem
    //czyli powiedzmy ze zaciagamy ustawienia z roznych serverow o trackedcharacter
    //teraz porownanie ja z webcrawlermapa
    //po 1. naszym posrednikiem bedzie service, to on bedzie




}
