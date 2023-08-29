package mvc;

import java.util.Queue;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class DrawingFrame extends JFrame{
	private JPanel contentPane;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");	
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	
	public DrawingFrame() {
		setBackground(Color.WHITE);
		setTitle("Salihbegovic Amila IT54/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{57, 309, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Box horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_horizontalBox.gridx = 1;
		gbc_horizontalBox.gridy = 0;
		panel.add(horizontalBox, gbc_horizontalBox);
		
		view.setBackground(Color.WHITE);
		contentPane.add(view, BorderLayout.CENTER);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(124,208,247));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		tglbtnPoint.setForeground(new Color(124,208,247));
		tglbtnPoint.setBackground(Color.BLACK);
		
		pnlNorth.add(tglbtnPoint);
		tglbtnLine.setForeground(new Color(124,208,247));
		tglbtnLine.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnLine);
		tglbtnCircle.setForeground(new Color(124,208,247));
		tglbtnCircle.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnCircle);
		tglbtnDonut.setForeground(new Color(124,208,247));
		tglbtnDonut.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnDonut);
		tglbtnRectangle.setForeground(new Color(124,208,247));
		tglbtnRectangle.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnRectangle);
		
		ButtonGroup btnGroup = new ButtonGroup();

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnRectangle);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(124,208,247));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		tglbtnSelect.setForeground(new Color(124,208,247));
		tglbtnSelect.setBackground(Color.BLACK);
		pnlSouth.add(tglbtnSelect);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setForeground(new Color(124,208,247));
		btnModify.setBackground(Color.BLACK);
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getModel().getSelectedShapes().get(0) != null) {
					controller.modify();

				} else {
					JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
					tglbtnSelect.setSelected(true);
				}
				view.getModel().setSelectedShape(null);
				tglbtnSelect.setSelected(false);

				
			}
		});
		pnlSouth.add(btnModify);
		btnGroup.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(124,208,247));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
				tglbtnSelect.setSelected(false);
			}
		});
		pnlSouth.add(btnDelete);
		btnGroup.add(btnDelete);

		view.repaint();

	}
}
