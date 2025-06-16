package com.vynyleshop.org.vynyleshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vynyleshop.org.vynyleshop.model.Artist;
import com.vynyleshop.org.vynyleshop.model.ArtistDTO;
import com.vynyleshop.org.vynyleshop.model.VinylDTO;
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

  // GET N ARTISTS WITH OPTIONAL FILTERS - ArtistDTO
  @GetMapping("/filter")
  public List<ArtistDTO> getArtists(
    @RequestParam Optional<Integer> lastId,
    @RequestParam int limit,
    @RequestParam Optional<String> name
  ) { 
    // The starting ID is optional, so the default value is 0 (from the start)
    int startFrom = lastId.orElse(0); 
    // Get the N artists with the optional filters
    List<Artist> artists = artistService.getArtistsFiltered(
      startFrom, limit, name
    );

    return addImagesToArtists(artists);
  }

  // SHOW - ArtistDTO
  @GetMapping("/{id}")
  public ResponseEntity<ArtistDTO> show(@PathVariable Integer id) {
    Optional<Artist> result = artistService.findById(id);
    
    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    if (result.isPresent()) {
      Artist artist = result.get();
      ArtistDTO dto = addImagesToArtist(artist);
      return new ResponseEntity<ArtistDTO>(dto, HttpStatus.OK);
    }
    
    return new ResponseEntity<ArtistDTO>(HttpStatus.NOT_FOUND);
  }

  // GET RANDOM ARTIST - ArtistDTO
  @GetMapping("/random")
  public ResponseEntity<ArtistDTO> getRandomArtist() {
    // Get all the artists
    List<Artist> artistsList = artistService.findAll();
    // Take one random
    int randomIndex = (int) (Math.random() * artistsList.size());
    Artist randomArtist = artistsList.get(randomIndex);

    // NOTE: I'm using the constructor of the DTO to change each vinyl object
    if (randomArtist != null) {
      ArtistDTO dto = addImagesToArtist(randomArtist);
      return new ResponseEntity<ArtistDTO>(dto, HttpStatus.OK);
    }
    
    return new ResponseEntity<ArtistDTO>(HttpStatus.NOT_FOUND);
  }

  // Methods -----

  private List<ArtistDTO> addImagesToArtists(List<Artist> artists) {
    return artists.stream()
      .map(artist -> {
        List<String> images = imageService.getImagesFor("artist", artist.getName());
        return new ArtistDTO(artist, images, artist.getVinyls() != null
          ? artist.getVinyls().stream()
            .map(v -> new VinylDTO(v, imageService.getImagesFor("vinyl", v.getName())))
            .toList()
          : List.of());
      })
      .toList();
  }
  
  private ArtistDTO addImagesToArtist(Artist artist) {
    List<String> artistImages = imageService.getImagesFor("artist", artist.getName());

    List<VinylDTO> vinylDTOs = artist.getVinyls() != null
      ? artist.getVinyls().stream()
        .map(v -> {
          List<String> vinylImages = imageService.getImagesFor("vinyl", v.getName());
          return new VinylDTO(v, vinylImages);
        })
        .toList()
      : List.of();

    return new ArtistDTO(artist, artistImages, vinylDTOs);
  }
  
}
