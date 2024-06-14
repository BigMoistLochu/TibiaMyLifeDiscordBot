package bot.webscrapper.webscrapperfordiscordbotmodule.repositories;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.DiscordServer;
import org.springframework.data.repository.CrudRepository;

public interface DiscordServerRepository extends CrudRepository<DiscordServer,Long> {
    DiscordServer findFirstByServerName(String serverName);
}
