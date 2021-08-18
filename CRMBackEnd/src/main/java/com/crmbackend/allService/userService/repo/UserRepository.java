package com.crmbackend.allService.userService.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.crmbackend.dtos.IavaliableUsers;
import com.crmbackend.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
//content for future update

	@Query("select u from User u where u.username=:username")
	public Optional<User> getUserByUsername(@Param("username") String username);

	@Query("select u from User u where u.email=:email")
	public Optional<User> getUserByEmail(@Param("email") String email);

	public Long countById(Integer id); // prepare for delete verification if it is exist

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "select u.*,tu.*,count(CASE WHEN tu.active is not null THEN 1 END) as team_number "
			+ "from tbl_users u  left  join team_users tu on u.id = tu.user_id and tu.active = 'ACTIVE' GROUP by U.id ", nativeQuery = true)

	public List<IavaliableUsers> getAllUsersAndTeamInfo();
}
