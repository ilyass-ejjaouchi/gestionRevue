package com.revue.beans;

public enum EtatArticle {
	  EN_VUE_DE_PUBLICATION("EN_VUE_DE_PUBLICATION"),
	  POSTED("POSTED"),
	  ENCOURS_DEVALUATION("ENCOURS_DEVALUATION"),
	  ACCEPTED ("ACCEPTED"),
	  ACCEPTED_WITH_MODIF_MAJEUR ("ACCEPTED_WITH_MODIF_MAJEUR"),
	  ACCEPTED_WITH_MODIF_MINEUR ("ACCEPTED_WITH_MODIF_MINEUR"),
	  REFUS("REFUS"),
	  DEMANDE_MODIFICATIONS("DEMANDE_MODIFICATIONS"),
	  ACCEPTED_AFTER_MODIF("ACCEPTED_AFTER_MODIF"),
	  REFUS_OUT_OF_POLITIC("REFUS_OUT_OF_POLITIC");
	  
	  private String etat = "";
	   
	  EtatArticle (String name){
	    this.etat = name;
	  }
	   
	  public String getEtat() {
		return etat;
	  }

	   public void setEtat(String etat) {
			this.etat = etat;
		}

	public String toString(){
	    return etat;
	  }
}
