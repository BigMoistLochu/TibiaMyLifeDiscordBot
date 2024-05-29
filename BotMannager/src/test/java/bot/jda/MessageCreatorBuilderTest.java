package bot.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
class MessageCreatorBuilderTest {


    @Test
    void filter() {
        CommandEntity commandEntity = MessageCreatorBuilder.createMessage("/track follow arg1 arg2").filter().getCommandEntity();
        Assertions.assertEquals("track",commandEntity.getModule());
        Assertions.assertEquals("follow",commandEntity.getCommand());
        Assertions.assertEquals("arg1",commandEntity.getArgs().get(0));
        Assertions.assertEquals("arg2",commandEntity.getArgs().get(1));
    }


}