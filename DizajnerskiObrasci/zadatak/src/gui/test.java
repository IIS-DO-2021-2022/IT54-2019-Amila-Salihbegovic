package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class test extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup btnGroup =new ButtonGroup();
	private DefaultListModel<String> dml = new DefaultListModel<String>();
	private JLabel lblCrvena;
	private JLabel lblPlava;
	private JLabel lblZuta;
	private JTextField txtDodajBoju;
	private JPanel pnlCenter;
	private JList listBoje;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNaslov = new JLabel("Naslov");
		panel.add(lblNaslov);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnIspisi = new JButton("Ispisi");
		btnIspisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Antistres dugme.:)");
			}
		});
		panel_1.add(btnIspisi);
		
		JButton btnDodajBoju = new JButton("Dodaj boju");
		btnDodajBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgTest dlgTest = new DlgTest();
				dlgTest.setVisible(true);
				if(dlgTest.isOk==true) {
					dml.addElement(dlgTest.txtCrvena.getText()+ " " + dlgTest.txtPlava.getText() + " " + dlgTest.txtZuta.getText());
					pnlCenter.setBackground(new Color(Integer.parseInt(dlgTest.txtCrvena.getText()), Integer.parseInt(dlgTest.txtPlava.getText()), Integer.parseInt(dlgTest.txtZuta.getText())));
				} 
			}
		});
		panel_1.add(btnDodajBoju);
		
		JButton btnIzmeniBoju = new JButton("Izmeni boju");
		btnIzmeniBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgTest dlgTest = new DlgTest();
				try {
					String[] split = dml.getElementAt(listBoje.getSelectedIndex()).toString().split(" ");
					dlgTest.txtCrvena.setText(split[0]);
					dlgTest.txtPlava.setText(split[1]);
					dlgTest.txtZuta.setText(split[2]);
					dlgTest.setVisible(true);

				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Niste selektovali red.");
				}
			}
		});
		panel_1.add(btnIzmeniBoju);
		
		pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gridBagLayout);
		
		JToggleButton tglbtnCrvena = new JToggleButton("Crvena");
		tglbtnCrvena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dml.addElement(lblCrvena.getText());
				if(tglbtnCrvena.isSelected()) {
					lblCrvena.setForeground(Color.RED);
					lblPlava.setForeground(Color.BLACK);
					lblZuta.setForeground(Color.BLACK);

				}
				
			}
		});
		GridBagConstraints gbc_tglbtnCrvena = new GridBagConstraints();
		gbc_tglbtnCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCrvena.gridx = 0;
		gbc_tglbtnCrvena.gridy = 0;
		pnlCenter.add(tglbtnCrvena, gbc_tglbtnCrvena);
		
		lblCrvena = new JLabel("Crvena");
		GridBagConstraints gbc_lblCrvena = new GridBagConstraints();
		gbc_lblCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrvena.gridx = 1;
		gbc_lblCrvena.gridy = 0;
		pnlCenter.add(lblCrvena, gbc_lblCrvena);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		JList listBoje = new JList();
		scrollPane.setViewportView(listBoje);
		listBoje.setModel(dml);
		
		JToggleButton tglbtnPlava = new JToggleButton("Plava");
		tglbtnPlava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dml.addElement(lblPlava.getText());
				if(tglbtnPlava.isSelected()) {
					lblCrvena.setForeground(Color.BLACK);
					lblPlava.setForeground(Color.BLUE);
					lblZuta.setForeground(Color.BLACK);
			}}
		});
		GridBagConstraints gbc_tglbtnPlava = new GridBagConstraints();
		gbc_tglbtnPlava.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPlava.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPlava.gridx = 0;
		gbc_tglbtnPlava.gridy = 1;
		pnlCenter.add(tglbtnPlava, gbc_tglbtnPlava);
		
		lblPlava = new JLabel("Plava");
		GridBagConstraints gbc_lblPlava = new GridBagConstraints();
		gbc_lblPlava.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlava.gridx = 1;
		gbc_lblPlava.gridy = 1;
		pnlCenter.add(lblPlava, gbc_lblPlava);
		
		JToggleButton tglbtnZuta = new JToggleButton("Zuta");
		tglbtnZuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dml.addElement(lblZuta.getText());
				if(tglbtnZuta.isSelected()) {
					lblCrvena.setForeground(Color.BLACK);
					lblPlava.setForeground(Color.BLACK);
					lblZuta.setForeground(Color.YELLOW);
			}
			}}
		);
		GridBagConstraints gbc_tglbtnZuta = new GridBagConstraints();
		gbc_tglbtnZuta.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnZuta.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnZuta.gridx = 0;
		gbc_tglbtnZuta.gridy = 2;
		pnlCenter.add(tglbtnZuta, gbc_tglbtnZuta);
		
		lblZuta = new JLabel("Zuta");
		GridBagConstraints gbc_lblZuta = new GridBagConstraints();
		gbc_lblZuta.insets = new Insets(0, 0, 5, 5);
		gbc_lblZuta.gridx = 1;
		gbc_lblZuta.gridy = 2;
		pnlCenter.add(lblZuta, gbc_lblZuta);
		
		JLabel lblIzaberi = new JLabel("Izaberite boju");
		GridBagConstraints gbc_lblIzaberi = new GridBagConstraints();
		gbc_lblIzaberi.anchor = GridBagConstraints.EAST;
		gbc_lblIzaberi.insets = new Insets(0, 0, 5, 5);
		gbc_lblIzaberi.gridx = 0;
		gbc_lblIzaberi.gridy = 3;
		pnlCenter.add(lblIzaberi, gbc_lblIzaberi);
		
		JComboBox<String> cbxIzaberiBoju = new JComboBox<String>();
		cbxIzaberiBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dml.addElement(cbxIzaberiBoju.getSelectedItem().toString());
				switch(cbxIzaberiBoju.getSelectedItem().toString()) {
				case "Zelena":
					lblIzaberi.setForeground(Color.GREEN);
					break;
				case "Narandzasta":
					lblIzaberi.setForeground(Color.ORANGE);
					break;
				case "Ljubicasta":
					lblIzaberi.setForeground(Color.MAGENTA);
					break;
				
			}}
		});
		cbxIzaberiBoju.setModel(new DefaultComboBoxModel<String>(new String[] {"Zelena", "Narandzasta", "Ljubicasta"}));
		GridBagConstraints gbc_cbxIzaberiBoju = new GridBagConstraints();
		gbc_cbxIzaberiBoju.insets = new Insets(0, 0, 5, 0);
		gbc_cbxIzaberiBoju.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxIzaberiBoju.gridx = 2;
		gbc_cbxIzaberiBoju.gridy = 3;
		pnlCenter.add(cbxIzaberiBoju, gbc_cbxIzaberiBoju);
		
		JLabel lblDodajBoju = new JLabel("Dodaj boju");
		GridBagConstraints gbc_lblDodajBoju = new GridBagConstraints();
		gbc_lblDodajBoju.insets = new Insets(0, 0, 0, 5);
		gbc_lblDodajBoju.gridx = 0;
		gbc_lblDodajBoju.gridy = 4;
		pnlCenter.add(lblDodajBoju, gbc_lblDodajBoju);
		
		txtDodajBoju = new JTextField();
		txtDodajBoju.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dml.addElement(txtDodajBoju.getText());
					txtDodajBoju.setText("");
				}
				
			}
		});
		GridBagConstraints gbc_txtDodajBoju = new GridBagConstraints();
		gbc_txtDodajBoju.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDodajBoju.gridx = 2;
		gbc_txtDodajBoju.gridy = 4;
		pnlCenter.add(txtDodajBoju, gbc_txtDodajBoju);
		txtDodajBoju.setColumns(10);
	}

}
