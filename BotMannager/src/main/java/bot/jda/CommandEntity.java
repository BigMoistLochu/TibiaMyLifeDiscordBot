package bot.jda;

import java.util.List;

public class CommandEntity {

    private final String module;
    private final String command;
    private final String[] args;


    public CommandEntity(String module,String command,String... args){

        //walnie wyjatkiem jesli cos jest zle i tyle, nawet sie nie stworzy
        this.module = module;
        this.command = command;
        this.args = args;
    }

    public String getModule() {
        return module;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
