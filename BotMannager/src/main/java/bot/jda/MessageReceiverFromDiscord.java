package bot.jda;

import bot.jda.models.CommandMessageDiscordEntity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceiverFromDiscord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage(event.getMessage().getContentRaw()).filter().getCommandEntity();
    }





}
