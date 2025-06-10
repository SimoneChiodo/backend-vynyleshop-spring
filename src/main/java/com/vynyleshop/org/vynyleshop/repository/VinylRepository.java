package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vynyleshop.org.vynyleshop.model.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Integer>, JpaSpecificationExecutor<Vinyl> {
  // JpaRepository give us basic CRUD operations
  // JpaSpecificationExecutor allows us to create dynamic complex queries with specifications (filter by name, artist, ...)

  // To search by name, we create a custom method
  public List<Vinyl> findByNameContainingIgnoreCase(String name);

}
