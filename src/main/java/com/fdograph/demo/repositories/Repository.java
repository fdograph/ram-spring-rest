package com.fdograph.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Repository<T> {
  protected Map<Integer, T> items;

  public Repository() {
    this.items = new HashMap<>();
  }

  public abstract T get(Integer id);

  public List<T> getList(List<Integer> ids) {
    return ids.stream().map(this::get).collect(Collectors.toList());
  }

  public void set(Integer id, T item) {
    this.items.put(id, item);
  }

  public void invalidate(Integer id) {
    this.items.remove(id);
  }

  public void clear() {
    this.items.clear();
  }
}
