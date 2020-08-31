package com.fdograph.demo.api.entities;

public enum CharacterStatus {
  ALIVE("Alive"),
  DEAD("Dead"),
  UNKNOWN("Unknown");

  private final String name;

  CharacterStatus(String s) {
    name = s;
  }

  public boolean equalsName(String otherName) {
    return name.equals(otherName);
  }

  public String toString() {
    return this.name;
  }
}
