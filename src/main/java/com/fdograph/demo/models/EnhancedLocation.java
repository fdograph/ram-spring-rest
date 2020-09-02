package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Location;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder
public class EnhancedLocation {
  private Integer id;
  private String name;
  private String type;
  private String dimension;

  @Nullable
  private List<EnhancedCharacter> residents;

  public static EnhancedLocationBuilder builderFromLocation(@NonNull Location location) {
    return EnhancedLocation
        .builder()
        .id(location.getId())
        .name(location.getName())
        .type(location.getType())
        .dimension(location.getDimension());
  }

  public static EnhancedLocation fromLocation(@Nullable Location location) {
    return location == null ? null : EnhancedLocation.builderFromLocation(location).build();
  }
}
