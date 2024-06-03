package bot.webscrapper.webscrapperfordiscordbotmodule.models;


import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class TrackedCharacter {


    @Id
    private Long id;
    private String nick;
    private String serverDiscord;
    private String supportServers;

    public TrackedCharacter(){}

}
