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
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordFieldAuthentif;
	private Connexion uneConnexion = new Connexion();
	private int idPma;

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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblMerciDeVous = new JLabel("Merci de vous authentifier");
		lblMerciDeVous.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMerciDeVous.setBounds(275, 54, 271, 43);
		frame.getContentPane().add(lblMerciDeVous);

		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdentifiant.setBounds(108, 169, 102, 24);
		frame.getContentPane().add(lblIdentifiant);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMotDePasse.setBounds(108, 287, 128, 24);
		frame.getContentPane().add(lblMotDePasse);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(262, 167, 271, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JRadioButton adminButton = new JRadioButton("Admin");
		adminButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		adminButton.setBounds(222, 383, 81, 30);
		frame.getContentPane().add(adminButton);

		JRadioButton medecinButton = new JRadioButton("M\u00E9decin");
		medecinButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		medecinButton.setBounds(388, 383, 81, 30);
		frame.getContentPane().add(medecinButton);

		JRadioButton patientButton = new JRadioButton("Patient");
		patientButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		patientButton.setBounds(547, 383, 81, 30);
		frame.getContentPane().add(patientButton);

		ButtonGroup group = new ButtonGroup();
		group.add(adminButton);
		group.add(patientButton);
		group.add(medecinButton);

		/**
		 * bouton de connexion
		 */
		JButton btnConnexion = new JButton("connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Faire des conditions pour chaque type d'utilisateur
				 */
				ResultSet res = MethodesSql.afficherUser(uneConnexion.getConnection());

				try {
					while (res.next()) {
						idPma = res.getInt("id_pma");

					}
					if (patientButton.isSelected()) {
						

						if (idPma < 1000) {
							SessionPatient uneSessionP = new SessionPatient();

						} else {
							JFrame msgErreur = new JFrame("Erreur !!");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnConnexion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConnexion.setBounds(326, 461, 155, 30);
		frame.getContentPane().add(btnConnexion);

		/**
		 * Permet de regrouper les boutons et choisir une seule session utilisateur
		 */

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

		passwordFieldAuthentif = new JPasswordField();
		passwordFieldAuthentif.setBounds(262, 281, 271, 30);
		frame.getContentPane().add(passwordFieldAuthentif);
	}
}
