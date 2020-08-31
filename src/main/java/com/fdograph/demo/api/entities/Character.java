package com.fdograph.demo.api.entities;

import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Character extends ApiEntity {
  private Integer id;
  private String name;
  private CharacterStatus status;
  private String species;
  private String type;
  private CharacterGender gender;
  private String url;
  private String image;
  private Relation origin;
  private Relation location;
  private List<Integer> episode;
  private Timestamp created;

  Character(Integer id, String name, String status, String species, String type,
            String gender, String url, String image, Relation origin, Relation location,
            List<String> episode, Timestamp created) {
    this.id = id;
    this.name = name;
    this.status = CharacterStatus.valueOf(status.toUpperCase());
    this.species = species;
    this.type = type;
    this.gender = CharacterGender.valueOf(gender.toUpperCase());
    this.url = url;
    this.image = image;
    this.origin = origin;
    this.location = location;
    this.episode = getIdsFromUriList(episode);
    this.created = created;
  }
}
