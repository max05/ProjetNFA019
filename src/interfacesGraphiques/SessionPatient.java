package interfacesGraphiques;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import fr.gestion.rdv.Connexion;
import fr.gestion.rdv.Medecin;
import fr.gestion.rdv.MethodesSql;
import fr.gestion.rdv.Patient;

import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.text.*;

/**
 * Fenêtre session patient 
 * le patient peut voir la liste des medecin et prendre un rdv avec celui-ci
 * @author maxime , eric
 *
 */
public class SessionPatient {

	public JFrame framePatient;
	private JTextField textField_rechercherConsultation;
	private JTextField textField_NumSS;
	private JTextField textField_VotreNom;
	private JTextField textField_VotrePrenom;
	private JTextField textField_NumTel;
	private JTextField textField_DateConsultation;
	private JTextField textField_HeureConsultation;
	private JTextField textField_dateNaissPatient;
	private JTextField txtLille;
	private JTextField txtFranaise;
	private JTextField txtMaxpoulmanegmailcom;
	private JTextField textField_mutuelle;
	private JTextField textField_grpSangin;
	private JTextField textField_poidsPatient;
	private JTextField textField_taillePatient;
	private JTextField textField_imc;
	private JTextField textField_indicBio;
	private JTextField textField_antecedentMedic;
	private JTextField textField_vaccins;
	private JTextField textField_allergies;
	private JTextField textField_antecedentFami;
	private JTextField textField_rechercherDossier;
	private Medecin unMedecin = new Medecin(); //Création de l'objet Medecin
	private Patient unPatient = new Patient(); //Création de l'objet Patient
	private ArrayList<Medecin> lstMedecin = new ArrayList<Medecin>();//Stock les medecins et permet d'afficher les infos
	private Connexion uneConnexion = new Connexion();//Fait la connexion avec la bdd
	private int pId , pSs;
	private String pNom , pPrenom , pTel , pSS;
	private String pDate;//Création de la variable date
	private SimpleDateFormat formater = null; //Pour permettre de formater la date

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionPatient windowsPatient = new SessionPatient();
					windowsPatient.framePatient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SessionPatient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Création de la fenêtre patient
		 */
		framePatient = new JFrame();
		framePatient.setTitle("Espace Patient : Poulmane");
		framePatient.setBounds(100, 100, 800, 600);
		framePatient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePatient.getContentPane().setLayout(null);
		framePatient.setLocationRelativeTo(null);
		JList list_medecins = new JList(); //création de la jlist medecins
		listeMedecin(list_medecins);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBounds(20, 36, 740, 516);
		framePatient.getContentPane().add(tabbedPane);
        
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 255));
		tabbedPane.addTab("Gestion des consultations", null, panel, null);
		panel.setLayout(null);
		
		textField_rechercherConsultation = new JTextField();
		textField_rechercherConsultation.setBounds(10, 11, 547, 20);
		textField_rechercherConsultation.setColumns(10);
		panel.add(textField_rechercherConsultation);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 204, 255));
		panel_2.setBounds(10, 36, 715, 194);
		panel_2.setBorder(new TitledBorder(null, "Liste des M\u00E9decins", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 695, 162);
		panel_2.add(scrollPane);
		

		scrollPane.setViewportView(list_medecins);
		
		JPanel panel_ficheInfoMedecin = new JPanel();
		panel_ficheInfoMedecin.setBackground(new Color(204, 204, 255));
		panel_ficheInfoMedecin.setBounds(10, 243, 348, 215);
		panel_ficheInfoMedecin.setBorder(new TitledBorder(null, "Fiche information M\u00E9decins", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_ficheInfoMedecin);
		panel_ficheInfoMedecin.setLayout(null);
		
		//logo medecin
		JLabel lbl_logoMedecin = new JLabel("");
		lbl_logoMedecin.setBounds(232, 27, 106, 90);
		panel_ficheInfoMedecin.add(lbl_logoMedecin);
		Image logoImage3 = new ImageIcon(this.getClass().getResource("/logo_medecin2.jpg")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		lbl_logoMedecin.setIcon(new ImageIcon(logoImage3));
		
		JLabel lblNomMedecin = new JLabel("");
		lblNomMedecin.setBounds(12, 27, 156, 16);
		panel_ficheInfoMedecin.add(lblNomMedecin);
		
		JLabel lblPrenomMedecin = new JLabel("");
		lblPrenomMedecin.setBounds(12, 56, 146, 16);
		panel_ficheInfoMedecin.add(lblPrenomMedecin);
		
		JLabel lblTelMedecin = new JLabel("");
		lblTelMedecin.setBounds(12, 87, 146, 16);
		panel_ficheInfoMedecin.add(lblTelMedecin);
		
		JLabel lblMailMedecin = new JLabel("");
		lblMailMedecin.setBounds(12, 115, 146, 16);
		panel_ficheInfoMedecin.add(lblMailMedecin);
		
		list_medecins.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = list_medecins.getSelectedIndex();
				unMedecin = lstMedecin.get(i);
				pId = unPatient.getId();
				lblNomMedecin.setText("Nom : " +unMedecin.getNom());
				lblPrenomMedecin.setText("Prénom : " + unMedecin.getPrenom());
				lblTelMedecin.setText("Téléphone : "+unMedecin.getTel());
				lblMailMedecin.setText("Mail : "+unMedecin.getMail());
				
				
				
			}
		});

		JButton btnRechercherConsultation = new JButton("Rechercher");
		btnRechercherConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRechercherConsultation.setBounds(592, 8, 133, 23);
		panel.add(btnRechercherConsultation);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 204, 255));
		panel_4.setBorder(new TitledBorder(null, "Demander une consultation : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(368, 237, 357, 237);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblVotreNoSs = new JLabel("Votre Nr. SS : ");
		lblVotreNoSs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVotreNoSs.setBounds(10, 14, 92, 22);
		panel_4.add(lblVotreNoSs);
		
		JLabel lblVotreNom = new JLabel("Votre Nom : ");
		lblVotreNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVotreNom.setBounds(10, 47, 81, 24);
		panel_4.add(lblVotreNom);
		
		JLabel lblVotrePrnom = new JLabel("Votre Pr\u00E9nom : ");
		lblVotrePrnom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVotrePrnom.setBounds(10, 81, 116, 24);
		panel_4.add(lblVotrePrnom);
		
		JLabel lblVotreNrTel = new JLabel("Votre Nr. tel : ");
		lblVotreNrTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVotreNrTel.setBounds(10, 116, 116, 24);
		panel_4.add(lblVotreNrTel);
		
		JLabel lblDatesouhaite = new JLabel("Date (souhait\u00E9e) :");
		lblDatesouhaite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatesouhaite.setBounds(10, 151, 116, 24);
		panel_4.add(lblDatesouhaite);
		
		JLabel lblHeureSouhaite = new JLabel("Heure (souhait\u00E9e) :");
		lblHeureSouhaite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHeureSouhaite.setBounds(10, 186, 129, 24);
		panel_4.add(lblHeureSouhaite);
		
		JButton btnAjouterConsultation = new JButton("Ajouter");
		btnAjouterConsultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 pId = Integer.valueOf(textField_NumSS.getText());
			 pNom = textField_VotreNom.getText();
			 pPrenom = textField_VotrePrenom.getText();
			 pTel = textField_NumTel.getText();
			 pDate = textField_dateNaissPatient.getText();
			 
			 
			 MethodesSql.ajouterPatient(uneConnexion.getConnection(), pId, pNom, pPrenom, pTel, "", "", "", "", "");
			 MethodesSql.ajoutConsultation(uneConnexion.getConnection(), pId );

			}
		});
		btnAjouterConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAjouterConsultation.setBounds(10, 214, 103, 23);
		panel_4.add(btnAjouterConsultation);
		
		JButton btnModifierConsultation = new JButton("Modifier");
		btnModifierConsultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pNom = textField_VotreNom.getText();
				pPrenom = textField_VotrePrenom.getText();
				pTel = textField_NumTel.getText();
				pDate = textField_dateNaissPatient.getText();
				MethodesSql.modifierPatient(uneConnexion.getConnection(), pNom, pPrenom, pTel, "", "", "", "", pId, pSs);
				MethodesSql.modifierConsultation(uneConnexion.getConnection(), pId, pDate);
				
			}
		});
		btnModifierConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifierConsultation.setBounds(135, 214, 92, 23);
		panel_4.add(btnModifierConsultation);
		
		JButton btnSupprimerConsultation = new JButton("Supprimer");
		btnSupprimerConsultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MethodesSql.supprimerPatient(uneConnexion.getConnection(), pId);
				MethodesSql.supprimerConsultation(uneConnexion.getConnection(), pId);
			}
		});
		btnSupprimerConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSupprimerConsultation.setBounds(250, 214, 107, 23);
		panel_4.add(btnSupprimerConsultation);
		
		textField_NumSS = new JTextField();
		textField_NumSS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_NumSS.setColumns(10);
		textField_NumSS.setBounds(174, 14, 173, 24);
		panel_4.add(textField_NumSS);
		
		textField_VotreNom = new JTextField();
		textField_VotreNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_VotreNom.setColumns(10);
		textField_VotreNom.setBounds(174, 49, 173, 24);
		panel_4.add(textField_VotreNom);
		
		textField_VotrePrenom = new JTextField();
		textField_VotrePrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_VotrePrenom.setColumns(10);
		textField_VotrePrenom.setBounds(174, 83, 173, 24);
		panel_4.add(textField_VotrePrenom);
		
		textField_NumTel = new JTextField();
		textField_NumTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_NumTel.setColumns(10);
		textField_NumTel.setBounds(174, 118, 173, 24);
		panel_4.add(textField_NumTel);
		
		textField_DateConsultation = new JTextField();
		textField_DateConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_DateConsultation.setColumns(10);
		textField_DateConsultation.setBounds(174, 153, 103, 24);
		panel_4.add(textField_DateConsultation);
		
		textField_HeureConsultation = new JTextField();
		textField_HeureConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_HeureConsultation.setColumns(10);
		textField_HeureConsultation.setBounds(174, 188, 86, 24);
		panel_4.add(textField_HeureConsultation);
		
		JLabel lbl_clalendar = new JLabel("");
		lbl_clalendar.setBounds(287, 151, 49, 52);
		panel_4.add(lbl_clalendar);
		Image logoCalendar = new ImageIcon(this.getClass().getResource("/calendar_icon.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		lbl_clalendar.setIcon(new ImageIcon(logoCalendar));
		
		/*JLabel logoImage = new JLabel("");
		logoImage.setBounds(31, 11, 113, 104);
        Image logoImage1 = new ImageIcon(this.getClass().getResource("/key-lock-icon.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        logoImage.setIcon(new ImageIcon(logoImage1));
      
		frame.getContentPane().add(logoImage);*/
		
		//---------------------------------
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 153, 255));
		tabbedPane.addTab("Afficher mon dossier", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(153, 153, 255));
		panel_5.setBorder(new TitledBorder(null, "Compl\u00E9ments d'informations : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 11, 715, 227);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblSexe = new JLabel("Sexe : ");
		lblSexe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexe.setBounds(10, 21, 56, 24);
		panel_5.add(lblSexe);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance : ");
		lblDateDeNaissance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateDeNaissance.setBounds(10, 56, 127, 24);
		panel_5.add(lblDateDeNaissance);
		
		JLabel lblLieuDeNaissance = new JLabel("Lieu de naissance : ");
		lblLieuDeNaissance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLieuDeNaissance.setBounds(10, 91, 127, 24);
		panel_5.add(lblLieuDeNaissance);
		
		JLabel lblGroupeSanguin = new JLabel("Nationalit\u00E9 : ");
		lblGroupeSanguin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGroupeSanguin.setBounds(10, 126, 113, 24);
		panel_5.add(lblGroupeSanguin);
		
		JLabel lblTaille = new JLabel("E-mail : ");
		lblTaille.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTaille.setBounds(10, 161, 81, 24);
		panel_5.add(lblTaille);
		
		JLabel lblPoids = new JLabel("Mutuelle : ");
		lblPoids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPoids.setBounds(10, 196, 81, 24);
		panel_5.add(lblPoids);
		
		textField_dateNaissPatient = new JTextField();
		textField_dateNaissPatient.setText("21/04/1994");
		textField_dateNaissPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_dateNaissPatient.setColumns(10);
		textField_dateNaissPatient.setBounds(147, 56, 173, 24);
		panel_5.add(textField_dateNaissPatient);
		
		txtLille = new JTextField();
		txtLille.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLille.setColumns(10);
		txtLille.setBounds(147, 91, 173, 24);
		panel_5.add(txtLille);
		
		txtFranaise = new JTextField();
		txtFranaise.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFranaise.setColumns(10);
		txtFranaise.setBounds(147, 126, 173, 24);
		panel_5.add(txtFranaise);
		
		txtMaxpoulmanegmailcom = new JTextField();
		txtMaxpoulmanegmailcom.setText("max@gmail.com");
		txtMaxpoulmanegmailcom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaxpoulmanegmailcom.setColumns(10);
		txtMaxpoulmanegmailcom.setBounds(147, 161, 173, 24);
		panel_5.add(txtMaxpoulmanegmailcom);
		
		textField_mutuelle = new JTextField();
		textField_mutuelle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_mutuelle.setColumns(10);
		textField_mutuelle.setBounds(147, 192, 173, 24);
		panel_5.add(textField_mutuelle);
		
		JRadioButton radioBtnHomme = new JRadioButton("H");
		radioBtnHomme.setSelected(true);
		radioBtnHomme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radioBtnHomme.setBounds(147, 18, 62, 30);
		panel_5.add(radioBtnHomme);
		
		JRadioButton radioBtnFemme = new JRadioButton("F");
		radioBtnFemme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radioBtnFemme.setBounds(264, 18, 56, 30);
		panel_5.add(radioBtnFemme);
		
		JLabel lblTaille_1 = new JLabel("Taille : ");
		lblTaille_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTaille_1.setBounds(390, 21, 47, 24);
		panel_5.add(lblTaille_1);
		
		JLabel lblPoids_1 = new JLabel("Poids : ");
		lblPoids_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPoids_1.setBounds(390, 56, 47, 24);
		panel_5.add(lblPoids_1);
		
		JLabel lblGroupeSanguin_1 = new JLabel("Groupe sanguin : ");
		lblGroupeSanguin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGroupeSanguin_1.setBounds(389, 91, 113, 24);
		panel_5.add(lblGroupeSanguin_1);
		
		JLabel lblIndicateurs = new JLabel("Indicateurs");
		lblIndicateurs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIndicateurs.setBounds(389, 146, 81, 24);
		panel_5.add(lblIndicateurs);
		
		textField_grpSangin = new JTextField();
		textField_grpSangin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_grpSangin.setColumns(10);
		textField_grpSangin.setBounds(505, 91, 93, 24);
		panel_5.add(textField_grpSangin);
		
		textField_poidsPatient = new JTextField();
		textField_poidsPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_poidsPatient.setColumns(10);
		textField_poidsPatient.setBounds(505, 57, 93, 24);
		panel_5.add(textField_poidsPatient);
		
		textField_taillePatient = new JTextField();
		textField_taillePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_taillePatient.setColumns(10);
		textField_taillePatient.setBounds(505, 21, 93, 24);
		panel_5.add(textField_taillePatient);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "IMC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(603, 11, 102, 80);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		textField_imc = new JTextField();
		textField_imc.setBounds(10, 18, 82, 51);
		panel_6.add(textField_imc);
		textField_imc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_imc.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(505, 130, 200, 86);
		panel_5.add(scrollPane_1);
		
		textField_indicBio = new JTextField();
		scrollPane_1.setViewportView(textField_indicBio);
		textField_indicBio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_indicBio.setColumns(10);
		
		JLabel lblBiologiques = new JLabel("biologiques :");
		lblBiologiques.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBiologiques.setBounds(389, 174, 81, 24);
		panel_5.add(lblBiologiques);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(153, 153, 255));
		panel_7.setBorder(new TitledBorder(null, "Ant\u00E9c\u00E9dents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(10, 249, 715, 189);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblVaccins = new JLabel("Vaccins : ");
		lblVaccins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVaccins.setBounds(10, 37, 67, 24);
		panel_7.add(lblVaccins);
		
		JLabel lblAllergies = new JLabel("Allergies : ");
		lblAllergies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAllergies.setBounds(395, 37, 67, 24);
		panel_7.add(lblAllergies);
		
		JLabel lblAntcdents = new JLabel("Ant\u00E9c\u00E9dents");
		lblAntcdents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAntcdents.setBounds(10, 118, 85, 17);
		panel_7.add(lblAntcdents);
		
		JLabel lblMedicaux = new JLabel("medicaux : ");
		lblMedicaux.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedicaux.setBounds(10, 140, 73, 17);
		panel_7.add(lblMedicaux);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(105, 98, 215, 76);
		panel_7.add(scrollPane_4);
		
		textField_antecedentMedic = new JTextField();
		scrollPane_4.setViewportView(textField_antecedentMedic);
		textField_antecedentMedic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_antecedentMedic.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(105, 11, 215, 76);
		panel_7.add(scrollPane_2);
		
		textField_vaccins = new JTextField();
		scrollPane_2.setViewportView(textField_vaccins);
		textField_vaccins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_vaccins.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(505, 12, 200, 76);
		panel_7.add(scrollPane_3);
		
		textField_allergies = new JTextField();
		scrollPane_3.setViewportView(textField_allergies);
		textField_allergies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_allergies.setColumns(10);
		
		JLabel label = new JLabel("Ant\u00E9c\u00E9dents");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(395, 118, 85, 17);
		panel_7.add(label);
		
		JLabel lblFamiliaux = new JLabel("familiaux : ");
		lblFamiliaux.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFamiliaux.setBounds(395, 140, 73, 17);
		panel_7.add(lblFamiliaux);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(505, 98, 200, 76);
		panel_7.add(scrollPane_5);
		
		textField_antecedentFami = new JTextField();
		scrollPane_5.setViewportView(textField_antecedentFami);
		textField_antecedentFami.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_antecedentFami.setColumns(10);
		
		textField_rechercherDossier = new JTextField();
		textField_rechercherDossier.setColumns(10);
		textField_rechercherDossier.setBounds(10, 444, 507, 30);
		panel_1.add(textField_rechercherDossier);
		
		JButton btnRechercherDossier = new JButton("Rechercher");
		btnRechercherDossier.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRechercherDossier.setBounds(570, 444, 155, 30);
		panel_1.add(btnRechercherDossier);
		
		JButton btnHome = new JButton("");
		btnHome.setBounds(718, 13, 52, 25);
        framePatient.getContentPane().add(btnHome);
        Image iconHome = new ImageIcon(this.getClass().getResource("/iconHome3.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        btnHome.setIcon(new ImageIcon(iconHome));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.frame.setVisible(true);
				framePatient.setVisible(false);
			}
		});
		btnHome.setBounds(705, 13, 65, 25);
		framePatient.getContentPane().add(btnHome);
	}

	/**
	 * Méthode qui recupère les infos medecin via une requête select et en paramètre on passe une jlist
	 * @param liste2
	 */
	private void listeMedecin(JList liste2) {
		lstMedecin.clear();
		ResultSet resM = MethodesSql.afficherMedecins(uneConnexion.getConnection());
		
		try {
			while(resM.next()) {
				Medecin unMedecin = new Medecin();
				unMedecin.setId(resM.getInt("id_medecin"));
				unMedecin.setNom(resM.getString("nomM"));
				unMedecin.setPrenom(resM.getString("prenomM"));
				unMedecin.setMail(resM.getString("mailM"));
				unMedecin.setVille(resM.getString("villeM"));
				unMedecin.setCp(resM.getString("cpM"));
				unMedecin.setTel(resM.getString("telM"));
				unMedecin.setSpecialite(resM.getString("intitule"));
				lstMedecin.add(unMedecin);
			}
			liste2.setListData(lstMedecin.toArray());
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
}
