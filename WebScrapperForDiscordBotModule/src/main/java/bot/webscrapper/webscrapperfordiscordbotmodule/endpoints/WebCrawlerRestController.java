package bot.webscrapper.webscrapperfordiscordbotmodule.endpoints;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{discord_server}/{tibia_server}/{nick}")
    public ResponseEntity<TrackedCharacter> addCharacterToTrack(@PathVariable String discord_server, @PathVariable String tibia_server, @PathVariable String nick){
        System.out.println(discord_server+tibia_server+nick);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



}
