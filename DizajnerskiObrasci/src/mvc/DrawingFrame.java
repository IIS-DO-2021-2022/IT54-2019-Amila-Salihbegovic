package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
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
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.GridLayout;

public class DrawingFrame extends JFrame{
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JPanel contentPane = new JPanel();
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JToggleButton tglbtnSelect = new JToggleButton("Select");

	private final JButton btnModify = new JButton("Modify");
	private final JButton btnDelete = new JButton("Delete");

	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnToBack = new JButton("To Back ");
	private final JButton btnToFront = new JButton("To Front");
	private final JButton btnBringToFront = new JButton("Bring To Front");
	private final JButton btnBringToBack = new JButton("Bring To Back");
	private final JLabel lblNewLabel_2 = new JLabel("Commands:");
	private final JTextArea textArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton btnNext;
	private final JButton tglbtnInnerColor = new JButton("Inner color ");
	private final JButton tglbtnNOuterColor = new JButton("Area color");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Shapes:");
	private final JLabel lblNewLabel_1 = new JLabel("Colors:");
	

	public DrawingFrame() {
		view.setBackground(new Color(253, 245, 230));

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.mouseClicked(e);

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drawing Application-Salihbegovic Amila IT54/2019");
		setBounds(300, 300, 900, 800);
		setResizable(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		getContentPane().add(view, BorderLayout.CENTER);	

		ButtonGroup btnGroup = new ButtonGroup();

		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(new Color(245, 222, 179));
		pnlEast.setForeground(new Color(191, 205, 219));
		contentPane.add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[] { 246, 0 };
		gbl_pnlEast.rowHeights = new int[] { 150, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlEast.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlEast.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlEast.setLayout(gbl_pnlEast);
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
						btnSaveCommands.setBackground(new Color(245, 222, 179));
						btnSaveCommands.setForeground(new Color(244, 164, 96));
						btnSaveCommands.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnSaveCommands.setEnabled(true);
						GridBagConstraints gbc_btnSaveCommands = new GridBagConstraints();
						gbc_btnSaveCommands.insets = new Insets(0, 0, 5, 0);
						gbc_btnSaveCommands.gridx = 0;
						gbc_btnSaveCommands.gridy = 4;
						pnlEast.add(btnSaveCommands, gbc_btnSaveCommands);
						
								btnSaveCommands.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.saveCommands();
									}
								});
		
				JButton btnLoadCommands = new JButton("Load Commands");
				btnLoadCommands.setBackground(new Color(245, 222, 179));
				btnLoadCommands.setForeground(new Color(244, 164, 96));
				btnLoadCommands.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
				btnLoadCommands.setEnabled(true);
				GridBagConstraints gbc_btnLoadCommands = new GridBagConstraints();
				gbc_btnLoadCommands.insets = new Insets(0, 0, 5, 0);
				gbc_btnLoadCommands.gridx = 0;
				gbc_btnLoadCommands.gridy = 5;
				pnlEast.add(btnLoadCommands, gbc_btnLoadCommands);
				
						btnLoadCommands.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.loadCommands();
							}
						});

		btnNext = new JButton("Next");
		btnNext.setBackground(new Color(245, 222, 179));
		btnNext.setForeground(new Color(244, 164, 96));
		btnNext.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		btnNext.setEnabled(false);
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 6;
		pnlEast.add(btnNext, gbc_btnNext);
		
				JButton btnSaveDrawing = new JButton("Save Drawing");
				btnSaveDrawing.setForeground(new Color(244, 164, 96));
				btnSaveDrawing.setBackground(new Color(245, 222, 179));
				btnSaveDrawing.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
				btnSaveDrawing.setEnabled(true);
				GridBagConstraints gbc_btnSaveDrawing = new GridBagConstraints();
				gbc_btnSaveDrawing.insets = new Insets(0, 0, 5, 0);
				gbc_btnSaveDrawing.gridx = 0;
				gbc_btnSaveDrawing.gridy = 7;
				pnlEast.add(btnSaveDrawing, gbc_btnSaveDrawing);
				
						btnSaveDrawing.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.saveDrawing();
							}
						});
		
				JButton btnLoadDrawing = new JButton("Load Drawing");
				btnLoadDrawing.setForeground(new Color(244, 164, 96));
				btnLoadDrawing.setBackground(new Color(245, 222, 179));
				btnLoadDrawing.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
				btnLoadDrawing.setEnabled(true);
				GridBagConstraints gbc_btnLoadDrawing = new GridBagConstraints();
				gbc_btnLoadDrawing.insets = new Insets(0, 0, 5, 0);
				gbc_btnLoadDrawing.gridx = 0;
				gbc_btnLoadDrawing.gridy = 8;
				pnlEast.add(btnLoadDrawing, gbc_btnLoadDrawing);
				btnLoadDrawing.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.loadDrawing();
					}
				});
		panel.setBackground(new Color(245, 222, 179));
		
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(244, 164, 96));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Sitka Heading", Font.BOLD, 12));
		tglbtnSelect.setBackground(new Color(245, 222, 179));
		tglbtnSelect.setForeground(new Color(244, 164, 96));
		panel.add(tglbtnSelect);
		tglbtnSelect.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		btnDelete.setBackground(new Color(245, 222, 179));
		btnDelete.setForeground(new Color(244, 164, 96));
		panel.add(btnDelete);
		btnDelete.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		btnDelete.setEnabled(false);
		
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.delete();
					}
				});
				btnGroup.add(btnDelete);
				btnModify.setBackground(new Color(245, 222, 179));
				btnModify.setForeground(new Color(244, 164, 96));
				panel.add(btnModify);
				btnModify.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
				btnModify.setEnabled(false);
				
						btnModify.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
				
								controller.modify();
				
							}
						});
						btnGroup.add(btnModify);
						btnToFront.setBackground(new Color(245, 222, 179));
						btnToFront.setForeground(new Color(244, 164, 96));
						panel.add(btnToFront);
						btnToFront.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnToFront.setEnabled(false);
						btnGroup.add(btnToFront);
						btnGroup.add(btnToBack);
						btnToBack.setBackground(new Color(245, 222, 179));
						btnToBack.setForeground(new Color(244, 164, 96));
						panel.add(btnToBack);
						btnToBack.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnToBack.setEnabled(false);
						btnGroup.add(btnBringToFront);
						btnBringToFront.setBackground(new Color(245, 222, 179));
						btnBringToFront.setForeground(new Color(244, 164, 96));
						panel.add(btnBringToFront);
						btnBringToFront.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnBringToFront.setEnabled(false);
						btnGroup.add(btnBringToBack);
						btnBringToBack.setBackground(new Color(245, 222, 179));
						btnBringToBack.setForeground(new Color(244, 164, 96));
						panel.add(btnBringToBack);
						btnBringToBack.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnBringToBack.setEnabled(false);
						btnUndo.setBackground(new Color(245, 222, 179));
						btnUndo.setForeground(new Color(244, 164, 96));
						panel.add(btnUndo);
						btnUndo.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnUndo.setEnabled(true);
						btnGroup.add(btnUndo);
						panel.add(btnRedo);
						btnRedo.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnRedo.setEnabled(true);
						btnRedo.setForeground(new Color(244, 164, 96));
						btnGroup.add(btnRedo);
						panel_1.setBackground(new Color(245, 222, 179));
						
						contentPane.add(panel_1, BorderLayout.NORTH);
						lblNewLabel.setForeground(new Color(244, 164, 96));
						lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 15));
						
						panel_1.add(lblNewLabel);
						
								btnGroup.add(tglbtnPoint);
								tglbtnPoint.setBackground(new Color(245, 222, 179));
								tglbtnPoint.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnPoint);
								tglbtnPoint.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								btnGroup.add(tglbtnLine);
								tglbtnLine.setBackground(new Color(245, 222, 179));
								tglbtnLine.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnLine);
								tglbtnLine.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								btnGroup.add(tglbtnCircle);
								tglbtnCircle.setBackground(new Color(245, 222, 179));
								tglbtnCircle.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnCircle);
								tglbtnCircle.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								btnGroup.add(tglbtnDonut);
								tglbtnDonut.setBackground(new Color(245, 222, 179));
								tglbtnDonut.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnDonut);
								tglbtnDonut.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								btnGroup.add(tglbtnRectangle);
								tglbtnRectangle.setBackground(new Color(245, 222, 179));
								tglbtnRectangle.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnRectangle);
								tglbtnRectangle.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								btnGroup.add(tglbtnHexagon);
								tglbtnHexagon.setBackground(new Color(245, 222, 179));
								tglbtnHexagon.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnHexagon);
								tglbtnHexagon.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								lblNewLabel_1.setForeground(new Color(244, 164, 96));
								lblNewLabel_1.setFont(new Font("Sitka Heading", Font.BOLD, 15));
								
								panel_1.add(lblNewLabel_1);
								tglbtnInnerColor.setForeground(new Color(244, 164, 96));
								panel_1.add(tglbtnInnerColor);
								tglbtnInnerColor.setBackground(new Color(245, 245, 220));
								
																
																tglbtnInnerColor.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		controller.addinnercolor();
																		getTglbtnInnerColor().setSelected(false);
																	}
																});
																tglbtnInnerColor.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																tglbtnInnerColor.setEnabled(true);
																tglbtnNOuterColor.setForeground(new Color(244, 164, 96));
																panel_1.add(tglbtnNOuterColor);
																tglbtnNOuterColor.setBackground(new Color(245, 245, 220));
																
																tglbtnNOuterColor.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		controller.addOuterColor();
																		getTglbtnNOuterColor().setSelected(false);

																	}
																});
																tglbtnNOuterColor.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						
								btnRedo.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.redo();
									}
								});
						
								btnUndo.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.undo();
									}
								});
						
								btnBringToBack.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.toBack();
									}
								});
						
								btnBringToFront.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.toFront();
									}
								});
						
								btnToBack.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.back();
									}
								});
						
								btnToFront.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.front();
									}
								});

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

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getTglbtnInnerColor() {
		return tglbtnInnerColor;
	}

	public JButton getTglbtnNOuterColor() {
		return tglbtnNOuterColor;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
}
