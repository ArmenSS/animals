package com.animal.animalservice.security;

import com.animal.animalservice.dto.response.UserResponse;
import com.animal.animalservice.entity.User;
import com.animal.animalservice.mapper.UserMapper;
import com.animal.animalservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CurrentUserDetailServiceImpl implements UserDetailsService {

  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserResponse byEmail = userService.findByEmail(email);
    User user = userMapper.toEntity(byEmail);
    if (user == null) {
      log.error(this.getClass().getName(), UsernameNotFoundException.class);
      throw new UsernameNotFoundException("Username not found");
    }
    return new CurrentUser(user);
  }
}
