package bot.jda.models;

import java.util.List;

public class ChatCommand {

    private final String module;
    private final String command;
    private List<String> args;


    public ChatCommand(String module, String command, List<String> args){
        this.module = module;
        this.command = command;
        this.args = args;
    }

    public ChatCommand(String module, String command){
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

    public String getEndpoint(){return module+":"+command;}


    @Override
    public int hashCode() {
       int hash = 7;
       hash = 31 * hash + (module == null ? 0 : module.hashCode());
       hash = 31 * hash + (command == null ? 0 : command.hashCode());
       hash = 31 * hash + (args == null ? 0 : args.hashCode());
       return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof ChatCommand o)) return false;
        return this.module.equals(o.module) &&
                this.command.equals(o.command) && (this.args!= null ? this.args.equals(o.args) : true);
    }
}
