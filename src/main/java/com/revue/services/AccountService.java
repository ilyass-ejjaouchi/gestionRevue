package com.revue.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.revue.beans.Auteur;
import com.revue.config.security.Roles;
import com.revue.config.security.dto.AppRole;
import com.revue.config.security.dto.AppUser;
import com.revue.config.security.dto.RegistrationForm;
import com.revue.config.security.dto.RegistrationForm.Role;
import com.revue.exceptions.BadRequestException;
import com.revue.repositories.AppRoleRepository;
import com.revue.repositories.AppUserRepository;
import com.revue.repositories.AuteurRepository;

@Service
@Transactional
public class AccountService {
	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private AppRoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuteurRepository auteurRepo;

	public AppUser saveUser(AppUser u) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		return userRepository.save(u);
	}

	public AppRole saveRole(AppRole r) {
		AppRole role =roleRepository.findByRole(r.getRole());
		if (role == null ) {
			return roleRepository.save(r);
		}
		else {
			throw new BadRequestException("le role existe déja");
		}
	}

	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void addRoleToUser(String username, Roles r) {
		AppUser user=userRepository.findByUsername(username);
		AppRole role =roleRepository.findByRole(r);
		user.addRole(role);
		userRepository.save(user);
		}
	
	public AppUser signUp(RegistrationForm data) {
		String username = data.getUsername();
		AppUser user = findUserByUsername(username);
		if(user!=null) throw new BadRequestException("This user already exists, Try with an otherusername");
			String password = data.getPassword(); 
			String repassword = data.getRepassword();
		if(!password.equals(repassword))
			throw new RuntimeException("You must confirm your password");
			AppUser u = new AppUser(); 
			u.setUsername(username); 
			u.setPassword(password);
			saveUser(u);
			if (data.getRole().equals(Role.AUTEUR)) {
				Auteur a = new Auteur(data.getUsername(),data.getNom(), data.getPrenom(), data.getAffiliation());
				auteurRepo.save(a);
				addRoleToUser(username, Roles.AUTEUR);
			}
			addRoleToUser(username, Roles.UTILISATEUR);
			return (u);
	}
	 
}
