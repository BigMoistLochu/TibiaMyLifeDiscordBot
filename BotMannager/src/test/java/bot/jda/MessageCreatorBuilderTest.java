package bot.jda;

import bot.jda.models.CommandMessageDiscordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class MessageCreatorBuilderTest {


    @BeforeAll
    static void bef() throws IOException {
        ModuleAndCommandContainer.loadToMemoryAndCreateMapFromJsonFile("modules.json");
    }

    @Test
    void shouldCreateEquivalentCommandMessageDiscordEntityAfterFilteringWithTwoArguments() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/track buy arg1 arg2").filter().getCommandMessageDiscord();
        CommandMessageDiscordEntity commandMessageDiscordEntity2 = new CommandMessageDiscordEntity("track","buy", List.of("arg1","arg2"));
        Assertions.assertEquals(commandMessageDiscordEntity2, commandMessageDiscordEntity);
    }

    @Test
    void commandEntityShouldBeNullAfterFilteringWithNoExistenceModule() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/noexistencemodule level arg1 arg2").filter().getCommandMessageDiscord();
        Assertions.assertNull(commandMessageDiscordEntity);
    }

    @Test
    void commandEntityShouldBeNullAfterFilteringWithNoExistenceCommand() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/track noexistencecommand arg1 arg2").filter().getCommandMessageDiscord();
        Assertions.assertNull(commandMessageDiscordEntity);
    }

    @Test
    void shouldCreateEquivalentCommandMessageDiscordEntityAfterFilteringWithNoArgument() {
        CommandMessageDiscordEntity commandMessageDiscordEntity = MessageCreatorBuilder.createMessage("/track buy").filter().getCommandMessageDiscord();
        CommandMessageDiscordEntity commandMessageDiscordEntity2 = new CommandMessageDiscordEntity("track","buy");
        Assertions.assertEquals(commandMessageDiscordEntity2, commandMessageDiscordEntity);
    }

    @Test
    void shouldCreateEquivalentCommandMessageDiscordEntityWithListLength1() {
        CommandMessageDiscordEntity commandMessageDiscord = MessageCreatorBuilder.createMessage("/track buy ar1").filter().getCommandMessageDiscord();
        CommandMessageDiscordEntity expectedCommandMessageDiscord = new CommandMessageDiscordEntity("track","buy",List.of("ar1"));
        Assertions.assertEquals(expectedCommandMessageDiscord, commandMessageDiscord);
        Assertions.assertEquals(1,commandMessageDiscord.getArgs().size());
    }

    @Test
    void twoTheSameCommandMessageDiscordEntityShouldGetTheSameValueOfHashcode() {
        CommandMessageDiscordEntity commandMessageDiscord = MessageCreatorBuilder.createMessage("/track buy ar1 abd2").filter().getCommandMessageDiscord();
        CommandMessageDiscordEntity expectedCommandMessageDiscord = new CommandMessageDiscordEntity("track","buy",List.of("ar1","abd2"));
        CommandMessageDiscordEntity secondCommandMessageDiscord = MessageCreatorBuilder.createMessage("/track buy ar1 abd2").filter().getCommandMessageDiscord();
        CommandMessageDiscordEntity secondExpectedCommandMessageDiscord = new CommandMessageDiscordEntity("track","buy",List.of("ar1","abd2"));
        Assertions.assertEquals(expectedCommandMessageDiscord, commandMessageDiscord);
        Assertions.assertEquals(2,commandMessageDiscord.getArgs().size());
        Assertions.assertEquals(secondExpectedCommandMessageDiscord, secondCommandMessageDiscord);
        Assertions.assertEquals(2,secondCommandMessageDiscord.getArgs().size());
        Assertions.assertEquals(expectedCommandMessageDiscord.hashCode(),commandMessageDiscord.hashCode());
        Assertions.assertEquals(commandMessageDiscord.hashCode(),secondCommandMessageDiscord.hashCode());
    }


}