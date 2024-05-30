package bot.jda;


import bot.jda.models.CommandMessageDiscordEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageCreatorBuilder {

    private final String message;
    private CommandMessageDiscordEntity commandMessageDiscordEntity;

    private final List<String> modules = new ArrayList<>(List.of("track","auction"));

    private final List<String> commands = new ArrayList<>(List.of("buy","follow"));

    private MessageCreatorBuilder(String message){
        this.message = message;
    }

    public static MessageCreatorBuilder createMessage(String eventMessage){
        return new MessageCreatorBuilder(eventMessage);
    }

    public MessageCreatorBuilder filter(){
        if(message.startsWith("/")){
            String[] partsOfMessage = message.toLowerCase().substring(1).split(" ");
            if(partsOfMessage.length >= 2){
                if(checkAvilableModules(partsOfMessage[0])){
                    if(checkAvilableCommandInModule(partsOfMessage[1])){
                        if(partsOfMessage.length>=3){
                            List<String> temporaryArray = new ArrayList<>(Arrays.asList(partsOfMessage).subList(2, partsOfMessage.length));
                            commandMessageDiscordEntity = new CommandMessageDiscordEntity(partsOfMessage[0],partsOfMessage[1],temporaryArray);
                        }else{
                            commandMessageDiscordEntity = new CommandMessageDiscordEntity(partsOfMessage[0],partsOfMessage[1]);
                        }
                    }
                }
            }
        }
        return this;
    }

    public CommandMessageDiscordEntity getCommandEntity(){
        return commandMessageDiscordEntity;
    }


    private boolean checkAvilableModules(String module){
        return modules.contains(module);
    }

    private boolean checkAvilableCommandInModule(String command){
        return commands.contains(command);
    }






}
