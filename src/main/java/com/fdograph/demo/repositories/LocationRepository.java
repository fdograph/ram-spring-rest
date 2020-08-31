package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Location;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationRepository extends Repository<Location> {
  @Autowired
  private Api api;

  LocationRepository() {
    super();
  }

  public Location get(Integer id) {
    Location cached = this.items.get(id);
    if (cached != null) {
      return cached;
    }

    Location locations = this.api.getLocation(id);
    if (locations != null) {
      this.items.put(id, locations);
    }

    return locations;
  }

  public List<Location> getList(List<Integer> ids) {
    Set<Integer> searchIds = new HashSet<>(ids);
    searchIds.removeAll(this.items.keySet());

    if (searchIds.size() != 0) {
      this.api.getLocations(new ArrayList<>(searchIds))
          .forEach(item -> this.set(item.getId(), item));
    }

    return ids.stream().map(this::get).collect(Collectors.toList());
  }
}
