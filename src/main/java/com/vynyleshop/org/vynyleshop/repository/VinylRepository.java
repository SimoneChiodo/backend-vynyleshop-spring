package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vynyleshop.org.vynyleshop.model.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Integer> {
  // JpaRepository give us basic CRUD operations

  // To search by name, we create a custom method
  public List<Vinyl> findByNameContainingIgnoreCase(String name);

  // To retrieve the first N vinyls ordered by id ascending (used for the initial page)
  List<Vinyl> findByOrderByIdAsc(Pageable pageable);

  // To search for any vinyl with id greater than a specific one, ordered by id ascending (used for the other pages)
  List<Vinyl> findByIdGreaterThanOrderByIdAsc(Long id, Pageable pageable);
  
}
