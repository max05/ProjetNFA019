package fr.gestion.rdv;

import java.sql.Date;

import sun.print.PrintJob2D;

public class Patient extends Personne {
	

	private int SS ,idConsultation;
	private Date dateNaiss;
	private String sexe , dateHeure;
	
	public Patient() {
		
	}
	
	public Patient(int sS, Date dateNaiss , String pSexe , int pIdConsultation , String pDateHeure) {
		super();
		this.SS = sS;
		this.dateNaiss = dateNaiss;
		this.sexe = pSexe;
		this.idConsultation = pIdConsultation;
		this.dateHeure = pDateHeure;
	}


	public Patient(String pNom, String pPrenom, String pMail, String pAdresse, String pCp, String pVille, String pTel , String pSexe , int pId) {
		super(pNom, pPrenom, pMail, pAdresse, pCp, pVille, pTel , pId);
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
	
	public void setSexe(String pSexe) {
		this.sexe = pSexe;
	}
	
	public String getSexe() {
		return sexe;
	}

	public int getIdConsultation() {
		return idConsultation;
	}

	public void setIdConsultation(int idConsultation) {
		this.idConsultation = idConsultation;
	}

	public String getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(String dateHeure) {
		this.dateHeure = dateHeure;
	}
	

}
