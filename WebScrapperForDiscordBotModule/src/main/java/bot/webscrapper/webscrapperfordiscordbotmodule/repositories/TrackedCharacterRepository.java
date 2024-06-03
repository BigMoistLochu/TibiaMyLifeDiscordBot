package bot.webscrapper.webscrapperfordiscordbotmodule.repositories;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.TrackedCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackedCharacterRepository extends CrudRepository<TrackedCharacter,Long> {
}
