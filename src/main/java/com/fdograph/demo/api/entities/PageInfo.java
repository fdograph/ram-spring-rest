package com.fdograph.demo.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
public class PageInfo {
  private Integer count;
  private Integer pages;

  @Nullable
  private String next;

  @Nullable
  private String prev;
}
