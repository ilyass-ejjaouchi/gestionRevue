package com.revue.config.security;

public enum Roles {
	  ADMIN("ADMIN"),
	  AUTEUR("AUTEUR"),
	  UTILISATEUR("UTILISATEUR"),
	  REFEREE ("REFEREE"),
	  MEMBRE_COMITE_EDITORIAL ("MEMBRE_COMITE_EDITORIAL");
	  
	  private String role = "";
	   
	  Roles (String role){
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
