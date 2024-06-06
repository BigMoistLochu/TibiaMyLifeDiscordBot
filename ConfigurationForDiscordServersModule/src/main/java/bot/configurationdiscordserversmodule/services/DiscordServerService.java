package bot.configurationdiscordserversmodule.services;

import bot.configurationdiscordserversmodule.models.DiscordServer;
import bot.configurationdiscordserversmodule.repositories.DiscordServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscordServerService {

    private final DiscordServerRepository discordServerRepository;
    @Autowired
    public DiscordServerService(DiscordServerRepository discordServerRepository) {
        this.discordServerRepository = discordServerRepository;
    }

    public DiscordServer createServerDiscord(String serverName){
        if (serverName == null || serverName.isEmpty()) {
            throw new IllegalArgumentException("Server name must not be null or empty");
        }

        DiscordServer discordServer = new DiscordServer();
        discordServer.setServerDomain(serverName);
        return discordServerRepository.save(discordServer);
    }
}
