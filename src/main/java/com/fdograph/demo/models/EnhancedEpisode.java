package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Episode;
import com.fdograph.demo.api.entities.Location;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder
public class EnhancedEpisode {
  private Integer id;
  private String name;
  private String episode;

  @Nullable
  private List<EnhancedCharacter> characters;

  public static EnhancedEpisodeBuilder builderFromEpisode(@NonNull Episode episode) {
    return EnhancedEpisode
        .builder()
        .id(episode.getId())
        .name(episode.getName())
        .episode(episode.getEpisode());
  }

  public static EnhancedEpisode fromEpisode(@Nullable Episode episode) {
    return episode == null ? null : EnhancedEpisode.builderFromEpisode(episode).build();
  }
}
