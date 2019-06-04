package fr.gestion.rdv;

import java.sql.Date;

public class Patient extends Personne {
	

	private int SS;
	private Date dateNaiss;
	
	public Patient(int sS, Date dateNaiss) {
		super();
		this.SS = sS;
		this.dateNaiss = dateNaiss;
	}


	public Patient(String pNom, String pPrenom, String pMail, String pAdresse, String pCp, String pVille, String pTel) {
		super(pNom, pPrenom, pMail, pAdresse, pCp, pVille, pTel);
		// TODO Auto-generated constructor stub
	}

	public int getSS() {
		return SS;
	}

	public void setSS(int sS) {
		SS = sS;
	}


	public Date getDateNaiss() {
		return dateNaiss;
	}


	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

}
