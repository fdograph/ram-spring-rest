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
    EpisodesArchive archive = this.api.getEpisodesArchive(currentPage);
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
    LocationsArchive archive = this.api.getLocationsArchive(currentPage);
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
    Location originLoc = this.locationRepository.get(originId);
    EnhancedLocation origin = EnhancedLocation.buildFromLocation(originLoc);
    Integer locationId = character.getLocation().getId();
    Location locationLoc = this.locationRepository.get(locationId);
    EnhancedLocation location = EnhancedLocation.buildFromLocation(locationLoc);

    List<EnhancedEpisode> episodes = this.episodeRepository.getList(character.getEpisode())
        .stream()
        .map(EnhancedEpisode::buildFromEpisode)
        .collect(Collectors.toList());

    EnhancedCharacter enhancedCharacter = EnhancedCharacter.buildFromCharacter(character);
    enhancedCharacter.setEpisodes(episodes);
    enhancedCharacter.setOrigin(origin);
    enhancedCharacter.setLocation(location);

    return enhancedCharacter;
  }

  private EnhancedEpisode enhanceEpisode(Episode episode) {
    List<EnhancedCharacter> characters = this.characterRepository.getList(episode.getCharacters())
        .stream()
        .map(EnhancedCharacter::buildFromCharacter)
        .collect(Collectors.toList());

    EnhancedEpisode enhancedEpisode = EnhancedEpisode.buildFromEpisode(episode);
    enhancedEpisode.setCharacters(characters);

    return enhancedEpisode;
  }

  private EnhancedLocation enhanceLocation(Location location) {
    List<EnhancedCharacter> residents = this.characterRepository.getList(location.getResidents())
        .stream()
        .map(EnhancedCharacter::buildFromCharacter)
        .collect(Collectors.toList());

    EnhancedLocation enhancedLocation = EnhancedLocation.buildFromLocation(location);
    enhancedLocation.setResidents(residents);

    return enhancedLocation;
  }
}
