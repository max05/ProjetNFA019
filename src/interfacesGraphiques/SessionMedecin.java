package interfacesGraphiques;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.mysql.cj.protocol.Resultset;

import fr.gestion.rdv.Connexion;
import fr.gestion.rdv.Medecin;
import fr.gestion.rdv.MethodesSql;
import fr.gestion.rdv.Patient;

import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * Fenêtre session medecin
 * @author maxime , eric
 *
 */
public class SessionMedecin {

	public JFrame frameMedecin;
	private JTextField textField_rechercherConsultation;
	private JTextField textField_rechercherDiagnostic;
	private ArrayList<Patient> lstPatients = new ArrayList<Patient>();
	private ArrayList<Medecin> lstMedecins = new ArrayList<Medecin>();
	private Patient unPatient = new Patient();
	Connexion uneConnexion = new Connexion();
	private JTextField txtNomPatient;
	private JTextField txtPrenomPatient;
	private JTextField txtSsPatient;
	private JTextField txtTelPatient;
	private JTextField txtMailPatient;
	private JTextField txtAdressePatient;
	private JTextField txtVillePatient;
	private int pId;

	/**
	 * Create the application.
	 */
	public SessionMedecin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/**
		 * Création de la fenêtre medecin
		 */
		frameMedecin = new JFrame();
		frameMedecin.setTitle("Espace Medecin : Chauveau");
		frameMedecin.getContentPane().setForeground(new Color(0, 0, 0));
		frameMedecin.setBounds(100, 100, 800, 600);
		frameMedecin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMedecin.setLocationRelativeTo(null);
		JList listPatients = new JList(); //création de la jlist patient
		JList listConsultations = new JList();
		listePatient(listPatients);//affiche les patients via une méthode
		
		/**
		 * Action au moment de sélectionner un patient va récupérer les infos et 
		 * les mettres dans les textfield
		 */
		listPatients.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = listPatients.getSelectedIndex();
				unPatient = lstPatients.get(i);
				pId = unPatient.getId();
				String pSStoString = String.valueOf(unPatient.getSS());
				txtNomPatient.setText(unPatient.getNom());
				txtPrenomPatient.setText(unPatient.getPrenom());
				txtAdressePatient.setText(unPatient.getAdresse());
				txtMailPatient.setText(unPatient.getMail());
				txtSsPatient.setText(pSStoString);
				txtVillePatient.setText(unPatient.getVille());
				txtTelPatient.setText(unPatient.getTel());
				listeConsultation(listConsultations , pId);
				
				
				
			}
		});
		frameMedecin.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 28, 861, 534);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frameMedecin.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 255));
		tabbedPane.addTab("Gestion des consultations", null, panel, null);
		panel.setLayout(null);
		
		textField_rechercherConsultation = new JTextField();
		textField_rechercherConsultation.setBounds(10, 11, 594, 30);
		textField_rechercherConsultation.setColumns(10);
		panel.add(textField_rechercherConsultation);
		
		JButton btnRechercherConsultation = new JButton("Rechercher");
		btnRechercherConsultation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRechercherConsultation.setBounds(643, 9, 133, 31);
		panel.add(btnRechercherConsultation);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(153, 153, 255));
		panel_4.setBounds(10, 59, 767, 166);
		panel_4.setBorder(new TitledBorder(null, "Liste des patients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 744, 132);
		panel_4.add(scrollPane);
		

		scrollPane.setViewportView(listPatients);
		
		JPanel panel_infoPatient = new JPanel();
		panel_infoPatient.setBackground(new Color(153, 153, 255));
		panel_infoPatient.setForeground(new Color(0, 0, 0));
		panel_infoPatient.setBounds(311, 236, 465, 259);
		panel_infoPatient.setBorder(new TitledBorder(null, "Fiche informations Patient : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_infoPatient);
		panel_infoPatient.setLayout(null);
		
		JLabel lbl_logoMedecin4 = new JLabel("");
		lbl_logoMedecin4.setBounds(365, 23, 90, 90);
		panel_infoPatient.add(lbl_logoMedecin4);
		Image logoImage4 = new ImageIcon(this.getClass().getResource("/logo_medecin2.jpg")).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
		lbl_logoMedecin4.setIcon(new ImageIcon(logoImage4));
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(12, 45, 56, 16);
		panel_infoPatient.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setBounds(12, 81, 56, 16);
		panel_infoPatient.add(lblPrnom);
		
		JLabel lblSexe = new JLabel("N\u00B0 S\u00E9cu :");
		lblSexe.setBounds(12, 110, 56, 16);
		panel_infoPatient.add(lblSexe);
		
		JLabel lblNTel = new JLabel("N\u00B0 Tel :");
		lblNTel.setBounds(12, 145, 56, 16);
		panel_infoPatient.add(lblNTel);
		
		JLabel lblMail = new JLabel("Mail :");
		lblMail.setBounds(12, 180, 56, 16);
		panel_infoPatient.add(lblMail);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setBounds(12, 209, 56, 16);
		panel_infoPatient.add(lblAdresse);
		
		JLabel lblVille = new JLabel("Ville :");
		lblVille.setBounds(12, 238, 56, 16);
		panel_infoPatient.add(lblVille);
		
		txtNomPatient = new JTextField();
		txtNomPatient.setBounds(79, 42, 116, 22);
		panel_infoPatient.add(txtNomPatient);
		txtNomPatient.setColumns(10);
		
		txtPrenomPatient = new JTextField();
		txtPrenomPatient.setBounds(79, 78, 116, 22);
		panel_infoPatient.add(txtPrenomPatient);
		txtPrenomPatient.setColumns(10);
		
		txtSsPatient = new JTextField();
		txtSsPatient.setBounds(80, 107, 116, 22);
		panel_infoPatient.add(txtSsPatient);
		txtSsPatient.setColumns(10);
		
		txtTelPatient = new JTextField();
		txtTelPatient.setBounds(80, 142, 116, 22);
		panel_infoPatient.add(txtTelPatient);
		txtTelPatient.setColumns(10);
		
		txtMailPatient = new JTextField();
		txtMailPatient.setBounds(80, 177, 116, 22);
		panel_infoPatient.add(txtMailPatient);
		txtMailPatient.setColumns(10);
		
		txtAdressePatient = new JTextField();
		txtAdressePatient.setBounds(80, 206, 116, 22);
		panel_infoPatient.add(txtAdressePatient);
		txtAdressePatient.setColumns(10);
		
		txtVillePatient = new JTextField();
		txtVillePatient.setBounds(80, 235, 116, 22);
		panel_infoPatient.add(txtVillePatient);
		txtVillePatient.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(153, 153, 255));
		panel_5.setBorder(new TitledBorder(null, "Liste des consultations : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 236, 291, 259);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 28, 228, 220);
		panel_5.add(scrollPane_1);
		

		
		
		listConsultations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setViewportView(listConsultations);
		
		
		//---------------------------------------------------
		JPanel panelDiag = new JPanel();
		panelDiag.setBackground(new Color(204, 153, 255));
		tabbedPane.addTab("Les diagnostics (résultats de consultations)", null, panelDiag, null);
		panelDiag.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 540, 31);
		panelDiag.add(scrollPane_3);
		
		textField_rechercherDiagnostic = new JTextField();
		scrollPane_3.setViewportView(textField_rechercherDiagnostic);
		textField_rechercherDiagnostic.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRechercher.setBounds(627, 11, 133, 31);
		panelDiag.add(btnRechercher);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 153, 255));
		panel_1.setBounds(10, 53, 750, 166);
		panel_1.setBorder(new TitledBorder(null, "Liste des patients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDiag.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 21, 730, 134);
		panel_1.add(scrollPane_4);
		
		JList listPatient = new JList();
		listePatient(listPatient);
		scrollPane_4.setViewportView(listPatient);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 153, 255));
		panel_2.setBounds(313, 230, 449, 265);
		panel_2.setBorder(new TitledBorder(null, "Diagnostics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDiag.add(panel_2);
		panel_2.setLayout(null);
		
		//logo medecin
		JLabel lbl_logoMedecin5 = new JLabel("");
		lbl_logoMedecin5.setBounds(349, 11, 90, 89);
		panel_2.add(lbl_logoMedecin5);
		Image logoImage5 = new ImageIcon(this.getClass().getResource("/logo_medecin2.jpg")).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
		lbl_logoMedecin5.setIcon(new ImageIcon(logoImage5));
		
		JLabel lblNewLabel = new JLabel("Descriptif du diagnostique m\u00E9dical");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 235, 17);
		panel_2.add(lblNewLabel);
		
		JLabel lblAllergies = new JLabel("Allergies");
		lblAllergies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAllergies.setBounds(10, 114, 63, 17);
		panel_2.add(lblAllergies);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 136, 268, 40);
		panel_2.add(scrollPane_6);
		
		JList list_allergies = new JList();
		scrollPane_6.setViewportView(list_allergies);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 31, 268, 69);
		panel_2.add(scrollPane_5);
		
		JList listDescriptifDiagMedic = new JList();
		scrollPane_5.setViewportView(listDescriptifDiagMedic);
		
		JLabel lblAntcdentsMdicaux = new JLabel("Ant\u00E9c\u00E9dents m\u00E9dicaux");
		lblAntcdentsMdicaux.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAntcdentsMdicaux.setBounds(10, 187, 141, 17);
		panel_2.add(lblAntcdentsMdicaux);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(8, 208, 270, 46);
		panel_2.add(scrollPane_7);
		
		JList list_antecedentMedic = new JList();
		scrollPane_7.setViewportView(list_antecedentMedic);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(350, 122, 63, 17);
		panel_2.add(lblDate);
		
		JLabel lblDuDiagnostique = new JLabel("du diagnostique : ");
		lblDuDiagnostique.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDuDiagnostique.setBounds(325, 138, 114, 17);
		panel_2.add(lblDuDiagnostique);
		
		JList listDateDiag = new JList();
		listDateDiag.setBounds(314, 167, 125, 30);
		panel_2.add(listDateDiag);
		
		JLabel labelCalendar3d = new JLabel("");
		labelCalendar3d.setBackground(new Color(153, 153, 255));
		labelCalendar3d.setBounds(389, 208, 50, 46);
		panel_2.add(labelCalendar3d);
		Image logoCalendar3d = new ImageIcon(this.getClass().getResource("/calendar_icon.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		labelCalendar3d.setIcon(new ImageIcon(logoCalendar3d));
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(153, 153, 255));
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Liste des consultations : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 230, 272, 265);
		panelDiag.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(22, 21, 228, 233);
		panel_3.add(scrollPane_2);
		
		JList listConsultation = new JList();
		scrollPane_2.setViewportView(listConsultation);
		
		
		//---------------------------------------------------
		JPanel panelPrescriptions = new JPanel();
		panelPrescriptions.setBackground(new Color(204, 153, 255));
		tabbedPane.addTab("Prescriptions", null, panelPrescriptions, null);
		panelPrescriptions.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(153, 153, 255));
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "LISTE DES ORDONNANCES : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 11, 760, 140);
		panelPrescriptions.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(10, 21, 740, 108);
		panel_6.add(scrollPane_8);
		
		JList list_6 = new JList();
		scrollPane_8.setViewportView(list_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(153, 153, 255));
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "EXAMENS BIOLOGIQUES : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(10, 179, 760, 140);
		panelPrescriptions.add(panel_7);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(10, 21, 740, 108);
		panel_7.add(scrollPane_9);
		
		JList list_7 = new JList();
		scrollPane_9.setViewportView(list_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(153, 153, 255));
		panel_8.setBorder(new TitledBorder(null, "INTERVENTIONS MEDICALES ET CHIRURGICALES : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 341, 760, 140);
		panelPrescriptions.add(panel_8);
		panel_8.setLayout(null);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(10, 21, 740, 108);
		panel_8.add(scrollPane_10);
		
		JList list_8 = new JList();
		scrollPane_10.setViewportView(list_8);
		
		JPanel panelChercherMedecin = new JPanel();
		panelChercherMedecin.setBackground(new Color(204, 153, 255));
		tabbedPane.addTab("Chercher un médecin", null, panelChercherMedecin, null);
		panelChercherMedecin.setLayout(null);
		
		JTextArea txtrVille = new JTextArea();
		txtrVille.setBackground(new Color(204, 153, 255));
		txtrVille.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrVille.setText("Ville :");
		txtrVille.setBounds(10, 21, 50, 30);
		panelChercherMedecin.add(txtrVille);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setBounds(68, 11, 436, 30);
		panelChercherMedecin.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Recherche de la sp\u00E9cialit\u00E9 par ordre alphab\u00E9tique : ");
		lblNewLabel_1.setBackground(new Color(153, 153, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 75, 357, 30);
		panelChercherMedecin.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Acupuncteur", "Allergologue", "Ambulance /V\u00E9hicule sanitaire l\u00E9ger", "Anatomo-Cyto-Pathologiste", "Anesth\u00E9siste r\u00E9animateur", "Angiologue", "Canc\u00E9rologues", "Cardiologue ", "Chirurgien-dentiste", " Chirurgien g\u00E9n\u00E9ral", "Chirurgien infantile", "Chirurgien orthop\u00E9diste et traumatologue", "Chirurgien plasticien ", " Chirurgien thoracique et cardio-vasculaire", "Chirurgien urologue", "Chirurgien vasculaire", "Chirurgien visc\u00E9ral", "Dermatologue et v\u00E9n\u00E9rologue "}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(10, 146, 212, 30);
		panelChercherMedecin.add(comboBox);
		
		JLabel lblAd = new JLabel("A...D");
		lblAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAd.setBounds(20, 116, 73, 30);
		panelChercherMedecin.add(lblAd);
		
		JLabel lblEn = new JLabel("E...N");
		lblEn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEn.setBounds(306, 116, 73, 30);
		panelChercherMedecin.add(lblEn);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Echographiste", "Endocrinologue-diab\u00E9tologue", "Fournisseur de mat\u00E9riel m\u00E9dical et para-m\u00E9dical", "Gastro-ent\u00E9rologue et h\u00E9patologue", "G\u00E9riatre", "H\u00E9matologue", "Hom\u00E9opathe", "Laboratoires", "M\u00E9decin biologiste", "M\u00E9decin g\u00E9n\u00E9ticien", "M\u00E9decin sp\u00E9cialiste en m\u00E9decine nucl\u00E9aire", "M\u00E9decin sp\u00E9cialiste en sant\u00E9 publique et m\u00E9decine sociale", "M\u00E9decin thermaliste", "M\u00E9decine appliqu\u00E9e aux sports", "N\u00E9phrologue ", "Neurochirurgien", "Neurologue", "Neuropsychiatre "}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setBounds(292, 146, 212, 30);
		panelChercherMedecin.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Orthophoniste", "Orthoptiste", "Oto-Rhino-Laryngologue (ORL) et chirurgien cervico-facial", "P\u00E9diatre", "P\u00E9dicure-podologue", "Pharmacien", "Phoniatre", "Pneumologue", "Psychiatres", "Radiologue", "Radioth\u00E9rapeute", "R\u00E9animateur m\u00E9dical", "Rhumatologue", "Sage-femme", "Sp\u00E9cialiste en m\u00E9decine interne", "Sp\u00E9cialiste en m\u00E9decine physique et de r\u00E9adaptation", "Stomatologistes", "", ""}));
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_2.setBounds(564, 146, 212, 30);
		panelChercherMedecin.add(comboBox_2);
		
		JLabel lblOz = new JLabel("O...Z");
		lblOz.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOz.setBounds(574, 116, 73, 30);
		panelChercherMedecin.add(lblOz);
		
		JPanel panel_rechercherParNom = new JPanel();
		panel_rechercherParNom.setBorder(new TitledBorder(null, "Rechercher un medecin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_rechercherParNom.setBounds(10, 232, 766, 145);
		panelChercherMedecin.add(panel_rechercherParNom);
		panel_rechercherParNom.setLayout(null);
		
		JTextArea txtrNomDuMedecin = new JTextArea();
		txtrNomDuMedecin.setText("Nom du m\u00E9decin :");
		txtrNomDuMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrNomDuMedecin.setBackground(UIManager.getColor("Button.background"));
		txtrNomDuMedecin.setBounds(10, 50, 118, 51);
		panel_rechercherParNom.add(txtrNomDuMedecin);
		
		JTextArea textAreaRechercherMedecin = new JTextArea();
		textAreaRechercherMedecin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaRechercherMedecin.setBounds(214, 30, 513, 51);
		panel_rechercherParNom.add(textAreaRechercherMedecin);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnValider.setBounds(594, 103, 133, 31);
		panel_rechercherParNom.add(btnValider);
		
		JLabel lblEspaceMdecin = new JLabel("Espace M\u00E9decin");
		lblEspaceMdecin.setBounds(10, 0, 133, 26);
		lblEspaceMdecin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		frameMedecin.getContentPane().add(lblEspaceMdecin);
		
		JButton btnHome = new JButton("");
		btnHome.setBounds(718, 13, 52, 25);
        frameMedecin.getContentPane().add(btnHome);
        Image iconHome = new ImageIcon(this.getClass().getResource("/iconHome3.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        btnHome.setIcon(new ImageIcon(iconHome));
        
        /**
         * Action au clic pour se déconnecter et revenir dans le menu login
         */
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.frame.setVisible(true);
				frameMedecin.setVisible(false);
			}
		});
		frameMedecin.getContentPane().add(btnHome);
	}

	/**
	 * Méthode qui affiche les patients via requête et passe en paramètre une jlist pour afficher ses informations
	 * @param liste1
	 */
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
	
	/**
	 * Affiche la liste des consultations
	 * @param liste2
	 * @param pId
	 */
	private void listeConsultation(JList liste2 , int pId) {
		ResultSet resC = MethodesSql.afficherConsultation(uneConnexion.getConnection() , pId);
		try {
			while(resC.next()) {
				unPatient.setIdConsultation(resC.getInt("id_consultation"));
				lstPatients.add(unPatient);
			}
			
		}  catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
