package com.crmbackend.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordsEncoderTest {
	@Test
	public void testPasswordEncodedr() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "Hx123456";
		String newPassword = passwordEncoder.encode(rawPassword);
		System.out.println(newPassword);

		boolean match = passwordEncoder.matches(rawPassword, newPassword);
		assertThat(match).isTrue();
	}
}
