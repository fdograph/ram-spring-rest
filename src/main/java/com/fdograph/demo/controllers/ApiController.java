package com.fdograph.demo.controllers;

import com.fdograph.demo.models.EnhancedArchive;
import com.fdograph.demo.models.EnhancedCharacter;
import com.fdograph.demo.models.EnhancedEpisode;
import com.fdograph.demo.models.EnhancedLocation;
import com.fdograph.demo.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api")
public class ApiController {
  @Autowired
  private DataService dataService;

  @GetMapping("/character")
  public EnhancedArchive<EnhancedCharacter> getAllCharacters(
      @RequestParam("page") @Nullable Integer page) {
    try {
      return this.dataService.getCharactersArchive(page);
    } catch (RestClientException ex) {
      throw new ApiException(ex);
    }
  }

  @GetMapping("/character/{id}")
  public EnhancedCharacter getCharacter(@PathVariable int id) {
    try {
      return this.dataService.getCharacter(id);
    } catch (RestClientException ex) {
      throw new ApiException(ex);
    }
  }

  @GetMapping("/location")
  public EnhancedArchive<EnhancedLocation> getAllLocations(
      @RequestParam("page") @Nullable Integer page) {
    try {
      return this.dataService.getLocationsArchive(page);
    } catch (RestClientException ex) {
      throw new ApiException(ex);
    }
  }

  @GetMapping("/location/{id}")
  public EnhancedLocation getLocation(@PathVariable int id) {
    try {
      return this.dataService.getLocation(id);
    } catch (RestClientException ex) {
      throw new ApiException(ex);
    }
  }

  @GetMapping("/episode")
  public EnhancedArchive<EnhancedEpisode> getAllEpisodes(
      @RequestParam("page") @Nullable Integer page) {
    try {
      return this.dataService.getEpisodesArchive(page);
    } catch (RestClientException ex) {
      throw new ApiException(ex);
    }
  }

  @GetMapping("/episode/{id}")
  public EnhancedEpisode getEpisode(@PathVariable int id) {
    try {
      return this.dataService.getEpisode(id);
    } catch (RestClientException ex) {
      throw new ApiException(ex);
    }
  }
}