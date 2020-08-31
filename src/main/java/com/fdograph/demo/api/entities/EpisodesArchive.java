package com.fdograph.demo.api.entities;

import java.util.List;

public class EpisodesArchive extends PagedResults<Episode> {
  EpisodesArchive(PageInfo pageInfo, List<Episode> results) {
    super(pageInfo, results);
  }
}
