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
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

  private <T> ResponseEntity<T> request(String url, Class<T> mapperClass) throws
      RestClientException {
    logger.info("Requesting! - " + url);
    return restTemplate.exchange(url, HttpMethod.GET, httpEntity, mapperClass);
  }

  public Character getCharacter(Integer id) {
    String url = getApiUri("/character/{id}")
        .buildAndExpand(Collections.singletonMap("id", id.toString()))
        .toUriString();

    ResponseEntity<Character> response = request(url, Character.class);

    return response.getBody();
  }

  public List<Character> getCharacters(List<Integer> ids) {
    String url = getApiUri("/character/{ids}")
        .buildAndExpand(Collections.singletonMap("ids", ids))
        .toUriString();

    ResponseEntity<Character[]> response = request(url, Character[].class);

    return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
  }

  public CharactersArchive getAllCharacters(@Nullable Integer page) {
    String pageNum = Optional.ofNullable(page).orElse(1).toString();
    String url = getApiUri("/character")
        .query("page={page}")
        .buildAndExpand(Collections.singletonMap("page", pageNum))
        .toUriString();

    ResponseEntity<CharactersArchive> response = request(url, CharactersArchive.class);

    return response.getBody();
  }

  public Location getLocation(Integer id) {
    String url = getApiUri("/location/{id}")
        .buildAndExpand(Collections.singletonMap("id", id.toString()))
        .toUriString();
    ResponseEntity<Location> response = request(url, Location.class);

    return response.getBody();
  }

  public List<Location> getLocations(List<Integer> ids) {
    String url = getApiUri("/location/{ids}")
        .buildAndExpand(Collections.singletonMap("ids", ids))
        .toUriString();

    ResponseEntity<Location[]> response = request(url, Location[].class);

    return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
  }

  public LocationsArchive getAllLocations(@Nullable Integer page) {
    String pageNum = Optional.ofNullable(page).orElse(1).toString();
    String url = getApiUri("/location")
        .query("page={page}")
        .buildAndExpand(Collections.singletonMap("page", pageNum))
        .toUriString();

    ResponseEntity<LocationsArchive> response = request(url, LocationsArchive.class);

    return response.getBody();
  }

  public Episode getEpisode(Integer id) {
    String url = getApiUri("/episode/{id}")
        .buildAndExpand(Collections.singletonMap("id", id.toString()))
        .toUriString();
    ResponseEntity<Episode> response = request(url, Episode.class);

    return response.getBody();
  }

  public List<Episode> getEpisodes(List<Integer> ids) {
    String url = getApiUri("/episode/{ids}")
        .buildAndExpand(Collections.singletonMap("ids", ids))
        .toUriString();

    ResponseEntity<Episode[]> response = request(url, Episode[].class);

    return Arrays.stream(Objects.requireNonNull(response.getBody())).collect(Collectors.toList());
  }

  public EpisodesArchive getAllEpisodes(@Nullable Integer page) {
    String pageNum = Optional.ofNullable(page).orElse(1).toString();
    String url = getApiUri("/episode")
        .query("page={page}")
        .buildAndExpand(Collections.singletonMap("page", pageNum))
        .toUriString();

    ResponseEntity<EpisodesArchive> response = request(url, EpisodesArchive.class);

    return response.getBody();
  }
}
