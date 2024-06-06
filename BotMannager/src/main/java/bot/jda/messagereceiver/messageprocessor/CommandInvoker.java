package bot.jda.messagereceiver.messageprocessor;

import bot.jda.models.ChatCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandInvoker {

    private final MessageReceivedEvent event;
    private final ChatCommand chatCommand;
    private final Map<String,Consumer<CommandContext>> commandExecutorsMap = MapInitialization.getInstance().mapInitialization();

    private CommandInvoker(MessageReceivedEvent event, ChatCommand chatCommand){
        this.event = event;
        this.chatCommand = chatCommand;
    }
    public static CommandInvoker createCommandInvoker(MessageReceivedEvent event, ChatCommand commandMessageDiscord){
        return new CommandInvoker(event,commandMessageDiscord);
    }
    public void invoke(){
        Consumer<CommandContext> commandInvokeConsumer = commandExecutorsMap.get(chatCommand.getEndpoint());
        if(commandInvokeConsumer!=null) commandInvokeConsumer.accept(new CommandContext(event, chatCommand));
    }

    private static final class MapInitialization{

        private static MapInitialization INSTANCE = null;
        private final Map<String,Consumer<CommandContext>> singletonCommandMap = new HashMap<>(Map.of(
                "track:add", commandContext -> commandContext.modulTrackAndCommandAdd(),
                "track:sell",commandContext -> commandContext.modulTrackAndCommandSell(),
                "configuration:setwebhook",commandContext -> commandContext.modulConfigurationAndCommandSetWebHook()
        ));
        private MapInitialization(){}

        private static MapInitialization getInstance(){
            if(INSTANCE == null){
                INSTANCE = new MapInitialization();
            }
            return INSTANCE;
        }

        private Map<String,Consumer<CommandContext>> mapInitialization(){
            return singletonCommandMap;
        }


    }
    private final static class CommandContext {
        private final MessageReceivedEvent event;
        private final ChatCommand chatCommandFromInvoker;
        private CommandContext(MessageReceivedEvent event, ChatCommand chatCommandFromInvoker){
            this.event = event;
            this.chatCommandFromInvoker = chatCommandFromInvoker;
        }

        private void modulTrackAndCommandAdd(){
            //httprequest do restapi zeby zdobyc liste sledzonych postaci
            //uderzenie z tego podulu do Webhooka
            try {
                // Tworzenie obiektu HttpClient
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/api/v1/track/apeDiscord/ixodus/zmiekczacz"))
                        .POST(HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                event.getAuthor().openPrivateChannel()
                        .flatMap(channel -> channel.sendMessage(Integer.toString(response.statusCode())))
                        .queue();
            }catch (URISyntaxException e){
                System.out.println("blad z uri i wyslaniem http requesta do api");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                System.out.println("walnelo od clienta cos");
            }

        }

        private void modulTrackAndCommandSell(){
            event.getAuthor().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessage("xd"))
                    .queue();
        }

        private void modulConfigurationAndCommandSetWebHook(){

        }



        //MoveAll
        //Ping All
        //Inregracja webcrawlerow z botem(oddzielny mikroservis)
        //integracja otclienta z botem
        //modul sledzenia
    }



}


