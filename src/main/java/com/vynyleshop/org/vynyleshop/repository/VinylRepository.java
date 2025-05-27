package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import com.vynyleshop.org.vynyleshop.model.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Integer>, JpaSpecificationExecutor<Vinyl> {
  // JpaRepository give us basic CRUD operations
  // JpaSpecificationExecutor allows us to create dynamic complex queries with specifications (filter by name, artist, ...)

  // To search by name, we create a custom method
  public List<Vinyl> findByNameContainingIgnoreCase(String name);

  // Retrieve N random vinyls
  @Query(value = "SELECT * FROM vinyl ORDER BY RAND() LIMIT :limit", nativeQuery = true)
  List<Vinyl> findRandomVinyls(@Param("limit") int limit);

  // Retrive all vinyls ordered by id descending
  List<Vinyl> findAllByOrderByIdDesc(Pageable pageable);

}
