package com.vynyleshop.org.vynyleshop.controllers;

import java.util.List;
import java.util.Optional;

import com.vynyleshop.org.vynyleshop.model.Vynil;
import com.vynyleshop.org.vynyleshop.model.VynilDTO;
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
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5000") // Allow access only to React app
@RequestMapping("/api/vynil")
public class VynilRestController {
  
  @Autowired
  private VynilService vynilService;

  // GET - Vynil
  // @GetMapping()
  // public List<Vynil> index(Model model) {
  //   List<Vynil> vynils = vynilService.findAll(); 
  //   return vynils;
  // }
  // GET - VynilDTO
  @GetMapping
  public List<VynilDTO> index() {
    List<Vynil> vynils = vynilService.findAll();
    return vynils.stream().map(VynilDTO::new).toList();
    // NOTE: .map(VynilDTO::new) aplly the constructor of the DTO to each Vynil object
  }


  // SHOW - Vynil
  // @GetMapping("/{id}")
  // public ResponseEntity<Vynil> get(@PathVariable Integer id) {
  //   Optional<Vynil> result = vynilService.findById(id);
    
  //   if(result.isPresent())
  //     return new ResponseEntity<Vynil>(result.get(), HttpStatus.OK);
      
  //   return new ResponseEntity<Vynil>(HttpStatus.NOT_FOUND);
  // }
  // SHOW - VynilDTO
  @GetMapping("/{id}")
  public ResponseEntity<VynilDTO> get(@PathVariable Integer id) {
    Optional<Vynil> result = vynilService.findById(id);

    if (result.isPresent()) {
        VynilDTO dto = new VynilDTO(result.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  // STORE
  // @PostMapping("/create")
  // public ResponseEntity<Vynil> store(@RequestBody Vynil vynil) {
  //   return new ResponseEntity<Vynil>(vynilService.create(vynil), HttpStatus.OK); 
  // }
  
  // UPDATE
  // @PutMapping("/edit/{id}")
  // public ResponseEntity<Vynil> update(@RequestBody Vynil vynil, @PathVariable Integer id) {
  //   if(vynilService.findById(id).isEmpty())
  //     return new ResponseEntity<Vynil>(HttpStatus.NOT_FOUND); 
    
  //   vynil.setId(id); 
  //   return new ResponseEntity<Vynil>(vynilService.update(vynil), HttpStatus.OK); 
  // }
  
  // DELETE
  // @DeleteMapping("/delete/{id}")
  // public ResponseEntity<Vynil> delete(@PathVariable Integer id) {
  //   vynilService.delete(id);
  //   return new ResponseEntity<Vynil>(HttpStatus.OK);
  // }
  
  // SEARCH - Vynil
  // @GetMapping("/search")
  // public List<Vynil> search(@RequestParam String name) {
  //   List<Vynil> vynils = vynilService.searchByName(name); 
  //   return vynils;
  // }
  // SEARCH - VynilDTO
  @GetMapping("/search")
  public List<VynilDTO> search(@RequestParam String name) {
    List<Vynil> vynils = vynilService.searchByName(name);
    return vynils.stream().map(VynilDTO::new).toList();
  }

}
