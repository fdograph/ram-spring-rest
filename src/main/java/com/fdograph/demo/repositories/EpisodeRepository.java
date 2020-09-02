package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Episode;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EpisodeRepository extends DataRepository<Episode> {
  @Autowired
  private Api api;

  EpisodeRepository() {
    super();
  }

  protected List<Episode> fetch(Set<Integer> ids) {
    return this.api.getEpisodes(ids);
  }
}
