package bot.webscrapper.webscrapperfordiscordbotmodule.models.mappers;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;

public class TrackedCharacterDtoMapper {

    public static TrackedCharacterDto mapTrackedCharacterToDto(TrackedCharacter character){
        TrackedCharacterDto dto = new TrackedCharacterDto();
        dto.setNick(character.getNick());
        dto.setSupportServers(character.getSupportServers());
        return dto;
    }








}
