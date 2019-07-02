package interfacesGraphiques;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.gestion.rdv.Connexion;
import fr.gestion.rdv.MethodesSql;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Fenêtre de login , l'utilisateur saisit ses infos et
 * choisit son statut
 * @author maxime , eric
 *
 */
public class Login {

	public JFrame frame; //création de la variable jframe
	private JTextField textField;
	private JPasswordField passwordFieldAuthentif;
	private Connexion uneConnexion = new Connexion();
	private int idPma; //variable pour récuperer l'id utilisateur
	private String pLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Création de la fenêtre 
		 */
		frame = new JFrame(); 
		frame.setTitle("Login by Maxime and Eric");
		frame.getContentPane().setBackground(new Color(153, 153, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		/**
		 * Label merci de vous authentifier
		 */
		JLabel lblMerciDeVous = new JLabel("Merci de vous authentifier");
		lblMerciDeVous.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMerciDeVous.setBounds(275, 54, 271, 43);
		frame.getContentPane().add(lblMerciDeVous);

		/**
		 * Label identifiant
		 */
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdentifiant.setBounds(108, 169, 102, 24);
		frame.getContentPane().add(lblIdentifiant);

		/**
		 * Label mot de passe
		 */
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMotDePasse.setBounds(108, 287, 128, 24);
		frame.getContentPane().add(lblMotDePasse);

		/**
		 * Textfield pour la saisie du login
		 */
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(262, 167, 271, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		/**
		 * Bouton sélection admin
		 */
		JRadioButton adminButton = new JRadioButton("Admin");
		adminButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		adminButton.setBounds(222, 383, 81, 30);
		frame.getContentPane().add(adminButton);

		/**
		 * Bouton sélection medecin
		 */
		JRadioButton medecinButton = new JRadioButton("M\u00E9decin");
		medecinButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		medecinButton.setBounds(388, 383, 81, 30);
		frame.getContentPane().add(medecinButton);

		/**
		 * Bouton sélection patient
		 */
		JRadioButton patientButton = new JRadioButton("Patient");
		patientButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		patientButton.setBounds(547, 383, 81, 30);
		frame.getContentPane().add(patientButton);

		/**
		 * Regroupe les trois boutons et permet de sélectionner un seul bouton à la fois
		 */
		ButtonGroup group = new ButtonGroup();
		group.add(adminButton);
		group.add(patientButton);
		group.add(medecinButton);

		/**
		 * Bouton de connexion
		 */
		JButton btnConnexion = new JButton("connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Conditions pour chaques type d'utilisateur
				 */
				ResultSet res = MethodesSql.afficherUser(uneConnexion.getConnection()); //utilisation de la méthode pour afficher les users

				try {
					while (res.next()) {
						idPma = res.getInt("id_pma"); //récupérer l'id Patient Medecin Admin
						pLogin = res.getString("login"); //récupérer le login de l'utilisateur
						

					}
					String textLogin = textField.getText();

					if (patientButton.isSelected()) {
						if (idPma > 1 || idPma < 1000 && textLogin == pLogin) { //si l'id pma est supérieur à 1 ou inférieur à 1000 et que le textfield est égale au login utilisateur
							try {
								/**
								 * Si le bouton patient est sélectionné on affiche la fenêtre correspond
								 * à la session patient
								 */
								SessionPatient windowsPatient = new SessionPatient();
								windowsPatient.framePatient.setVisible(true);
								frame.setVisible(false);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * Si le bouton medecin est selectionné , on affiche la fenêtre session medecin
				 */
				if (medecinButton.isSelected()) {

					try {
						SessionMedecin window = new SessionMedecin();
						window.frameMedecin.setVisible(true);
						frame.setVisible(false);
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}

				/**
				 * Si le bouton admin est sélectionné , on affiche la fenêtre session admin
				 */
				if (adminButton.isSelected()) {

					try {
						SessionAdmin window = new SessionAdmin();
						window.frmEspaceAdministrateur.setVisible(true);
						frame.setVisible(false);
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}

			} 
		});// fin de l'accolade bouton connexion
		btnConnexion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConnexion.setBounds(326, 461, 155, 30);
		frame.getContentPane().add(btnConnexion);

		// logo1
		JLabel logoImage = new JLabel("");
		logoImage.setBounds(31, 11, 113, 104);
		Image logoImage1 = new ImageIcon(this.getClass().getResource("/key-lock-icon.png")).getImage()
				.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		logoImage.setIcon(new ImageIcon(logoImage1));

		frame.getContentPane().add(logoImage);

		// logo2
		JLabel logoMedecine = new JLabel("");
		logoMedecine.setBounds(31, 418, 90, 86);
		frame.getContentPane().add(logoMedecine);
		Image logoImage2 = new ImageIcon(this.getClass().getResource("/images_medecine.png")).getImage()
				.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		logoMedecine.setIcon(new ImageIcon(logoImage2));

		frame.getContentPane().add(logoMedecine);

		/**
		 * Textfield pour le mot de passe (cache le mdp)
		 */
		passwordFieldAuthentif = new JPasswordField();
		passwordFieldAuthentif.setBounds(262, 281, 271, 30);
		frame.getContentPane().add(passwordFieldAuthentif);
	}

}
