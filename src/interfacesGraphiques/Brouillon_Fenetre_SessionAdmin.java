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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class Brouillon_Fenetre_SessionAdmin {

	private JFrame frame;
	private JTextField textField_NomMedecin;
	private JTextField textField_PrenomMedecin;
	private JTextField textField_NumMedecin;
	private JTextField textField_EmailMedecin;
	private JTextField textField_AdresseMedecin;
	private JTextField textField_VilleMedecin;
	private JTextField textField_CodePostMedecin;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_NumSecuPatient;
	private JTextField textField_NomPatient;
	private JTextField textField_PrenomPatient;
	private JTextField textField_TelPatient;
	private JTextField textField_EmailPatient;
	private JTextField textField_AdressPatient;
	private JTextField textField_VillePatient;
	private JTextField textField_CodePostPatient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Brouillon_Fenetre_SessionAdmin window = new Brouillon_Fenetre_SessionAdmin();
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
	public Brouillon_Fenetre_SessionAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 801, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane_1.setBounds(33, 38, 722, 564);
		frame.getContentPane().add(tabbedPane_1);
		
		/*JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(665, 32, 17, 171);
		panel_Medecin.add(scrollBar);*/
		/*panel_Patients.setBorder(new TitledBorder(null, "Fiche informations Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));*/
		
		JPanel panel_Patients = new JPanel();
		tabbedPane_1.addTab("Gestion des patients", null, panel_Patients, null);
		panel_Patients.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setBounds(10, 187, 643, 272);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Fiche informations du Patient : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		lblPrenomPatient.setBounds(10, 113, 80, 14);
		panel.add(lblPrenomPatient);
		
		JLabel lblNumTelPatient = new JLabel("No tel : ");
		lblNumTelPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumTelPatient.setBounds(10, 153, 80, 14);
		panel.add(lblNumTelPatient);
		
		JLabel lblSexePatient = new JLabel("Sexe : ");
		lblSexePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexePatient.setBounds(10, 193, 80, 14);
		panel.add(lblSexePatient);
		
		JButton btnAjouterPatient = new JButton("Ajouter ");
		btnAjouterPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAjouterPatient.setBounds(133, 227, 101, 23);
		panel.add(btnAjouterPatient);
		
		JButton btnModifierPatient = new JButton("Modifier");
		btnModifierPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifierPatient.setBounds(292, 227, 101, 23);
		panel.add(btnModifierPatient);
		
		JButton btnSupprimerPatient = new JButton("Supprimer");
		btnSupprimerPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSupprimerPatient.setBounds(455, 227, 110, 23);
		panel.add(btnSupprimerPatient);
		
		JRadioButton rdbtnSexeH = new JRadioButton("H");
		rdbtnSexeH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSexeH.setBounds(126, 189, 43, 23);
		panel.add(rdbtnSexeH);
		
		JRadioButton rdbtnSexeF = new JRadioButton("F");
		rdbtnSexeF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSexeF.setBounds(191, 189, 43, 23);
		panel.add(rdbtnSexeF);
		
		textField_NumSecuPatient = new JTextField();
		textField_NumSecuPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_NumSecuPatient.setColumns(10);
		textField_NumSecuPatient.setBounds(121, 33, 145, 20);
		panel.add(textField_NumSecuPatient);
		
		textField_NomPatient = new JTextField();
		textField_NomPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_NomPatient.setColumns(10);
		textField_NomPatient.setBounds(121, 72, 145, 20);
		panel.add(textField_NomPatient);
		
		textField_PrenomPatient = new JTextField();
		textField_PrenomPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_PrenomPatient.setColumns(10);
		textField_PrenomPatient.setBounds(121, 110, 145, 20);
		panel.add(textField_PrenomPatient);
		
		textField_TelPatient = new JTextField();
		textField_TelPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_TelPatient.setColumns(10);
		textField_TelPatient.setBounds(121, 150, 145, 20);
		panel.add(textField_TelPatient);
		
		JLabel lblEmailPatient = new JLabel("Email : ");
		lblEmailPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailPatient.setBounds(337, 36, 74, 19);
		panel.add(lblEmailPatient);
		
		JLabel lblAdressePatient = new JLabel("Adresse : ");
		lblAdressePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdressePatient.setBounds(337, 75, 67, 19);
		panel.add(lblAdressePatient);
		
		JLabel lblVillePatient = new JLabel("Ville : ");
		lblVillePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVillePatient.setBounds(337, 113, 50, 19);
		panel.add(lblVillePatient);
		
		JLabel lblCpPatient = new JLabel("CP : ");
		lblCpPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpPatient.setBounds(337, 153, 50, 19);
		panel.add(lblCpPatient);
		
		textField_EmailPatient = new JTextField();
		textField_EmailPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_EmailPatient.setColumns(10);
		textField_EmailPatient.setBounds(428, 33, 145, 20);
		panel.add(textField_EmailPatient);
		
		textField_AdressPatient = new JTextField();
		textField_AdressPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_AdressPatient.setColumns(10);
		textField_AdressPatient.setBounds(428, 75, 145, 20);
		panel.add(textField_AdressPatient);
		
		textField_VillePatient = new JTextField();
		textField_VillePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_VillePatient.setColumns(10);
		textField_VillePatient.setBounds(428, 112, 145, 20);
		panel.add(textField_VillePatient);
		
		textField_CodePostPatient = new JTextField();
		textField_CodePostPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_CodePostPatient.setColumns(10);
		textField_CodePostPatient.setBounds(428, 150, 145, 20);
		panel.add(textField_CodePostPatient);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setBounds(10, 483, 540, 30);
		textField_4.setText("");
		textField_4.setColumns(10);
		panel_Patients.add(textField_4);
		
		JButton button = new JButton("Rechercher");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(560, 482, 123, 30);
		panel_Patients.add(button);
		
		JPanel panel_ListePatient = new JPanel();
		panel_ListePatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_ListePatient.setBorder(new TitledBorder(null, "Liste des Patients : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ListePatient.setBounds(10, 11, 643, 152);
		panel_Patients.add(panel_ListePatient);
		panel_ListePatient.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 623, 115);
		panel_ListePatient.add(scrollPane_1);
		
		JList list_1 = new JList();
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_1.setViewportView(list_1);
		
		JPanel panel_GestionMedecin = new JPanel();
		tabbedPane_1.addTab("Gestion des médecins", null, panel_GestionMedecin, null);
		panel_GestionMedecin.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 26, 697, 167);
		panel_GestionMedecin.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("MS Gothic", Font.BOLD, 24));
		panel_2.setBorder(new TitledBorder(null, "blabla", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 224, 697, 241);
		panel_GestionMedecin.add(panel_2);
		
		
		JLabel lblEspaceAdministrateur = new JLabel("Espace administrateur");
		lblEspaceAdministrateur.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEspaceAdministrateur.setBounds(40, 11, 193, 26);
		frame.getContentPane().add(lblEspaceAdministrateur);
		
		JPanel panel_Medecin = new JPanel();
		panel_Medecin.setBounds(33, 64, 717, 538);
		frame.getContentPane().add(panel_Medecin);
		panel_Medecin.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 672, 171);
		panel_Medecin.add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel lbl_liste_medecins = new JLabel("Liste des Medecins");
		lbl_liste_medecins.setBounds(10, 0, 131, 25);
		panel_Medecin.add(lbl_liste_medecins);
		
		JLabel lblFicheInformationsMdecin = new JLabel("Fiche informations M\u00E9decin");
		lblFicheInformationsMdecin.setBounds(10, 246, 185, 14);
		panel_Medecin.add(lblFicheInformationsMdecin);
		
		JLabel lbl_email_Medecin = new JLabel("Email:");
		lbl_email_Medecin.setBounds(419, 290, 51, 14);
		panel_Medecin.add(lbl_email_Medecin);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom:");
		lblPrnom.setBounds(26, 328, 51, 14);
		panel_Medecin.add(lblPrnom);
		
		JLabel lblSpcialiste = new JLabel("Sp\u00E9cialiste:");
		lblSpcialiste.setBounds(26, 359, 63, 14);
		panel_Medecin.add(lblSpcialiste);
		
		JLabel lblNoTel = new JLabel("No Tel:");
		lblNoTel.setBounds(26, 398, 56, 14);
		panel_Medecin.add(lblNoTel);
		
		textField_NomMedecin = new JTextField();
		textField_NomMedecin.setBounds(115, 287, 145, 20);
		panel_Medecin.add(textField_NomMedecin);
		textField_NomMedecin.setColumns(10);
		
		JLabel label_1 = new JLabel("Nom:");
		label_1.setBounds(25, 290, 80, 14);
		panel_Medecin.add(label_1);
		
		textField_PrenomMedecin = new JTextField();
		textField_PrenomMedecin.setColumns(10);
		textField_PrenomMedecin.setBounds(115, 325, 145, 20);
		panel_Medecin.add(textField_PrenomMedecin);
		
		textField_NumMedecin = new JTextField();
		textField_NumMedecin.setColumns(10);
		textField_NumMedecin.setBounds(115, 395, 145, 20);
		panel_Medecin.add(textField_NumMedecin);
		
		textField_EmailMedecin = new JTextField();
		textField_EmailMedecin.setColumns(10);
		textField_EmailMedecin.setBounds(532, 287, 96, 20);
		panel_Medecin.add(textField_EmailMedecin);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(419, 328, 51, 14);
		panel_Medecin.add(lblAdresse);
		
		JLabel lblVille = new JLabel("Ville:");
		lblVille.setBounds(419, 359, 51, 14);
		panel_Medecin.add(lblVille);
		
		JLabel lblCp = new JLabel("CP:");
		lblCp.setBounds(419, 398, 51, 14);
		panel_Medecin.add(lblCp);
		
		textField_AdresseMedecin = new JTextField();
		textField_AdresseMedecin.setColumns(10);
		textField_AdresseMedecin.setBounds(532, 325, 96, 20);
		panel_Medecin.add(textField_AdresseMedecin);
		
		textField_VilleMedecin = new JTextField();
		textField_VilleMedecin.setColumns(10);
		textField_VilleMedecin.setBounds(532, 359, 96, 20);
		panel_Medecin.add(textField_VilleMedecin);
		
		textField_CodePostMedecin = new JTextField();
		textField_CodePostMedecin.setColumns(10);
		textField_CodePostMedecin.setBounds(532, 395, 96, 20);
		panel_Medecin.add(textField_CodePostMedecin);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjouter.setBounds(155, 436, 89, 23);
		panel_Medecin.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(292, 436, 89, 23);
		panel_Medecin.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(433, 436, 89, 23);
		panel_Medecin.add(btnSupprimer);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRechercher.setBounds(586, 491, 121, 23);
		panel_Medecin.add(btnRechercher);
		
		textField_3 = new JTextField();
		textField_3.setText("");
		textField_3.setBounds(30, 492, 540, 20);
		panel_Medecin.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox_Specialiste = new JComboBox();
		comboBox_Specialiste.setModel(new DefaultComboBoxModel(new String[] {"M\u00E9decine  g\u00E9n\u00E9raliste", "P\u00E9diatrie", "Psychatrie", "G\u00E9rontologie"}));
		comboBox_Specialiste.setBounds(115, 358, 145, 22);
		panel_Medecin.add(comboBox_Specialiste);
	}
	}


