package com.fdograph.demo.api.entities;

public enum CharacterGender {
  FEMALE("Female"),
  MALE("Male"),
  GENDERLESS("Genderless"),
  UNKNOWN("Unknown");

  private final String name;

  CharacterGender(String s) {
    name = s;
  }

  public boolean equalsName(String otherName) {
    return name.equals(otherName);
  }

  public String toString() {
    return this.name;
  }
}
