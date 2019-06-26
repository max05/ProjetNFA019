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
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class SessionAdmin {

	private JFrame frmEspaceAdministrateur;
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
	private Connexion uneConnexion = new Connexion();
	private ArrayList<Patient> lstPatients = new ArrayList<Patient>();
	private ArrayList<Medecin> lstMedecins = new ArrayList<Medecin>();
	private Medecin unMedecin;
	private Patient unPatient;
	private int pSS, pId , pSpecialite;
	private String pNom, pPrenom, pTel, pEmail, pAdresse, pVille, pCp;
	private JTextField textField_telMedecin_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionAdmin window = new SessionAdmin();
					window.frmEspaceAdministrateur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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

		frmEspaceAdministrateur = new JFrame();
		frmEspaceAdministrateur.setResizable(false);
		frmEspaceAdministrateur.setTitle("Espace administrateur");
		frmEspaceAdministrateur.setBounds(100, 100, 800, 600);
		frmEspaceAdministrateur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEspaceAdministrateur.getContentPane().setLayout(null);
		frmEspaceAdministrateur.setLocationRelativeTo(null);
		JList list_1 = new JList();
		JList list_2 = new JList();
		JComboBox comboBox_medecin = new JComboBox();
				
		listePatient(list_1);
		listeMedecin(list_2);
		
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
		});
		
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
		});
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane_1.setBounds(33, 38, 722, 514);
		frmEspaceAdministrateur.getContentPane().add(tabbedPane_1);

		/*
		 * JScrollBar scrollBar = new JScrollBar(); scrollBar.setBounds(665, 32, 17,
		 * 171); panel_Medecin.add(scrollBar);
		 */
		/*
		 * panel_Patients.setBorder(new TitledBorder(null, "Fiche informations Patient",
		 * TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 */

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
				MethodesSql.ajouterPatient(uneConnexion.getConnection(), pSS, pNom, pPrenom, pTel, pEmail, pAdresse,
						pVille, pCp);
				
				listePatient(list_1);
				effacerText();
			}

		});
		btnAjouterPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAjouterPatient.setBounds(133, 219, 101, 30);
		panel.add(btnAjouterPatient);

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
				MethodesSql.modifierPatient(uneConnexion.getConnection(), pNom, pPrenom, pTel, pEmail, pAdresse, pVille,pCp, pId);
				listePatient(list_1);
			}
		});
		btnModifierPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifierPatient.setBounds(303, 219, 101, 30);
		panel.add(btnModifierPatient);

		JButton btnSupprimerPatient = new JButton("Supprimer");
		btnSupprimerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MethodesSql.supprimerPatient(uneConnexion.getConnection(), pId);
				effacerText();
				listePatient(list_1);
			}
		});
		btnSupprimerPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSupprimerPatient.setBounds(455, 220, 110, 30);
		panel.add(btnSupprimerPatient);

		JRadioButton rdbtnSexeH = new JRadioButton("H");
		rdbtnSexeH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSexeH.setBounds(121, 182, 55, 30);
		panel.add(rdbtnSexeH);

		JRadioButton rdbtnSexeF = new JRadioButton("F");
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

		JPanel panel_GestionMedecin = new JPanel();
		panel_GestionMedecin.setBackground(new Color(204, 153, 255));
		tabbedPane_1.addTab("Gestion des médecins", null, panel_GestionMedecin, null);
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


		comboBox_medecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ResultSet resSpecialite = MethodesSql.afficherSpecialite(uneConnexion.getConnection());
		try {
			while(resSpecialite.next()) {
				comboBox_medecin.addItem(resSpecialite.getString("intitule"));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		comboBox_medecin.setModel(new DefaultComboBoxModel(new String[] { "M\u00E9decine g\u00E9n\u00E9rale",
//				"G\u00E9rontologie", "P\u00E9diatrie", "Psychologie", "Dermathologie", "Dentiste" }));
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
			
			MethodesSql.ajouterMedecin(uneConnexion.getConnection(), pNom, pPrenom, pEmail, pAdresse, pVille, pCp, pTel, pSpecialite);
			listeMedecin(list_2);
			}
		});
		button_ajouterMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_ajouterMedecin.setBounds(174, 194, 101, 30);
		panel_2.add(button_ajouterMedecin);

		JButton button_supprimerMedecin = new JButton("Supprimer");
		button_supprimerMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MethodesSql.supprimerMedecin(uneConnexion.getConnection(), pId);
				effacerTextMedecin();
				listeMedecin(list_2);
				
			}
		});
		button_supprimerMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_supprimerMedecin.setBounds(498, 194, 110, 30);
		panel_2.add(button_supprimerMedecin);

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
				MethodesSql.modifierMedecin(uneConnexion.getConnection(), pNom, pPrenom, pEmail, pAdresse, pVille, pCp, pTel, pSpecialite, pId);
				listeMedecin(list_2);
			}
		});
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
	}
	
	private void listeMedecin(JList liste2) {
		lstMedecins.clear();
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
				lstMedecins.add(unMedecin);
			}
			liste2.setListData(lstMedecins.toArray());
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
	
	private void listePatient(JList liste1) {
		lstPatients.clear();
		ResultSet res = MethodesSql.afficherPatients(uneConnexion.getConnection());

		try {
			while (res.next()) {

				Patient unPatient = new Patient();
				unPatient.setId(res.getInt("id_patient"));
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
