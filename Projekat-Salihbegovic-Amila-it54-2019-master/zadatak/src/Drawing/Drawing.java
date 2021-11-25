package Drawing;

import geometry.circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.rectangle;
import geometry.Shape;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JTree;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Drawing extends JFrame {

	private JPanel contentPane;
	private PnlDrawing pnlDrawing = new PnlDrawing(this);
	
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");	
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drawing frame = new Drawing();
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
	public Drawing() {
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
		
		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing, BorderLayout.CENTER);

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
			public void actionPerformed(ActionEvent e) {

				if (pnlDrawing.getSelectedShape() != null) {
					modify();
					pnlDrawing.getSelectedShape().setSelected(false);

				} else {
					JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
					tglbtnSelect.setSelected(true);
				}
				pnlDrawing.setSelectedShape(null);
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
				delete();
				tglbtnSelect.setSelected(false);
			}
		});
		pnlSouth.add(btnDelete);
		btnGroup.add(btnDelete);

		pnlDrawing.repaint();

	}
	protected void delete() {

		Shape selectedShape = pnlDrawing.getSelectedShape();

		if (selectedShape != null) 
		{
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) 
			{
				pnlDrawing.getShapes().remove(selectedShape);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error", JOptionPane.WARNING_MESSAGE);
		}
		pnlDrawing.setSelectedShape(null);
		pnlDrawing.repaint();
	}

	protected void modify() 
	{

		Shape selectedShape = pnlDrawing.getSelectedShape();

		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				Point p = (Point) selectedShape;
				DlgPoint dglpoint = new DlgPoint();

				dglpoint.getTxtX().setText("" + Integer.toString(p.getX()));
				dglpoint.getTxtY().setText("" + Integer.toString(p.getY()));
				dglpoint.getBtnColor().setBackground(p.getColor());
				dglpoint.setModal(true);
				dglpoint.setVisible(true);

				if (dglpoint.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dglpoint.getP());
					repaint();
				}

			} else if (selectedShape instanceof Donut) {

				Donut donut = (Donut) selectedShape;
				DlgDonut dgldonut = new DlgDonut();

				dgldonut.getTxtDX().setText("" + Integer.toString(donut.getcenter().getX()));
				dgldonut.getTxtDY().setText("" + Integer.toString(donut.getcenter().getY()));
				dgldonut.getTxtDR().setText("" + Integer.toString(donut.getradius()));
				dgldonut.getTxtDIR().setText("" + Integer.toString(donut.getInnerRadius()));
				dgldonut.getBtnInnerColor().setBackground(donut.getInnerColor());
				dgldonut.getBtnOuterColor().setBackground(donut.getColor());
				dgldonut.setModal(true);
				dgldonut.setVisible(true);

				if (dgldonut.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dgldonut.getDonut());
					repaint();
				}
			} else if (selectedShape instanceof circle && (selectedShape instanceof Donut) == false) {

				circle circle = (circle) selectedShape;
				DlgCircle dglcircle = new DlgCircle();

				dglcircle.getTxtX().setText("" + Integer.toString(circle.getcenter().getX()));
				dglcircle.getTxtY().setText("" + Integer.toString(circle.getcenter().getY()));
				dglcircle.getTxtRadius().setText("" + Integer.toString(circle.getradius()));
				dglcircle.getBtnInnerColor().setBackground(circle.getInnerColor());
				dglcircle.getBtnOutlineColor().setBackground(circle.getColor());
				dglcircle.setVisible(true);
				dglcircle.setModal(true);

				if (dglcircle.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dglcircle.getCircle());
					repaint();
				}

			} else if (selectedShape instanceof Line) {

				Line line = (Line) selectedShape;
				DlgLine dlgline = new DlgLine();

				dlgline.getTxtXStart().setText("" + Integer.toString(line.getstartpoint().getX()));
				dlgline.gettxtYStart().setText("" + Integer.toString(line.getstartpoint().getY()));
				dlgline.getTxtXEnd().setText("" + Integer.toString(line.getendpoint().getX()));
				dlgline.getTxtYEnd().setText("" + Integer.toString(line.getendpoint().getY()));
				dlgline.getBtnColor().setBackground(line.getColor());
				dlgline.setModal(true);
				dlgline.setVisible(true);

				if (dlgline.isOK()) {

					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlgline.getLine());
					repaint();
				}

			} else if (selectedShape instanceof rectangle) {

				rectangle rect = (rectangle) selectedShape;
				DlgRectangle dlgrectangle = new DlgRectangle();

				dlgrectangle.getTxtX().setText("" + Integer.toString(rect.getupperleft().getX()));
				dlgrectangle.getTxtY().setText("" + Integer.toString(rect.getupperleft().getY()));
				dlgrectangle.getTxtHeight().setText("" + Integer.toString(rect.getheight()));
				dlgrectangle.getTxtWidth().setText("" + Integer.toString(rect.getwidth()));
				dlgrectangle.getBtnInnerColor().setBackground(rect.getInnerColor());
				dlgrectangle.getBtnOutlineColor().setBackground(rect.getColor());
				dlgrectangle.setVisible(true);
				dlgrectangle.setModal(true);


				if (dlgrectangle.isOK()) {
					pnlDrawing.getShapes().remove(selectedShape);
					pnlDrawing.getShapes().add(dlgrectangle.getRect());
					repaint();
				}
			}

		}
	}
	
	public PnlDrawing getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(PnlDrawing pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}
}
