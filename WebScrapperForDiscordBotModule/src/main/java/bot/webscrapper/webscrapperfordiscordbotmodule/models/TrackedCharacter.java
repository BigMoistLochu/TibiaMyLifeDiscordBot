package bot.webscrapper.webscrapperfordiscordbotmodule.models;



public class TrackedCharacter {


    private String nick;
    private boolean isOnline;

    private int experience;

    public TrackedCharacter(){}

    public TrackedCharacter(String nick, boolean isOnline, int experience){
        this.nick = nick;
        this.isOnline = isOnline;
        this.experience = experience;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
