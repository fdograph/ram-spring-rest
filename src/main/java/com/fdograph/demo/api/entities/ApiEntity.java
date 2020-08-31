package com.fdograph.demo.api.entities;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class ApiEntity {
  @Autowired
  private Logger logger;

  public List<Integer> getIdsFromUriList(List<String> urls) {
    return urls.stream().map(this::getIdFromUri).collect(Collectors.toList());
  }

  public Integer getIdFromUri(@Nullable String uriString) {
    if (uriString == null || uriString.equals("")) {
      return 0;
    }

    try {
      URI uri = new URI(uriString);
      List<String> segments = UriComponentsBuilder.fromUri(uri).buildAndExpand().getPathSegments();
      return Integer.valueOf(segments.get(segments.size() - 1));

    } catch (Exception e) {
      logger.info("Error parsing URL: " + uriString);
      return null;
    }
  }
}
