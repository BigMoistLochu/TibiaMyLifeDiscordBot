package bot.jda;


import bot.jda.models.CommandMessageDiscordEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageCreatorBuilder {

    private final String message;
    private CommandMessageDiscordEntity commandMessageDiscordEntity;

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
                if(isModuleAvailable(partsOfMessage[0])){
                    if(isCommandAvailableInModule(partsOfMessage[1])){
                        if(partsOfMessage.length>=3){
                            List<String> temporaryArray = new ArrayList<>(Arrays.asList(partsOfMessage).subList(2, partsOfMessage.length));
                            commandMessageDiscordEntity = new CommandMessageDiscordEntity(partsOfMessage[0],partsOfMessage[1],temporaryArray);
                        }else{
                            commandMessageDiscordEntity = new CommandMessageDiscordEntity(partsOfMessage[0],partsOfMessage[1]);
                        }
                    }
                }
            }
        }//add else if you want get normal message
        return this;
    }

    public CommandMessageDiscordEntity getCommandMessageDiscord(){
        return commandMessageDiscordEntity;
    }


    private boolean isModuleAvailable(String module){
        return ModuleAndCommandContainer.hasModule(module);
    }

    private boolean isCommandAvailableInModule(String command){
        return ModuleAndCommandContainer.hasCommand(command);
    }






}
