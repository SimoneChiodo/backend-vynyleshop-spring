package com.vynyleshop.org.vynyleshop.controllers;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.vynyleshop.org.vynyleshop.model.Vinyl;
import com.vynyleshop.org.vynyleshop.model.VinylDTO;
import com.vynyleshop.org.vynyleshop.service.ImageService;
import com.vynyleshop.org.vynyleshop.service.VinylService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5000") // Allow access only to React app
@RequestMapping("/api/vinyl")
public class VinylRestController {
  
  @Autowired
  private VinylService vinylService;
  @Autowired
  private ImageService imageService;

  // GET N VINYLS WITH OPTIONAL FILTERS - VinylDTO
  @GetMapping("/filter")
  public ResponseEntity<List<VinylDTO>> getVinyls(
    @RequestParam Optional<Integer> lastId,
    @RequestParam int limit,
    @RequestParam Optional<String> name,
    @RequestParam Optional<String> artistName,
    @RequestParam Optional<Year> releaseYear,
    @RequestParam Optional<Integer> available,
    @RequestParam Optional<String> format
  ) {
    // The starting ID is optional, so the default value is 0 (from the start)
    int startFrom = lastId.orElse(0); 
    // Get the N vinyls with the optional filters
    List<Vinyl> vinyls = vinylService.getVinylsFiltered(
        startFrom, limit, name, artistName, releaseYear, available, format
    );
    // Assign the images
    List<VinylDTO> dtos = addImagesToVinyls(vinyls); 
    
    return ResponseEntity.ok(dtos);
  }

  // SHOW - VinylDTO
  @GetMapping("/{id}")
  public ResponseEntity<VinylDTO> get(@PathVariable Integer id) {
    Optional<Vinyl> result = vinylService.findById(id);

    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    if (result.isPresent()) {
        Vinyl vinyl = result.get();
        VinylDTO dto = addImagesToVinyl(vinyl);
        return new ResponseEntity<VinylDTO>(dto, HttpStatus.OK);
    }

    return new ResponseEntity<VinylDTO>(HttpStatus.NOT_FOUND);
  }

  // GET RANDOM VINYLS - VinylDTO
  @GetMapping("/randoms")
  public ResponseEntity<List<VinylDTO>> getRandomVinyls(
    @RequestParam int limit) {
    // Get all the vinyls
    List<Vinyl> vinylList = vinylService.findAll();

    // In case of error
    if (vinylList.isEmpty()) 
      return new ResponseEntity<List<VinylDTO>>(HttpStatus.NOT_FOUND);

    // If the limit is greater than the size of the vinyl list, set it to the size of the list
    limit = Math.min(limit, vinylList.size());

    // Shuffle the list
    Collections.shuffle(vinylList);

    // Take the first N vinyls
    List<Vinyl> randomVinyls = vinylList.stream().limit(limit).toList();

    // Assign the images
    List<VinylDTO> dto = addImagesToVinyls(randomVinyls);

    return new ResponseEntity<List<VinylDTO>>(dto, HttpStatus.OK);
  }

  // GET RANDOM VINYL - VinylDTO
  @GetMapping("/random")
  public ResponseEntity<VinylDTO> getRandomVinyl() {
    // Get all the vinyls
    List<Vinyl> vinylList = vinylService.findAll();
    // Take one random
    int randomIndex = (int) (Math.random() * vinylList.size());
    Vinyl randomVinyl = vinylList.get(randomIndex);

    // Assign the images and return the result
    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    if (randomVinyl != null) {
        VinylDTO dto = addImagesToVinyl(randomVinyl);
        return new ResponseEntity<VinylDTO>(dto, HttpStatus.OK);
    }

    return new ResponseEntity<VinylDTO>(HttpStatus.NOT_FOUND);
  }

  // Function to assign images to a vinyl list
  private List<VinylDTO> addImagesToVinyls(List<Vinyl> vinyls) {
    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    return vinyls.stream()
        .map(vinyl -> {
            List<String> images = imageService.getImagesFor("vinyl", vinyl.getName());
            return new VinylDTO(vinyl, images);
        })
        .toList();
  }

  private VinylDTO addImagesToVinyl(Vinyl vinyl) {
    List<String> images = imageService.getImagesFor("vinyl", vinyl.getName());
    return new VinylDTO(vinyl, images);
  }

}
