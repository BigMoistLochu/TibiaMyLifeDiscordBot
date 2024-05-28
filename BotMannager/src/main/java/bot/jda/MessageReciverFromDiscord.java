package bot.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReciverFromDiscord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
    }
}
