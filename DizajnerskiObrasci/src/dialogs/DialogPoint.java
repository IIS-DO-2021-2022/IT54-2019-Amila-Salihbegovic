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

import geometricShapes.Point;

public class DialogPoint extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8729366104588282859L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private Point p;
	private boolean isOK;
	private JButton btnColor; 
	private Color color;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogPoint dialog = new DialogPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogPoint() {
		setTitle("Modify point");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
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
			JLabel lblHeadline = new JLabel("Insert values");
			lblHeadline.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblHeadline = new GridBagConstraints();
			gbc_lblHeadline.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeadline.gridx = 1;
			gbc_lblHeadline.gridy = 0;
			contentPanel.add(lblHeadline, gbc_lblHeadline);
		}
		{
			JLabel lblX = new JLabel("Insert X:");
			lblX.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 2;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			txtX.setText("");
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 5);
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 2;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Insert Y:");
			lblY.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 3;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.setText("");
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 5);
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 3;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnColor.getBackground());
				if (outlineColor != null)
					btnColor.setBackground(outlineColor);
			}

		});
		btnColor.setBackground(new Color(230, 230, 250));
		btnColor.setForeground(Color.BLUE);
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.gridx = 3;
		gbc_btnColor.gridy = 7;
		contentPanel.add(btnColor, gbc_btnColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {

							try {
								if (Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {

									p = new Point(Integer.parseInt(txtX.getText().toString()),
											Integer.parseInt(txtY.getText().toString()), false, btnColor.getBackground());
									isOK = true;
									setVisible(false);

								}
							} catch (Exception e1) {
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

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}
	public Color getColor() {
		return color;
	}

	public void setC(Color color) {
		this.color = color;
	}
	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}
}
