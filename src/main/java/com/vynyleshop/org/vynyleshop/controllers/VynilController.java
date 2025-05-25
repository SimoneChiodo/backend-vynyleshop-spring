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
import com.vynyleshop.org.vynyleshop.service.VynilService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vinyl")
public class VynilController {
  
  @Autowired
  private VynilService vynilService;
  @Autowired
  private ArtistService artistService;
  @Autowired
  private ImageService imageService;

  // GET
  @GetMapping()
  public String index(Model model) {
    model.addAttribute("vynilList", vynilService.findAll());
    return "Vinyl/index";
  }

  // SHOW
  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {
    Vinyl Vinyl = vynilService.findById(id).get();
  
    // Images List
    List<String> images = imageService.getImagesFor("Vinyl", Vinyl.getName());

    Vinyl.setImages(images);
    model.addAttribute("Vinyl", Vinyl);
    
    return "Vinyl/show";
  }

  // CREATE
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("Vinyl", new Vinyl());
    model.addAttribute("isCreate", true);
    model.addAttribute("artistList", artistService.findAll()); 
    return "Vinyl/create-or-edit";
  }

  // STORE
  @PostMapping("/create")
  public String store(@Valid @ModelAttribute Vinyl formVynil, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("Vinyl", formVynil);
      model.addAttribute("isCreate", true);
      model.addAttribute("artistList", artistService.findAll()); 
      return "Vinyl/create-or-edit";
    }

    vynilService.create(formVynil);
    return "redirect:/Vinyl";
  }

  // EDIT
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Vinyl Vinyl = vynilService.findById(id).get();
    model.addAttribute("Vinyl", Vinyl);
    model.addAttribute("isCreate", false);
    model.addAttribute("artistList", artistService.findAll());
    return "Vinyl/create-or-edit";
  }

  // UPDATE
  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute Vinyl formVynil, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("Vinyl", formVynil);
      model.addAttribute("isCreate", false);
      model.addAttribute("artistList", artistService.findAll());
      return "Vinyl/create-or-edit";
    }

    vynilService.update(formVynil);
    return "redirect:/Vinyl/" + formVynil.getId();
  }

  // DELETE
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    vynilService.delete(id);
    return "redirect:/Vinyl";
  }

  // SEARCH
  @GetMapping("/search")
  public String search(@RequestParam String name, Model model) {
    List<Vinyl> Vinyls = vynilService.searchByName(name);
    model.addAttribute("vynilList", Vinyls);
    model.addAttribute("searchName", name);
    return "Vinyl/index";
  }
  
}
