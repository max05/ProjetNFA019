package fr.gestion.rdv;

public class Medecin extends Personne {
	
	private int specialite;
	
	public Medecin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medecin(String pNom, String pPrenom, String pMail, String pAdresse, String pCp, String pVille, String pTel , int pId) {
		super(pNom, pPrenom, pMail, pAdresse, pCp, pVille, pTel, pId);
		// TODO Auto-generated constructor stub
	}

	
	public Medecin(int specialite) {
		super();
		this.specialite = specialite;
	}

	public int getSpecialite() {
		return specialite;
	}

	public void setSpecialite(int specialite) {
		this.specialite = specialite;
	}


	
	

}
