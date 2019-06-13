package fr.gestion.rdv;
import java.sql.*;

public class MethodesSql {
	
	static PreparedStatement prep = null;
	
	public static void ajouterMedecin(Connection conn , String pNom , String pPrenom , String pMail , String pAdresse , String pVille , String pCp , String pTel , int pSpecialite ) {
		try {
			prep = conn.prepareStatement("insert into medecin (nomM , prenomM , mailM , adresseM , villeM , cpM , telM , id_specialite values (?,?,?,?,?,?,?,?)");
			prep.setString(1, pNom);
			prep.setString(2, pPrenom);
			prep.setString(3, pMail);
			prep.setString(4, pAdresse);
			prep.setString(5, pVille);
			prep.setString(6, pCp);
			prep.setString(7, pTel);
			prep.setInt(8, pSpecialite);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}
	
	public static void ajouterPatient(Connection conn , int pSs , String pNom , String pPrenom , String pTel , String pSexe , String pMail , String pAdresse , String pVille , String pCp , Date pDateNaiss) {
		
		try {
			prep = conn.prepareStatement("insert into patient (SS , nomP , prenomP , telP , sexeP , mailP , adresseP , villeP , cpP , dateNaissP) values (?,?,?,?,?,?,?,?,?,?)");
			prep.setInt(1, pSs);
			prep.setString(2, pNom);
			prep.setString(3, pPrenom);
			prep.setString(4, pTel);
			prep.setString(5, pSexe);
			prep.setString(6, pMail);
			prep.setString(7, pAdresse);
			prep.setString(8, pVille);
			prep.setString(9, pCp);
			prep.setDate(10, pDateNaiss);
			prep.executeUpdate();
			prep.close();
			
		} catch (SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}
	
	public static void modifierMedecin(Connection conn , int pSs , String pNom , String pPrenom , String pTel , String pSexe , String pMail , String pAdresse , String pVille , String pCp , Date pDateNaiss) {
		try {
			prep = conn.prepareStatement("update medecin ");
			
		}catch (SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}

}
