package Drawing;

import geometry.Point;
import geometry.circle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private circle circle;
	private boolean isOK;
	private JButton btnInnerColor;
	private JButton btnOutlineColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setTitle("Add/modify circle");
		setResizable(false);
		setModal(true);
		setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(124, 208, 247));
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("Insert coordinate X (center point):");
			lblX.setForeground(new Color(124, 208, 247));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 2;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			txtX.setTransferHandler(null);			
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 2;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Insert coordinate Y (center point):");
			lblY.setForeground(new Color(124, 208, 247));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 3;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.setTransferHandler(null);
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 3;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Insert radius:");
			lblRadius.setForeground(new Color(124, 208, 247));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 4;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setText("");
			txtRadius.setTransferHandler(null);
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 4;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
				if (innerColor != null)
					btnInnerColor.setBackground(innerColor);

			}
		});
		btnInnerColor.setBackground(new Color(124,208,247));
		btnInnerColor.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 2;
		gbc_btnInnerColor.gridy = 6;
		contentPanel.add(btnInnerColor, gbc_btnInnerColor);

		btnOutlineColor = new JButton("Outline Color");
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColor.getBackground());
				if (outlineColor != null)
					btnOutlineColor.setBackground(outlineColor);

			}
		});
		btnOutlineColor.setBackground(new Color(124,208,247));
		btnOutlineColor.setForeground(Color.BLACK);
		
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutlineColor.gridx = 2;
		gbc_btnOutlineColor.gridy = 7;
		contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty()) {
							setisOK(false);
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtRadius.getText().toString()) <= 0
										|| Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greather than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									circle = new circle(new Point(Integer.parseInt(txtX.getText().toString()),
											Integer.parseInt(txtY.getText().toString())),
											Integer.parseInt(txtRadius.getText().toString()), false, btnInnerColor.getBackground(), btnOutlineColor.getBackground());
								
									setisOK(true);
									setVisible(false);
								}
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null, "Enter numbers only", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}
	
	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setisOK(boolean isOK) {
		this.isOK = isOK;
	}
	
	public JTextField getTxtRadius() {
		return txtRadius;
	}
	
	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}
	
	public circle getCircle() {
		return circle;
	}

	public void setCircle(circle circle) {
		this.circle = circle;
	}
	
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}
	
	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnOutlineColor = btnOutlineColor;
	}
	
}
