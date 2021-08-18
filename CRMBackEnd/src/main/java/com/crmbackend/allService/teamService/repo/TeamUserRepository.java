package com.crmbackend.allService.teamService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crmbackend.entity.TeamUsers;

public interface TeamUserRepository extends PagingAndSortingRepository<TeamUsers, Integer> {

	@Query("select t from TeamUsers t ")
	public List<TeamUsers> getAllTeamandDetails();
}