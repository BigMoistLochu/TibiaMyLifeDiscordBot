package bot.webscrapper.webscrapperfordiscordbotmodule.endpoints;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/track")
public class WebCrawlerRestController {

    private final TrackedCharacterService trackedCharacterService;

    @Autowired
    public WebCrawlerRestController(TrackedCharacterService trackedCharacterService) {
        this.trackedCharacterService = trackedCharacterService;
    }





}
