package brouillon_DoctoLib;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Test {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordFieldAuthentif;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
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
		
		JButton btnConnexion = new JButton("connexion");
		btnConnexion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConnexion.setBounds(326, 461, 155, 30);
		frame.getContentPane().add(btnConnexion);
		
		//logo1
		JLabel logoImage = new JLabel("");
		logoImage.setBounds(31, 11, 113, 104);
        Image logoImage1 = new ImageIcon(this.getClass().getResource("/key-lock-icon.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        logoImage.setIcon(new ImageIcon(logoImage1));
      
		frame.getContentPane().add(logoImage);
		
		//logo2
		JLabel logoMedecine = new JLabel("");
		logoMedecine.setBounds(31, 418, 90, 86);
		frame.getContentPane().add(logoMedecine);
        Image logoImage2 = new ImageIcon(this.getClass().getResource("/images_medecine.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        logoMedecine.setIcon(new ImageIcon(logoImage2));
      
		frame.getContentPane().add(logoMedecine);
		
		passwordFieldAuthentif = new JPasswordField();
		passwordFieldAuthentif.setBounds(262, 281, 271, 30);
		frame.getContentPane().add(passwordFieldAuthentif);
	}

}
