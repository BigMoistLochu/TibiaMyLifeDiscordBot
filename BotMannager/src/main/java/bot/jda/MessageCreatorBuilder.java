package bot.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class MessageCreatorBuilder {

    private final String message;
    private CommandEntity commandEntity;

    private final List<String> modules = new ArrayList<>(List.of("track","auction"));

    private final boolean isAvilableModulAndComment = true;

    private MessageCreatorBuilder(String message){
        this.message = message;
    }

    public static MessageCreatorBuilder createMessage(MessageReceivedEvent event){
        return new MessageCreatorBuilder(event.getMessage().getContentRaw());
    }

    public boolean filter(){
        if(message.startsWith("/")){
            String[] partsOfMessage = message.toLowerCase().substring(1).split(" ");
            if(checkAvilableModules(partsOfMessage[0])){
                if(checkAvilableCommandInModule(partsOfMessage[1])){

                    //reszte to argumenty wiec przeslij je do listy
                }
                System.out.println("jest taki modul");
            }
        }
        return false;
    }

    private boolean checkAvilableModules(String module){
        return modules.contains(module);
    }

    private boolean checkAvilableCommandInModule(String command){
        return modules.contains(command);
    }






}
