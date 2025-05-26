package com.vynyleshop.org.vynyleshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vynyleshop.org.vynyleshop.model.Vinyl;
import com.vynyleshop.org.vynyleshop.repository.VinylRepository;

@Service
public class VinylService {
  
  @Autowired
  private VinylRepository vinylRepository;

  //INDEX
  public List<Vinyl> findAll() {
    return vinylRepository.findAll();
  }

  //SHOW
  public Optional<Vinyl> findById(Integer id) {
    return vinylRepository.findById(id);
  }

  //CREATE
  public Vinyl create(Vinyl Vinyl) {
    return vinylRepository.save(Vinyl);
  }

  //UPDATE
  public Vinyl update(Vinyl Vinyl) {
    return vinylRepository.save(Vinyl);
  }

  //DELETE
  public void delete(Integer id) {
    vinylRepository.deleteById(id);
  }

  //SEARCH
  public List<Vinyl> searchByName(String name) {
    return vinylRepository.findByNameContainingIgnoreCase(name);
  }

  //FILTERED INDEX
  public List<Vinyl> filter(String name, String artist, Integer releaseYear, Boolean available, String format) {
    return vinylRepository.findAll().stream()
        .filter(v -> name == null || v.getName().toLowerCase().contains(name.toLowerCase()))
        .filter(v -> artist == null || v.getArtist().getName().toLowerCase().contains(artist.toLowerCase()))
        .filter(v -> releaseYear == null || v.getReleaseYear().getValue() == releaseYear.intValue())
        .filter(v -> available == null || (available ? v.getAvailable() > 0 : v.getAvailable() <= 0))
        .filter(v -> format == null || v.getFormat().equalsIgnoreCase(format))
        .toList();
  }

  // PAGE INDEX
  // Returns the first N vinyls (initial page), using ascending ID order
  public List<Vinyl> findFirstN(int size) {
    return vinylRepository.findByOrderByIdAsc(PageRequest.of(0, size));
  }

  // Returns the next N vinyls after a given ID, for efficient keyset pagination
  public List<Vinyl> findNextN(Long afterId, int size) {
    return vinylRepository.findByIdGreaterThanOrderByIdAsc(afterId, PageRequest.of(0, size));
  }

}
