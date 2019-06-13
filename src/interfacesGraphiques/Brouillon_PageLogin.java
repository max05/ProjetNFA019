package interfacesGraphiques;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class Brouillon_PageLogin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Brouillon_PageLogin window = new Brouillon_PageLogin();
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
	public Brouillon_PageLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 732, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMerciDeVous = new JLabel("Merci de vous authentifier");
		lblMerciDeVous.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMerciDeVous.setBounds(275, 54, 210, 43);
		frame.getContentPane().add(lblMerciDeVous);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdentifiant.setBounds(108, 169, 102, 24);
		frame.getContentPane().add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotDePasse.setBounds(108, 223, 102, 24);
		frame.getContentPane().add(lblMotDePasse);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(237, 163, 271, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(237, 225, 271, 30);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		JRadioButton adminButton = new JRadioButton("Admin");
		adminButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adminButton.setBounds(231, 279, 81, 23);
		frame.getContentPane().add(adminButton);
		
		JRadioButton medecinButton = new JRadioButton("M\u00E9decin");
		medecinButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medecinButton.setBounds(343, 279, 81, 23);
		frame.getContentPane().add(medecinButton);
		
		JRadioButton patientButton = new JRadioButton("Patient");
		patientButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patientButton.setBounds(445, 279, 81, 23);
		frame.getContentPane().add(patientButton);
		
		JButton btnConnexion = new JButton("connexion");
		btnConnexion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConnexion.setBounds(304, 349, 142, 23);
		frame.getContentPane().add(btnConnexion);
		
//		//logo1
//		JLabel logoImage = new JLabel("");
//		logoImage.setBounds(31, 11, 113, 104);
//        Image logoImage1 = new ImageIcon(this.getClass().getResource("/key-lock-icon.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//        logoImage.setIcon(new ImageIcon(logoImage1));
//      
//		frame.getContentPane().add(logoImage);
//		
//		//logo2
//		JLabel logoMedecine = new JLabel("");
//		logoMedecine.setBounds(31, 286, 90, 86);
//		frame.getContentPane().add(logoMedecine);
//        Image logoImage2 = new ImageIcon(this.getClass().getResource("/images_medecine.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//        logoMedecine.setIcon(new ImageIcon(logoImage2));
//      
//		frame.getContentPane().add(logoMedecine);
	}
}
