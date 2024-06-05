package bot.jda;

import bot.jda.messagereceiver.messageprocessor.MessageCreatorBuilder;
import bot.jda.models.ChatCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class MessageCreatorBuilderTest {


    @BeforeAll
    static void bef() throws IOException {
        ModuleAndCommandLoader.loadToMemoryAndCreateMapFromJsonFile("modules.json");
    }

    @Test
    void shouldCreateEquivalentCommandMessageDiscordEntityAfterFilteringWithTwoArguments() {
        ChatCommand chatCommand = MessageCreatorBuilder.createMessage("/track buy arg1 arg2").filter().getCommandMessageDiscord();
        ChatCommand chatCommand2 = new ChatCommand("track","buy", List.of("arg1","arg2"));
        Assertions.assertEquals(chatCommand2, chatCommand);
    }

    @Test
    void commandEntityShouldBeNullAfterFilteringWithNoExistenceModule() {
        ChatCommand chatCommand = MessageCreatorBuilder.createMessage("/noexistencemodule level arg1 arg2").filter().getCommandMessageDiscord();
        Assertions.assertNull(chatCommand);
    }

    @Test
    void commandEntityShouldBeNullAfterFilteringWithNoExistenceCommand() {
        ChatCommand chatCommand = MessageCreatorBuilder.createMessage("/track noexistencecommand arg1 arg2").filter().getCommandMessageDiscord();
        Assertions.assertNull(chatCommand);
    }

    @Test
    void shouldCreateEquivalentCommandMessageDiscordEntityAfterFilteringWithNoArgument() {
        ChatCommand chatCommand = MessageCreatorBuilder.createMessage("/track buy").filter().getCommandMessageDiscord();
        ChatCommand chatCommand2 = new ChatCommand("track","buy");
        Assertions.assertEquals(chatCommand2, chatCommand);
    }

    @Test
    void shouldCreateEquivalentCommandMessageDiscordEntityWithListLength1() {
        ChatCommand commandMessageDiscord = MessageCreatorBuilder.createMessage("/track buy ar1").filter().getCommandMessageDiscord();
        ChatCommand expectedCommandMessageDiscord = new ChatCommand("track","buy",List.of("ar1"));
        Assertions.assertEquals(expectedCommandMessageDiscord, commandMessageDiscord);
        Assertions.assertEquals(1,commandMessageDiscord.getArgs().size());
    }

    @Test
    void twoTheSameCommandMessageDiscordEntityShouldGetTheSameValueOfHashcode() {
        ChatCommand commandMessageDiscord = MessageCreatorBuilder.createMessage("/track buy ar1 abd2").filter().getCommandMessageDiscord();
        ChatCommand expectedCommandMessageDiscord = new ChatCommand("track","buy",List.of("ar1","abd2"));
        ChatCommand secondCommandMessageDiscord = MessageCreatorBuilder.createMessage("/track buy ar1 abd2").filter().getCommandMessageDiscord();
        ChatCommand secondExpectedCommandMessageDiscord = new ChatCommand("track","buy",List.of("ar1","abd2"));
        Assertions.assertEquals(expectedCommandMessageDiscord, commandMessageDiscord);
        Assertions.assertEquals(2,commandMessageDiscord.getArgs().size());
        Assertions.assertEquals(secondExpectedCommandMessageDiscord, secondCommandMessageDiscord);
        Assertions.assertEquals(2,secondCommandMessageDiscord.getArgs().size());
        Assertions.assertEquals(expectedCommandMessageDiscord.hashCode(),commandMessageDiscord.hashCode());
        Assertions.assertEquals(commandMessageDiscord.hashCode(),secondCommandMessageDiscord.hashCode());
    }


}