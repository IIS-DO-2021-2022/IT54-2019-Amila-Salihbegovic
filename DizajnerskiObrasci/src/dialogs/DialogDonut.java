package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometricShapes.Donut;
import geometricShapes.Point;

public class DialogDonut extends JDialog implements DialogShape{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;
	private JTextField txtDIR;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	public Donut donut;
	private boolean isOK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDonut dialog = new DialogDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDonut() {
		setTitle("Add/modify donut");
		setBackground(Color.WHITE);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.BLUE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(230, 230, 250));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("Insert coordinate X (center point):");
			lblX.setForeground(Color.BLUE);
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
			GridBagConstraints gbc_txtDX = new GridBagConstraints();
			gbc_txtDX.insets = new Insets(0, 0, 5, 0);
			gbc_txtDX.gridx = 1;
			gbc_txtDX.gridy = 2;
			contentPanel.add(txtX, gbc_txtDX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Insert coordinate Y (center point):");
			lblY.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 3;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.setTransferHandler(null);
			GridBagConstraints gbc_txtDY = new GridBagConstraints();
			gbc_txtDY.insets = new Insets(0, 0, 5, 0);
			gbc_txtDY.gridx = 1;
			gbc_txtDY.gridy = 3;
			contentPanel.add(txtY, gbc_txtDY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Insert radius:");
			lblRadius.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 4;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtR = new JTextField();
			txtR.setTransferHandler(null);
			GridBagConstraints gbc_txtDR = new GridBagConstraints();
			gbc_txtDR.insets = new Insets(0, 0, 5, 0);
			gbc_txtDR.gridx = 1;
			gbc_txtDR.gridy = 4;
			contentPanel.add(txtR, gbc_txtDR);
			txtR.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Insert inner radius:");
			lblInnerRadius.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 5;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtDIR = new JTextField();
			txtDIR.setTransferHandler(null);
			GridBagConstraints gbc_txtDIR = new GridBagConstraints();
			gbc_txtDIR.insets = new Insets(0, 0, 5, 0);
			gbc_txtDIR.gridx = 1;
			gbc_txtDIR.gridy = 5;
			contentPanel.add(txtDIR, gbc_txtDIR);
			txtDIR.setColumns(10);
		}
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
				if (innerColor != null)
					btnInnerColor.setBackground(innerColor);

			}
		});
		btnInnerColor.setBackground(new Color(230, 230, 250));
		btnInnerColor.setForeground(Color.BLUE);
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 2;
		gbc_btnInnerColor.gridy = 6;
		contentPanel.add(btnInnerColor, gbc_btnInnerColor);

		btnOuterColor = new JButton("Outline Color");
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOuterColor.getBackground());
				if (outlineColor != null)
					btnOuterColor.setBackground(outlineColor);

			}
		});
		btnOuterColor.setBackground(new Color(230, 230, 250));
		btnOuterColor.setForeground(Color.BLUE);
		GridBagConstraints gbc_btnOuterColor = new GridBagConstraints();
		gbc_btnOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOuterColor.gridx = 2;
		gbc_btnOuterColor.gridy = 7;
		contentPanel.add(btnOuterColor, gbc_btnOuterColor);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty() || txtR.getText().trim().isEmpty() || txtDIR.getText().trim().isEmpty()) 
						{
							JOptionPane.showMessageDialog(null, "All values are required!");
						}
						else {
							try {
								if (Integer.parseInt(txtDIR.getText().toString()) <= 0
										|| Integer.parseInt(txtR.getText().toString()) <= 0
										|| Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0)
									JOptionPane.showMessageDialog(null, "Insert values greater then 0!", "Error",JOptionPane.ERROR_MESSAGE);
								else {
									if (Integer.parseInt(txtDIR.getText().toString()) < Integer
											.parseInt(txtR.getText().toString())) {
										donut = new Donut(
												new Point(Integer.parseInt(txtX.getText().toString()),
														Integer.parseInt(txtY.getText().toString())),
												Integer.parseInt(txtR.getText().toString()),
												Integer.parseInt(txtDIR.getText().toString()), false, btnOuterColor.getBackground(), btnInnerColor.getBackground());

										isOK=true;
										setVisible(false);
									} 
									else {
										JOptionPane.showMessageDialog(null,
												"Please insert inner radius less than outher radius!", "Error",
												JOptionPane.ERROR_MESSAGE);
									}

								}
							}
							catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
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

	public JTextField getTxtDIR() {
		return txtDIR;
	}

	public void setTxtDIR(JTextField txtDIR) {
		this.txtDIR = txtDIR;
	}
	public Donut getDonut() {
		return donut;
	}

	public void setDount(Donut donut) {
		this.donut = donut;
	}
	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerolor) {
		this.btnInnerColor = btnInnerColor;
	}
	public boolean isOK() {
		return isOK;
	}

	public void setisOK(boolean isOK) {
		this.isOK = isOK;
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

	public JTextField getTxtR() {
		return txtR;
	}

	public void setTxtR(JTextField txtR) {
		this.txtR = txtR;
	}

	@Override
	public void setInnerColor(Color color) {
		if (color != null) {
            getBtnInnerColor().setBackground(color);
        }
	}

	@Override
	public void setOuterColor(Color color) {
		 if (color != null) {
	            getBtnOuterColor().setBackground(color);
	        }
	}
	
}
