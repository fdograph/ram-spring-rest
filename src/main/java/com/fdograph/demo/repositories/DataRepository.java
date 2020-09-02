package com.fdograph.demo.repositories;

import com.fdograph.demo.api.entities.ApiEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DataRepository<T extends ApiEntity> {
  protected Map<Integer, T> items;

  public DataRepository() {
    this.items = new HashMap<>();
  }

  protected abstract List<T> fetch(List<Integer> ids);

  private void insert(List<Integer> ids) {
    List<T> fetchedItems = this.fetch(ids);

    if (fetchedItems != null) {
      fetchedItems.forEach(item -> this.set(item.getId(), item));
    }
  }

  public T get(Integer id) {
    T cached = this.items.get(id);
    if (cached != null) {
      return cached;
    }

    List<Integer> searchIds = List.of(id);
    this.insert(searchIds);

    return this.items.get(id);
  }

  public List<T> getList(List<Integer> ids) {
    Set<Integer> searchIds = new HashSet<>(ids);
    searchIds.removeAll(this.items.keySet());

    if (searchIds.size() != 0) {
      this.insert(new ArrayList<>(searchIds));
    }

    return ids.stream().map(this::get).collect(Collectors.toList());
  }

  public void set(Integer id, T item) {
    this.items.put(id, item);
  }
}