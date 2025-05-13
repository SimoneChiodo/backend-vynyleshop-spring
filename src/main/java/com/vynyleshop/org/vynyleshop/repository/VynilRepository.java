package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vynyleshop.org.vynyleshop.model.Vynil;

public interface VynilRepository extends JpaRepository<Vynil, Integer> {
  // JpaRepository fornisce già metodi per CRUD e ricerca

  // Per la Ricerca per nome, creiamo un metodo personalizzato
  public List<Vynil> findByNameContainingIgnoreCase(String name);
}
