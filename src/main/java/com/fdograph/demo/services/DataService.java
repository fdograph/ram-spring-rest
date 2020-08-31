package com.fdograph.demo.services;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Character;
import com.fdograph.demo.api.entities.CharactersArchive;
import com.fdograph.demo.api.entities.Episode;
import com.fdograph.demo.api.entities.EpisodesArchive;
import com.fdograph.demo.api.entities.Location;
import com.fdograph.demo.api.entities.LocationsArchive;
import com.fdograph.demo.models.BasicCharacter;
import com.fdograph.demo.models.BasicEpisode;
import com.fdograph.demo.models.BasicLocation;
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
import org.springframework.stereotype.Component;

@Component
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
    CharactersArchive archive = this.api.getAllCharacters(currentPage);
    List<EnhancedCharacter> results = archive.getResults()
        .stream()
        .peek(item -> this.characterRepository.set(item.getId(), item))
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
    EpisodesArchive archive = this.api.getAllEpisodes(currentPage);
    List<EnhancedEpisode> results = archive.getResults()
        .stream()
        .peek(item -> this.episodeRepository.set(item.getId(), item))
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
    LocationsArchive archive = this.api.getAllLocations(currentPage);
    List<EnhancedLocation> results = archive.getResults()
        .stream()
        .peek(item -> this.locationRepository.set(item.getId(), item))
        .map(this::enhanceLocation)
        .collect(Collectors.toList());

    return new EnhancedArchive<EnhancedLocation>(
        currentPage,
        archive.getInfo().getCount(),
        archive.getInfo().getPages(),
        results
    );
  }

  private EnhancedCharacter enhanceCharacter(Character character) {
    Integer originId = character.getOrigin().getId();
    Integer locationId = character.getLocation().getId();

    List<BasicEpisode> episodes = this.episodeRepository.getList(character.getEpisode())
        .stream()
        .map(BasicEpisode::buildFromEpisode)
        .collect(Collectors.toList());

    return EnhancedCharacter.builderFromCharacter(character)
        .origin(originId == null ? null :
            BasicLocation.buildFromLocation(this.locationRepository.get(originId)))
        .location(locationId == null ? null :
            BasicLocation.buildFromLocation(this.locationRepository.get(locationId)))
        .episodes(episodes)
        .build();
  }

  private EnhancedEpisode enhanceEpisode(Episode episode) {
    List<BasicCharacter> characters = this.characterRepository.getList(episode.getCharacters())
        .stream()
        .map(BasicCharacter::buildFromCharacter)
        .collect(Collectors.toList());

    return EnhancedEpisode
        .builderFromEpisode(episode)
        .characters(characters)
        .build();
  }

  private EnhancedLocation enhanceLocation(Location location) {
    List<BasicCharacter> residents = this.characterRepository.getList(location.getResidents())
        .stream()
        .map(BasicCharacter::buildFromCharacter)
        .collect(Collectors.toList());

    return EnhancedLocation
        .builderFromLocation(location)
        .residents(residents)
        .build();
  }
}
