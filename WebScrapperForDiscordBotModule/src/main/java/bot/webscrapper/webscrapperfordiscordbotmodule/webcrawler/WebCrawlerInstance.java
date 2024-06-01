package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawler;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

public class WebCrawlerInstance {

    private Document doc;
    private Logger logger = Logger.getLogger(WebCrawlerInstance.class.getName());

    private WebCrawlerInstance(String url){
        try {
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e){
            logger.warning("Bledny URL lub problem z utworzeniem Instancji WebScrappera");
        }
    }

    public static WebCrawlerInstance createWebCrawlerInstance(String url){
        return new WebCrawlerInstance(url);
    }

    public TrackedCharacter scrapAndGetTrackedCharacter(){
        //metoda1 zbierz dane o nicku
        //metoda2 zbierz dane o tym czy jest online
        //metoda3 zbierz dane o tym ile ma expa


        return new TrackedCharacter("nickTutaj",true,123);
    }

    private String getNickFromWebsite(){
        return "nick";
    }

    private String getIsOnlineFromWebsite(){

        return "online";
    }

    private String getExperienceFromWebsite(){
        return "experience";
    }








}
