package com.vynyleshop.org.vynyleshop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vynyleshop.org.vynyleshop.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
  // JpaRepository give us basic CRUD operations

  // To search by name, we create a custom method
  public List<Artist> findByNameContainingIgnoreCase(String name);

  // Retrieve N artist
  List<Artist> findByIdGreaterThanOrderByIdAsc(Integer lastId, Pageable pageable);

  // Retrieve N artist, filtered by name
  List<Artist> findByNameContainingIgnoreCaseAndIdGreaterThanOrderByIdAsc(String name, Integer lastId, Pageable pageable);

  // Retrieve N random artists
  @Query(value = "SELECT * FROM artist ORDER BY RAND() LIMIT :limit", nativeQuery = true)
  List<Artist> findRandomArtists(@Param("limit") int limit);

}
