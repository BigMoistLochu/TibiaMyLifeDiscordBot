package bot.jda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class ModuleAndCommandContainer {

    private static Map<String, List<String> > commandContainer;
    private static final Logger logger = Logger.getLogger(ModuleAndCommandContainer.class.getName());

    private ModuleAndCommandContainer(){}
    public static void loadToMemoryAndCreateMapFromJsonFile(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if(commandContainer==null){
            //podmienic mozna na typ TypeReference jesli klasa okaze sie zbyt skaplikowana
            commandContainer = objectMapper.readValue(new File(path), HashMap.class);
            if(commandContainer!=null) logger.info("Pomyslnie zaladowano plik json do pamieci");
        }
    }

    public static boolean hasModule(String module){
        return commandContainer.containsKey(module);
    }

    public static boolean hasCommand(String command){
        for (List<String> value : commandContainer.values()){
            if(value.contains(command)) return true;
        }
        return false;
    }


}
