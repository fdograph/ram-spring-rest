package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Episode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EpisodeRepository extends Repository<Episode> {
  @Autowired
  private Api api;

  EpisodeRepository() {
    super();
  }

  protected Episode fetchOne(Integer id) {
    return this.api.getEpisode(id);
  }

  protected List<Episode> fetchMany(List<Integer> ids) {
    return this.api.getEpisodes(ids);
  }
}
