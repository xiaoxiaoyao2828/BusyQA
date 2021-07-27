package com.crmbackend.security.securityServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crmbackend.allService.userService.repo.UserRepository;
import com.crmbackend.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.getUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username " + username));

		return UserDetailsImpl.build(user);
	}

}
