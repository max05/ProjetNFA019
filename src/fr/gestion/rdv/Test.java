package fr.gestion.rdv;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connexion uneConnexion = new Connexion();
		MethodesSql.ajouterMedecin(uneConnexion.getConnection(), "Poulmane", "Maxime", "max.poulmane@gmail.com", "rue ferdinand buisson", "Hellemmes", "59260", "066545728", 2);
		


	}

}
