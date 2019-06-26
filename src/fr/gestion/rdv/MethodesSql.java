package fr.gestion.rdv;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

public class MethodesSql {
	
	static PreparedStatement prep = null;
	private ArrayList<Patient> lstPatient = new ArrayList<Patient>();
	
	public static void ajouterMedecin(Connection conn , String pNom , String pPrenom , String pMail , String pAdresse , String pVille , String pCp , String pTel , int pSpecialite ) {
		try {
			prep = conn.prepareStatement("insert into medecin (nomM , prenomM , mailM , adresseM , villeM , cpM , telM , id_specialite) values (?,?,?,?,?,?,?,?)");
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
	
	public static void ajouterPatient(Connection conn , int pSs , String pNom , String pPrenom , String pTel ,  String pMail , String pAdresse , String pVille , String pCp) {
		
		try {
			prep = conn.prepareStatement("insert into patient (SS , nomP , prenomP , telP , mailP , adresseP , villeP , cpP) values (?,?,?,?,?,?,?,?)");
			prep.setInt(1, pSs);
			prep.setString(2, pNom);
			prep.setString(3, pPrenom);
			prep.setString(4, pTel);
			//prep.setString(5, pSexe);
			prep.setString(5, pMail);
			prep.setString(6, pAdresse);
			prep.setString(7, pVille);
			prep.setString(8, pCp);
			prep.executeUpdate();
			prep.close();
			
		} catch (SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}
	
	public static void modifierMedecin(Connection conn , String pNom , String pPrenom , String pMail , String pAdresse , String pVille , String pCp , String pTel , int pSpecialite , int pId) {
		try {
			prep = conn.prepareStatement("update medecin set nomM = ? , prenomM = ? , mailM = ? , adresseM = ? , villeM = ? , cpM = ? , telM = ? , id_specialite = ? where id_medecin = ?");
			prep.setString(1, pNom);
			prep.setString(2, pPrenom);
			prep.setString(3, pMail);
			prep.setString(4, pAdresse);
			prep.setString(5, pVille);
			prep.setString(6, pCp);
			prep.setString(7, pTel);
			prep.setInt(8, pSpecialite);
			prep.setInt(9, pId);
			prep.executeUpdate();
			prep.close();
			
		}catch (SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}
	
	public static void modifierPatient(Connection conn , String pNom , String pPrenom , String pTel ,String pMail , String pAdresse , String pVille , String pCp , int pId) {
		try {
			prep = conn.prepareStatement("update patient set nomP = ? , prenomP = ? , telP = ? , mailP = ? , adresseP = ? , villeP = ? , cpP = ? where id_patient = ?");
			prep.setString(1, pNom);
			prep.setString(2, pPrenom);
			prep.setString(3, pTel);
			//prep.setString(4, pSexe);
			prep.setString(4, pMail);
			prep.setString(5, pAdresse);
			prep.setString(6, pVille);
			prep.setString(7, pCp);
			prep.setInt(8, pId);
			prep.executeUpdate();
			prep.close();
			
			
		}catch (SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}
	
	public static void supprimerMedecin(Connection conn , int pId) {
		try {
			prep = conn.prepareStatement("delete from medecin where id_medecin = ? ");
			prep.setInt(1, pId);
			prep.executeUpdate();
			prep.close();
			
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
			
		}
	}
	
	public static void supprimerPatient(Connection conn , int pId) {
		try {
			prep = conn.prepareStatement("delete from patient where id_patient = ?");
			prep.setInt(1, pId);
			prep.executeUpdate();
			prep.close();
			
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		}
	}
	
	public static ResultSet afficherMedecin(Connection conn , int pId) {
		ResultSet res = null;
		try {
			Statement statemment = conn.createStatement();
			res = statemment.executeQuery("select nomM , prenomM , mailM , adresseM , villeM , cpM , telM , s.intitule from medecin inner join specialite s on medecin.id_specialite = s.id_specialite where id_medecin ="+"'"+pId+"'");
			
			
			
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		} finally {
			return res;
		}
	}
	
	public static ResultSet afficherPatient(Connection conn , int pId) {
		ResultSet res = null;
		try {
			Statement st = conn.createStatement();
			res = st.executeQuery("select SS , nomP , prenomP , telP , sexeP , mailP , adresseP , villeP , cpP from patient where id_patient ="+"'"+pId+"'");
		} catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		} finally {
			return res;
		}
	}
	
	public static ResultSet afficherPatients(Connection conn) {
		ResultSet res = null;
		try {
			Statement st = conn.createStatement();
			res = st.executeQuery("select id_patient, SS , nomP , prenomP , telP , sexeP , mailP , adresseP , villeP , cpP from patient");

			
		} catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode()); 
		} finally {
			return res;
		}

	}
	
	public static ResultSet afficherMedecins(Connection conn) {
		ResultSet res = null;
		try {
			Statement st = conn.createStatement();
			res = st.executeQuery("select id_medecin , nomM , prenomM , mailM , adresseM , villeM , cpM , telM , s.intitule from medecin inner join specialite s on medecin.id_specialite = s.id_specialite");
			
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		} finally {
			return res;
		}
	}
	
	public static ResultSet afficherSpecialite(Connection conn) {
		ResultSet res = null;
		try {
			Statement st = conn.createStatement();
			res = st.executeQuery("select intitule from specialite");
			
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		} finally {
			return res;
		}
	}
	
	public static ResultSet afficherUser(Connection conn) {
		ResultSet res = null;
		try {
			Statement st = conn.createStatement();
			res = st.executeQuery("select login , mdp , id_pma from user");
			
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex.getMessage());
			System.out.println("SQLState : "+((SQLException)ex).getSQLState());
			System.out.println("VendorError : "+((SQLException)ex).getErrorCode());
		} finally {
			return res;
		}
	}
	
}
