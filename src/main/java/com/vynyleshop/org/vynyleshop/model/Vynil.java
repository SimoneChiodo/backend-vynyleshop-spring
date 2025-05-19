package com.vynyleshop.org.vynyleshop.model;

import java.time.Year;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vynil")
public class Vynil {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @ManyToOne
  @JoinColumn(name = "artist_id", referencedColumnName = "id")
  @JsonBackReference
  private Artist artist;

  private List<String> images;

  @NotNull(message = "Available cannot be null")
  private Integer available;

  @NotBlank(message = "Sideone cannot be blank")
  @Length(max = 500, message = "Sideone cannot exceed 500 characters")
  private String sideone;
  
  @NotBlank(message = "Sidetwo cannot be blank")
  @Length(max = 500, message = "Sidetwo cannot exceed 500 characters")
  private String sidetwo;
  
  @NotBlank(message = "Genre cannot be blank")
  private String genre; 

  @NotBlank(message = "Format cannot be blank")
  private String format;
  
  @NotBlank(message = "Color cannot be blank")
  private String color;
  
  @NotBlank(message = "Edition cannot be blank")
  private String edition;
  
  @NotBlank(message = "Code cannot be blank")
  private String code;
  
  @NotNull(message = "ReleaseYear cannot be null")
  private Year releaseYear;
  
  @NotBlank(message = "Label cannot be blank")
  private String label;
  
  @NotBlank(message = "Country cannot be blank")
  private String country;
  
  // Getters and Setters

  public String getSideone() {
    return this.sideone;
  }

  public void setSideone(String sideone) {
    this.sideone = sideone;
  }

  public String getSidetwo() {
    return this.sidetwo;
  }

  public void setSidetwo(String sidetwo) {
    this.sidetwo = sidetwo;
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

  public Artist getArtist() {
    return this.artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  public Integer getAvailable() {
    return this.available;
  }

  public void setAvailable(Integer available) {
    this.available = available;
  }

  public String getGenre() {
    return this.genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getFormat() {
    return this.format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getEdition() {
    return this.edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Year getReleaseYear() {
    return this.releaseYear;
  }

  public void setReleaseYear(Year releaseYear) {
    this.releaseYear = releaseYear;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  // Methods to convert tracklist to/from List<String>
  // Assuming tracklist is a JSON array string like: ["Track 1", "Track 2", "Track 3"]
  public List<String> getSideoneAsList() {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.sideone, new TypeReference<List<String>>() {});
    } catch (Exception e) {
        return List.of(); // Return an empty list in case of error
    }
  }
  public List<String> getSidetwoAsList() {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.sidetwo, new TypeReference<List<String>>() {});
    } catch (Exception e) {
        return List.of(); // Return an empty list in case of error
    }
  }

  public void setSideoneFromList(List<String> tracks) throws Exception {
      ObjectMapper objectMapper = new ObjectMapper();
      this.sideone = objectMapper.writeValueAsString(tracks);
  }
  public void setSidetwoFromList(List<String> tracks) throws Exception {
      ObjectMapper objectMapper = new ObjectMapper();
      this.sidetwo= objectMapper.writeValueAsString(tracks);
  }

}
