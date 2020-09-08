package com.fdograph.demo.services;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Character;
import com.fdograph.demo.api.entities.CharactersArchive;
import com.fdograph.demo.api.entities.Episode;
import com.fdograph.demo.api.entities.EpisodesArchive;
import com.fdograph.demo.api.entities.Location;
import com.fdograph.demo.api.entities.LocationsArchive;
import com.fdograph.demo.models.EnhancedArchive;
import com.fdograph.demo.models.EnhancedCharacter;
import com.fdograph.demo.models.EnhancedEpisode;
import com.fdograph.demo.models.EnhancedLocation;
import com.fdograph.demo.repositories.CharacterRepository;
import com.fdograph.demo.repositories.EpisodeRepository;
import com.fdograph.demo.repositories.LocationRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataService {
  @Autowired
  private Api api;

  @Autowired
  private CharacterRepository characterRepository;

  @Autowired
  private EpisodeRepository episodeRepository;

  @Autowired
  private LocationRepository locationRepository;

  public EnhancedCharacter getCharacter(Integer id) {
    return enhanceCharacter(this.characterRepository.get(id));
  }

  public EnhancedArchive<EnhancedCharacter> getCharactersArchive(@Nullable Integer page) {
    Integer currentPage = Optional.ofNullable(page).orElse(1);
    CharactersArchive archive = this.api.getCharactersArchive(currentPage);
    List<EnhancedCharacter> results = archive.getResults()
        .stream()
        .peek(this.characterRepository::set)
        .map(this::enhanceCharacter)
        .collect(Collectors.toList());

    return new EnhancedArchive<EnhancedCharacter>(
        currentPage,
        archive.getInfo().getCount(),
        archive.getInfo().getPages(),
        results
    );
  }

  public EnhancedEpisode getEpisode(Integer id) {
    return enhanceEpisode(this.episodeRepository.get(id));
  }

  public EnhancedArchive<EnhancedEpisode> getEpisodesArchive(@Nullable Integer page) {
    Integer currentPage = Optional.ofNullable(page).orElse(1);
    EpisodesArchive archive = this.api.getEpisodesArchive(currentPage);
    List<EnhancedEpisode> results = archive.getResults()
        .stream()
        .peek(this.episodeRepository::set)
        .map(this::enhanceEpisode)
        .collect(Collectors.toList());

    return new EnhancedArchive<EnhancedEpisode>(
        currentPage,
        archive.getInfo().getCount(),
        archive.getInfo().getPages(),
        results
    );
  }

  public EnhancedLocation getLocation(Integer id) {
    return enhanceLocation(this.locationRepository.get(id));
  }

  public EnhancedArchive<EnhancedLocation> getLocationsArchive(@Nullable Integer page) {
    Integer currentPage = Optional.ofNullable(page).orElse(1);
    LocationsArchive archive = this.api.getLocationsArchive(currentPage);
    List<EnhancedLocation> results = archive.getResults()
        .stream()
        .peek(this.locationRepository::set)
        .map(this::enhanceLocation)
        .collect(Collectors.toList());

    return new EnhancedArchive<EnhancedLocation>(
        currentPage,
        archive.getInfo().getCount(),
        archive.getInfo().getPages(),
        results
    );
  }

  private EnhancedCharacter enhanceCharacter(@Nullable Character character) {
    if (character == null) {
      return null;
    }

    List<EnhancedEpisode> episodes = this.episodeRepository.getList(character.getEpisode())
        .stream()
        .map(EnhancedEpisode::fromEpisode)
        .collect(Collectors.toList());

    return EnhancedCharacter
        .builderFromCharacter(character)
        .episodes(episodes)
        .origin(EnhancedLocation.fromLocation(
            this.locationRepository.get(character.getOrigin().getId())
        ))
        .location(EnhancedLocation.fromLocation(
            this.locationRepository.get(character.getLocation().getId())
        ))
        .build();
  }

  private EnhancedEpisode enhanceEpisode(@Nullable Episode episode) {
    if (episode == null) {
      return null;
    }

    List<EnhancedCharacter> characters = this.characterRepository.getList(episode.getCharacters())
        .stream()
        .map(EnhancedCharacter::fromCharacter)
        .collect(Collectors.toList());

    return EnhancedEpisode
        .builderFromEpisode(episode)
        .characters(characters)
        .build();
  }

  private EnhancedLocation enhanceLocation(@Nullable Location location) {
    if (location == null) {
      return null;
    }

    List<EnhancedCharacter> residents = this.characterRepository.getList(location.getResidents())
        .stream()
        .map(EnhancedCharacter::fromCharacter)
        .collect(Collectors.toList());

    return EnhancedLocation
        .builderFromLocation(location)
        .residents(residents)
        .build();
  }
}
