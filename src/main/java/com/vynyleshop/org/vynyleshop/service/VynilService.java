package com.vynyleshop.org.vynyleshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vynyleshop.org.vynyleshop.model.Vynil;
import com.vynyleshop.org.vynyleshop.repository.VynilRepository;

@Service
public class VynilService {
  
  @Autowired
  private VynilRepository vynilRepository;

  //INDEX
  public List<Vynil> findAll() {
    return vynilRepository.findAll();
  }

  //SHOW
  public Vynil findById(Integer id) {
    return vynilRepository.findById(id).get();
  }

  //CREATE
  public Vynil create(Vynil vynil) {
    return vynilRepository.save(vynil);
  }

  //UPDATE
  public Vynil update(Vynil vynil) {
    return vynilRepository.save(vynil);
  }

  //DELETE
  public void delete(Integer id) {
    vynilRepository.deleteById(id);
  }

  //SEARCH
  public List<Vynil> searchByName(String name) {
    return vynilRepository.findByNameContainingIgnoreCase(name);
  }

}
