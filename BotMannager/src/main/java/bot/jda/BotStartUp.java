package bot.jda;

import bot.jda.messagereceiver.MessageReceiverFromDiscord;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.util.logging.Logger;


public class BotStartUp {

    private static final Logger logger = Logger.getLogger(BotStartUp.class.getName());
    public static void main(String[] args){
        try {
            ModuleAndCommandLoader.loadToMemoryAndCreateMapFromJsonFile("BotMannager/modules.json");
        }catch (IOException e){
            logger.info("Nie udalo sie zaladowac pliku json do pamieci");
        }

        //start websocket on all server discord
       JDABuilder.createLight(System.getenv("Discord_Token"))
                .enableIntents(GatewayIntent.GUILD_MESSAGES,GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MessageReceiverFromDiscord())
                .build();
    }
}