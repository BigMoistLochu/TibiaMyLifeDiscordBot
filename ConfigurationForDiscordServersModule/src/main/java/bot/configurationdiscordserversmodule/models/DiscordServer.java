package bot.configurationdiscordserversmodule.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiscordServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverName;
    private String webHookUrl;

    public DiscordServer(){}

    public Long getId() {
        return id;
    }
    public String getServerName() {
        return serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public String getWebHookUrl() {
        return webHookUrl;
    }
    public void setWebHookUrl(String webHookUrl) {
        this.webHookUrl = webHookUrl;
    }
}
