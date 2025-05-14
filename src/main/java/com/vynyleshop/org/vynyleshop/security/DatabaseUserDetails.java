package com.vynyleshop.org.vynyleshop.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vynyleshop.org.vynyleshop.model.Role;
import com.vynyleshop.org.vynyleshop.model.User;

public class DatabaseUserDetails implements UserDetails {

  private final Integer id;
  private final String username;
  private final String password;
  private final Set<GrantedAuthority> authorities;

  public DatabaseUserDetails(User user){
    this.id = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
    
    authorities = new HashSet<GrantedAuthority>();
    for(Role role : user.getRoles()){
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
  }

  // Methdos from UserDetails
  @Override
  public Set<GrantedAuthority> getAuthorities(){
    return this.authorities;
  }

  @Override
  public String getPassword(){
    return this.password;
  }

  @Override
  public String getUsername(){
    return this.username;
  }
  
}
