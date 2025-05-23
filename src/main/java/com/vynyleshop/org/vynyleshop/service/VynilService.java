package com.vynyleshop.org.vynyleshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vynyleshop.org.vynyleshop.model.Vynil;
import com.vynyleshop.org.vynyleshop.repository.VynilRepository;

@Service
public class VynilService {
  
  @Autowired
  private VynilRepository vynilRepository;

  //INDEX
  public List<Vynil> findAll() {
    return vynilRepository.findAll();
  }

  //SHOW
  public Optional<Vynil> findById(Integer id) {
    return vynilRepository.findById(id);
  }

  //CREATE
  public Vynil create(Vynil vynil) {
    return vynilRepository.save(vynil);
  }

  //UPDATE
  public Vynil update(Vynil vynil) {
    return vynilRepository.save(vynil);
  }

  //DELETE
  public void delete(Integer id) {
    vynilRepository.deleteById(id);
  }

  //SEARCH
  public List<Vynil> searchByName(String name) {
    return vynilRepository.findByNameContainingIgnoreCase(name);
  }

  //FILTERED INDEX
  public List<Vynil> filter(String name, String artist, Integer releaseYear, Boolean available, String format) {
    return vynilRepository.findAll().stream()
        .filter(v -> name == null || v.getName().toLowerCase().contains(name.toLowerCase()))
        .filter(v -> artist == null || v.getArtist().getName().toLowerCase().contains(artist.toLowerCase()))
        .filter(v -> releaseYear == null || v.getReleaseYear().getValue() == releaseYear.intValue())
        .filter(v -> available == null || (available ? v.getAvailable() > 0 : v.getAvailable() <= 0))
        .filter(v -> format == null || v.getFormat().equalsIgnoreCase(format))
        .toList();
  }

}
