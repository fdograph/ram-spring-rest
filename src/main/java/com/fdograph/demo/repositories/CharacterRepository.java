package com.fdograph.demo.repositories;

import com.fdograph.demo.api.Api;
import com.fdograph.demo.api.entities.Character;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterRepository extends Repository<Character> {
  @Autowired
  private Api api;

  CharacterRepository() {
    super();
  }

  protected Character fetchOne(Integer id) {
    return this.api.getCharacter(id);
  }

  protected List<Character> fetchMany(List<Integer> ids) {
    return this.api.getCharacters(ids);
  }
}
