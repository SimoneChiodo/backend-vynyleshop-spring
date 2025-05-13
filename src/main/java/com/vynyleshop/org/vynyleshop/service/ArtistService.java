package com.vynyleshop.org.vynyleshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vynyleshop.org.vynyleshop.model.Artist;
import com.vynyleshop.org.vynyleshop.repository.ArtistRepository;

@Service
public class ArtistService {
  
  @Autowired
  private ArtistRepository artistRepository;

  //INDEX
  public List<Artist> findAll() {
    return artistRepository.findAll();
  }

  //SHOW
  public Artist findById(Integer id) {
    return artistRepository.findById(id).get();
  }

  //CREATE
  public Artist create(Artist artist) {
    return artistRepository.save(artist);
  }
  
  //UPDATE
  public Artist update(Artist artist) {
    return artistRepository.save(artist);
  }

  //DELETE
  public void delete(Integer id) {
    artistRepository.deleteById(id);
  }

  //SEARCH
  public List<Artist> searchByName(String name) {
    return artistRepository.findByNomeContainingIgnoreCase(name);
  }
}
