package com.fdograph.demo.repositories;

import com.fdograph.demo.api.entities.ApiEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Repository<T extends ApiEntity> {
  protected Map<Integer, T> items;

  public Repository() {
    this.items = new HashMap<>();
  }

  protected abstract T fetchOne(Integer id);

  protected abstract List<T> fetchMany(List<Integer> ids);

  public T get(Integer id) {
    T cached = this.items.get(id);
    if (cached != null) {
      return cached;
    }

    T item = this.fetchOne(id);
    if (item != null) {
      this.items.put(id, item);
    }

    return item;
  }

  public List<T> getList(List<Integer> ids) {
    Set<Integer> searchIds = new HashSet<>(ids);
    searchIds.removeAll(this.items.keySet());

    if (searchIds.size() != 0) {
      this.fetchMany(new ArrayList<>(searchIds))
          .forEach(item -> this.set(item.getId(), item));
    }

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
