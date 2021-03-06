package com.fdograph.demo.api;

import com.fdograph.demo.api.entities.Character;
import com.fdograph.demo.api.entities.CharactersArchive;
import com.fdograph.demo.api.entities.Episode;
import com.fdograph.demo.api.entities.EpisodesArchive;
import com.fdograph.demo.api.entities.Location;
import com.fdograph.demo.api.entities.LocationsArchive;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class Api {
  private static final String protocol = "https";
  private static final String host = "rickandmortyapi.com";
  private static final String basePath = "/api";

  private final HttpEntity<String> httpEntity;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Logger logger;

  Api() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    this.httpEntity = new HttpEntity<>(headers);
  }

  private UriComponentsBuilder getApiUri(String pathTemplate) {
    return UriComponentsBuilder.newInstance()
        .scheme(protocol)
        .host(host)
        .path(basePath)
        .path(pathTemplate);
  }

  private <T> ResponseEntity<T> request(String url, Class<T> mapperClass) {
    logger.info("Requesting - " + url);

    ResponseEntity<T> response =
        restTemplate.exchange(url, HttpMethod.GET, httpEntity, mapperClass);

    if (!response.getStatusCode().equals(HttpStatus.OK)) {
      throw new ApiClientException(response);
    }

    return response;
  }

  public List<Character> getCharacters(Set<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return null;
    }

    String url = getApiUri("/character/{ids}")
        .buildAndExpand(Collections.singletonMap("ids", ids))
        .toUriString();

    ResponseEntity<Character[]> response = request(url, Character[].class);

    return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
  }

  public CharactersArchive getCharactersArchive(@Nullable Integer page) {
    String pageNum = Optional.ofNullable(page).orElse(1).toString();
    String url = getApiUri("/character")
        .query("page={page}")
        .buildAndExpand(Collections.singletonMap("page", pageNum))
        .toUriString();

    ResponseEntity<CharactersArchive> response = request(url, CharactersArchive.class);

    return response.getBody();
  }

  public List<Location> getLocations(Set<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return null;
    }

    String url = getApiUri("/location/{ids}")
        .buildAndExpand(Collections.singletonMap("ids", ids))
        .toUriString();

    ResponseEntity<Location[]> response = request(url, Location[].class);

    return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
  }

  public LocationsArchive getLocationsArchive(@Nullable Integer page) {
    String pageNum = Optional.ofNullable(page).orElse(1).toString();
    String url = getApiUri("/location")
        .query("page={page}")
        .buildAndExpand(Collections.singletonMap("page", pageNum))
        .toUriString();

    ResponseEntity<LocationsArchive> response = request(url, LocationsArchive.class);

    return response.getBody();
  }

  public List<Episode> getEpisodes(Set<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return null;
    }

    String url = getApiUri("/episode/{ids}")
        .buildAndExpand(Collections.singletonMap("ids", ids))
        .toUriString();

    ResponseEntity<Episode[]> response = request(url, Episode[].class);

    return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
  }

  public EpisodesArchive getEpisodesArchive(@Nullable Integer page) {
    String pageNum = Optional.ofNullable(page).orElse(1).toString();
    String url = getApiUri("/episode")
        .query("page={page}")
        .buildAndExpand(Collections.singletonMap("page", pageNum))
        .toUriString();

    ResponseEntity<EpisodesArchive> response = request(url, EpisodesArchive.class);

    return response.getBody();
  }
}
