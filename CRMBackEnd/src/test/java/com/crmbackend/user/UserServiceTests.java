package com.crmbackend.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.crmbackend.allService.userService.uService.UserService;
import com.crmbackend.entity.Role;
import com.crmbackend.entity.User;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class UserServiceTests {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserService service;

	@Test
	public void testCreateNewUserWithSingleRolePasswordEncode() {
		Role roleUser = entityManager.find(Role.class, 3);
		User userLucas = new User("lucas9324", "Lucas", "Tom", "qqq542417349", "Lucas9324@outlook.com", "7364832234");
		userLucas.addRole(roleUser);

		User savedUser = service.save(userLucas);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

}
