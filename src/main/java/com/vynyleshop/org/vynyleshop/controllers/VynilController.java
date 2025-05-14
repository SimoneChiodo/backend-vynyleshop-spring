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

import com.vynyleshop.org.vynyleshop.model.Vynil;
import com.vynyleshop.org.vynyleshop.service.ArtistService;
import com.vynyleshop.org.vynyleshop.service.VynilService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/vynil")
public class VynilController {
  
  @Autowired
  private VynilService vynilService;
  @Autowired
  private ArtistService artistService;

  // GET
  @GetMapping()
  public String index(Model model) {
    model.addAttribute("vynilList", vynilService.findAll());
    return "vynil/index";
  }

  // SHOW
  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {
    Vynil vynil = vynilService.findById(id);
    model.addAttribute("vynil", vynil);
    return "vynil/show";
  }

  // CREATE
  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("vynil", new Vynil());
    model.addAttribute("isCreate", true);
    model.addAttribute("artistList", artistService.findAll()); 
    return "vynil/create-or-edit";
  }

  // STORE
  @PostMapping("/create")
  public String store(@Valid @ModelAttribute Vynil formVynil, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("vynil", formVynil);
      model.addAttribute("isCreate", true);
      model.addAttribute("artistList", artistService.findAll()); 
      return "vynil/create-or-edit";
    }

    vynilService.create(formVynil);
    return "redirect:/vynil";
  }

  // EDIT
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Vynil vynil = vynilService.findById(id);
    model.addAttribute("vynil", vynil);
    model.addAttribute("isCreate", false);
    model.addAttribute("artistList", artistService.findAll());
    return "vynil/create-or-edit";
  }

  // UPDATE
  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute Vynil formVynil, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("vynil", formVynil);
      model.addAttribute("isCreate", false);
      model.addAttribute("artistList", artistService.findAll());
      return "vynil/create-or-edit";
    }

    vynilService.update(formVynil);
    return "redirect:/vynil/" + formVynil.getId();
  }

  // DELETE
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    vynilService.delete(id);
    return "redirect:/vynil";
  }

  // SEARCH
  @GetMapping("/search")
  public String search(@RequestParam String name, Model model) {
    List<Vynil> vynils = vynilService.searchByName(name);
    model.addAttribute("vynilList", vynils);
    model.addAttribute("searchName", name);
    return "vynil/index";
  }
  
}
