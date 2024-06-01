package bot.webscrapper.webscrapperfordiscordbotmodule.models;

public class TrackedCharacterDto {
    private String nick;
    private String isOnline;

    private String experience;

    public TrackedCharacterDto(String nick, String isOnline, String experience){
        if(nick==null) throw new IllegalArgumentException();
        if(isOnline==null) throw new IllegalArgumentException();
        if(experience==null) throw new IllegalArgumentException();
        this.nick = nick;
        this.isOnline = isOnline;
        this.experience = experience;
    }




}
