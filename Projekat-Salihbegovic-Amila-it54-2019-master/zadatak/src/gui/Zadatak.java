package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Zadatak extends JFrame {

	private JPanel contentPane;
	private JTextField textIgrac;
	private DefaultListModel<String> dml = new DefaultListModel<String>();
	private JList list;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zadatak frame = new Zadatak();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Zadatak() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.YELLOW);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Forma za unos igraca");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnPrvi = new JButton("Ivanovic");
		btnPrvi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dml.addElement("Ivanovic");
			}
		});
		GridBagConstraints gbc_btnPrvi = new GridBagConstraints();
		gbc_btnPrvi.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPrvi.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrvi.gridx = 0;
		gbc_btnPrvi.gridy = 1;
		panel.add(btnPrvi, gbc_btnPrvi);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(dml);
		
		JButton btnDrugi = new JButton("Matic");
		btnDrugi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dml.addElement("Matic");
			}
		});
		GridBagConstraints gbc_btnDrugi = new GridBagConstraints();
		gbc_btnDrugi.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDrugi.insets = new Insets(0, 0, 5, 5);
		gbc_btnDrugi.gridx = 0;
		gbc_btnDrugi.gridy = 2;
		panel.add(btnDrugi, gbc_btnDrugi);
		
		JButton btnTreci = new JButton("Kolarov");
		btnTreci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dml.addElement("Kolarov");
			}
		});
		GridBagConstraints gbc_btnTreci = new GridBagConstraints();
		gbc_btnTreci.insets = new Insets(0, 0, 5, 5);
		gbc_btnTreci.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTreci.gridx = 0;
		gbc_btnTreci.gridy = 3;
		panel.add(btnTreci, gbc_btnTreci);
		
		JCheckBox chckbxNoviIgrac = new JCheckBox("Unos Igraca");
		chckbxNoviIgrac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textIgrac != null) {
				dml.addElement(textIgrac.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter name");
				}
			}
		});
		GridBagConstraints gbc_chckbxNoviIgrac = new GridBagConstraints();
		gbc_chckbxNoviIgrac.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNoviIgrac.gridx = 0;
		gbc_chckbxNoviIgrac.gridy = 4;
		panel.add(chckbxNoviIgrac, gbc_chckbxNoviIgrac);
		
		textIgrac = new JTextField();
		textIgrac.setText("");
		GridBagConstraints gbc_textIgrac = new GridBagConstraints();
		gbc_textIgrac.insets = new Insets(0, 0, 5, 0);
		gbc_textIgrac.fill = GridBagConstraints.HORIZONTAL;
		gbc_textIgrac.gridx = 1;
		gbc_textIgrac.gridy = 4;
		panel.add(textIgrac, gbc_textIgrac);
		textIgrac.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dml.addElement(comboBox.getSelectedItem().toString());
		}
	});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mitrovic", "Jovic", "Gudelj"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 5;
		panel.add(comboBox, gbc_comboBox);
		
		JButton btnDijalogIgrac = new JButton("Dijalog igrac");
		btnDijalogIgrac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgIgrac dlgigrac = new DlgIgrac();
				dlgigrac.setVisible(true);
				if(dlgigrac.isOK==true) {
					dml.addElement(dlgigrac.textIme.getText() + "" + dlgigrac.textPrezime.getText());
				}
			}
		});
		GridBagConstraints gbc_btnDijalogIgrac = new GridBagConstraints();
		gbc_btnDijalogIgrac.insets = new Insets(0, 0, 5, 0);
		gbc_btnDijalogIgrac.gridx = 1;
		gbc_btnDijalogIgrac.gridy = 6;
		panel.add(btnDijalogIgrac, gbc_btnDijalogIgrac);
		
		
	}

}
