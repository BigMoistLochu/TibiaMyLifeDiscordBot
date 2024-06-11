package bot.webscrapper.webscrapperfordiscordbotmodule;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.DiscordServer;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import bot.webscrapper.webscrapperfordiscordbotmodule.repositories.TrackedCharacterRepository;
import bot.webscrapper.webscrapperfordiscordbotmodule.scheduler.RefreshMapWithDatabaseDataScheduler;
import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebScrapperForDiscordBotModuleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebScrapperForDiscordBotModuleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //start schedulers...
        RefreshMapWithDatabaseDataScheduler.getINSTANCE().startScheduler();

    }
}
