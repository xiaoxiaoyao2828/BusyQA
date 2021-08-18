package com.crmbackend.allService.teamService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crmbackend.entity.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Integer> {
	
	@Query("select t from Team t")
	public List<Object> getAllTeamAndDetails();

	Boolean existsByTeamname(String teamname);

}