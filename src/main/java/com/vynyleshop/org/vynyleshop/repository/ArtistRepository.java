package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vynyleshop.org.vynyleshop.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
  // JpaRepository fornisce già metodi per CRUD e ricerca

  // Per la Ricerca per nome, creiamo un metodo personalizzato
  public List<Artist> findByNomeContainingIgnoreCase(String name);
}
