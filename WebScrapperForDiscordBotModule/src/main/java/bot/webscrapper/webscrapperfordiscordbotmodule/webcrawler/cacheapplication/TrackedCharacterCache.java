package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cacheapplication;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class TrackedCharacterCache {

    private static TrackedCharacterCache INSTANCE = null;
    private final Map<String,List<TrackedCharacter>> trackedCharactersMapByCategoryAndServer = new ConcurrentHashMap<>();

    private TrackedCharacterCache(){}

    public synchronized static TrackedCharacterCache getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new TrackedCharacterCache();
        }
        return INSTANCE;
    }

    public TrackedCharacterCache uploadTrackedCharacter(String serverDiscord,TrackedCharacter character){
        if(!trackedCharactersMapByCategoryAndServer.containsKey(serverDiscord)){
            trackedCharactersMapByCategoryAndServer.put(serverDiscord,new CopyOnWriteArrayList<>());
        }
        //a co sie stanie jak bot padnie?? hmm?
        //cala konfiguracja powinna znajdowac sie bazie danych
        List<TrackedCharacter> containedCharacters = trackedCharactersMapByCategoryAndServer.get(serverDiscord);
        if(containedCharacters.contains(character)){
            containedCharacters.remove(character);
        }
        return this;
    }







}
