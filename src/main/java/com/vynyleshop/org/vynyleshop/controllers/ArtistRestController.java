package com.vynyleshop.org.vynyleshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vynyleshop.org.vynyleshop.model.Artist;
import com.vynyleshop.org.vynyleshop.service.ArtistService;
import com.vynyleshop.org.vynyleshop.service.ImageService;

@RestController
@CrossOrigin(origins = "http://localhost:5000") // Allow access only to React app
@RequestMapping("/api/artist")
public class ArtistRestController {
  
  @Autowired
  private ArtistService artistService;
  @Autowired
  private ImageService imageService;

  // GET
  @GetMapping()
  public List<Artist> index(Model model) {
    List<Artist> artists = artistService.findAll();

    for (Artist artist : artists) {
      List<String> images = imageService.getImagesFor("artist", artist.getName());
      artist.setImages(images);
    }

    return artists;
  }

  // SHOW
  @GetMapping("/{id}")
  public ResponseEntity<Artist> show(@PathVariable Integer id) {
    Optional<Artist> result = artistService.findById(id);
    
    if(result.isPresent()) {
      Artist artist = result.get();

      List<String> images = imageService.getImagesFor("artist", artist.getName());

      artist.setImages(images);
      return new ResponseEntity<Artist>(artist, HttpStatus.OK);
    }
      
    return new ResponseEntity<Artist>(HttpStatus.NOT_FOUND);
  }

  // SEARCH
  @GetMapping("/search")
  public List<Artist> search(@RequestParam String name) {
    List<Artist> artists = artistService.searchByName(name);

    for (Artist artist : artists) {
      List<String> images = imageService.getImagesFor("artist", artist.getName());
      artist.setImages(images);
    }

    return artists;
  }
  
}
