package bot.webscrapper.webscrapperfordiscordbotmodule.models.entities;


import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class TrackedCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nick;
    @Enumerated(EnumType.STRING)
    private SupportServers supportServers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discord_server_id", referencedColumnName = "id")
    private DiscordServer discordServer;
    public TrackedCharacter(){}
    public TrackedCharacter(String nick, SupportServers supportServers) {
        this.nick = nick;
        this.supportServers = supportServers;
    }
    public TrackedCharacter(String nick, SupportServers supportServers, DiscordServer discordServer){
        this.nick = nick;
        this.supportServers = supportServers;
        this.discordServer = discordServer;
    }

}
