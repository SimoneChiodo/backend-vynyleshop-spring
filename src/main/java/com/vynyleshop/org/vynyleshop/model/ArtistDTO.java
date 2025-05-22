package com.vynyleshop.org.vynyleshop.model;

import java.util.List;

// Adaptation of the Artist class to be used as a Data Transfer Object (DTO)
// This class will be transferred to the frontend via REST API
public class ArtistDTO {
  
  private Integer id;
  private String name;
  private String bio;
  private List<String> images;
  private List<String> vynils;

  // Costructors

  public ArtistDTO(Artist a) {
    this.id = a.getId();
    this.name = a.getName();
    this.bio = a.getBio();
    this.images = a.getImages();
    this.vynils = a.getVynilsAsList();
  }

  public ArtistDTO(Artist a, List<String> images) {
    this.id = a.getId();
    this.name = a.getName();
    this.bio = a.getBio();
    this.images = images;
    this.vynils = a.getVynilsAsList();
  }


  // Getters and Setters

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBio() {
    return this.bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public List<String> getImages() {
    return this.images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  public List<String> getVynils() {
    return this.vynils;
  }

  public void setVynils(List<String> vynils) {
    this.vynils = vynils;
  }


}
