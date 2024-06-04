package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cacheapplication;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class TrackedCharacterCache {

    private static TrackedCharacterCache INSTANCE = null;

    private final Map<String,List<TrackedCharacter>> trackedCharactersMapByCategoryAndServer = new ConcurrentHashMap<>();

    private final Map<String,TrackedCharacter> webScrapperMap = new ConcurrentHashMap<>();
    private final TrackedCharacterService trackedCharacterService = MyServiceInjector.getBean(TrackedCharacterService.class);
    private final Logger logger = Logger.getLogger(TrackedCharacterCache.class.getName());
    public synchronized static TrackedCharacterCache getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new TrackedCharacterCache();
        }
        return INSTANCE;
    }

    /**
     * Ta metoda bedzie uzywana zeby zaciagac configuracje z bazy danych ktora jest niezalezna od stanu i pamieci aplikacji
     * bedzie wywolywana przez schedulera.
     * Opis jak dziala:
     * Zaciagana jest lista z TrackedCharacters z serwisu->Wyciagamy Liste serverow->Nadpisujemy Liste ktora jest
     * w Pamieci aplikacji by zaktualizowac configuracje jesli jakis uzytkownik dokonal zmian
     */
    public void upLoadTrackedCharactersFromDataBaseIntoList(){
        Set<String> servers = new HashSet<>();
        List<TrackedCharacter> upLoadedListFromDataBase= trackedCharacterService.getAllTrackedCharacters();
        upLoadedListFromDataBase
                .forEach((trackedCharacter -> servers.add(trackedCharacter.getServerDiscord())));

        for(String server: servers){
            if(!trackedCharactersMapByCategoryAndServer.containsKey(server))
                trackedCharactersMapByCategoryAndServer.computeIfAbsent(server,(createNewList)-> new ArrayList<>());

            List<TrackedCharacter> list = upLoadedListFromDataBase.stream()
                    .filter(trackedCharacter -> trackedCharacter.getServerDiscord().equals(server))
                    .toList();

            trackedCharactersMapByCategoryAndServer.put(server,list);
        }
    }

    public TrackedCharacterCache uploadTrackedCharacter(String serverDiscord,TrackedCharacter character){
        trackedCharacterService.getAllTrackedCharacters();
        return this;
    }
}
