package bot.jda.messagereceiver;

import bot.jda.messagereceiver.messageprocessor.CommandInvoker;
import bot.jda.messagereceiver.messageprocessor.MessageCreatorBuilder;
import bot.jda.models.ChatCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class MessageReceiverFromDiscord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        ChatCommand commandMessageDiscord = MessageCreatorBuilder
                .createMessage(event.getMessage().getContentRaw())
                .filter()
                .getCommandMessageDiscord();

        //wiemy ze istnieje modul oraz funkcja z modulu ale nie sprawdzamy argumentow
        if(commandMessageDiscord!=null){
            //messagecordinator.createrequest.getresponse.invoke()
            CommandInvoker.createCommandInvoker(event,commandMessageDiscord).invoke();
        }

    }







}
