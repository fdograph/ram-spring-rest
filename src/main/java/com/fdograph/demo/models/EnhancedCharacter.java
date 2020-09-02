package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Character;
import com.fdograph.demo.api.entities.CharacterGender;
import com.fdograph.demo.api.entities.CharacterStatus;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class EnhancedCharacter {
  private Integer id;
  private String name;
  private CharacterStatus status;
  private String species;
  private String type;
  private CharacterGender gender;
  private String image;

  @Nullable
  private EnhancedLocation origin;

  @Nullable
  private EnhancedLocation location;

  @Nullable
  private List<EnhancedEpisode> episodes;

  public static EnhancedCharacter buildFromCharacter(@Nullable Character character) {
    if (character == null) {
      return null;
    }

    return EnhancedCharacter.builder()
        .id(character.getId())
        .name(character.getName())
        .status(character.getStatus())
        .species(character.getSpecies())
        .type(character.getType())
        .gender(character.getGender())
        .image(character.getImage())
        .build();
  }
}
