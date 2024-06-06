package bot.configurationdiscordserversmodule.endpoints;

import bot.configurationdiscordserversmodule.models.ServerCredentials;
import bot.configurationdiscordserversmodule.services.ServerCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/configuration")
public class DiscordServerCreatorRestController {

    private final ServerCredentialsService serverCredentialsService;

    @Autowired
    public DiscordServerCreatorRestController(ServerCredentialsService serverCredentialsService) {
        this.serverCredentialsService = serverCredentialsService;
    }

    @GetMapping("/init/{server}")
    public ResponseEntity<ServerCredentials> initServerDiscordToDB(@PathVariable String server){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(serverCredentialsService.serverInit(server));
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
