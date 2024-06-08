package bot.configurationdiscordserversmodule.repositories;

import bot.configurationdiscordserversmodule.models.DiscordServer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordServerRepository extends CrudRepository<DiscordServer,Long> {
    boolean existsDiscordServerByServerName(String serverName);
    DiscordServer findFirstByServerName(String serverName);
}
