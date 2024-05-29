package bot.jda;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class BotStartUp {
    public static void main(String[] args) {
       JDABuilder.createLight(System.getenv("DISCORD_TOKEN"))
                .enableIntents(GatewayIntent.GUILD_MESSAGES,GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageReceiverFromDiscord())
                .build();
    }
}