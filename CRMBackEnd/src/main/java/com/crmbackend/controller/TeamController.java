package com.crmbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crmbackend.allService.teamService.tService.TeamService;
import com.crmbackend.allService.userService.repo.RoleRepository;
import com.crmbackend.allService.userService.repo.UserRepository;
import com.crmbackend.entity.Team;
import com.crmbackend.entity.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	TeamService teamService;

	@GetMapping("/getAllTeams")
	public List<Team> getAllTeams() {

		return teamService.listAll();

	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return teamService.getAllUsers();

	}

}
