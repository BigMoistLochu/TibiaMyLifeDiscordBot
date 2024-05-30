package bot.jda;

import bot.jda.models.CommandMessageDiscordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MessageCreatorBuilderTest {


    @Test
    void shouldCreateEquivalentCommandEntityAfterFilteringWithTwoArguments() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/track follow arg1 arg2").filter().getCommandEntity();
        CommandMessageDiscordEntity commandMessageDiscordEntity2 = new CommandMessageDiscordEntity("track","follow", List.of("arg1","arg2"));
        Assertions.assertEquals(commandMessageDiscordEntity2, commandMessageDiscordEntity);
    }

    @Test
    void commandEntityShouldBeNullAfterFilteringWithNoExistenceModule() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/noexistencemodule follow arg1 arg2").filter().getCommandEntity();
        Assertions.assertNull(commandMessageDiscordEntity);
    }

    @Test
    void commandEntityShouldBeNullAfterFilteringWithNoExistenceCommand() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/track noexistencecommand arg1 arg2").filter().getCommandEntity();
        Assertions.assertNull(commandMessageDiscordEntity);
    }


}