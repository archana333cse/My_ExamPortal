package com.exam1;

import com.exam1.helper.UserFoundException;
import com.exam1.model.Role;
import com.exam1.model.User;
import com.exam1.model.UserRole;
import com.exam1.service.UserService;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Examserver1Application implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Examserver1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println("Starting Code!!");
			User user = new User();
			user.setFirstname("Archna");
			user.setLastname("Pandey");
			user.setUsername("archna.pandey");
			user.setPassword(this.bCryptPasswordEncoder.encode("abcd"));
			user.setEmail("arch@gmail.com");
			user.setProfile("default.png");

			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");
			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);
			userRoleSet.add(userRole);
			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());
		}catch(UserFoundException e)
		{
			e.printStackTrace();
		}

	}
}
