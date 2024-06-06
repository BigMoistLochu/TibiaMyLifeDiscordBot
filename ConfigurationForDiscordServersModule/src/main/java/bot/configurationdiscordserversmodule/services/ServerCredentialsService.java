package bot.configurationdiscordserversmodule.services;

import bot.configurationdiscordserversmodule.models.ServerCredentials;
import bot.configurationdiscordserversmodule.repositories.ServerCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServerCredentialsService {

    private final ServerCredentialsRepository serverCredentialsRepository;
    @Autowired
    public ServerCredentialsService(ServerCredentialsRepository serverCredentialsRepository) {
        this.serverCredentialsRepository = serverCredentialsRepository;
    }

    public ServerCredentials serverInit(String serverDomain){
        ServerCredentials serverCredentials = new ServerCredentials();
        serverCredentials.setServerDomain(serverDomain);
        return serverCredentialsRepository.save(serverCredentials);
    }
}
