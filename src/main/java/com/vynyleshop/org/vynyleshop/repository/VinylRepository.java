package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vynyleshop.org.vynyleshop.model.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Integer> {
  // JpaRepository fornisce già metodi per CRUD e ricerca

  // Per la Ricerca per nome, creiamo un metodo personalizzato
  public List<Vinyl> findByNameContainingIgnoreCase(String name);
}
