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
import com.vynyleshop.org.vynyleshop.model.ArtistDTO;
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
  public List<ArtistDTO> index(Model model) {
    List<Artist> artists = artistService.findAll();

    // NOTE: .map(ArtistDTO::new) aplly the constructor of the DTO to each Artist object
    return artists.stream()
        .map(artist -> {
            List<String> images = imageService.getImagesFor("artist", artist.getName());
            return new ArtistDTO(artist, images);
        })
        .toList();
  }

  // SHOW
  @GetMapping("/{id}")
  public ResponseEntity<ArtistDTO> show(@PathVariable Integer id) {
    Optional<Artist> result = artistService.findById(id);
    
    if (result.isPresent()) {
        Artist artist = result.get();
        List<String> images = imageService.getImagesFor("artist", artist.getName());
        ArtistDTO dto = new ArtistDTO(artist, images);
        return new ResponseEntity<ArtistDTO>(dto, HttpStatus.OK);
    }
      
    return new ResponseEntity<ArtistDTO>(HttpStatus.NOT_FOUND);
  }

  // SEARCH
  @GetMapping("/search")
  public List<ArtistDTO> search(@RequestParam String name) {
    List<Artist> artists = artistService.searchByName(name);

    // NOTE: .map(ArtistDTO::new) aplly the constructor of the DTO to each Artist object
    return artists.stream()
        .map(artist -> {
            List<String> images = imageService.getImagesFor("artist", artist.getName());
            return new ArtistDTO(artist, images);
        })
        .toList();
  }
  
}
