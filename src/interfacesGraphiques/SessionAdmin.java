package interfacesGraphiques;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.UnexpectedException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.mysql.cj.protocol.Resultset;
//import com.sun.glass.events.MouseEvent;

import fr.gestion.rdv.Connexion;
import fr.gestion.rdv.Medecin;
import fr.gestion.rdv.MethodesSql;
import fr.gestion.rdv.Patient;

import javax.swing.JRadioButton;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;

import java.awt.Font;
import java.awt.Image;

import javax.swing.border.EtchedBorder;

/**
 * Fen�tre de la session administrateur ,
 * il g�re les patients et les medecins mais pas les demandes de rdv
 * @author maxime , eric
 *
 */
public class SessionAdmin {

	public JFrame frmEspaceAdministrateur;
	private JTextField textField_4;
	private JTextField textField_NumSecuPatient;
	private JTextField textField_NomPatient;
	private JTextField textField_PrenomPatient;
	private JTextField textField_TelPatient;
	private JTextField textField_EmailPatient;
	private JTextField textField_AdressPatient;
	private JTextField textField_VillePatient;
	private JTextField textField_CodePostPatient;
	private JTextField textField_nomMedecin;
	private JTextField textField_prenomMedecin;
	private JTextField textField_mailMedecin;
	private JTextField textField_adrMedecin;
	private JTextField textField_villeMedecin;
	private JTextField textField_cpMedecin;
	private JTextField textField_telMedecin;
	private JTextField textField;
	private Connexion uneConnexion = new Connexion(); //Fait la connexion avec la bdd
	private ArrayList<Patient> lstPatients = new ArrayList<Patient>(); //Stock dans un tableau les patients et permet de r�cuperer les infos
	private ArrayList<Medecin> lstMedecins = new ArrayList<Medecin>();//Stock dans un tableau les medecins et permet de r�cuperer les infos
	private Medecin unMedecin;
	private Patient unPatient;
	private int pSS, pId, pSpecialite;
	private String pSexe;
	private String pNom, pPrenom, pTel, pEmail, pAdresse, pVille, pCp;
	private JTextField textField_telMedecin_1;


	/**
	 * Create the application.
	 */
	public SessionAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/**
		 * Cr�ation de la fen�tre espace administrateur
		 */
		frmEspaceAdministrateur = new JFrame();
		frmEspaceAdministrateur.setResizable(false);
		frmEspaceAdministrateur.setTitle("Espace administrateur");
		frmEspaceAdministrateur.setBounds(100, 100, 800, 600);
		frmEspaceAdministrateur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEspaceAdministrateur.setLocationRelativeTo(null);
		JList list_2 = new JList(); //liste des medecins
		JComboBox comboBox_medecin = new JComboBox(); //comboBox qui contient les sp�cialit�s des medecins
		listeMedecin(list_2); //m�thode qui affiche la liste des medecins via requ�te
		JRadioButton rdbtnSexeH = new JRadioButton("H");
		JRadioButton rdbtnSexeF = new JRadioButton("F");
		
		/**
		 * On groupe les boutons pour �viter de selectionner deux boutons en m�me temps
		 */
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSexeF);
		group.add(rdbtnSexeH);
		rdbtnSexeF.setText("F");
		rdbtnSexeH.setText("H");

		/**
		 * m�thode qui permet de s�lectionner un medecin via le clic de la souris et
		 * affiche ses informations dans les diff�rents textfield
		 */
		list_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = list_2.getSelectedIndex();
				unMedecin = lstMedecins.get(i);
				pId = unMedecin.getId();
				textField_nomMedecin.setText(unMedecin.getNom());
				textField_prenomMedecin.setText(unMedecin.getPrenom());
				textField_adrMedecin.setText(unMedecin.getAdresse());
				textField_cpMedecin.setText(unMedecin.getCp());
				textField_mailMedecin.setText(unMedecin.getMail());
				textField_villeMedecin.setText(unMedecin.getVille());
				textField_telMedecin_1.setText(unMedecin.getTel());
				

			}
		}); //fin de la m�thode
		frmEspaceAdministrateur.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(33, 38, 722, 514);
		tabbedPane_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmEspaceAdministrateur.getContentPane().add(tabbedPane_1);


		JPanel panel_GestionMedecin = new JPanel();
		panel_GestionMedecin.setBackground(new Color(204, 153, 255));
		tabbedPane_1.addTab("Gestion des m�decins", null, panel_GestionMedecin, null);
		panel_GestionMedecin.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 153, 255));
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Liste des M\u00E9decins : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 697, 170);
		panel_GestionMedecin.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 21, 677, 138);
		panel_1.add(scrollPane_2);

		scrollPane_2.setViewportView(list_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 153, 255));
		panel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Fiche informations M\u00E9decins : ", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(10, 204, 697, 235);
		panel_GestionMedecin.add(panel_2);
		panel_2.setLayout(null);

		JLabel label = new JLabel("Nom :");
		label.setBounds(10, 21, 57, 27);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(label);

		JLabel lblPrnom_1 = new JLabel("Pr\u00E9nom :");
		lblPrnom_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrnom_1.setBounds(10, 64, 71, 30);
		panel_2.add(lblPrnom_1);

		JLabel lblSpcialite = new JLabel("Sp\u00E9cialit\u00E9e :");
		lblSpcialite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpcialite.setBounds(10, 104, 80, 30);
		panel_2.add(lblSpcialite);

		/**
		 * On r�cup�re les sp�cialit�s via la m�thode pour afficher les sp�cialit� (via requ�te select) , 
		 * puis ins�re dans la comboBox
		 */
		comboBox_medecin.setFont(new Font("Tahoma", Font.PLAIN, 14));		
		ResultSet resSpecialite = MethodesSql.afficherSpecialite(uneConnexion.getConnection());
		try {
			while (resSpecialite.next()) {
				comboBox_medecin.addItem(resSpecialite.getString("intitule"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBox_medecin.setBounds(122, 106, 155, 30);
		panel_2.add(comboBox_medecin);

		textField_nomMedecin = new JTextField();
		textField_nomMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_nomMedecin.setColumns(10);
		textField_nomMedecin.setBounds(122, 20, 155, 30);
		panel_2.add(textField_nomMedecin);

		textField_prenomMedecin = new JTextField();
		textField_prenomMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_prenomMedecin.setColumns(10);
		textField_prenomMedecin.setBounds(122, 65, 155, 30);
		panel_2.add(textField_prenomMedecin);

		JLabel lblNrTel = new JLabel("Nr tel :");
		lblNrTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNrTel.setBounds(10, 155, 80, 30);
		panel_2.add(lblNrTel);

		textField_telMedecin_1 = new JTextField();
		textField_telMedecin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_telMedecin_1.setColumns(10);
		textField_telMedecin_1.setBounds(122, 156, 155, 30);
		panel_2.add(textField_telMedecin_1);

		JLabel lblEMail = new JLabel("e Mail :");
		lblEMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEMail.setBounds(368, 21, 71, 30);
		panel_2.add(lblEMail);

		JLabel lblAdresse_1 = new JLabel("Adresse :");
		lblAdresse_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdresse_1.setBounds(368, 64, 71, 30);
		panel_2.add(lblAdresse_1);

		JLabel lblVille_1 = new JLabel("Ville :");
		lblVille_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVille_1.setBounds(368, 104, 71, 30);
		panel_2.add(lblVille_1);

		JLabel lblCpMedecin = new JLabel("CP :");
		lblCpMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpMedecin.setBounds(368, 155, 71, 30);
		panel_2.add(lblCpMedecin);

		textField_mailMedecin = new JTextField();
		textField_mailMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_mailMedecin.setColumns(10);
		textField_mailMedecin.setBounds(498, 21, 155, 30);
		panel_2.add(textField_mailMedecin);

		textField_adrMedecin = new JTextField();
		textField_adrMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_adrMedecin.setColumns(10);
		textField_adrMedecin.setBounds(498, 62, 155, 30);
		panel_2.add(textField_adrMedecin);

		textField_villeMedecin = new JTextField();
		textField_villeMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_villeMedecin.setColumns(10);
		textField_villeMedecin.setBounds(498, 105, 155, 30);
		panel_2.add(textField_villeMedecin);

		textField_cpMedecin = new JTextField();
		textField_cpMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_cpMedecin.setColumns(10);
		textField_cpMedecin.setBounds(498, 156, 155, 30);
		panel_2.add(textField_cpMedecin);

		/**
		 * Ajoute un medecin dans la base de donn�e via une requ�te inscrit dans une m�thode ,
		 * on recup�re les informations des textfield dans les variables pour l'ins�rer dans cette m�thode
		 */
		JButton button_ajouterMedecin = new JButton("Ajouter ");
		button_ajouterMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pNom = textField_nomMedecin.getText();
				pPrenom = textField_prenomMedecin.getText();
				pEmail = textField_mailMedecin.getText();
				pAdresse = textField_adrMedecin.getText();
				pVille = textField_villeMedecin.getText();
				pCp = textField_cpMedecin.getText();
				pTel = textField_telMedecin_1.getText();
				pSpecialite = comboBox_medecin.getSelectedIndex();

				MethodesSql.ajouterMedecin(uneConnexion.getConnection(), pNom, pPrenom, pEmail, pAdresse, pVille, pCp,
						pTel, pSpecialite); //m�thode qui ajoute un medecin dans la base de donn�e
				listeMedecin(list_2);
			}
		}); //fin du bouton Ajouter
		button_ajouterMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_ajouterMedecin.setBounds(174, 194, 101, 30);
		panel_2.add(button_ajouterMedecin);

		/**
		 * Supprime un medecin via une requ�te inscrit dans une m�thode
		 */
		JButton button_supprimerMedecin = new JButton("Supprimer");
		button_supprimerMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MethodesSql.supprimerMedecin(uneConnexion.getConnection(), pId); //m�thode qui supprime un medecin dans la bdd
				effacerTextMedecin(); //m�thode pour effacer des informations inscrit dans les textfield
				listeMedecin(list_2); //affiche la liste des m�decins dans la jlist medecin

			}
		}); //fin du bouton supprimer
		button_supprimerMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_supprimerMedecin.setBounds(498, 194, 110, 30);
		panel_2.add(button_supprimerMedecin);

		/**
		 * Modifie un medecin via une requ�te , comme pour le bouton ajouter medecin ,
		 * on recup�re les informations des textfield pour ensuite les placer dans des variables et 
		 * ainsi inscrire dans la m�thode qui modifie le medecin dans la bdd
		 * 
		 */
		JButton button_modifierMedecin = new JButton("Modifier");
		button_modifierMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pNom = textField_nomMedecin.getText();
				pPrenom = textField_prenomMedecin.getText();
				pEmail = textField_mailMedecin.getText();
				pAdresse = textField_adrMedecin.getText();
				pVille = textField_villeMedecin.getText();
				pCp = textField_cpMedecin.getText();
				pTel = textField_telMedecin_1.getText();
				pSpecialite = comboBox_medecin.getSelectedIndex();
				MethodesSql.modifierMedecin(uneConnexion.getConnection(), pNom, pPrenom, pEmail, pAdresse, pVille, pCp,
						pTel, pSpecialite, pId);
				listeMedecin(list_2);
			}
		}); //fin du bouton modifier
		button_modifierMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_modifierMedecin.setBounds(333, 194, 101, 30);
		panel_2.add(button_modifierMedecin);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(10, 450, 553, 30);
		panel_GestionMedecin.add(textField);

		JButton button_rechercherMedecin = new JButton("Rechercher");
		button_rechercherMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_rechercherMedecin.setBounds(584, 450, 123, 30);
		panel_GestionMedecin.add(button_rechercherMedecin);
		JList list_1 = new JList(); //liste des patients

		listePatient(list_1);

		/**
		 * m�thode qui permet de cliquer sur un patient dans la jlist et ainsi r�cuperer ses informations dans les textfield
		 */
		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = list_1.getSelectedIndex();
				unPatient = lstPatients.get(i);
				pId = unPatient.getId();
				String pSS = String.valueOf(unPatient.getSS());
				textField_NomPatient.setText(unPatient.getNom());
				textField_PrenomPatient.setText(unPatient.getPrenom());
				textField_AdressPatient.setText(unPatient.getAdresse());
				textField_CodePostPatient.setText(unPatient.getCp());
				textField_NumSecuPatient.setText(pSS);
				textField_VillePatient.setText(unPatient.getVille());
				textField_EmailPatient.setText(unPatient.getMail());
				textField_TelPatient.setText(unPatient.getTel());
			}
		}); //fin de la m�thode

		JPanel panel_Patients = new JPanel();
		panel_Patients.setBackground(new Color(204, 153, 255));
		tabbedPane_1.addTab("Gestion des patients", null, panel_Patients, null);
		panel_Patients.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 255));
		panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setBounds(10, 174, 673, 262);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Fiche informations du Patient : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Patients.add(panel);
		panel.setLayout(null);

		JLabel lblNumroSs = new JLabel("Num\u00E9ro SS : ");
		lblNumroSs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumroSs.setBounds(10, 34, 101, 19);
		panel.add(lblNumroSs);

		JLabel lblNomPatient = new JLabel("Nom :");
		lblNomPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomPatient.setBounds(10, 75, 80, 14);
		panel.add(lblNomPatient);

		JLabel lblPrenomPatient = new JLabel("Prenom :");
		lblPrenomPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrenomPatient.setBounds(10, 100, 80, 30);
		panel.add(lblPrenomPatient);

		JLabel lblNumTelPatient = new JLabel("No tel : ");
		lblNumTelPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumTelPatient.setBounds(10, 138, 80, 30);
		panel.add(lblNumTelPatient);

		JLabel lblSexePatient = new JLabel("Sexe : ");
		lblSexePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexePatient.setBounds(10, 179, 80, 30);
		panel.add(lblSexePatient);

		/**
		 * Ajoute un patient via une m�thode ajouterPatient qui l'ins�re dans la bdd
		 */
		JButton btnAjouterPatient = new JButton("Ajouter ");
		btnAjouterPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pSS = Integer.valueOf(textField_NumSecuPatient.getText());
				pNom = textField_NomPatient.getText();
				pPrenom = textField_PrenomPatient.getText();
				pTel = textField_TelPatient.getText();
				pEmail = textField_EmailPatient.getText();
				pAdresse = textField_AdressPatient.getText();
				pVille = textField_VillePatient.getText();
				pCp = textField_CodePostPatient.getText();
				if(rdbtnSexeF.isSelected()) {
					pSexe = "F";
					
				}
				if(rdbtnSexeH.isSelected()) {
					pSexe = "H";
				}
				MethodesSql.ajouterPatient(uneConnexion.getConnection(), pSS, pNom, pPrenom, pTel, pEmail, pAdresse,
						pVille, pCp , pSexe);

				listePatient(list_1);
				effacerText();
			}

		}); //fin du bouton ajouter
		btnAjouterPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAjouterPatient.setBounds(133, 219, 101, 30);
		panel.add(btnAjouterPatient);

		/**
		 * Modifie un patient via une m�thode qui le modifie dans la bdd
		 */
		JButton btnModifierPatient = new JButton("Modifier");
		btnModifierPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pSS = Integer.valueOf(textField_NumSecuPatient.getText());
				pNom = textField_NomPatient.getText();
				pPrenom = textField_PrenomPatient.getText();
				pTel = textField_TelPatient.getText();
				pEmail = textField_EmailPatient.getText();
				pAdresse = textField_AdressPatient.getText();
				pVille = textField_VillePatient.getText();
				pCp = textField_CodePostPatient.getText();
				MethodesSql.modifierPatient(uneConnexion.getConnection(), pNom, pPrenom, pTel, pEmail, pAdresse, pVille,
						pCp, pId ,pSS);
				listePatient(list_1);
			}
		}); //fin du bouton modifier
		btnModifierPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifierPatient.setBounds(303, 219, 101, 30);
		panel.add(btnModifierPatient);

		/**
		 * Supprime un patient de la bdd via une m�thode
		 */
		JButton btnSupprimerPatient = new JButton("Supprimer");
		btnSupprimerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MethodesSql.supprimerPatient(uneConnexion.getConnection(), pId);
				effacerText();
				listePatient(list_1);
			}
		});//fin du bouton supprimer
		btnSupprimerPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSupprimerPatient.setBounds(455, 220, 110, 30);
		panel.add(btnSupprimerPatient);

		/**
		 * Bouton sexe Homme
		 */
		rdbtnSexeH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSexeH.setBounds(121, 182, 55, 30);
		panel.add(rdbtnSexeH);

		/**
		 * Bouton sexe Femme
		 */
		rdbtnSexeF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSexeF.setBounds(211, 182, 55, 30);
		panel.add(rdbtnSexeF);
				

		textField_NumSecuPatient = new JTextField();
		textField_NumSecuPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_NumSecuPatient.setColumns(10);
		textField_NumSecuPatient.setBounds(121, 33, 145, 30);
		panel.add(textField_NumSecuPatient);

		textField_NomPatient = new JTextField();
		textField_NomPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_NomPatient.setColumns(10);
		textField_NomPatient.setBounds(121, 72, 145, 30);
		panel.add(textField_NomPatient);

		textField_PrenomPatient = new JTextField();
		textField_PrenomPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_PrenomPatient.setColumns(10);
		textField_PrenomPatient.setBounds(121, 110, 145, 30);
		panel.add(textField_PrenomPatient);

		textField_TelPatient = new JTextField();
		textField_TelPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_TelPatient.setColumns(10);
		textField_TelPatient.setBounds(121, 150, 145, 30);
		panel.add(textField_TelPatient);

		JLabel lblEmailPatient = new JLabel("Email : ");
		lblEmailPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailPatient.setBounds(337, 36, 74, 30);
		panel.add(lblEmailPatient);

		JLabel lblAdressePatient = new JLabel("Adresse : ");
		lblAdressePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdressePatient.setBounds(337, 75, 67, 30);
		panel.add(lblAdressePatient);

		JLabel lblVillePatient = new JLabel("Ville : ");
		lblVillePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVillePatient.setBounds(337, 113, 50, 30);
		panel.add(lblVillePatient);

		JLabel lblCpPatient = new JLabel("CP : ");
		lblCpPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpPatient.setBounds(337, 153, 50, 30);
		panel.add(lblCpPatient);

		textField_EmailPatient = new JTextField();
		textField_EmailPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_EmailPatient.setColumns(10);
		textField_EmailPatient.setBounds(428, 33, 145, 30);
		panel.add(textField_EmailPatient);

		textField_AdressPatient = new JTextField();
		textField_AdressPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_AdressPatient.setColumns(10);
		textField_AdressPatient.setBounds(428, 75, 145, 30);
		panel.add(textField_AdressPatient);

		textField_VillePatient = new JTextField();
		textField_VillePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_VillePatient.setColumns(10);
		textField_VillePatient.setBounds(428, 112, 145, 30);
		panel.add(textField_VillePatient);

		textField_CodePostPatient = new JTextField();
		textField_CodePostPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_CodePostPatient.setColumns(10);
		textField_CodePostPatient.setBounds(428, 150, 145, 30);
		panel.add(textField_CodePostPatient);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setBounds(10, 447, 540, 30);
		textField_4.setText("");
		textField_4.setColumns(10);
		panel_Patients.add(textField_4);

		JButton button = new JButton("Rechercher");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(560, 447, 123, 30);
		panel_Patients.add(button);

		JPanel panel_ListePatient = new JPanel();
		panel_ListePatient.setBackground(new Color(153, 153, 255));
		panel_ListePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_ListePatient.setBorder(
				new TitledBorder(null, "Liste des Patients : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ListePatient.setBounds(10, 11, 673, 152);
		panel_Patients.add(panel_ListePatient);
		panel_ListePatient.setLayout(null);

		JScrollPane scrollPane_listPatients = new JScrollPane();
		scrollPane_listPatients.setBounds(10, 24, 653, 117);
		panel_ListePatient.add(scrollPane_listPatients);

		scrollPane_listPatients.setViewportView(list_1);
		
		JButton btnHome = new JButton("");
		btnHome.setBounds(719, 13, 63, 25);
        frmEspaceAdministrateur.getContentPane().add(btnHome);
        Image iconHome = new ImageIcon(this.getClass().getResource("/iconHome3.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        btnHome.setIcon(new ImageIcon(iconHome));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.frame.setVisible(true);
				frmEspaceAdministrateur.setVisible(false);
			}
		});
		frmEspaceAdministrateur.getContentPane().add(btnHome);
	}

	/**
	 * M�thode qui r�cup�re les informations du medecin via une requ�te et en param�tre on passe une jlist pour
	 * pouvoir les afficher
	 * @param liste2
	 */
	private void listeMedecin(JList liste2) {
		lstMedecins.clear();
		ResultSet resM = MethodesSql.afficherMedecins(uneConnexion.getConnection());

		try {
			while (resM.next()) {
				Medecin unMedecin = new Medecin();
				unMedecin.setId(resM.getInt("id_medecin"));
				unMedecin.setNom(resM.getString("nomM"));
				unMedecin.setPrenom(resM.getString("prenomM"));
				unMedecin.setMail(resM.getString("mailM"));
				unMedecin.setVille(resM.getString("villeM"));
				unMedecin.setCp(resM.getString("cpM"));
				unMedecin.setTel(resM.getString("telM"));
				unMedecin.setSpecialite(resM.getString("intitule"));
				lstMedecins.add(unMedecin);
			}
			liste2.setListData(lstMedecins.toArray());
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}

	/**
	 * M�thode qui affiche les patients via requ�te et passe en param�tre une jlist pour afficher ses informations
	 * @param liste1
	 */
	private void listePatient(JList liste1) {
		lstPatients.clear();
		ResultSet res = MethodesSql.afficherPatients(uneConnexion.getConnection());
		try {
			while (res.next()) {

				Patient unPatient = new Patient();
				unPatient.setId(res.getInt("id_patient"));
				unPatient.setSS(res.getInt("SS"));
				unPatient.setNom(res.getString("nomP"));
				unPatient.setPrenom(res.getString("prenomP"));
				unPatient.setTel(res.getString("telP"));
				unPatient.setSexe(res.getString("sexeP"));
				unPatient.setMail(res.getString("mailP"));
				unPatient.setAdresse(res.getString("adresseP"));
				unPatient.setVille(res.getString("villeP"));
				unPatient.setCp(res.getString("cpP"));
				lstPatients.add(unPatient);

			}

			liste1.setListData(lstPatients.toArray());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * M�thode pour effacer les textfield du patient
	 */
	private void effacerText() {
		textField_AdressPatient.setText("");
		textField_CodePostPatient.setText("");
		textField_EmailPatient.setText("");
		textField_NomPatient.setText("");
		textField_NumSecuPatient.setText("");
		textField_PrenomPatient.setText("");
		textField_TelPatient.setText("");
		textField_VillePatient.setText("");
		
	}

	/**
	 * M�thode pour effacer les textfield du medecin
	 */
	private void effacerTextMedecin() {
		textField_adrMedecin.setText("");
		textField_cpMedecin.setText("");
		textField_mailMedecin.setText("");
		textField_nomMedecin.setText("");
		textField_prenomMedecin.setText("");
		textField_telMedecin_1.setText("");
		textField_villeMedecin.setText("");

	}
}
