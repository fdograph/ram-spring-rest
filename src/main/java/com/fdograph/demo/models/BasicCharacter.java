package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Character;
import com.fdograph.demo.api.entities.CharacterGender;
import com.fdograph.demo.api.entities.CharacterStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicCharacter {
  private Integer id;
  private String name;
  private CharacterStatus status;
  private String species;
  private String type;
  private CharacterGender gender;
  private String image;

  public static BasicCharacter buildFromCharacter(Character character) {
    return BasicCharacter.builder()
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
