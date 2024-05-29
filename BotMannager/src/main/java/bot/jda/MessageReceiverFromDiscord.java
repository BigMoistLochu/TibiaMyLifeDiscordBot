package bot.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceiverFromDiscord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //     /(modul) (command) (arg…)
        CommandEntity commandEntity = MessageCreatorBuilder.createMessage(event.getMessage().getContentRaw()).filter().getCommandEntity();


        //jednoczesnie musi byc przygotowana tutaj odpowiedz,lub reakcja na wiadomosc poprawna
    }





}
