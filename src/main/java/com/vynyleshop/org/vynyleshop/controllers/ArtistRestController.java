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

        return artists.stream()
        .map(artist -> {
                // Assign the images to the artist
                List<String> artistImages = imageService.getImagesFor("artist", artist.getName());

                // Assign the images to the vinyls of the artist
                List<VinylDTO> vinylDTOs = artist.getVinyls() != null
                    ? artist.getVinyls().stream()
                        .map(v -> new VinylDTO(v, imageService.getImagesFor("vinyl", v.getName())))
                        .toList()
                    : List.of();

                return new ArtistDTO(artist, artistImages, vinylDTOs);
            })
            .toList();
    }

    // RANDOM N ARTISTS - ArtistDTO
    @GetMapping("/random")
    public List<ArtistDTO> getRandomArtists(@RequestParam int limit) {
        // Get the N random artists
        List<Artist> artists = artistService.getRandomArtists(limit);

        return artists.stream()
            .map(artist -> {
                // Assign the images to the artist
                List<String> artistImages = imageService.getImagesFor("artist", artist.getName());

                // Assign the images to the vinyls of the artist
                List<VinylDTO> vinylDTOs = artist.getVinyls() != null
                    ? artist.getVinyls().stream()
                        .map(v -> new VinylDTO(v, imageService.getImagesFor("vinyl", v.getName())))
                        .toList()
                    : List.of();

                return new ArtistDTO(artist, artistImages, vinylDTOs);
            })
            .toList();
    }

    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> show(@PathVariable Integer id) {
        Optional<Artist> result = artistService.findById(id);
        
        // NOTE: I'm using the constructor of the DTO to change each vinyl object
        if (result.isPresent()) {
        Artist artist = result.get();
        List<String> artistImages = imageService.getImagesFor("artist", artist.getName());

        List<VinylDTO> vinylDTOs = artist.getVinyls() != null
            ? artist.getVinyls().stream()
                .map(v -> {
                    List<String> vinylImages = imageService.getImagesFor("vinyl", v.getName());
                    return new VinylDTO(v, vinylImages);
                })
                .toList()
            : List.of();

        ArtistDTO dto = new ArtistDTO(artist, artistImages, vinylDTOs);
        return new ResponseEntity<ArtistDTO>(dto, HttpStatus.OK);
    }
        
        return new ResponseEntity<ArtistDTO>(HttpStatus.NOT_FOUND);
    }
    
}
