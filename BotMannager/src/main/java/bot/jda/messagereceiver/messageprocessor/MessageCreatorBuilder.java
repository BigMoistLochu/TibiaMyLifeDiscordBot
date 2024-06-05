package bot.jda.messagereceiver.messageprocessor;


import bot.jda.ModuleAndCommandLoader;
import bot.jda.models.ChatCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageCreatorBuilder {

    private final String message;
    private ChatCommand chatCommand;

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
                            chatCommand = new ChatCommand(partsOfMessage[0],partsOfMessage[1],temporaryArray);
                        }else{
                            chatCommand = new ChatCommand(partsOfMessage[0],partsOfMessage[1]);
                        }
                    }
                }
            }
        }//add else if you want get normal message
        return this;
    }

    public ChatCommand getCommandMessageDiscord(){
        return chatCommand;
    }


    private boolean isModuleAvailable(String module){
        return ModuleAndCommandLoader.hasModule(module);
    }

    private boolean isCommandAvailableInModule(String command){
        return ModuleAndCommandLoader.hasCommand(command);
    }






}
