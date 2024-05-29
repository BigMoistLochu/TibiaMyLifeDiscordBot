package bot.jda;

import java.util.List;

public class CommandEntity {

    private final String module;
    private final String command;
    private List<String> args;


    public CommandEntity(String module,String command,List<String> args){

        //walnie wyjatkiem jesli cos jest zle i tyle, nawet sie nie stworzy
        this.module = module;
        this.command = command;
        this.args = args;
    }

    public CommandEntity(String module,String command){
        //walnie wyjatkiem jesli cos jest zle i tyle, nawet sie nie stworzy
        this.module = module;
        this.command = command;
    }

    public String getModule() {
        return module;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArgs() {
        return args;
    }


}
