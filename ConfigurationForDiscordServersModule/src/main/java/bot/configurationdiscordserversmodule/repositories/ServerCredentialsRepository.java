package bot.configurationdiscordserversmodule.repositories;

import bot.configurationdiscordserversmodule.models.ServerCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerCredentialsRepository extends CrudRepository<ServerCredentials,Long> {
}
