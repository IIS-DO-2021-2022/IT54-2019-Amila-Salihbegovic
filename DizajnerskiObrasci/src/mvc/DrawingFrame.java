package mvc;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DrawingFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");	
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnDelete;
	private JButton btnModify;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnInnerColor;
	private JButton btnAreaColor;
	private JButton btnNext;
	private JPanel panelLog = new JPanel();
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private final JLabel lblNewLabel = new JLabel("Shapes:");
	
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.mouseClicked(e);

			}
		});
		setBackground(Color.WHITE);
		setTitle("Salihbegovic Amila IT54/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 1050, 800);
		setResizable(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		JPanel panel = new JPanel();
//		contentPane.add(panel, BorderLayout.NORTH);
//		GridBagLayout gbl_panel = new GridBagLayout();
//		gbl_panel.columnWidths = new int[]{57, 309, 0, 0, 0};
//		gbl_panel.rowHeights = new int[]{23, 0};
//		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
//		panel.setLayout(gbl_panel);
		
//		Box horizontalBox = Box.createHorizontalBox();
//		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
//		gbc_horizontalBox.insets = new Insets(0, 0, 0, 0);
//		gbc_horizontalBox.anchor = GridBagConstraints.NORTHWEST;
//		gbc_horizontalBox.gridx = 0;
//		gbc_horizontalBox.gridy = 0;
//		panel.add(horizontalBox, gbc_horizontalBox);
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 127, 80));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		view.setBackground(new Color(255, 228, 181));
		getContentPane().add(view, BorderLayout.CENTER);

		
		pnlNorth.add(lblNewLabel);
		
		tglbtnPoint.setForeground(new Color(255, 127, 80));
		tglbtnPoint.setBackground(Color.BLACK);
		
		pnlNorth.add(tglbtnPoint);
	
		tglbtnLine.setForeground(new Color(255, 127, 80));
		tglbtnLine.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnLine);
		tglbtnCircle.setForeground(new Color(255, 127, 80));
		tglbtnCircle.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnCircle);
		tglbtnRectangle.setForeground(new Color(255, 127, 80));
		tglbtnRectangle.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnRectangle);
		tglbtnDonut.setForeground(new Color(255, 127, 80));
		tglbtnDonut.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnDonut);
		tglbtnDonut.setForeground(Color.RED);
		tglbtnDonut.setBackground(Color.BLACK);
		pnlNorth.add(tglbtnHexagon);
		tglbtnHexagon.setForeground(new Color(255, 127, 80));
		tglbtnHexagon.setBackground(Color.BLACK);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnHexagon);
		
		
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(205, 133, 63));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnModify_1 = new JButton("Modify");
		btnModify_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnModify_1.setForeground(new Color(205, 133, 63));
		btnModify_1.setBackground(Color.BLACK);
		btnModify_1.addActionListener(new ActionListener() {
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
		pnlSouth.add(btnModify_1);
		btnGroup.add(btnModify_1);
		tglbtnSelect.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnSelect.setForeground(new Color(205, 133, 63));
		tglbtnSelect.setBackground(new Color(0, 0, 0));
		pnlSouth.add(tglbtnSelect);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setForeground(new Color(205, 133, 63));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
				tglbtnSelect.setSelected(false);
			}
		});
		pnlSouth.add(btnDelete);
		btnGroup.add(btnDelete);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.setHorizontalAlignment(SwingConstants.CENTER);
		btnUndo.setForeground(new Color(205, 133, 63));
		btnUndo.setBackground(Color.BLACK);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		pnlSouth.add(btnUndo);
		btnGroup.add(btnUndo);
		JButton btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.setHorizontalAlignment(SwingConstants.CENTER);
		btnRedo.setForeground(new Color(205, 133, 63));
		btnRedo.setBackground(Color.BLACK);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		pnlSouth.add(btnRedo);
		btnGroup.add(btnRedo);
		JButton btnBringToBack = new JButton("To back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBringToBack.setForeground(new Color(205, 133, 63));
		btnBringToBack.setBackground(Color.BLACK);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		pnlSouth.add(btnBringToBack);
		btnGroup.add(btnBringToBack);
		JButton btnBringToFront = new JButton("To front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.setHorizontalAlignment(SwingConstants.CENTER);
		btnBringToFront.setForeground(new Color(205, 133, 63));
		btnBringToFront.setBackground(Color.BLACK);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		pnlSouth.add(btnBringToFront);
		btnGroup.add(btnBringToFront);
		JButton btnToFront = new JButton("Front");
		btnToFront.setEnabled(false);
		btnToFront.setHorizontalAlignment(SwingConstants.CENTER);
		btnToFront.setForeground(new Color(205, 133, 63));
		btnToFront.setBackground(Color.BLACK);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.front();
			}
		});
		pnlSouth.add(btnToFront);
		btnGroup.add(btnToFront);
		JButton btnToBack = new JButton("Back");
		btnToBack.setEnabled(false);
		btnToBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnToBack.setForeground(new Color(205, 133, 63));
		btnToBack.setBackground(Color.BLACK);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.back();
			}
		});
		pnlSouth.add(btnToBack);
		btnGroup.add(btnToBack);
		
		JButton btnInnerColor = new JButton("Inner color");
		btnInnerColor.setEnabled(false);
		btnInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
		btnInnerColor.setForeground(new Color(205, 133, 63));
		btnInnerColor.setBackground(Color.BLACK);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.innercolor();
			}
		});
		pnlSouth.add(btnInnerColor);
		btnGroup.add(btnInnerColor);

		JButton btnAreaColor = new JButton("area color");
		btnAreaColor.setEnabled(false);
		btnAreaColor.setHorizontalAlignment(SwingConstants.CENTER);
		btnAreaColor.setForeground(new Color(205, 133, 63));
		btnAreaColor.setBackground(Color.BLACK);
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.areacolor();
			}
		});
		pnlSouth.add(btnAreaColor);
		btnGroup.add(btnAreaColor);
		
		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(new Color(255, 222, 173));
		pnlEast.setForeground(new Color(205, 133, 63));
		contentPane.add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[] { 150, 0 };
		gbl_pnlEast.rowHeights = new int[] { 150, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlEast.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlEast.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlEast.setLayout(gbl_pnlEast);
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setTabSize(40);
		textArea.setColumns(30);
		textArea.setRows(8);
		textArea.setSize(60, 50);
		textArea.setEditable(false);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlEast.add(scrollPane, gbc_scrollPane);

		JButton btnSaveCommands = new JButton("Save Commands");
		btnSaveCommands.setEnabled(true);
		GridBagConstraints gbc_btnSaveCommands = new GridBagConstraints();
		gbc_btnSaveCommands.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveCommands.gridx = 0;
		gbc_btnSaveCommands.gridy = 2;
		pnlEast.add(btnSaveCommands, gbc_btnSaveCommands);

		btnSaveCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveCommands();
			}
		});

		JButton btnLoadCommands = new JButton("Load Commands");
		btnLoadCommands.setEnabled(true);
		GridBagConstraints gbc_btnLoadCommands = new GridBagConstraints();
		gbc_btnLoadCommands.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadCommands.gridx = 0;
		gbc_btnLoadCommands.gridy = 3;
		pnlEast.add(btnLoadCommands, gbc_btnLoadCommands);

		btnLoadCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadCommands();
			}
		});

		btnNext = new JButton("Next");
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		btnNext.setEnabled(false);
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 4;
		pnlEast.add(btnNext, gbc_btnNext);

		JButton btnSaveDrawing = new JButton("Save Drawing");
		btnSaveDrawing.setEnabled(true);
		GridBagConstraints gbc_btnSaveDrawing = new GridBagConstraints();
		gbc_btnSaveDrawing.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveDrawing.gridx = 0;
		gbc_btnSaveDrawing.gridy = 5;
		pnlEast.add(btnSaveDrawing, gbc_btnSaveDrawing);

		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
			}
		});

		JButton btnLoadDrawing = new JButton("Load Drawing");
		btnLoadDrawing.setEnabled(true);
		GridBagConstraints gbc_btnLoadDrawing = new GridBagConstraints();
		gbc_btnLoadDrawing.gridx = 0;
		gbc_btnLoadDrawing.gridy = 6;
		pnlEast.add(btnLoadDrawing, gbc_btnLoadDrawing);

		btnLoadDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDrawing();
			}
		});
		
		
		view.repaint();
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
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

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	public void setBtnAreaColor(JButton btnAreaColor) {
		this.btnAreaColor = btnAreaColor;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JPanel getPanelLog() {
		return panelLog;
	}

	public void setPanelLog(JPanel panelLog) {
		this.panelLog = panelLog;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}
