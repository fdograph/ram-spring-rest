package com.fdograph.demo.api.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PagedResults<T> {
  protected PageInfo info;
  protected List<T> results;
}
