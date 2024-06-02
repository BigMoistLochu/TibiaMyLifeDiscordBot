package bot.webscrapper.webscrapperfordiscordbotmodule;

import bot.webscrapper.webscrapperfordiscordbotmodule.exceptions.InvalidScrapingDataException;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;
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

    }
}
