package com.fdograph.demo.models;

import com.fdograph.demo.api.entities.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicLocation {
  private Integer id;
  private String name;
  private String type;
  private String dimension;

  public static BasicLocation buildFromLocation(Location location) {
    return BasicLocation.builder()
        .id(location.getId())
        .name(location.getName())
        .type(location.getType())
        .dimension(location.getDimension())
        .build();
  }
}
