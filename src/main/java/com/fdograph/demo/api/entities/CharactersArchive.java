package com.fdograph.demo.api.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CharactersArchive extends PagedResults<Character> {
  CharactersArchive(PageInfo pageInfo, List<Character> results) {
    super(pageInfo, results);
  }
}
