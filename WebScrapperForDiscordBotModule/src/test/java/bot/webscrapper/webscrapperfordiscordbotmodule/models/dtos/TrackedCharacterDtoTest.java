package bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos;

import bot.webscrapper.webscrapperfordiscordbotmodule.exceptions.InvalidScrapingDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackedCharacterDtoTest {

    @Test
    void creatingNewTrackedCharacterDtoShouldThrowInvalidScrapingExceptionWhenNickIsNull() {
        Assertions.assertThrows(InvalidScrapingDataException.class,()->new TrackedCharacterDto(null,"true","123"));
    }
}