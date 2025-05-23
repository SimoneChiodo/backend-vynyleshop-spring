package com.vynyleshop.org.vynyleshop.controllers;

import java.util.List;
import java.util.Optional;

import com.vynyleshop.org.vynyleshop.model.Vynil;
import com.vynyleshop.org.vynyleshop.model.VynilDTO;
import com.vynyleshop.org.vynyleshop.service.ImageService;
import com.vynyleshop.org.vynyleshop.service.VynilService;

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
@RequestMapping("/api/vynil")
public class VynilRestController {
  
  @Autowired
  private VynilService vynilService;
  @Autowired
  private ImageService imageService;

  // GET - VynilDTO
  @GetMapping
  public List<VynilDTO> index() {
    List<Vynil> vynils = vynilService.findAll();

    // NOTE: I'm using the constructor of the DTO to change each Vynil object
    return vynils.stream()
        .map(vynil -> {
            List<String> images = imageService.getImagesFor("vynil", vynil.getName());
            return new VynilDTO(vynil, images);
        })
        .toList();
  }

  // FILTERED GET - VynilDTO
  @GetMapping("/filter")
  public List<VynilDTO> filter(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String artist,
    @RequestParam(required = false) Integer releaseYear,
    @RequestParam(required = false) Boolean available,
    @RequestParam(required = false) String format
  ) {
    List<Vynil> vynils = vynilService.filter(name, artist, releaseYear, available, format);

    return vynils.stream()
        .map(vynil -> {
            List<String> images = imageService.getImagesFor("vynil", vynil.getName());
            return new VynilDTO(vynil, images);
        })
        .toList();
  }

  // SHOW - VynilDTO
  @GetMapping("/{id}")
  public ResponseEntity<VynilDTO> get(@PathVariable Integer id) {
    Optional<Vynil> result = vynilService.findById(id);

    // NOTE: I'm using the constructor of the DTO to change each Vynil object
    if (result.isPresent()) {
        Vynil vynil = result.get();
        List<String> images = imageService.getImagesFor("vynil", vynil.getName());
        VynilDTO dto = new VynilDTO(vynil, images);
        return new ResponseEntity<VynilDTO>(dto, HttpStatus.OK);
    }

    return new ResponseEntity<VynilDTO>(HttpStatus.NOT_FOUND);
  }
  
  // SEARCH - VynilDTO
  @GetMapping("/search")
  public List<VynilDTO> search(@RequestParam String name) {
    List<Vynil> vynils = vynilService.searchByName(name);

    // NOTE: I'm using the constructor of the DTO to change each Vynil object
    return vynils.stream()
        .map(vynil -> {
            List<String> images = imageService.getImagesFor("vynil", vynil.getName());
            return new VynilDTO(vynil, images);
        })
        .toList();
  }

}
