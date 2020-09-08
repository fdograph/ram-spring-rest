package com.fdograph.demo.repositories;

import com.fdograph.demo.api.entities.ApiEntity;
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

  protected abstract List<T> fetch(Set<Integer> ids);

  private void populate(Set<Integer> ids) {
    List<T> fetchedItems = this.fetch(ids);

    if (fetchedItems != null) {
      fetchedItems.forEach(this::set);
    }
  }

  public T get(Integer id) {
    T cached = this.items.get(id);
    if (cached != null) {
      return cached;
    }

    this.populate(Set.of(id));

    return this.items.get(id);
  }

  public List<T> getList(Set<Integer> ids) {
    Set<Integer> searchIds = new HashSet<>(ids);
    searchIds.removeAll(this.items.keySet());

    if (searchIds.size() != 0) {
      this.populate(searchIds);
    }

    return ids.stream().map(this::get).collect(Collectors.toList());
  }

  public void set(T item) {
    this.items.put(item.getId(), item);
  }
}
