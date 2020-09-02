package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Episode;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class EnhancedEpisode {
  private Integer id;
  private String name;
  private String episode;

  @Nullable
  private List<EnhancedCharacter> characters;

  public static EnhancedEpisode buildFromEpisode(@Nullable Episode episode) {
    if (episode == null) {
      return null;
    }

    return builder()
        .id(episode.getId())
        .name(episode.getName())
        .episode(episode.getEpisode())
        .build();
  }
}
