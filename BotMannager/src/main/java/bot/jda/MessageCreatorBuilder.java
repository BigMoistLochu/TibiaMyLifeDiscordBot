package bot.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageCreatorBuilder {

    private final String message;
    private CommandEntity commandEntity;

    private final List<String> modules = new ArrayList<>(List.of("track","auction"));

    private final List<String> commands = new ArrayList<>(List.of("buy","follow"));
    private final boolean isAvilableModulAndComment = true;

    private MessageCreatorBuilder(String message){
        this.message = message;
    }

    public static MessageCreatorBuilder createMessage(String eventMessage){
        return new MessageCreatorBuilder(eventMessage);
    }

    public MessageCreatorBuilder filter(){
        if(message.startsWith("/")){
            String[] partsOfMessage = message.toLowerCase().substring(1).split(" ");
            if(checkLengthOfArray(partsOfMessage)){
                if(checkAvilableModules(partsOfMessage[0])){
                    if(checkAvilableCommandInModule(partsOfMessage[1])){
                        if(checkIfArrguIsAvilable(partsOfMessage)){
                            List<String> temporaryArray = new ArrayList<>();
                            for (int i = 2; i < partsOfMessage.length; i++) {
                                temporaryArray.add(partsOfMessage[i]);
                            }
                            commandEntity = new CommandEntity(partsOfMessage[0],partsOfMessage[1],temporaryArray);
                        }else{
                            commandEntity = new CommandEntity(partsOfMessage[0],partsOfMessage[1]);
                        }
                        System.out.println("jest taka komenda");
                    }
                    System.out.println("jest taki modul");
                }
            }
        }
        return this;
    }

    public CommandEntity getCommandEntity(){
        return commandEntity;
    }

    private boolean checkIfArrguIsAvilable(String[] partsOfMessage){
        if(partsOfMessage.length<=2) return false;
        return true;
    }

    private boolean checkLengthOfArray(String[] partsOfMessage){
        if(partsOfMessage.length<2) return false;
        return true;
    }

    private boolean checkAvilableModules(String module){
        return modules.contains(module);
    }

    private boolean checkAvilableCommandInModule(String command){
        return commands.contains(command);
    }






}
