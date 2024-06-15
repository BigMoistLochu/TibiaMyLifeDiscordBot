package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.webcrawler.CacheWebCrawlerApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        //given
        List<TrackedCharacter> firstListToPut = new ArrayList<>(List.of(new TrackedCharacter("Jefrey", SupportServers.GUNZODUS),
                new TrackedCharacter("Jefrey", SupportServers.GUNZODUS),new TrackedCharacter("Abua", SupportServers.GUNZODUS)));
        List<TrackedCharacter> secondListToPut = new ArrayList<>(List.of(new TrackedCharacter("Jefrey",SupportServers.GUNZODUS),
                new TrackedCharacter("Jenny", SupportServers.IXODUS)));
        //when
        cacheWebCrawlerApplication.refreshMapWithDatabaseData(firstListToPut);
        //then
        assertTrue(fakeWebScrapperMap.containsKey("Jefrey"));
        assertTrue(fakeWebScrapperMap.containsKey("Abua"));
        assertEquals(2,fakeWebScrapperMap.size());
        //when
        cacheWebCrawlerApplication.refreshMapWithDatabaseData(secondListToPut);
        //then
        assertTrue(fakeWebScrapperMap.containsKey("Jefrey"));
        assertTrue(fakeWebScrapperMap.containsKey("Jenny"));
        assertEquals(2,fakeWebScrapperMap.size());
    }
    @Test
    void shouldUpdateCharacterInWebMap(){
        //Given
        cacheWebCrawlerApplication.getWebScrapperMap()
                .put("Web",new TrackedCharacterDto("Web",SupportServers.GUNZODUS));
        cacheWebCrawlerApplication.getWebScrapperMap()
                .put("Abua",new TrackedCharacterDto("Abua", SupportServers.IXODUS));
        //when
        TrackedCharacterDto characterAfterUpdate = cacheWebCrawlerApplication.updateCharacterInWebMap(new TrackedCharacterDto("Web","true","100"));
        //then
        assertNotNull(characterAfterUpdate, "Updated character should not be null");
        assertEquals(2, cacheWebCrawlerApplication.getWebScrapperMap().size(), "Map size should remain 2");
        assertEquals(characterAfterUpdate, cacheWebCrawlerApplication.getWebScrapperMap().get("Web"),
                "Updated character should match the character in the map");
        assertEquals("Web", characterAfterUpdate.getNick(), "Character nick should be 'Web'");
        assertEquals(SupportServers.GUNZODUS, characterAfterUpdate.getSupportServers(),
                "Character server should be 'GUNZODUS'");
        assertTrue(characterAfterUpdate.isExp(), "Character exp should be true");
        assertTrue(characterAfterUpdate.isOnline(), "Character online should be true");
    }


}