package com.fdograph.demo.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnhancedArchive<T> {
  private Integer page;
  private Integer count;
  private Integer pages;
  private List<T> results;
}
