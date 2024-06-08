package bot.configurationdiscordserversmodule.endpoints;

import bot.configurationdiscordserversmodule.exceptions.ErrorResponse;
import bot.configurationdiscordserversmodule.exceptions.ServerAlreadyExistsException;
import bot.configurationdiscordserversmodule.models.DiscordServer;
import bot.configurationdiscordserversmodule.services.DiscordServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/configuration")
public class DiscordServerRestController {

    private final DiscordServerService discordServerService;

    @Autowired
    public DiscordServerRestController(DiscordServerService discordServerService) {
        this.discordServerService = discordServerService;
    }
    @PostMapping("/init/{serverName}")
    public ResponseEntity<DiscordServer> createServerDiscord(@PathVariable String serverName) {
        DiscordServer discordServer = discordServerService.createServerDiscord(serverName);
        return ResponseEntity.status(HttpStatus.CREATED).body(discordServer);
    }

    @GetMapping("/get/server/{serverName}")
    public ResponseEntity<DiscordServer> getServerDiscord(@PathVariable String serverName){
        return ResponseEntity.status(HttpStatus.OK)
                .body(discordServerService.getServerDiscordByName(serverName));
    }
    @GetMapping("/get/server/all")
    public ResponseEntity<List<DiscordServer>> getALlServersDiscord(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(discordServerService.getAllServerDiscord());
    }
    @PatchMapping("/webhook/set/{serverName}")
    public ResponseEntity<DiscordServer> setWebHookDiscord(@PathVariable String serverName,@RequestParam("webhookurl") String webhookUrl){
        return ResponseEntity.status(HttpStatus.OK)
                .body(discordServerService.setWebHookOnDiscordServer(serverName,webhookUrl));
    }
    @PatchMapping("/webhook/remove/{serverName}")
    public ResponseEntity<DiscordServer> removeWebHookDiscord(@PathVariable String serverName){
        return ResponseEntity.status(HttpStatus.OK).body(discordServerService.removeWebHookOnDiscordServer(serverName));
    }
}
