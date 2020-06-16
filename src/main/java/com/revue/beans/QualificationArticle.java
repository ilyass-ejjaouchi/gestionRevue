package com.revue.beans;

public enum QualificationArticle {
	  ACCEPTED ("ACCEPTED"),
	  ACCEPTED_WITH_MODIF ("ACCEPTED_WITH_MODIF"),
	  REFUS("REFUS");
	
	  private String qualification = "";
	   
	  QualificationArticle (String name){
	    this.qualification = name;
	  }
	   
	  public String getEtat() {
		return qualification;
	  }

	  public void setEtat(String etat) {
			this.qualification = etat;
		}

	  public String toString(){
		    return qualification;
		  }
}
