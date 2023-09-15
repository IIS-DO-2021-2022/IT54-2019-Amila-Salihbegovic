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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import java.awt.Dimension;

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
	private final JTextArea textArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton btnNext;
	private final JButton tglbtnInnerColor = new JButton("Inner color ");
	private final JButton tglbtnNOuterColor = new JButton("Border color");
	private final JPanel panel = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnNewMenu = new JMenu("Shapes");
	private final JMenu mnNewMenu_1 = new JMenu("Colors");
	private final JMenu mnNewMenu_2 = new JMenu("Commands");
	private final JMenu mnNewMenu_3 = new JMenu("Save/Load");
	

	public DrawingFrame() {
		view.setAlignmentY(0.0f);
		view.setAlignmentX(0.0f);
		view.setBackground(new Color(230, 230, 250));

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
		ButtonGroup btnGroup = new ButtonGroup();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
				mnNewMenu.setAlignmentY(Component.TOP_ALIGNMENT);
				mnNewMenu.setForeground(SystemColor.textHighlight);
				mnNewMenu.setFont(new Font("Sitka Heading", Font.BOLD, 15));
				
				menuBar.add(mnNewMenu);
				
						btnGroup.add(tglbtnPoint);
						tglbtnPoint.setMaximumSize(new Dimension(93, 21));
						tglbtnPoint.setPreferredSize(new Dimension(90, 21));
						mnNewMenu.add(tglbtnPoint);
						tglbtnPoint.setBackground(SystemColor.menu);
						tglbtnPoint.setForeground(Color.BLUE);
						tglbtnPoint.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnGroup.add(tglbtnLine);
						tglbtnLine.setMaximumSize(new Dimension(93, 21));
						mnNewMenu.add(tglbtnLine);
						tglbtnLine.setBackground(new Color(255, 255, 240));
						tglbtnLine.setForeground(Color.BLUE);
						tglbtnLine.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnGroup.add(tglbtnCircle);
						tglbtnCircle.setMaximumSize(new Dimension(93, 21));
						mnNewMenu.add(tglbtnCircle);
						tglbtnCircle.setBackground(new Color(255, 255, 240));
						tglbtnCircle.setForeground(Color.BLUE);
						tglbtnCircle.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnGroup.add(tglbtnDonut);
						tglbtnDonut.setMaximumSize(new Dimension(93, 21));
						mnNewMenu.add(tglbtnDonut);
						tglbtnDonut.setBackground(new Color(255, 255, 240));
						tglbtnDonut.setForeground(Color.BLUE);
						tglbtnDonut.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnGroup.add(tglbtnRectangle);
						mnNewMenu.add(tglbtnRectangle);
						tglbtnRectangle.setBackground(new Color(255, 255, 240));
						tglbtnRectangle.setForeground(Color.BLUE);
						tglbtnRectangle.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						btnGroup.add(tglbtnHexagon);
						tglbtnHexagon.setMaximumSize(new Dimension(92, 21));
						mnNewMenu.add(tglbtnHexagon);
						tglbtnHexagon.setBackground(new Color(255, 255, 240));
						tglbtnHexagon.setForeground(Color.BLUE);
						tglbtnHexagon.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
						mnNewMenu_1.setAlignmentY(Component.TOP_ALIGNMENT);
						mnNewMenu_1.setForeground(SystemColor.textHighlight);
						mnNewMenu_1.setFont(new Font("Sitka Heading", Font.BOLD, 15));
						
						menuBar.add(mnNewMenu_1);
						tglbtnInnerColor.setMaximumSize(new Dimension(140, 21));
						mnNewMenu_1.add(tglbtnInnerColor);
						tglbtnInnerColor.setForeground(Color.BLUE);
						tglbtnInnerColor.setBackground(SystemColor.menu);
						
														
														tglbtnInnerColor.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																controller.addinnercolor();
																getTglbtnInnerColor().setSelected(false);
															}
														});
														tglbtnInnerColor.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
														tglbtnInnerColor.setEnabled(true);
														mnNewMenu_1.add(tglbtnNOuterColor);
														tglbtnNOuterColor.setForeground(Color.BLUE);
														tglbtnNOuterColor.setBackground(SystemColor.menu);
														
														tglbtnNOuterColor.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																controller.addOuterColor();
																getTglbtnNOuterColor().setSelected(false);

															}
														});
														tglbtnNOuterColor.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
														mnNewMenu_2.setAlignmentY(Component.TOP_ALIGNMENT);
														mnNewMenu_2.setForeground(SystemColor.textHighlight);
														mnNewMenu_2.setFont(new Font("Sitka Heading", Font.BOLD, 15));
														
														menuBar.add(mnNewMenu_2);
														tglbtnSelect.setMaximumSize(new Dimension(133, 21));
														mnNewMenu_2.add(tglbtnSelect);
														tglbtnSelect.setBackground(SystemColor.menu);
														tglbtnSelect.setForeground(Color.BLUE);
														tglbtnSelect.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
														btnDelete.setMaximumSize(new Dimension(133, 21));
														mnNewMenu_2.add(btnDelete);
														btnDelete.setBackground(SystemColor.menu);
														btnDelete.setForeground(Color.BLUE);
														btnDelete.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
														btnDelete.setEnabled(false);
														
																btnDelete.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		controller.delete();
																	}
																});
																btnGroup.add(btnDelete);
																btnModify.setMaximumSize(new Dimension(133, 21));
																mnNewMenu_2.add(btnModify);
																btnModify.setBackground(SystemColor.menu);
																btnModify.setForeground(Color.BLUE);
																btnModify.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																btnModify.setEnabled(false);
																
																		btnModify.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent e) {
																
																				controller.modify();
																
																			}
																		});
																		btnGroup.add(btnModify);
																		btnToFront.setMaximumSize(new Dimension(133, 21));
																		mnNewMenu_2.add(btnToFront);
																		btnToFront.setBackground(SystemColor.menu);
																		btnToFront.setForeground(Color.BLUE);
																		btnToFront.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																		btnToFront.setEnabled(false);
																		btnGroup.add(btnToFront);
																		btnGroup.add(btnToBack);
																		btnToBack.setMaximumSize(new Dimension(133, 21));
																		mnNewMenu_2.add(btnToBack);
																		btnToBack.setBackground(SystemColor.menu);
																		btnToBack.setForeground(Color.BLUE);
																		btnToBack.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																		btnToBack.setEnabled(false);
																		btnGroup.add(btnBringToFront);
																		btnBringToFront.setMaximumSize(new Dimension(133, 21));
																		mnNewMenu_2.add(btnBringToFront);
																		btnBringToFront.setBackground(SystemColor.menu);
																		btnBringToFront.setForeground(Color.BLUE);
																		btnBringToFront.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																		btnBringToFront.setEnabled(false);
																		btnGroup.add(btnBringToBack);
																		btnBringToBack.setMaximumSize(new Dimension(133, 21));
																		mnNewMenu_2.add(btnBringToBack);
																		btnBringToBack.setBackground(SystemColor.menu);
																		btnBringToBack.setForeground(Color.BLUE);
																		btnBringToBack.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																		btnBringToBack.setEnabled(false);
																		mnNewMenu_3.setAlignmentY(Component.TOP_ALIGNMENT);
																		mnNewMenu_3.setForeground(SystemColor.textHighlight);
																		mnNewMenu_3.setFont(new Font("Sitka Heading", Font.BOLD, 15));
																		
																		menuBar.add(mnNewMenu_3);
																		
																				JButton btnSaveCommands = new JButton("Save Commands");
																				btnSaveCommands.setMaximumSize(new Dimension(200, 21));
																				mnNewMenu_3.add(btnSaveCommands);
																				btnSaveCommands.setBackground(SystemColor.menu);
																				btnSaveCommands.setForeground(Color.BLUE);
																				btnSaveCommands.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																				btnSaveCommands.setEnabled(true);
																				
																						JButton btnLoadCommands = new JButton("Load Commands");
																						mnNewMenu_3.add(btnLoadCommands);
																						btnLoadCommands.setBackground(SystemColor.menu);
																						btnLoadCommands.setForeground(Color.BLUE);
																						btnLoadCommands.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																						btnLoadCommands.setEnabled(true);
																						
																								JButton btnSaveDrawing = new JButton("Save Drawing");
																								btnSaveDrawing.setMaximumSize(new Dimension(200, 21));
																								mnNewMenu_3.add(btnSaveDrawing);
																								btnSaveDrawing.setForeground(Color.BLUE);
																								btnSaveDrawing.setBackground(SystemColor.menu);
																								btnSaveDrawing.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																								btnSaveDrawing.setEnabled(true);
																								
																										JButton btnLoadDrawing = new JButton("Load Drawing");
																										btnLoadDrawing.setMaximumSize(new Dimension(200, 21));
																										mnNewMenu_3.add(btnLoadDrawing);
																										btnLoadDrawing.setForeground(Color.BLUE);
																										btnLoadDrawing.setBackground(SystemColor.menu);
																										btnLoadDrawing.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
																										btnLoadDrawing.setEnabled(true);
																										btnLoadDrawing.addActionListener(new ActionListener() {
																											public void actionPerformed(ActionEvent e) {
																												controller.loadDrawing();
																											}
																										});
																								
																										btnSaveDrawing.addActionListener(new ActionListener() {
																											public void actionPerformed(ActionEvent e) {
																												controller.saveDrawing();
																											}
																										});
																						
																								btnLoadCommands.addActionListener(new ActionListener() {
																									public void actionPerformed(ActionEvent e) {
																										controller.loadCommands();
																									}
																								});
																				
																						btnSaveCommands.addActionListener(new ActionListener() {
																							public void actionPerformed(ActionEvent e) {
																								controller.saveCommands();
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
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		getContentPane().add(view, BorderLayout.CENTER);	

		

		JPanel pnlEast = new JPanel();
		pnlEast.setAlignmentY(0.0f);
		pnlEast.setAlignmentX(0.0f);
		pnlEast.setBackground(new Color(255, 255, 255));
		pnlEast.setForeground(new Color(191, 205, 219));
		contentPane.add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[] { 172, 0 };
		gbl_pnlEast.rowHeights = new int[] { 208, 0, 0, 0 };
		gbl_pnlEast.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlEast.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlEast.setLayout(gbl_pnlEast);
								textArea.setAlignmentY(0.0f);
								textArea.setAlignmentX(0.0f);
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
										scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
										pnlEast.add(scrollPane, gbc_scrollPane);
						
								btnNext = new JButton("Next");
								btnNext.setBackground(SystemColor.menu);
								btnNext.setForeground(Color.BLUE);
								btnNext.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
								GridBagConstraints gbc_btnNext = new GridBagConstraints();
								btnNext.setEnabled(false);
								gbc_btnNext.insets = new Insets(0, 0, 5, 0);
								gbc_btnNext.gridx = 0;
								gbc_btnNext.gridy = 1;
								pnlEast.add(btnNext, gbc_btnNext);
		panel.setBackground(new Color(255, 255, 255));
		
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.add(btnUndo);
		btnUndo.setBackground(new Color(255, 255, 255));
		btnUndo.setForeground(Color.BLUE);
		btnUndo.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		btnUndo.setEnabled(true);
		btnGroup.add(btnUndo);
		
				btnUndo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.undo();
					}
				});
		panel.add(btnRedo);
		btnRedo.setBackground(new Color(255, 255, 255));
		btnRedo.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		btnRedo.setEnabled(true);
		btnRedo.setForeground(Color.BLUE);
		btnGroup.add(btnRedo);
		
				btnRedo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.redo();
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
