package Stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import geometry.Point;
import geometry.rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stack extends JFrame {

	private JPanel contentPane;
	DefaultListModel<rectangle> dlm = new DefaultListModel<rectangle>();
	private JList lstRectangle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack frame = new Stack();
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
	public Stack() {
		setTitle("Salihbegovic Amila it54/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblrectengle = new JLabel("Rectangle");
		pnlNorth.add(lblrectengle);
		
		JPanel pnlWest = new JPanel();
		contentPane.add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[]{0, 0};
		gbl_pnlWest.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pnlWest.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gbl_pnlWest);
		
		JButton btnAddRectangle = new JButton("Add rectangle");
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true); 
				if(dlgStack.isOK)
				{
					try {
						int x = Integer.parseInt(dlgStack.getTxtX().getText()); 
						int y = Integer.parseInt(dlgStack.getTxtY().getText());
						int width = Integer.parseInt(dlgStack.getTxtWidth().getText());
						int height = Integer.parseInt(dlgStack.getTxtHeight().getText());		
						rectangle Rectangle = new rectangle(new Point(x,y), height, width); 	
						dlm.add(0, Rectangle);	
					}
					 catch(Exception NumberFormatException) {
						 
						 JOptionPane.showMessageDialog(null,"Please, insert values!"); 
					 }
				}
			}
		});
		GridBagConstraints gbc_btnAddRectangle = new GridBagConstraints();
		gbc_btnAddRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRectangle.gridx = 0;
		gbc_btnAddRectangle.gridy = 3;
		pnlWest.add(btnAddRectangle, gbc_btnAddRectangle);
		
		JButton btnDeleteRectangle = new JButton("Delete rectangle");
		btnDeleteRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgStack dlgstack = new DlgStack();
				if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty, please insert values!");
				}
				else {
					Point p= dlm.getElementAt(0).getupperleft(); 
					int width = dlm.getElementAt(0).getwidth();
					int height = dlm.getElementAt(0).getheight();
					
					dlgstack.getTxtX().setText("" + Integer.toString(p.getX()));
					dlgstack.getTxtY().setText("" + Integer.toString(p.getY()));
					dlgstack.getTxtWidth().setText("" + Integer.toString(width));
					dlgstack.getTxtHeight().setText("" + Integer.toString(height));
					
					dlgstack.setVisible(true);
					
					if(dlgstack.isOK)
					{
						dlm.remove(0); 
					}
				}
				
			}
		});
		GridBagConstraints gbc_btnDeleteRectangle = new GridBagConstraints();
		gbc_btnDeleteRectangle.gridx = 0;
		gbc_btnDeleteRectangle.gridy = 4;
		pnlWest.add(btnDeleteRectangle, gbc_btnDeleteRectangle);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gridBagLayout);
		
		lstRectangle = new JList();
		GridBagConstraints gbc_lstRectangle = new GridBagConstraints();
		gbc_lstRectangle.fill = GridBagConstraints.BOTH;
		gbc_lstRectangle.gridx = 0;
		gbc_lstRectangle.gridy = 0;
		pnlCenter.add(lstRectangle, gbc_lstRectangle);
		lstRectangle.setModel(dlm);
	}

}
