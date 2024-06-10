package bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos;

import bot.webscrapper.webscrapperfordiscordbotmodule.exceptions.InvalidScrapingDataException;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TrackedCharacterDto {
    private String nick;
    private SupportServers supportServers;
    private boolean isOnline;
    private boolean isExp;
    private int experience;

    public TrackedCharacterDto(String nick){
        this.nick = nick;
    }
    public TrackedCharacterDto(String nick,SupportServers supportServers){
        this.nick = nick;
        this.supportServers = supportServers;
    }
    /**
     * Ten konstruktor jest dla WebCrawlerInstance by nie mogl stworzyc obiektu
     * i przechowywac go w pamieci jak scrapper polegnie na jakims polu
     * @param nick
     * @param isOnline
     * @param experience
     */
    public TrackedCharacterDto(String nick, String isOnline, String experience){
        if(nick==null) throw new InvalidScrapingDataException("invalid nick data to create TrackedCharacterDto");
        if(isOnline==null || (!isOnline.equals("true") && !isOnline.equals("false")) ) throw new InvalidScrapingDataException("invalid checking online data to create TrackedCharacterDto");
        if(experience==null) throw new InvalidScrapingDataException("invalid experience data to create TrackedCharacterDto");
        try {
            this.experience = Integer.parseInt(experience);
            this.nick = nick;
            this.isOnline = Boolean.parseBoolean(isOnline);
        }catch (NumberFormatException e){
            throw new InvalidScrapingDataException("Invalid data from scrapper, experience is not number");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (nick == null ? 0 : nick.hashCode());
        hash = 31 * hash + (isOnline == false ? 0 : 1);
        hash = 31 * hash + (supportServers == null ? 0 : supportServers.hashCode());
        hash = 31 * hash + experience;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(this==obj) return true;
        if(!(obj instanceof TrackedCharacterDto o)) return false;
        return this.nick.equals(o.nick) &&
                this.isOnline == o.isOnline &&
                this.supportServers.equals(o.supportServers) &&
                this.experience == o.experience;
    }
}
