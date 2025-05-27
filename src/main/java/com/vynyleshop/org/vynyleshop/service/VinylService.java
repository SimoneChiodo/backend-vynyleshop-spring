package com.vynyleshop.org.vynyleshop.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

  //GET N VINYL WITH OPTIONAL FILTERS
  public List<Vinyl> getVinylsFiltered(
    int lastId,
    int limit,
    Optional<String> name,
    Optional<String> artistName,
    Optional<Year> releaseYear,
    Optional<Integer> available,
    Optional<String> format
  ) {
    //NOTE: Specification<Vinyl> is useful to create dynamic SQL-Query
    Specification<Vinyl> spec = (root, query, cb) -> {
      // A predicate asks a question where the answer is true or false
      // We use it to add filters to the Query
      List<Predicate> predicates = new ArrayList<>();
      
      // Only vinyl with higher ID
      predicates.add(cb.greaterThan(root.get("id"), lastId));
      
      // Only vinyl containing the name
      name.ifPresent(n -> 
        predicates.add(cb.like(cb.lower(root.get("name")), "%" + n.toLowerCase() + "%"))
      );

      // Only vinyl containing the artist name
      artistName.ifPresent(n ->
          predicates.add(cb.like(cb.lower(root.get("artist").get("name")), "%" + n.toLowerCase() + "%"))
      );

      // Only vinyl with the same release eyar
      releaseYear.ifPresent(year -> 
        predicates.add(cb.equal(root.get("releaseYear"), year))
      );

      // Only vinyl with the same state of availability
      available.ifPresent(av -> {
        // If available
        if (av == 1) 
          predicates.add(cb.greaterThan(root.get("available"), 0));
        // If not available
        else if (av == 0) 
          predicates.add(cb.equal(root.get("available"), 0));
      });

      // Only vinyl with the same format
      format.ifPresent(f -> 
        predicates.add(cb.equal(root.get("format"), f))
      );

      // Select the ascendent order by ID
      if(query != null)
        query.orderBy(cb.asc(root.get("id")));

      // Return the Query
      return cb.and(predicates.toArray(new Predicate[0]));
    };

    // Retrive only the N vinyls
    Pageable pageable = PageRequest.of(0, limit, Sort.by("id").ascending());

    // Do the Query
    return vinylRepository.findAll(spec, pageable).getContent();
  }

  //GET N RANDOM VYNILS
  public List<Vinyl> getRandomVinyls(int limit) {
    return vinylRepository.findRandomVinyls(limit);
  }

  //GET LAST CREATED N VYNILS
  public List<Vinyl> getLatestVinyls(int limit) {
    Pageable pageable = PageRequest.of(0, limit);
    return vinylRepository.findAllByOrderByIdDesc(pageable);
  }

}
