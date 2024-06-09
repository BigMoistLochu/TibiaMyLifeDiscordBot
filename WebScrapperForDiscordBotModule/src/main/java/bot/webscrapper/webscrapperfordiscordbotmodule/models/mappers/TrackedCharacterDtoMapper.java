package bot.webscrapper.webscrapperfordiscordbotmodule.models.mappers;

import bot.webscrapper.webscrapperfordiscordbotmodule.models.entities.TrackedCharacter;
import bot.webscrapper.webscrapperfordiscordbotmodule.models.dtos.TrackedCharacterDto;

import java.util.List;

public class TrackedCharacterDtoMapper {

    public static List<TrackedCharacterDto> mapToDtoList(List<TrackedCharacter> trackedCharacterList){
        return trackedCharacterList.stream()
                .map(trackedCharacter -> {
                    TrackedCharacterDto dto = new TrackedCharacterDto();
                    dto.setNick(trackedCharacter.getNick());
                    dto.setSupportServers(trackedCharacter.getSupportServers());
                    return dto;
                }).toList();
    }








}
