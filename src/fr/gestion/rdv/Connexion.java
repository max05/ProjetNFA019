package fr.gestion.rdv;
import java.util.*;


import java.sql.*;

public class Connexion {
	
	private static String url = "jdbc:mysql://localhost:3306/BdProjetNFA019?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static String login = "root";
	private static String password = "naruto59";
	private static Connection conn = null;

	/**
	 * Constructeur par d�faut , il fait la connexion avec la base de donn�e
	 */
	public Connexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cr�er et retourner une connexion
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		if (conn == null) {
			new Connexion();
		}
		return conn;

	}

	/**
	 * Fermer la connexion � la base de donn�e
	 */
	public static void stop() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
