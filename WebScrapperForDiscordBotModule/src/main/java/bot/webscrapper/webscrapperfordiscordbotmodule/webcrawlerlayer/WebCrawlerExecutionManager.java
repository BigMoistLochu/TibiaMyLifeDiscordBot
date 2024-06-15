package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer;

import bot.webscrapper.webscrapperfordiscordbotmodule.configuration.BeanManager;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.schedulers.SchedulerTaskManager;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.webcrawler.CacheWebCrawlerApplication;

import java.util.List;
import java.util.logging.Logger;


public class WebCrawlerExecutionManager{

    private final TrackedCharacterService trackedCharacterService = BeanManager.getTrackedCharacterService();
    private final CacheWebCrawlerApplication cacheWebCrawlerApplication = CacheWebCrawlerApplication.getINSTANCE();

    private final Logger logger = Logger.getLogger(WebCrawlerExecutionManager.class.getName());

    private final SchedulerTaskManager schedulerTaskManager = SchedulerTaskManager.getInstance();

    public WebCrawlerExecutionManager(){
        //klasa zajmuje sie odpalaniem schedulderow,zaciaganiem konfiguracji
        schedulerTaskManager.scheduleCacheMapUpdateStartTask(()->updateCacheMap());
    }
    /**
     * Odświeża mapę danymi z bazy danych.Oraz usuwa jesli mapa z Cache nie zgadza sie z aktualna Lista
     * Funkcja powinna byc co jakis czas uzywana w schedulderze by zaciagac najnowsza konfiguracje
     */
    private void updateCacheMap(){
        List<TrackedCharacter> trackedCharacterList = trackedCharacterService.getListTrackedCharacters();
        if(trackedCharacterList!=null) cacheWebCrawlerApplication.refreshMapWithDatabaseData(trackedCharacterList);
        else logger.info("List From TrackedCharacter is null");
    }




}
