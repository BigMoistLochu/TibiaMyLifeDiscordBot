package bot.configurationdiscordserversmodule.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServerCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverDomain;
    private String webHookUrl;


    public Long getId() {
        return id;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverName) {
        this.serverDomain = serverName;
    }

    public String getWebHookUrl() {
        return webHookUrl;
    }

    public void setWebHookUrl(String webHookUrl) {
        this.webHookUrl = webHookUrl;
    }
}
