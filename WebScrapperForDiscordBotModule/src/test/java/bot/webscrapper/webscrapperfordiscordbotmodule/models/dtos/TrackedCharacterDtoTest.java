package bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos;

import bot.webscrapper.webscrapperfordiscordbotmodule.exceptions.scrappersexceptions.InvalidScrapingDataException;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.enums.SupportServers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackedCharacterDtoTest {

    @Test
    void creatingNewTrackedCharacterDtoShouldThrowInvalidScrapingExceptionWhenNickIsNull() {
        Assertions.assertThrows(InvalidScrapingDataException.class,()->new TrackedCharacterDto(null,"true","123"));
    }

    @Test
    void creatingNewTrackedCharacterDtoShouldThrowInvalidScrapingExceptionWhenIsOnlineIsNull() {
        Assertions.assertThrows(InvalidScrapingDataException.class,()->new TrackedCharacterDto("nick",null,"123"));
    }

    @Test
    void creatingNewTrackedCharacterDtoShouldThrowInvalidScrapingExceptionWhenExperienceIsNull() {
        Assertions.assertThrows(InvalidScrapingDataException.class,()->new TrackedCharacterDto("nick","true",null));
    }

    @Test
    void creatingNewTrackedCharacterDtoShouldThrowInvalidScrapingExceptionWhenIsOnlineIsDifferentThanTrueOrFalse() {
        Assertions.assertThrows(InvalidScrapingDataException.class,()->new TrackedCharacterDto("nick","abc","123"));
    }

    @Test
    void creatingNewTrackedCharacterDtoShouldThrowInvalidScrapingExceptionWhenExperienceIsNotNumeric() {
        Assertions.assertThrows(InvalidScrapingDataException.class,()->new TrackedCharacterDto("nick","true","notNumeric"));
    }

    @Test
    void creatingNewTrackedCharacterDtoWithGoodCredientialsShouldReturnCorrectTrackedCharacter() {
        TrackedCharacterDto characterDto = new TrackedCharacterDto("Henry","true","2900");
        assertEquals("Henry",characterDto.getNick());
        assertTrue(characterDto.isOnline());
        assertEquals(2900,characterDto.getExperience());
    }

    @Test
    void creatingNewTrackedCharacterDtoWithNickAndServerShouldReturnSameHashAndEqualMethodOnSameObject(){
        TrackedCharacterDto characterDto = new TrackedCharacterDto("Dzj", SupportServers.GUNZODUS);
        TrackedCharacterDto differentCharacterDto = new TrackedCharacterDto("Dzj",SupportServers.GUNZODUS);
        assertEquals(differentCharacterDto,characterDto);
        assertEquals(differentCharacterDto.hashCode(),characterDto.hashCode());
    }
}