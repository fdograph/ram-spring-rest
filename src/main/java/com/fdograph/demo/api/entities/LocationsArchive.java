package com.fdograph.demo.api.entities;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LocationsArchive extends PagedResults<Location> {
  LocationsArchive(PageInfo pageInfo, List<Location> results) {
    super(pageInfo, results);
  }
}
