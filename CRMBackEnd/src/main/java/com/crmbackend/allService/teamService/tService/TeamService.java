package com.crmbackend.allService.teamService.tService;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crmbackend.allService.teamService.repo.TeamRepository;
import com.crmbackend.allService.teamService.repo.TeamUserRepository;
import com.crmbackend.allService.userService.repo.RoleRepository;
import com.crmbackend.allService.userService.repo.UserRepository;
import com.crmbackend.entity.Team;
import com.crmbackend.entity.TeamUsers;
import com.crmbackend.entity.User;

@Service
@Transactional
public class TeamService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private TeamUserRepository teamUserRepo;

	@Autowired
	private TeamRepository teamRepo;

	public List<Team> listAll() {

		List<Object> alldata = teamRepo.getAllTeamAndDetails();

		List<Team> finalResult = alldata.stream().map(t -> new Team(
				((Team) t).getId(), ((Team) t).getTeamname(), ((Team) t).getDelete_date(), ((Team) t).getDescription(),
				((Team) t).getActive(), ((Team) t).getTeam_users().stream()
						.filter(u -> u.getActive().equals(TeamUsers.Status.ACTIVE)).collect(Collectors.toSet())))
				.collect(Collectors.toList());

		return finalResult;

	}

	public List<User> getAllUsers() {
		List<User> allUser = (List<User>) userRepo.findAll();

		List<User> finalResult = allUser.stream().map(t -> new User(t.getId(), t.getUsername(), t.getFirstName(),
				t.getLastName(), t.getEmail(), t.getPhoneNumber(), t.getRoles(), t.getTeam_users().stream()
						.filter(u -> u.getActive().equals(TeamUsers.Status.ACTIVE)).collect(Collectors.toSet())))
				.collect(Collectors.toList());
		return finalResult;
	}
}