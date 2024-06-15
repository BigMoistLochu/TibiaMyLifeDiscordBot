package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.webcrawler;

import bot.webscrapper.webscrapperfordiscordbotmodule.exceptions.scrappersexceptions.InvalidScrapingDataException;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.Logger;

public class WebCrawlerInstance {

    private Document doc;
    private Logger logger = Logger.getLogger(WebCrawlerInstance.class.getName());
    private CacheWebCrawlerApplication cacheApplication = CacheWebCrawlerApplication.getINSTANCE();

    private WebCrawlerInstance(String url){
        try {
            doc = Jsoup.connect(url).timeout(5000)
                    .userAgent("Mozilla")
                    .get();
        }
        catch (IOException e){
            logger.warning("Bledny URL lub problem z utworzeniem Instancji WebScrappera");
        }
    }

    public static WebCrawlerInstance createWebCrawlerInstance(String url){
        return new WebCrawlerInstance(url);
    }

    /**
     * Scrapper aktualnie dzialac bedzie na ixodus.net,gunzodus.net,ezodus.net
     * @return
     */
    public void scrapAndGetTrackedCharacter(){
        try {
            TrackedCharacterDto characterDto = new TrackedCharacterDto(getNickFromWebsite(),getIsOnlineFromWebsite(),getExperienceFromWebsite());
            if(characterDto!=null) {
                TrackedCharacterDto updatedCharacter = cacheApplication.updateCharacterInWebMap(characterDto);
                if(updatedCharacter!=null) logger.info("Postac o nicku: " + updatedCharacter.getNick() + " zostala zaktualizowana przez scrapper");
            }
        }catch (InvalidScrapingDataException dataException){
            logger.info("Blad przy tworzeniu TrackedCharacterDto: " + dataException.getMessage());
        }
    }

    private String getNickFromWebsite(){
        Element element = doc.selectFirst("table.guild-list:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > font:nth-child(1) > strong:nth-child(1)");
        if(element==null) return null;
        if(!element.hasText())  return null;
        return element.text();
    }

    private String getIsOnlineFromWebsite(){
        return "online";
    }

    private String getExperienceFromWebsite(){
        Element element = doc.selectFirst("table.guild-list:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > strong:nth-child(1)");
        if(element==null) return null;
        if(!element.hasText())  return null;
        return element.text();
    }

    private SupportServers getSupportServerFromUrl(String url){
        //convert url to support server like:
        //https://www.gunzodus.net/character/show/Pupik -> SupportServers.GUNZODUS;
        //https://www.ixodus.net/character/show/Zmiekczacz -> SupportServers.Ixodus;
        //https://realera.org/community/characters/enemy-oz -> SupportServers.Realera;
        return SupportServers.GUNZODUS;
    }







}
