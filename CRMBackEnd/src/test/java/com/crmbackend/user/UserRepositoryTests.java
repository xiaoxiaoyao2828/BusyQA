package com.crmbackend.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

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

	@Test
	public void testCreateNewUserWithSingleRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userPeiranLiu = new User("liupeiran9324", "Peiran", "Liu", "z1324356", "liupeiran9324@outlook.com",
				"6476858067");
		userPeiranLiu.addRole(roleAdmin);

		User savedUser = repo.save(userPeiranLiu);
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
		User userPeiran = repo.findById(3).get();
		assertThat(userPeiran).isNotNull();
	}

	@Test
	public void testGetUserByEmail() {
		User userPeiran = repo.getUserByEmail(("liupeiran9324@Outlook.com").toLowerCase()).get();
		assertThat(userPeiran).isNotNull();
	}

	@Test
	public void testExistUsername() {
		Boolean result = repo.existsByUsername(("ddddd"));
		assertThat(result).isTrue();
	}

	@Test
	public void testGetUserByUsername() {
		User userPeiran = repo.getUserByUsername("liupeiran9324").get();
		System.out.println(userPeiran);
		assertThat(userPeiran).isNotNull();
	}

	@Test
	public void testCountById() {
		int id = 4;
		long count = repo.countById(id);
		System.out.println(count);
		assertThat(count).isGreaterThan(0);
	}

}
