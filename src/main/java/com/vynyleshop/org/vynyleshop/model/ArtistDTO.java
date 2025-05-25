package com.vynyleshop.org.vynyleshop.model;

import java.util.List;

// Adaptation of the Artist class to be used as a Data Transfer Object (DTO)
// This class will be transferred to the frontend via REST API
public class ArtistDTO {
  
  private Integer id;
  private String name;
  private String bio;
  private List<String> images;
  private List<VinylDTO> vinyls;

  // Costructors

  public ArtistDTO(Artist a, List<String> images, List<VinylDTO> vinyls) {
    this.id = a.getId();
    this.name = a.getName();
    this.bio = a.getBio();
    this.images = images;
    this.vinyls = vinyls;
  }

  // Getters and Setters

  public List<VinylDTO> getVinyls() {
    return this.vinyls;
  }

  public void setVinyls(List<VinylDTO> vinyls) {
    this.vinyls = vinyls;
  }

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

}
