package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.mappers.TrackedCharacterDtoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class CacheWebCrawlerApplicationTest {

    private final CacheWebCrawlerApplication cacheWebCrawlerApplication = CacheWebCrawlerApplication.getINSTANCE();
    private final Map<String, TrackedCharacterDto> fakeWebScrapperMap = cacheWebCrawlerApplication.getWebScrapperMap();
    @BeforeEach
    void clearFakeMapBeforeEachTest(){
        fakeWebScrapperMap.clear();
    }
    @Test
    void fakeWebScrapperMapShouldContain2TrackedCharacterDtoAfterInvoke() {
        List<TrackedCharacter> trackedCharacters = new ArrayList<>(List.of(new TrackedCharacter("Jefrey", SupportServers.GUNZODUS),
                new TrackedCharacter("Jefrey", SupportServers.GUNZODUS),new TrackedCharacter("Abua", SupportServers.GUNZODUS)));
        cacheWebCrawlerApplication.refreshMapWithDatabaseData(trackedCharacters);
        assertTrue(fakeWebScrapperMap.containsKey("Jefrey"));
        assertTrue(fakeWebScrapperMap.containsKey("Abua"));
        assertEquals(2,fakeWebScrapperMap.size());
    }

    @Test
    void fakeWebScrapperMapShouldContain2TrackedCharacterDtoAfterInvokeTwoTimes() {
        List<TrackedCharacter> firstListToPut = new ArrayList<>(List.of(new TrackedCharacter("Jefrey", SupportServers.GUNZODUS),
                new TrackedCharacter("Jefrey", SupportServers.GUNZODUS),new TrackedCharacter("Abua", SupportServers.GUNZODUS)));
        List<TrackedCharacter> secondListToPut = new ArrayList<>(List.of(new TrackedCharacter("Jefrey",SupportServers.GUNZODUS),
                new TrackedCharacter("Jenny", SupportServers.IXODUS)));
        cacheWebCrawlerApplication.refreshMapWithDatabaseData(firstListToPut);
        assertTrue(fakeWebScrapperMap.containsKey("Jefrey"));
        assertTrue(fakeWebScrapperMap.containsKey("Abua"));
        assertEquals(2,fakeWebScrapperMap.size());
        cacheWebCrawlerApplication.refreshMapWithDatabaseData(secondListToPut);
        assertTrue(fakeWebScrapperMap.containsKey("Jefrey"));
        assertTrue(fakeWebScrapperMap.containsKey("Jenny"));
        assertEquals(2,fakeWebScrapperMap.size());

    }
}