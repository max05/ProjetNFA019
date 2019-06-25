package fr.gestion.rdv;

public class Medecin extends Personne {
	
	private String specialite;
	
	public Medecin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medecin(String pNom, String pPrenom, String pMail, String pAdresse, String pCp, String pVille, String pTel , int pId) {
		super(pNom, pPrenom, pMail, pAdresse, pCp, pVille, pTel, pId);
		// TODO Auto-generated constructor stub
	}

	
	public Medecin(String specialite) {
		super();
		this.specialite = specialite;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}


	
	

}
