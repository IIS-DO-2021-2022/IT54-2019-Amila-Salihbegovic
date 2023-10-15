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

import geometricShapes.Line;
import geometricShapes.Point;

public class DialogLine extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXStart;
	private JTextField txtYStart;
	private JTextField txtXEnd;
	private JTextField txtYEnd;
	private Point p;
	public Line line;
	private boolean isOK;
	private JButton btnColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogLine dialog = new DialogLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogLine() {
		setTitle("Modify line");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(230, 230, 250));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblHeadline = new JLabel("Insert values:");
			lblHeadline.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblHeadline = new GridBagConstraints();
			gbc_lblHeadline.insets = new Insets(0, 0, 5, 0);
			gbc_lblHeadline.gridx = 1;
			gbc_lblHeadline.gridy = 0;
			contentPanel.add(lblHeadline, gbc_lblHeadline);
		}
		{
			JLabel lblX = new JLabel("Insert coordinate X (start point):");
			lblX.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 3;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtXStart = new JTextField();
			txtXStart.setText("");
			GridBagConstraints gbc_txtXStart = new GridBagConstraints();
			gbc_txtXStart.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXStart.insets = new Insets(0, 0, 5, 0);
			gbc_txtXStart.gridx = 1;
			gbc_txtXStart.gridy = 3;
			contentPanel.add(txtXStart, gbc_txtXStart);
			txtXStart.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Insert coordinate Y (start point):");
			lblY.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 4;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtYStart = new JTextField();
			txtYStart.setText("");
			GridBagConstraints gbc_txtYStart = new GridBagConstraints();
			gbc_txtYStart.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYStart.insets = new Insets(0, 0, 5, 0);
			gbc_txtYStart.gridx = 1;
			gbc_txtYStart.gridy = 4;
			contentPanel.add(txtYStart, gbc_txtYStart);
			txtYStart.setColumns(10);
		}
		{
			JLabel lblXend = new JLabel("Insert coordinate X (end point):");
			lblXend.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblXend = new GridBagConstraints();
			gbc_lblXend.anchor = GridBagConstraints.EAST;
			gbc_lblXend.insets = new Insets(0, 0, 5, 5);
			gbc_lblXend.gridx = 0;
			gbc_lblXend.gridy = 5;
			contentPanel.add(lblXend, gbc_lblXend);
		}
		{
			txtXEnd = new JTextField();
			txtXEnd.setText("");
			GridBagConstraints gbc_txtXEnd = new GridBagConstraints();
			gbc_txtXEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXEnd.insets = new Insets(0, 0, 5, 0);
			gbc_txtXEnd.gridx = 1;
			gbc_txtXEnd.gridy = 5;
			contentPanel.add(txtXEnd, gbc_txtXEnd);
			txtXEnd.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Insert coordinate Y (end point):");
			lblNewLabel.setForeground(Color.BLUE);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 6;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtYEnd = new JTextField();
			txtYEnd.setText("");
			GridBagConstraints gbc_txtYEnd = new GridBagConstraints();
			gbc_txtYEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYEnd.gridx = 1;
			gbc_txtYEnd.gridy = 6;
			contentPanel.add(txtYEnd, gbc_txtYEnd);
			txtYEnd.setColumns(10);
		}
		btnColor = new JButton("OUTLINE COLOR");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnColor.getBackground());
				if (outlineColor != null)
					btnColor.setBackground(outlineColor);

			}
		});
		btnColor.setBackground(new Color(230, 230, 250));
		btnColor.setForeground(Color.BLUE);
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutlineColor.gridx = 2;
		gbc_btnOutlineColor.gridy = 8;
		contentPanel.add(btnColor, gbc_btnOutlineColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtXStart.getText().trim().isEmpty() || txtYStart.getText().trim().isEmpty()
								|| txtXEnd.getText().trim().isEmpty() || txtYEnd.getText().trim().isEmpty()) {
							isOK = false;
							JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtXStart.getText().toString()) < 0
										|| Integer.parseInt(txtYStart.getText().toString()) < 0
										|| Integer.parseInt(txtXEnd.getText().toString()) < 0
										|| Integer.parseInt(txtYEnd.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "Error",
											JOptionPane.ERROR_MESSAGE);

								} else {
									line = new Line(
											new Point(Integer.parseInt(txtXStart.getText().toString()),
													Integer.parseInt(txtYStart.getText().toString())),
											new Point(Integer.parseInt(txtXEnd.getText().toString()),
													Integer.parseInt(txtYEnd.getText().toString())),
											false, btnColor.getBackground());

									isOK = true;
									setVisible(false);

								}

							} catch (Exception e2) {
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
	public boolean isOK() {
		return isOK;
	}

	public void setisOK(boolean isOK) {
		this.isOK = isOK;
	}

	public JTextField getTxtXStart() {
		return txtXStart;
	}

	public void setTxtXStart(JTextField txtXStart) {
		this.txtXStart = txtXStart;
	}

	public JTextField gettxtYStart() {
		return txtYStart;
	}

	public void settxtYStart(JTextField txtYStart) {
		this.txtYStart = txtYStart;
	}

	public JTextField getTxtXEnd() {
		return txtXEnd;
	}

	public void setTxtXEnd(JTextField txtXEnd) {
		this.txtXEnd = txtXEnd;
	}

	public JTextField getTxtYEnd() {
		return txtYEnd;
	}

	public void setTxtYEnd(JTextField txtYEnd) {
		this.txtYEnd = txtYEnd;
	}


	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtColor(JButton btnOutlineColor) {
		this.btnColor = btnColor;
	}
}
