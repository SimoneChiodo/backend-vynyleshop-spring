package com.vynyleshop.org.vynyleshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public Optional<Artist> findById(Integer id) {
    return artistRepository.findById(id);
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
    // If name is empty
    if (name == null || name.isBlank()) 
      return artistRepository.findAll(); 
    
    // Search by name
    return artistRepository.findByNameContainingIgnoreCase(name);
  }

  //GET N ARTIST WITH OPTIONAL FILTERS
  public List<Artist> getArtistsFiltered(int startFrom, int limit, Optional<String> name) {
    // Retrieve just N artist
    Pageable pageable = PageRequest.of(0, limit);

    // If i filter by name
    if (name.isPresent() && !name.get().isBlank()) 
      return artistRepository.findByNameContainingIgnoreCaseAndIdGreaterThanOrderByIdAsc(
          name.get(), startFrom, pageable
      );
    // If i don't need to filter
    else 
      return artistRepository.findByIdGreaterThanOrderByIdAsc(
          startFrom, pageable
      );
  }

}