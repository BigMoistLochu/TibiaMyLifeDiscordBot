package bot.configurationdiscordserversmodule.services;

import bot.configurationdiscordserversmodule.exceptions.ServerAlreadyExistsException;
import bot.configurationdiscordserversmodule.models.DiscordServer;
import bot.configurationdiscordserversmodule.repositories.DiscordServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscordServerService {

    private final DiscordServerRepository discordServerRepository;
    @Autowired
    public DiscordServerService(DiscordServerRepository discordServerRepository) {
        this.discordServerRepository = discordServerRepository;
    }


    public DiscordServer getServerDiscordByName(String serverName){
        if (serverName == null || serverName.isEmpty()) {
            throw new IllegalArgumentException("Server name must not be null or empty");
        }

        if(!discordServerRepository.existsDiscordServerByServerName(serverName))
            throw new IllegalArgumentException("Server with name "+serverName+" doesn't exist");

        return discordServerRepository.findFirstByServerName(serverName);
    }

    public List<DiscordServer> getAllServerDiscord(){
        return (List<DiscordServer>) discordServerRepository.findAll();
    }


    public DiscordServer createServerDiscord(String serverName){

        if (serverName == null || serverName.isEmpty()) {
            throw new IllegalArgumentException("Server name must not be null or empty");
        }

        if(discordServerRepository.existsDiscordServerByServerName(serverName))
            throw new ServerAlreadyExistsException("Server with name " + serverName + " already exists");


        DiscordServer discordServer = new DiscordServer();
        discordServer.setServerName(serverName);
        return discordServerRepository.save(discordServer);
    }

    @Transactional
    public DiscordServer setWebHookOnDiscordServer(String serverName,String webhookUrl){
        return updateWebHookOnDiscordServer(serverName,webhookUrl);
    }

    @Transactional
    public DiscordServer removeWebHookOnDiscordServer(String serverName){
       return updateWebHookOnDiscordServer(serverName,null);
    }

    private DiscordServer updateWebHookOnDiscordServer(String serverName, String value){
        if(serverName == null || serverName.isEmpty()) {
            throw new IllegalArgumentException("Server name or webhook url must not be null or empty");
        }

        if(!discordServerRepository.existsDiscordServerByServerName(serverName))
            throw new IllegalArgumentException("Server with name "+serverName+" doesn't exist");

        DiscordServer discordServer = discordServerRepository.findFirstByServerName(serverName);
        discordServer.setWebHookUrl(value);
        return discordServer;
    }




}
