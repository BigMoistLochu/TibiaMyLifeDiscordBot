package bot.jda.messagereceiver.messageprocessor;

import bot.jda.models.ChatCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandInvoker {

    private final MessageReceivedEvent event;
    private final ChatCommand chatCommand;
    private final Map<String,Consumer<CommandContext>> consumerMap = MapInitialization.getInstance().mapInitialization();

    private CommandInvoker(MessageReceivedEvent event, ChatCommand chatCommand){
        this.event = event;
        this.chatCommand = chatCommand;
    }
    public static CommandInvoker createCommandInvoker(MessageReceivedEvent event, ChatCommand commandMessageDiscord){
        return new CommandInvoker(event,commandMessageDiscord);
    }
    public void invoke(){
        Consumer<CommandContext> commandInvokeConsumer = consumerMap.get(chatCommand.getEndpoint());
        if(commandInvokeConsumer!=null) commandInvokeConsumer.accept(new CommandContext(event, chatCommand));
    }

    private static final class MapInitialization{

        private static MapInitialization INSTANCE = null;
        public MapInitialization(){}

        private static MapInitialization getInstance(){
            if(INSTANCE==null){
                INSTANCE = new MapInitialization();
            }
            return INSTANCE;
        }

        private Map<String,Consumer<CommandContext>> mapInitialization(){
            Map<String,Consumer<CommandContext>> map = new HashMap<>();
            map.put("track:buy", commandContext -> commandContext.modulTrackAndCommandBuy());
            map.put("track:sell",commandContext -> commandContext.modulTrackAndCommandSell());
            return map;
        }


    }
    private static class CommandContext {
        private final MessageReceivedEvent event;
        private final ChatCommand chatCommandFromInvoker;
        private CommandContext(MessageReceivedEvent event, ChatCommand chatCommandFromInvoker){
            this.event = event;
            this.chatCommandFromInvoker = chatCommandFromInvoker;
        }

        private void modulTrackAndCommandBuy(){
            event.getAuthor().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessage("xd"))
                    .queue();
        }

        private void modulTrackAndCommandSell(){
            event.getAuthor().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessage("xd"))
                    .queue();
        }



        //MoveAll
        //Ping All
        //Inregracja webcrawlerow z botem(oddzielny mikroservis)
        //integracja otclienta z botem
        //modul sledzenia
    }



}


