package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Episode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EpisodeRepository extends Repository<Episode> {
  @Autowired
  private Api api;

  EpisodeRepository() {
    super();
  }

  public Episode get(Integer id) {
    Episode cached = this.items.get(id);
    if (cached != null) {
      return cached;
    }

    Episode episode = this.api.getEpisode(id);
    if (episode != null) {
      this.items.put(id, episode);
    }

    return episode;
  }

  public List<Episode> getList(List<Integer> ids) {
    Set<Integer> searchIds = new HashSet<>(ids);
    searchIds.removeAll(this.items.keySet());

    if (searchIds.size() != 0) {
      this.api.getEpisodes(new ArrayList<>(searchIds))
          .forEach(item -> this.set(item.getId(), item));
    }

    return ids.stream().map(this::get).collect(Collectors.toList());
  }
}
