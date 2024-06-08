package bot.configurationdiscordserversmodule.endpoints;

import bot.configurationdiscordserversmodule.exceptions.ErrorResponse;
import bot.configurationdiscordserversmodule.exceptions.ServerAlreadyExistsException;
import bot.configurationdiscordserversmodule.models.DiscordServer;
import bot.configurationdiscordserversmodule.services.DiscordServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/set/webhook/{channel}")
    public String setWebHookDiscord(@PathVariable String channel){
        return "hello";
    }
    @PostMapping("/update/webhook/{channel}")
    public String updateWebHookDiscord(@PathVariable String channel){
        return "hello";
    }
    @PostMapping("/remove/webhook")
    public String removeWebHookDiscord(){
        return "hello";
    }
}
