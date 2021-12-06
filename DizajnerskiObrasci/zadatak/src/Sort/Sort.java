package Sort;

import geometry.rectangle;
import geometry.Point;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Sort extends JFrame {

	private JPanel contentPane;
	DefaultListModel<rectangle> dlm = new DefaultListModel<rectangle>();
	private JList lstRectangle;
	ArrayList <rectangle> list = new ArrayList<rectangle>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort frame = new Sort();
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
	public Sort() {
		setTitle("Salihbegovic Amila IT54/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblHeadline = new JLabel("Add/Sort rectangles");
		pnlNorth.add(lblHeadline);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 0};
		gridBagLayout_1.rowHeights = new int[]{0, 0};
		gridBagLayout_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gridBagLayout_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		lstRectangle = new JList();
		scrollPane.setViewportView(lstRectangle);
		lstRectangle.setModel(dlm);
		
		JPanel pnlWest = new JPanel();
		contentPane.add(pnlWest, BorderLayout.WEST);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gridBagLayout);
		
		JButton btnAddRectangle = new JButton("Add rectangle");
		btnAddRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgSort dlgSort = new DlgSort();
				dlgSort.setVisible(true); 
				if(dlgSort.isOK)
				{
					try {
						int x = Integer.parseInt(dlgSort.getTxtX().getText()); 
						int y = Integer.parseInt(dlgSort.getTxtY().getText());
						int width = Integer.parseInt(dlgSort.getTxtWidth().getText());
						int height = Integer.parseInt(dlgSort.getTxtHeight().getText());
						
						rectangle Rectangle = new rectangle(new Point(x,y), height, width); 
						
						dlm.add(0, Rectangle);
						list.add(Rectangle);
						
						
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
		
		JButton btnSortRectangles = new JButton("Sort rectangles");
		btnSortRectangles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty, please insert values!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					list.sort(null);
					dlm.clear();
					for(int i=0; i<list.size();i++) {
						dlm.addElement(list.get(i));
					}
				}
			}
		});
		GridBagConstraints gbc_btnSortRectangles = new GridBagConstraints();
		gbc_btnSortRectangles.gridx = 0;
		gbc_btnSortRectangles.gridy = 4;
		pnlWest.add(btnSortRectangles, gbc_btnSortRectangles);
	}

	

}
