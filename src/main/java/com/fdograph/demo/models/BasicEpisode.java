package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Episode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicEpisode {
  private Integer id;
  private String name;
  private String airDate;
  private String episode;

  public static BasicEpisode buildFromEpisode(Episode episode) {
    return BasicEpisode.builder()
        .id(episode.getId())
        .name(episode.getName())
        .airDate(episode.getAirDate())
        .episode(episode.getEpisode())
        .build();
  }
}
