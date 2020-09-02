package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Character;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepository extends DataRepository<Character> {
  @Autowired
  private Api api;

  CharacterRepository() {
    super();
  }

  protected List<Character> fetch(Set<Integer> ids) {
    return this.api.getCharacters(ids);
  }
}
