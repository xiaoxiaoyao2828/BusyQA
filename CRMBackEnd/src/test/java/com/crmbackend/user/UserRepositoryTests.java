package com.crmbackend.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.crmbackend.allService.teamService.repo.TeamRepository;
import com.crmbackend.allService.teamService.repo.TeamUserRepository;
import com.crmbackend.allService.userService.repo.UserRepository;
import com.crmbackend.entity.Role;
import com.crmbackend.entity.User;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private TeamRepository teamRepo;
	@Autowired
	private TeamUserRepository teamUserRepo;

	@Test
	public void testCreateNewUserWithSingleRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userJiahuaHe = new User("hejiahua1234", "Jiahua", "He", "JH123456789", "xiaoxiaoyao2828@126.com",
				"6478889988");
		userJiahuaHe.addRole(roleAdmin);

		User savedUser = repo.save(userJiahuaHe);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithMultiRoles() {
		User userXiaoHe = new User("xiaohe9324", "Xiao", "He", "19930204", "xiaoxiaoyao2828@gmail.com", "6476463243");
		Role roleGeneralManager = new Role(2);
		Role roleSimpleUser = new Role(3);

		userXiaoHe.addRole(roleGeneralManager);
		userXiaoHe.addRole(roleSimpleUser);

		User savedUser = repo.save(userXiaoHe);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testGetUserById() {
		User userJiahuaHe = repo.findById(3).get();
		assertThat(userJiahuaHe).isNotNull();
	}

	@Test
	public void testGetUserByEmail() {
		User userJiahuaHe = repo.getUserByEmail(("xiaoxiaoyao2828@126.com").toLowerCase()).get();
		assertThat(userJiahuaHe).isNotNull();
	}

	@Test
	public void testExistUsername() {
		Boolean result = repo.existsByUsername(("abcde"));
		assertThat(result).isTrue();
	}

	@Test
	public void testGetUserByUsername() {
		User userJiahuaHe = repo.getUserByUsername("hejiahua1234").get();
		System.out.println(userJiahuaHe);
		assertThat(userJiahuaHe).isNotNull();
	}

	@Test
	public void testCountById() {
		int id = 4;
		long count = repo.countById(id);
		System.out.println(count);
		assertThat(count).isGreaterThan(0);
	}

	@Test
	public void testFetchTeams() {
		List<Object> team = teamRepo.getAllTeamAndDetails();
		System.out.println(team);

	}

}
