package com.hiwotab.springboot1920.services;

import com.hiwotab.springboot1920.model.NRole;
import com.hiwotab.springboot1920.model.NUser;
import com.hiwotab.springboot1920.repositories.NUserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

  private NUserRepo userRepo;

  public SSUserDetailsService(NUserRepo uRepo){
      this.userRepo=uRepo;
  }
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
      try {
          NUser user = userRepo.findByUsername(username);
          if (user == null) {
              System.out.println("User not Found with the provided username" + user.toString());
              return null;
          }
          System.out.println("user from username" + user.toString());
          return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                  getAuthorities(user));
      }catch(Exception e){
          throw new UsernameNotFoundException("User not found");
      }
  }
      private Set<GrantedAuthority> getAuthorities(NUser user) {
          Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
          for (NRole role : user.getRoles()) {
              GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getUrole());
              authorities.add(grantedAuthority);

          }
          System.out.println("User Authorities are" + authorities.toString());
          return authorities;
      }
}
