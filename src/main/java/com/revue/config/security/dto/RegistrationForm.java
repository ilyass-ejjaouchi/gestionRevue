package com.revue.config.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegistrationForm {
	public enum Role {
		  AUTEUR("AUTEUR"),
		  UTILISATEUR("UTILISATEUR");
		  
		  private String role = "";
		   
		  Role (String role){
		    this.role = role;
		  }
		   
		  public String getRole() {
			return role;
		  }

		   public void setRole(String role) {
				this.role = role;
		   }

		   public String toString(){
			    return role;
		   }
	}
	private String username;
	private String nom = null;
	private String prenom = null;
	private String affiliation = null;
	private Role role;
	private String password;
	private String repassword;

}
