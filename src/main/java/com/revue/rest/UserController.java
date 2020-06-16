package com.revue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.revue.config.security.Roles;
import com.revue.config.security.dto.AppUser;
import com.revue.config.security.dto.RegistrationForm;
import com.revue.services.AccountService;

@org.springframework.web.bind.annotation.RestController
public class UserController {
	@Autowired
	private AccountService accountService;
	
@PostMapping("/users")
public AppUser signUp(@RequestBody RegistrationForm data) {
	return accountService.signUp(data);
	}

@PreAuthorize("hasAuthority('ADMIN')")
@PostMapping("/userRoles")
public void associateUserWithRole(@RequestParam String username,@RequestParam Roles role) {
	accountService.addRoleToUser(username, role);
}

}