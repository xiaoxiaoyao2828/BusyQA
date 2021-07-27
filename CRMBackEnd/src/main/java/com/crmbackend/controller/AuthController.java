package com.crmbackend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crmbackend.allService.userService.repo.RoleRepository;
import com.crmbackend.allService.userService.repo.UserRepository;
import com.crmbackend.entity.Role;
import com.crmbackend.entity.User;
import com.crmbackend.payLoad.request.LoginRequest;
import com.crmbackend.payLoad.request.SignupRequest;
import com.crmbackend.payLoad.response.JwtResponse;
import com.crmbackend.payLoad.response.ReturnMessageResponse;
import com.crmbackend.security.jwt.JwtUtils;
import com.crmbackend.security.securityServices.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> rolesString = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
						userDetails.getFirstname(), userDetails.getLastname(), userDetails.getPhone(), rolesString));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> regietserUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println(signUpRequest);
		if (userRepo.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new ReturnMessageResponse("Error: Username is already exist!"));

		}

		if (userRepo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new ReturnMessageResponse("Error: Email is already used"));
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstname(), signUpRequest.getLastname(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), signUpRequest.getPhone());

		Set<String> roleSetString = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
//if there are no roll specific

		System.out.println(roleSetString);
		if (roleSetString == null) {
			Role userRole = roleRepo.findByName("User")
					.orElseThrow(() -> new RuntimeException("Error: Role user is missing/not found"));
			roles.add(userRole);
		} else {
			roleSetString.forEach(role -> {
				switch (role) {

				case "Admin":
					Role adminRole = roleRepo.findByName("Admin")
							.orElseThrow(() -> new RuntimeException("Error: Can't find Role Admin."));
					roles.add(adminRole);
					break;

				case "Manager":
					Role managerRole = roleRepo.findByName("Manager")
							.orElseThrow(() -> new RuntimeException("Error: Can't find Role Manager."));
					roles.add(managerRole);

					break;

				case "User":
					Role userRole = roleRepo.findByName("User")
							.orElseThrow(() -> new RuntimeException("Error: Can't find Role User."));
					roles.add(userRole);

					break;

				}
			});
		}
		user.setRoles(roles);
		userRepo.save(user);

		return ResponseEntity.ok(new ReturnMessageResponse("You registered successfully!! Explore as you like."));

	}

}
