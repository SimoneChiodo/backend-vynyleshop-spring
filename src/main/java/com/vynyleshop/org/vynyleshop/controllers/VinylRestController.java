package com.vynyleshop.org.vynyleshop.controllers;

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

  // GET - VinylDTO
  @GetMapping
  public List<VinylDTO> index() {
    List<Vinyl> vinyls = vinylService.findAll();

    return assignImagesToVinyls(vinyls);
  }

  // FILTERED GET - VinylDTO
  @GetMapping("/filter")
  public List<VinylDTO> filter(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String artist,
    @RequestParam(required = false) Integer releaseYear,
    @RequestParam(required = false) Boolean available,
    @RequestParam(required = false) String format
  ) {
    List<Vinyl> vinyls = vinylService.filter(name, artist, releaseYear, available, format);

    return assignImagesToVinyls(vinyls);
  }

  // PAGE GET - VinylDTO
  @GetMapping("/page")
  public List<VinylDTO> pagination(
      @RequestParam(required = false) Long afterId,
      @RequestParam(defaultValue = "20") int size) {
      List<Vinyl> vinyls;

      if (afterId == null) // First page
          vinyls = vinylService.findFirstN(size);
      else // Next pages
          vinyls = vinylService.findNextN(afterId, size);

      return assignImagesToVinyls(vinyls);
  }


  // SHOW - VinylDTO
  @GetMapping("/{id}")
  public ResponseEntity<VinylDTO> get(@PathVariable Integer id) {
    Optional<Vinyl> result = vinylService.findById(id);

    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    if (result.isPresent()) {
        Vinyl vinyl = result.get();
        List<String> images = imageService.getImagesFor("vinyl", vinyl.getName());
        VinylDTO dto = new VinylDTO(vinyl, images);
        return new ResponseEntity<VinylDTO>(dto, HttpStatus.OK);
    }

    return new ResponseEntity<VinylDTO>(HttpStatus.NOT_FOUND);
  }
  
  // SEARCH - VinylDTO
  @GetMapping("/search")
  public List<VinylDTO> search(@RequestParam String name) {
    List<Vinyl> vinyls = vinylService.searchByName(name);

    return assignImagesToVinyls(vinyls);
  }
  

  // Function to assign images to a vinyl list
  private List<VinylDTO> assignImagesToVinyls(List<Vinyl> vinyls) {
    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    return vinyls.stream()
        .map(vinyl -> {
            List<String> images = imageService.getImagesFor("vinyl", vinyl.getName());
            return new VinylDTO(vinyl, images);
        })
        .toList();
  }

}
