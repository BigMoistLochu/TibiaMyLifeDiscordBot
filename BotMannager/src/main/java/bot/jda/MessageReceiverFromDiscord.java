package bot.jda;

import bot.jda.models.CommandMessageDiscordEntity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceiverFromDiscord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        CommandMessageDiscordEntity commandMessageDiscord = MessageCreatorBuilder.createMessage(event.getMessage().getContentRaw()).filter().getCommandMessageDiscord();

        //wiemy ze istnieje modul oraz funkcja z modulu ale nie sprawdzamy argumentow
        if(commandMessageDiscord!=null){
            //przygotowac tutaj wykonanie odpowiedniej metody, jesli scrapp to po prostu postem dodac
            //spakowana commandmessage musisz wyslac do odpowiedniego komandera ktory to przetworzy
            //komander tez bedzie posiadal odpowiedz w postaci eventu

        }

    }





}
