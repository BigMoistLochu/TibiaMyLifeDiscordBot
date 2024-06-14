package bot.webscrapper.webscrapperfordiscordbotmodule.endpoints;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import bot.webscrapper.webscrapperfordiscordbotmodule.services.TrackedCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/track")
public class WebCrawlerRestController {

    private final TrackedCharacterService trackedCharacterService;

    @Autowired
    public WebCrawlerRestController(TrackedCharacterService trackedCharacterService) {
        this.trackedCharacterService = trackedCharacterService;
    }

    @PostMapping("/character/add")
    public ResponseEntity<TrackedCharacter> addCharacterToTrack(@RequestParam("discord_server") String discordServer,@RequestParam("support_server") String supportServer,@RequestParam("nick") String nick){

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trackedCharacterService.createTrackedCharacter(discordServer,nick,supportServer));
    }
    @DeleteMapping("/{discord_server}/{tibia_server}/{nick}")
    public ResponseEntity<TrackedCharacter> deleteCharacterToTrack(@PathVariable String discord_server, @PathVariable String tibia_server, @PathVariable String nick){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }





}
