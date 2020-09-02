package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository extends DataRepository<Location> {
  @Autowired
  private Api api;

  LocationRepository() {
    super();
  }

  protected List<Location> fetch(List<Integer> ids) {
    return this.api.getLocations(ids);
  }
}
