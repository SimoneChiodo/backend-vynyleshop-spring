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

import com.vynyleshop.org.vynyleshop.model.Vinyl;
import com.vynyleshop.org.vynyleshop.service.ArtistService;
import com.vynyleshop.org.vynyleshop.service.ImageService;
import com.vynyleshop.org.vynyleshop.service.VinylService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/vinyl")
public class VinylController {
  
  @Autowired
  private VinylService vinylService;
  @Autowired
  private ArtistService artistService;
  @Autowired
  private ImageService imageService;

  // GET
  @GetMapping()
  public String index(Model model) {
    model.addAttribute("vinylList", vinylService.findAll());
    return "vinyl/index";
  }

  // SHOW
  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {
    Vinyl vinyl = vinylService.findById(id).get();
  
    // Images List
    List<String> images = imageService.getImagesFor("vinyl", vinyl.getName());

    vinyl.setImages(images);
    model.addAttribute("vinyl", vinyl);
    
    return "vinyl/show";
  }

  // CREATE
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("vinyl", new Vinyl());
    model.addAttribute("isCreate", true);
    model.addAttribute("artistList", artistService.findAll()); 
    return "vinyl/create-or-edit";
  }

  // STORE
  @PostMapping("/create")
  public String store(@Valid @ModelAttribute Vinyl formVinyl, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("vinyl", formVinyl);
      model.addAttribute("isCreate", true);
      model.addAttribute("artistList", artistService.findAll()); 
      return "vinyl/create-or-edit";
    }

    vinylService.create(formVinyl);
    return "redirect:/vinyl";
  }

  // EDIT
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Vinyl vinyl = vinylService.findById(id).get();
    model.addAttribute("vinyl", vinyl);
    model.addAttribute("isCreate", false);
    model.addAttribute("artistList", artistService.findAll());
    return "vinyl/create-or-edit";
  }

  // UPDATE
  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute Vinyl formVinyl, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("vinyl", formVinyl);
      model.addAttribute("isCreate", false);
      model.addAttribute("artistList", artistService.findAll());
      return "vinyl/create-or-edit";
    }

    vinylService.update(formVinyl);
    return "redirect:/vinyl/" + formVinyl.getId();
  }

  // DELETE
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    vinylService.delete(id);
    return "redirect:/vinyl";
  }

  // SEARCH
  @GetMapping("/search")
  public String search(@RequestParam String name, Model model) {
    List<Vinyl> vinyls = vinylService.searchByName(name);
    model.addAttribute("vinylList", vinyls);
    model.addAttribute("searchName", name);
    return "vinyl/index";
  }
  
}
