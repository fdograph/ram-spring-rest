package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Character;
import com.fdograph.demo.api.entities.Episode;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnhancedEpisode {
  private Integer id;
  private String name;
  private String airDate;
  private String episode;
  private List<BasicCharacter> characters;

  public static EnhancedEpisodeBuilder builderFromEpisode(Episode episode) {
    return builder()
        .id(episode.getId())
        .name(episode.getName())
        .airDate(episode.getAirDate())
        .episode(episode.getEpisode());
  }
}
