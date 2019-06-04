package fr.gestion.rdv;

public class Personne {
	
	private String nom;
	private String prenom;
	private String mail;
	private String adresse;
	private String cp;
	private String ville;
	private String tel;
	
	public Personne() {
		
	}
	
	public Personne(String pNom , String pPrenom , String pMail , String pAdresse , String pCp , String pVille , String pTel){
		this.nom = pNom;
		this.prenom = pPrenom;
		this.mail = pMail;
		this.adresse = pAdresse;
		this.cp = pCp;
		this.ville = pVille;
		this.tel = pTel;
		
	}
	
	public String toString() {
		return this.nom+","+this.prenom+","+this.adresse+","+this.cp+","+this.ville+","+this.tel+","+this.mail;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
