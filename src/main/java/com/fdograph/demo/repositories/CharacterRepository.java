package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Character;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterRepository extends Repository<Character> {
  @Autowired
  private Api api;

  CharacterRepository() {
    super();
  }

  public Character get(Integer id) {
    Character cached = this.items.get(id);
    if (cached != null) {
      return cached;
    }

    Character character = this.api.getCharacter(id);
    if (character != null) {
      this.items.put(id, character);
    }

    return character;
  }

  public List<Character> getList(List<Integer> ids) {
    Set<Integer> searchIds = new HashSet<>(ids);
    searchIds.removeAll(this.items.keySet());

    if (searchIds.size() != 0) {
      this.api.getCharacters(new ArrayList<>(searchIds))
          .forEach(item -> this.set(item.getId(), item));
    }

    return ids.stream().map(this::get).collect(Collectors.toList());
  }
}
