package bot.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceiverFromDiscord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //     /(modul) (command) (arg…)
        //wyslij na podany http://localhost:8080/api/v1/modul wiadomosc w contencie wyslac?
        MessageCreatorBuilder.createMessage(event).filter();


        //jednoczesnie musi byc przygotowana tutaj odpowiedz,lub reakcja na wiadomosc poprawna
    }





}
