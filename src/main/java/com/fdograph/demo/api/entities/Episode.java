package com.fdograph.demo.api.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Episode extends ApiEntity {
  private Integer id;
  private String name;
  private String episode;
  private Set<Integer> characters;
  private String url;
  private Timestamp created;

  Episode(Integer id, String name, String episode, List<String> characters, String url,
          Timestamp created) {
    this.id = id;
    this.name = name;
    this.episode = episode;
    this.characters = getIdsFromUriList(characters);
    this.url = url;
    this.created = created;
  }
}
