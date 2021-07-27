package com.crmbackend.allService.userService.uService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crmbackend.allService.userService.exceptions.UserNotFoundException;
import com.crmbackend.allService.userService.repo.RoleRepository;
import com.crmbackend.allService.userService.repo.UserRepository;
import com.crmbackend.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getByUsername(String username) {
		return userRepo.getUserByUsername(username).get();
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

	public User get(Integer id) throws UserNotFoundException {

		try {
			return userRepo.findById(id).get();
		} catch (Exception e) {			
			throw new UserNotFoundException("Sorry the User you are looking for is not found: " + id);
		}

	}

	public User save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		if (isUpdatingUser) {
			User existingUser = userRepo.findById(user.getId()).get();

			if (user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			} else {
				encodePassword(user);
			}

		} else {
			encodePassword(user);
		}
		return userRepo.save(user);

	}

	public void delete(Integer id) throws UserNotFoundException {
		Long countById = userRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new UserNotFoundException("Sorry the User could not be found: " + id);
		}

		userRepo.deleteById(id);
	}

	public boolean isEmailUnique(Integer id, String email) {
		User userByEmail = userRepo.getUserByEmail(email).get();

		if (userByEmail == null) {
			return true;
		}

		boolean isCreatingNewUser = (id == null);

		if (isCreatingNewUser) {
			if (userByEmail != null)
				return false;
		} else {
			if (userByEmail.getId() != id) {
				return false;
			}
		}
		return true;
	}

	public boolean isUsernameUnique(Integer id, String username) {
		User userByUsername = userRepo.getUserByUsername(username).get();

		if (userByUsername == null) {
			return true;
		}

		boolean isCreatingNewUser = (id == null);

		if (isCreatingNewUser) {
			if (userByUsername != null)
				return false;
		} else {
			if (userByUsername.getId() != id) {
				return false;
			}
		}
		return true;
	}

}
