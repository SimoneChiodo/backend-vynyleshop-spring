package com.vynyleshop.org.vynyleshop.model;


import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "artist")
public class Artist {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  private List<String> images;

  @NotBlank(message = "Bio cannot be blank")
  @Length(max = 500, message = "Bio cannot exceed 500 characters")
  private String bio;

  @OneToMany(mappedBy = "artist", cascade = { CascadeType.REMOVE })
  @JsonManagedReference
  private List<Vinyl> Vinyls;

  // Getters and Setters

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

  public List<Vinyl> getVinyls() {
    return this.Vinyls;
  }

  public void setVinyls(List<Vinyl> Vinyls) {
    this.Vinyls = Vinyls;
  }

  // Functions

  public Integer getNumberOfVinyls() {
    return this.Vinyls.size();
  }

}
