package bot.webscrapper.webscrapperfordiscordbotmodule.scheduler;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.WebCrawlerInstance;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler.cachewebcrawlerapplication.CacheWebCrawlerApplication;

import java.util.Timer;
import java.util.TimerTask;

public class WebCrawlerScheduler {

    private static WebCrawlerScheduler INSTANCE = null;
    private final CacheWebCrawlerApplication cacheWebCrawlerApplication = CacheWebCrawlerApplication.getINSTANCE();

    private Timer timer = new Timer();
    private WebCrawlerScheduler(){}
    public synchronized static WebCrawlerScheduler getInstance(){
        if(INSTANCE==null){
            INSTANCE = new WebCrawlerScheduler();
        }
        return INSTANCE;
    }

    public void startWorkScheduler(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cacheWebCrawlerApplication.getWebScrapperMap().forEach((key,value)->{
                    Thread thread = new Thread(()->{
                        System.out.println("utworz scrapper instance...");
                    });
                    thread.start();
                });
            }
        },1,5*60*1000);



    }



}
