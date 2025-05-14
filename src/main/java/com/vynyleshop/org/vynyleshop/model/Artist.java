package com.vynyleshop.org.vynyleshop.model;


import java.util.List;

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

  @OneToMany(mappedBy = "artist", cascade = { CascadeType.REMOVE })
  @JsonManagedReference
  private List<Vynil> vynils;

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

  public List<Vynil> getVynils() {
    return this.vynils;
  }

  public void setVynils(List<Vynil> vynils) {
    this.vynils = vynils;
  }

  public Integer getNumberOfVynils() {
    return this.vynils.size();
  }

}
