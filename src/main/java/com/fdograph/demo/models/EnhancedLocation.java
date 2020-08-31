package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Location;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnhancedLocation {
  private Integer id;
  private String name;
  private String type;
  private String dimension;
  private List<BasicCharacter> residents;

  public static EnhancedLocationBuilder builderFromLocation(Location location) {
    return EnhancedLocation
        .builder()
        .id(location.getId())
        .name(location.getName())
        .type(location.getType())
        .dimension(location.getDimension());
  }
}
