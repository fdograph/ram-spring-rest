package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationRepository extends Repository<Location> {
  @Autowired
  private Api api;

  LocationRepository() {
    super();
  }

  protected Location fetchOne(Integer id) {
    return this.api.getLocation(id);
  }

  protected List<Location> fetchMany(List<Integer> ids) {
    return this.api.getLocations(ids);
  }
}
