package com.vynyleshop.org.vynyleshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

  // GET
  @GetMapping()
  public String index() {
    return "home/index";
  }
  
}
