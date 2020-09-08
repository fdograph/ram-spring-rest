package com.fdograph.demo.controllers;

import com.fdograph.demo.models.EnhancedArchive;
import com.fdograph.demo.models.EnhancedCharacter;
import com.fdograph.demo.models.EnhancedEpisode;
import com.fdograph.demo.models.EnhancedLocation;
import com.fdograph.demo.services.DataService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
  @Autowired
  private DataService dataService;

  @GetMapping("/character")
  public EnhancedArchive<EnhancedCharacter> getAllCharacters(
      @RequestParam("page") @Nullable Integer page) {
    return this.dataService.getCharactersArchive(page);
  }

  @GetMapping("/character/{id}")
  public EnhancedCharacter getCharacter(HttpServletResponse response, @PathVariable Integer id) {
    EnhancedCharacter result = this.dataService.getCharacter(id);

    if (result == null) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    return result;
  }

  @GetMapping("/location")
  public EnhancedArchive<EnhancedLocation> getAllLocations(
      @RequestParam("page") @Nullable Integer page) {
    return this.dataService.getLocationsArchive(page);
  }

  @GetMapping("/location/{id}")
  public EnhancedLocation getLocation(HttpServletResponse response, @PathVariable Integer id) {
    EnhancedLocation result = this.dataService.getLocation(id);

    if (result == null) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    return result;
  }

  @GetMapping("/episode")
  public EnhancedArchive<EnhancedEpisode> getAllEpisodes(
      @RequestParam("page") @Nullable Integer page) {
    return this.dataService.getEpisodesArchive(page);
  }

  @GetMapping("/episode/{id}")
  public EnhancedEpisode getEpisode(HttpServletResponse response, @PathVariable Integer id) {
    EnhancedEpisode result = this.dataService.getEpisode(id);

    if (result == null) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    return result;
  }
}