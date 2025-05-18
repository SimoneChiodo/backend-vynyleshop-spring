package com.vynyleshop.org.vynyleshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vynyleshop.org.vynyleshop.model.Artist;
import com.vynyleshop.org.vynyleshop.service.ArtistService;
import com.vynyleshop.org.vynyleshop.service.ImageService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/artist")
public class ArtistController {
  
  @Autowired
  private ArtistService artistService;
  @Autowired
  private ImageService imageService;

  // GET
  @GetMapping()
  public String index(Model model) {
    model.addAttribute("artistList", artistService.findAll());
    return "artist/index";
  }

  // SHOW
  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {
    Artist artist = artistService.findById(id).get();
  
    // Images List
    List<String> images = imageService.getImagesFor("artist", artist.getName());
    
    artist.setImages(images);
    model.addAttribute("artist", artist);
    return "artist/show";
  }

  // CREATE
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("artist", new Artist());
    model.addAttribute("isCreate", true); 
    return "artist/create-or-edit";
  }

  // STORE
  @PostMapping("/create")
  public String store(@Valid @ModelAttribute Artist formArtist, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("artist", formArtist);
    model.addAttribute("isCreate", true); 
      return "artist/create-or-edit";
    }

    artistService.create(formArtist);
    return "redirect:/artist";
  }

  // EDIT
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Artist artist = artistService.findById(id).get();
    model.addAttribute("artist", artist);
    model.addAttribute("isCreate", false); 
    return "artist/create-or-edit";
  }

  // UPDATE
  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute Artist formArtist, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("artist", formArtist);
      model.addAttribute("isCreate", false); 
      return "artist/create-or-edit";
    }

    artistService.update(formArtist);
    return "redirect:/artist/" + formArtist.getId();
  }

  // DELETE
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    artistService.delete(id);
    return "redirect:/artist";
  }

  // SEARCH
  @GetMapping("/search")
  public String search(@RequestParam String name, Model model) {
    List<Artist> artists = artistService.searchByName(name);
    model.addAttribute("artists", artists);
    return "artist/index";
  }
  
}
