package com.fdograph.demo.api.entities;

import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Location extends ApiEntity {
  private Integer id;
  private String name;
  private String type;
  private String dimension;
  private List<Integer> residents;
  private String url;
  private Timestamp created;

  Location(Integer id, String name, String type, String dimension, List<String> residents,
           String url, Timestamp created) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.dimension = dimension;
    this.residents = getIdsFromUriList(residents);
    this.url = url;
    this.created = created;
  }
}
