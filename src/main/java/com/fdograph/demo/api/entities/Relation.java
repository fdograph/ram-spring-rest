package com.fdograph.demo.api.entities;

import com.fdograph.demo.api.entities.ApiEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Relation extends ApiEntity {
  private String name;

  @Nullable
  private Integer id;

  public Relation(String name, @Nullable String url) {
    this.name = name;
    this.id = url == null ? null : this.getIdFromUri(url);
  }
}