package Stack;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgStack extends JDialog {
	protected JTextField txtX;
	protected JTextField txtY;
	protected JTextField txtHeight;
	protected JTextField txtWidth;
	protected boolean isOK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgStack dialog = new DlgStack();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgStack() {
		setResizable(false);
		setTitle("Salihbegovic Amila IT54/2019");
		setModal(true);
		setBounds(100, 100, 350, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						isOK=true;
						setVisible(false);
						
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
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblInsertvalues = new JLabel("Insert values ");
				panel.add(lblInsertvalues);
			}
		}
		{
			JPanel pnlCenter = new JPanel();
			getContentPane().add(pnlCenter, BorderLayout.CENTER);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnlCenter.setLayout(gridBagLayout);
			{
				JLabel lblX = new JLabel("Insert X:");
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 1;
				gbc_lblX.gridy = 1;
				pnlCenter.add(lblX, gbc_lblX);
			}
			{
				txtX = new JTextField();
				txtX.setText("");
				GridBagConstraints gbc_txtX = new GridBagConstraints();
				gbc_txtX.insets = new Insets(0, 0, 5, 0);
				gbc_txtX.gridx = 2;
				gbc_txtX.gridy = 1;
				pnlCenter.add(txtX, gbc_txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblY = new JLabel("Insert Y:");
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 1;
				gbc_lblY.gridy = 2;
				pnlCenter.add(lblY, gbc_lblY);
			}
			{
				txtY = new JTextField();
				txtY.setText("");
				GridBagConstraints gbc_txtY = new GridBagConstraints();
				gbc_txtY.insets = new Insets(0, 0, 5, 0);
				gbc_txtY.gridx = 2;
				gbc_txtY.gridy = 2;
				pnlCenter.add(txtY, gbc_txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblheight = new JLabel("Insert height:");
				GridBagConstraints gbc_lblheight = new GridBagConstraints();
				gbc_lblheight.insets = new Insets(0, 0, 5, 5);
				gbc_lblheight.gridx = 1;
				gbc_lblheight.gridy = 3;
				pnlCenter.add(lblheight, gbc_lblheight);
			}
			{
				txtHeight = new JTextField();
				txtHeight.setText("");
				GridBagConstraints gbc_txtHeight = new GridBagConstraints();
				gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
				gbc_txtHeight.gridx = 2;
				gbc_txtHeight.gridy = 3;
				pnlCenter.add(txtHeight, gbc_txtHeight);
				txtHeight.setColumns(10);
			}
			{
				JLabel lblwidth = new JLabel("Insert width:");
				GridBagConstraints gbc_lblwidth = new GridBagConstraints();
				gbc_lblwidth.insets = new Insets(0, 0, 0, 5);
				gbc_lblwidth.gridx = 1;
				gbc_lblwidth.gridy = 4;
				pnlCenter.add(lblwidth, gbc_lblwidth);
			}
			{
				txtWidth = new JTextField();
				txtWidth.setText("");
				GridBagConstraints gbc_txtWidth = new GridBagConstraints();
				gbc_txtWidth.gridx = 2;
				gbc_txtWidth.gridy = 4;
				pnlCenter.add(txtWidth, gbc_txtWidth);
				txtWidth.setColumns(10);
			}
		}
	}
	public final JTextField getTxtX() {
		return txtX;
	}

	public final void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public final JTextField getTxtY() {
		return txtY;
	}

	public final void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public final JTextField getTxtHeight() {
		return txtHeight;
	}

	public final void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public final JTextField getTxtWidth() {
		return txtWidth;
	}

	public final void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

}
