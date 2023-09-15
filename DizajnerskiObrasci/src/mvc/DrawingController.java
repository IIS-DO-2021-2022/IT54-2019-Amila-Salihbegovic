package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import Observer.ShapeObserver;
import adapter.HexagonAdapter;
import command.AddShapeCommand;
import command.BackShapeCommand;
import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.Command;
import command.FrontShapeCommand;
import command.RemoveShapeCommand;
import command.UpdateCircleCommand;
import command.UpdateDountCommand;
import command.UpdateHexagonCommand;
import command.UpdateLineCommand;
import command.UpdatePointCommand;
import command.UpdateRectangleCommand;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import strategy.FileCommand;
import strategy.FileLoader;
import strategy.FileLog;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Color innerColor;
	private Color outerColor;
	private ShapeObserver observer;
	private Shape selectedShape;
	private Point startPoint;
	private Stack<Command> activities = new Stack<Command>();
	private Stack<Command> undoActivities = new Stack<Command>();

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		observer = new ShapeObserver(frame);
		model.addNewPCL(observer);
	}

	public void mouseClicked(MouseEvent me) {
		if (frame.getTglbtnSelect().isSelected()) {
			handleSelectAction(me);
		} else if (frame.getTglbtnPoint().isSelected()) {
			createPointShape(me);
		} else if (frame.getTglbtnLine().isSelected()) {
			createLineShape(me);
		} else if (frame.getTglbtnCircle().isSelected()) {
			createCircleShape(me);
		} else if (frame.getTglbtnDonut().isSelected()) {
			createDonutShape(me);
		} else if (frame.getTglbtnHexagon().isSelected()) {
			createHexagonShape(me);
		} else if (frame.getTglbtnRectangle().isSelected()) {
			createRectangleShape(me);
		}
		frame.getView().repaint();
	}

	private void handleSelectAction(MouseEvent me) {
		Point click = new Point(me.getX(), me.getY());
		selectedShape = null;

		 List<Shape> shapes = model.getShapes();

		    for (int i = shapes.size() - 1; i >= 0; i--) {
		        Shape shape = shapes.get(i);
		        if (shape.contains(click.getX(), click.getY())) {
		            selectedShape = shape;
		            break;
		        }
		    }

		if (selectedShape != null) {
			boolean isSelected = selectedShape.isSelected();

			if (!isSelected) {
				selectedShape.setSelected(true);
				model.addSelected(selectedShape);
				frame.getTextArea().append("Selected-> " + selectedShape.toString() + "\n");
			} else {
				selectedShape.setSelected(false);
				model.removeSelected(selectedShape);
				frame.getTextArea().append("Deselected-> " + selectedShape.toString() + "\n");
			}
		}
	}

	private void createPointShape(MouseEvent me) {
		Shape newShape = new Point(me.getX(), me.getY(), false, Color.BLACK);
		addShapeAndLog(newShape);
	}

	private void createLineShape(MouseEvent me) {
		if (startPoint == null) {
			startPoint = new Point(me.getX(), me.getY());
		} else {
			Shape newShape = new Line(startPoint, new Point(me.getX(), me.getY(), false, Color.BLACK));
			startPoint = null;
			addShapeAndLog(newShape);
		}
	}

	private void createCircleShape(MouseEvent me) {
	    int x = me.getX();
	    int y = me.getY();

	    DialogCircle dialog = createCircleDialog(x, y);
	    setColors(dialog);

	    dialog.setVisible(true);

	    if (dialog.isOK()) {
	        Shape newShape = dialog.getCircle();
	        addShapeAndLog(newShape);
	    }
	}

	private DialogCircle createCircleDialog(int x, int y) {
	    DialogCircle dialog = new DialogCircle();
	    dialog.getTxtX().setText(Integer.toString(x));
	    dialog.getTxtX().setEditable(false);
	    dialog.getTxtY().setText(Integer.toString(y));
	    dialog.getTxtY().setEditable(false);
	    return dialog;
	}

	private void setColors(DialogCircle dialog) {
	    if (innerColor != null) {
	        dialog.getBtnInnerColor().setBackground(innerColor);
	    }
	    if (outerColor != null) {
	        dialog.getBtnOutlineColor().setBackground(outerColor);
	    }
	}
	private void createDonutShape(MouseEvent me) {
	    int x = me.getX();
	    int y = me.getY();

	    DialogDonut dialog = createDonutDialog(x, y);
	    setColors(dialog);

	    dialog.setVisible(true);

	    if (dialog.isOK()) {
	        Shape newShape = dialog.getDonut();
	        addShapeAndLog(newShape);
	    }
	}

	private DialogDonut createDonutDialog(int x, int y) {
	    DialogDonut dialog = new DialogDonut();
	    dialog.setModal(true);
	    dialog.getTxtX().setText(Integer.toString(x));
	    dialog.getTxtX().setEditable(false);
	    dialog.getTxtY().setText(Integer.toString(y));
	    dialog.getTxtY().setEditable(false);
	    return dialog;
	}

	private void setColors(DialogDonut dialog) {
	    if (innerColor != null) {
	        dialog.getBtnInnerColor().setBackground(innerColor);
	    }
	    if (outerColor != null) {
	        dialog.getBtnOuterColor().setBackground(outerColor);
	    }
	}
	private void createHexagonShape(MouseEvent me) {
	    int x = me.getX();
	    int y = me.getY();

	    DialogHexagon dialog = createHexagonDialog(x, y);
	    setColors(dialog);

	    dialog.setVisible(true);

	    if (dialog.isOK()) {
	        try {
	            Shape newShape = dialog.getHexagon();
	            addShapeAndLog(newShape);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private DialogHexagon createHexagonDialog(int x, int y) {
	    DialogHexagon dialog = new DialogHexagon();
	    dialog.getTxtX().setText(Integer.toString(x));
	    dialog.getTxtX().setEditable(false);
	    dialog.getTxtY().setText(Integer.toString(y));
	    dialog.getTxtY().setEditable(false);
	    return dialog;
	}

	private void setColors(DialogHexagon dialog) {
	    if (innerColor != null) {
	        dialog.getBtnInnerColor().setBackground(innerColor);
	    }
	    if (outerColor != null) {
	        dialog.getBtnOutlineColor().setBackground(outerColor);
	    }
	}

	private void createRectangleShape(MouseEvent me) {
	    int x = me.getX();
	    int y = me.getY();

	    DialogRectangle dialog = createRectangleDialog(x, y);
	    setColors(dialog);

	    dialog.setVisible(true);

	    if (dialog.isOK()) {
	        Shape newShape = dialog.getRect();
	        addShapeAndLog(newShape);
	    }
	}

	private DialogRectangle createRectangleDialog(int x, int y) {
	    DialogRectangle dialog = new DialogRectangle();
	    dialog.setModal(true);
	    dialog.getTxtX().setText(Integer.toString(x));
	    dialog.getTxtX().setEditable(false);
	    dialog.getTxtY().setText(Integer.toString(y));
	    dialog.getTxtY().setEditable(false);
	    return dialog;
	}

	private void setColors(DialogRectangle dialog) {
	    if (innerColor != null) {
	        dialog.getBtnInnerColor().setBackground(innerColor);
	    }
	    if (outerColor != null) {
	        dialog.getBtnOutlineColor().setBackground(outerColor);
	    }
	}

	private void addShapeAndLog(Shape newShape) {
		frame.getTextArea().append("Added-> " + newShape.toString() + "\n");
		Command cmd = new AddShapeCommand(newShape, model);
		cmd.execute();
		activities.push(cmd);

		frame.getBtnUndo().setEnabled(true);
		clearRedo();
		frame.getView().repaint();
	}

	private void clearRedo() {
		getUndoActivities().clear();
		frame.getBtnRedo().setEnabled(false);
	}

	public void modify() {
		Shape selectedShape = model.getSelectedShapes().get(0);
		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				ModifyPoint(selectedShape);
			} else if (selectedShape instanceof Donut) {

				ModifyDonut(selectedShape);
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {

				ModifyCircle(selectedShape);
			} else if (selectedShape instanceof Line) {

				ModifyLine(selectedShape);
			} else if (selectedShape instanceof Rectangle) {

				ModifyRectangle(selectedShape);
			} else if (selectedShape instanceof HexagonAdapter) {
				ModifyHexagon(selectedShape);
			}

		}
		clearRedo();
		model.getSelectedShapes().get(0).setSelected(true);

		frame.getView().repaint();
	}

	private void ModifyPoint(Shape shape) {
		Command command;
		Point p = (Point) shape;
		DialogPoint dglpoint = new DialogPoint();

		dglpoint.getTxtX().setText("" + Integer.toString(p.getX()));
		dglpoint.getTxtY().setText("" + Integer.toString(p.getY()));
		dglpoint.getBtnColor().setBackground(p.getColor());
		dglpoint.setModal(true);
		dglpoint.setVisible(true);

		if (dglpoint.isOK()) {
			if (dglpoint.getTxtX().getText().trim().isEmpty() || dglpoint.getTxtY().getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			try {
				if (Integer.parseInt(dglpoint.getTxtX().getText().toString()) < 0
						|| Integer.parseInt(dglpoint.getTxtY().getText().toString()) < 0) {
					JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					frame.getTextArea().append("Modified-> " + ((Point) dglpoint.getP()).toString() + "\n");
					command = new UpdatePointCommand((Point) selectedShape, dglpoint.getP());
					command.execute();
					activities.push(command);
					selectedShape.setSelected(true);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void ModifyHexagon(Shape selectedShape2) {
		Command command;
		HexagonAdapter hex = (HexagonAdapter) selectedShape;
		DialogHexagon dlghexagon = new DialogHexagon();

		dlghexagon.getTxtX().setText("" + Integer.toString(hex.getNewHexagon().getX()));
		dlghexagon.getTxtY().setText("" + Integer.toString(hex.getNewHexagon().getY()));
		dlghexagon.getTxtR().setText("" + Integer.toString(hex.getNewHexagon().getR()));
		dlghexagon.getBtnInnerColor().setBackground(hex.getNewHexagon().getAreaColor());
		dlghexagon.getBtnOutlineColor().setBackground(hex.getNewHexagon().getBorderColor());
		dlghexagon.setModal(true);
		dlghexagon.setVisible(true);

		if (dlghexagon.isOK()) {
			if (dlghexagon.getTxtX().getText().trim().isEmpty() || dlghexagon.getTxtY().getText().trim().isEmpty()
					|| dlghexagon.getTxtR().getText().trim().isEmpty()) {

				JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (Integer.parseInt(dlghexagon.getTxtR().getText().toString()) <= 0
							|| Integer.parseInt(dlghexagon.getTxtX().getText().toString()) < 0
							|| Integer.parseInt(dlghexagon.getTxtY().getText().toString()) < 0) {
						JOptionPane.showMessageDialog(null, "Insert values greather than 0!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						frame.getTextArea()
								.append("Modified-> " + ((HexagonAdapter) dlghexagon.getHexagon()).toString() + "\n");
						command = new UpdateHexagonCommand((HexagonAdapter) selectedShape, dlghexagon.getHexagon());
						command.execute();
						activities.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Enter numbers only", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void ModifyRectangle(Shape selectedShape2) {
		Command command;
		Rectangle rect = (Rectangle) selectedShape;
		DialogRectangle dlgrectangle = new DialogRectangle();

		dlgrectangle.getTxtX().setText("" + Integer.toString(rect.getupperleft().getX()));
		dlgrectangle.getTxtY().setText("" + Integer.toString(rect.getupperleft().getY()));
		dlgrectangle.getTxtHeight().setText("" + Integer.toString(rect.getheight()));
		dlgrectangle.getTxtWidth().setText("" + Integer.toString(rect.getwidth()));
		dlgrectangle.getBtnInnerColor().setBackground(rect.getInnerColor());
		dlgrectangle.getBtnOutlineColor().setBackground(rect.getColor());
		dlgrectangle.setVisible(true);
		dlgrectangle.setModal(true);

		if (dlgrectangle.isOK()) {
			if (dlgrectangle.getTxtX().getText().trim().isEmpty() || dlgrectangle.getTxtY().getText().trim().isEmpty()
					|| dlgrectangle.getTxtHeight().getText().trim().isEmpty()
					|| dlgrectangle.getTxtWidth().getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "All values are required!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (Integer.parseInt(dlgrectangle.getTxtX().getText().toString()) < 0
							|| Integer.parseInt(dlgrectangle.getTxtY().getText().toString()) < 0
							|| Integer.parseInt(dlgrectangle.getTxtHeight().getText().toString()) < 0
							|| Integer.parseInt(dlgrectangle.getTxtWidth().getText().toString()) < 0) {
						JOptionPane.showMessageDialog(null, "Insert values greater then 0!", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						frame.getTextArea()
								.append("Modified-> " + ((Rectangle) dlgrectangle.getRect()).toString() + "\n");
						command = new UpdateRectangleCommand((Rectangle) selectedShape, dlgrectangle.getRect());
						command.execute();
						activities.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Enter numbers only", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void ModifyLine(Shape selectedShape2) {
		Command command;
		Line line = (Line) selectedShape;
		DialogLine dlgline = new DialogLine();

		dlgline.getTxtXStart().setText("" + Integer.toString(line.getstartpoint().getX()));
		dlgline.gettxtYStart().setText("" + Integer.toString(line.getstartpoint().getY()));
		dlgline.getTxtXEnd().setText("" + Integer.toString(line.getendpoint().getX()));
		dlgline.getTxtYEnd().setText("" + Integer.toString(line.getendpoint().getY()));
		dlgline.getBtnColor().setBackground(line.getColor());
		dlgline.setModal(true);
		dlgline.setVisible(true);

		if (dlgline.isOK()) {
			if (dlgline.getTxtXStart().getText().trim().isEmpty() || dlgline.gettxtYStart().getText().trim().isEmpty()
					|| dlgline.getTxtXEnd().getText().trim().isEmpty()
					|| dlgline.getTxtYEnd().getText().trim().isEmpty()) {

				JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (Integer.parseInt(dlgline.getTxtXStart().getText().toString()) < 0
							|| Integer.parseInt(dlgline.gettxtYStart().getText().toString()) < 0
							|| Integer.parseInt(dlgline.getTxtXEnd().getText().toString()) < 0
							|| Integer.parseInt(dlgline.getTxtYEnd().getText().toString()) < 0) {
						JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "Error",
								JOptionPane.ERROR_MESSAGE);

					} else {
						frame.getTextArea().append("Modified-> " + ((Line) dlgline.getLine()).toString() + "\n");
						command = new UpdateLineCommand((Line) selectedShape, dlgline.getLine());
						command.execute();
						activities.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Enter numbers only", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void ModifyCircle(Shape selectedShape2) {
		Command command;
		Circle circle = (Circle) selectedShape;
		DialogCircle dglcircle = new DialogCircle();

		dglcircle.getTxtX().setText("" + Integer.toString(circle.getcenter().getX()));
		dglcircle.getTxtY().setText("" + Integer.toString(circle.getcenter().getY()));
		dglcircle.getTxtRadius().setText("" + Integer.toString(circle.getradius()));
		dglcircle.getBtnInnerColor().setBackground(circle.getInnerColor());
		dglcircle.getBtnOutlineColor().setBackground(circle.getColor());
		dglcircle.setVisible(true);
		dglcircle.setModal(true);

		if (dglcircle.isOK()) {
			if (dglcircle.getTxtX().getText().trim().isEmpty() || dglcircle.getTxtY().getText().trim().isEmpty()
					|| dglcircle.getTxtRadius().getText().trim().isEmpty()) {

				JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (Integer.parseInt(dglcircle.getTxtRadius().getText().toString()) <= 0
							|| Integer.parseInt(dglcircle.getTxtX().getText().toString()) < 0
							|| Integer.parseInt(dglcircle.getTxtY().getText().toString()) < 0) {
						JOptionPane.showMessageDialog(null, "Insert values greather than 0!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						frame.getTextArea().append("Modified-> " + ((Circle) dglcircle.getCircle()).toString() + "\n");
						command = new UpdateCircleCommand((Circle) selectedShape, dglcircle.getCircle());
						command.execute();
						activities.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Enter numbers only", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void ModifyDonut(Shape selectedShape2) {
		Command command;
		Donut donut = (Donut) selectedShape;
		DialogDonut dgldonut = new DialogDonut();

		dgldonut.getTxtX().setText("" + Integer.toString(donut.getcenter().getX()));
		dgldonut.getTxtY().setText("" + Integer.toString(donut.getcenter().getY()));
		dgldonut.getTxtR().setText("" + Integer.toString(donut.getradius()));
		dgldonut.getTxtDIR().setText("" + Integer.toString(donut.getInnerRadius()));
		dgldonut.getBtnInnerColor().setBackground(donut.getInnerColor());
		dgldonut.getBtnOuterColor().setBackground(donut.getColor());
		dgldonut.setModal(true);
		dgldonut.setVisible(true);

		if (dgldonut.isOK()) {
			if (dgldonut.isOK()) {

				if (dgldonut.getTxtX().getText().trim().isEmpty() || dgldonut.getTxtY().getText().trim().isEmpty()
						|| dgldonut.getTxtR().getText().trim().isEmpty()
						|| dgldonut.getTxtDIR().getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "All values are required!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						if (Integer.parseInt(dgldonut.getTxtX().getText().toString()) < 0
								|| Integer.parseInt(dgldonut.getTxtY().getText().toString()) < 0
								|| Integer.parseInt(dgldonut.getTxtR().getText().toString()) < 0
								|| Integer.parseInt(dgldonut.getTxtDIR().getText().toString()) < 0) {
							JOptionPane.showMessageDialog(null, "Insert values greater then 0!", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							frame.getTextArea().append("Modified-> " + ((Donut) dgldonut.getDonut()).toString() + "\n");
							command = new UpdateDountCommand((Donut) selectedShape, dgldonut.getDonut());
							command.execute();
							selectedShape.setSelected(true);
							activities.push(command);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	public void delete() {
	    ArrayList<Shape> selectedShapes = model.getSelectedShapes();

	    if (selectedShapes == null || selectedShapes.isEmpty()) {
	        showSelectionError();
	        return;
	    }

	    int selectedOption = showDeleteConfirmationDialog();

	    if (selectedOption == JOptionPane.YES_OPTION) {
	        performDelete(selectedShapes);
	    }

	    clearRedo();
	    frame.getView().repaint();
	    frame.getTglbtnSelect().setSelected(false);
	}

	private void showSelectionError() {
	    JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
	            JOptionPane.WARNING_MESSAGE);
	}

	private int showDeleteConfirmationDialog() {
	    return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
	            "Warning message", JOptionPane.YES_NO_OPTION);
	}

	private void performDelete(ArrayList<Shape> selectedShapes) {
	    frame.getTextArea().append("Deleted-> " + selectedShapes.toString() + "\n");
	    Command command = new RemoveShapeCommand(model, selectedShapes);
	    command.execute();
	    activities.push(command);
	}
	public void addinnercolor() {
		innerColor = JColorChooser.showDialog(null, "Choose inner color", frame.getTglbtnInnerColor().getBackground());
		if (innerColor != null) {
			frame.getTglbtnInnerColor().setBackground(innerColor);
		}
	}

	public void addOuterColor() {
		outerColor = JColorChooser.showDialog(null, "Choose outer color", frame.getTglbtnNOuterColor().getBackground());
		if (outerColor != null) {
			frame.getTglbtnNOuterColor().setBackground(outerColor);
		}
	}

	public void undo() {
	    if (!activities.isEmpty()) {
	        Command command = activities.pop();
	        command.unexecute();
	        undoActivities.push(command);
	        frame.getBtnRedo().setEnabled(!undoActivities.isEmpty());
	        frame.getBtnUndo().setEnabled(!activities.isEmpty());
	        frame.getTextArea().append("MOVEUNDO \n");
	        frame.getView().repaint();
	    }
	    if(undoActivities.size()==0) {
	    	frame.getBtnUndo().setEnabled(false);
	    }
	    model.getSelectedShapes().get(0).setSelected(true);
	}

	public void redo() {
	    if (!undoActivities.isEmpty()) {
	        Command command = undoActivities.pop();
	        command.execute();
	        activities.push(command);
	        frame.getBtnUndo().setEnabled(true);
	        frame.getBtnRedo().setEnabled(!undoActivities.isEmpty());
	        frame.getTextArea().append("MOVEREDO \n");
	        frame.getView().repaint();
	    }
	    if(activities.size()==0) {
	    	frame.getBtnRedo().setEnabled(false);
	    }
	    model.getSelectedShapes().get(0).setSelected(true);
	}
	public void toBack() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new BringToBackCommand(model, shape);
		command.execute();
		activities.push(command);
		clearRedo();
		frame.getTextArea().append("Send_to_back: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void toFront() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new BringToFrontCommand(model, shape);
		command.execute();
		activities.push(command);
		clearRedo();
		frame.getTextArea().append("Bring_to_front: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void front() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new FrontShapeCommand(shape, model);
		command.execute();
		activities.push(command);
		clearRedo();
		frame.getTextArea().append("Send_front: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void back() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new BackShapeCommand(shape, model);
		command.execute();
		activities.push(command);
		clearRedo();
		frame.getTextArea().append("Send_back: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void saveCommands() {
		FileLoader file = new FileLoader(new FileCommand(frame));

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = chooser.showSaveDialog(frame);

		if (dialog == JFileChooser.APPROVE_OPTION) {
			String filepath = chooser.getSelectedFile().getPath();
			file.save(filepath);
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void loadCommands() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = chooser.showOpenDialog(frame);
		File file = chooser.getSelectedFile();
		if (file == null) {
	        JOptionPane.showMessageDialog(null, "No file selected", "Message", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }
		if (dialog == JFileChooser.APPROVE_OPTION) {
			clearAll();
			frame.getTextArea().setText(" ");

			try {
				Scanner scanner = new Scanner(file);
				frame.getTextArea().append("Click next to load commands. \n");
				frame.getBtnNext().setEnabled(true);

				frame.getBtnNext().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Scanner scan = scanner;
						try {
							if (scan.hasNextLine()) {
								String line = scan.nextLine();
								readNextLine(line);
							} else {
								frame.getBtnNext().setEnabled(false);
								JOptionPane.showMessageDialog(null, "There is no more shapes to load.", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								scanner.close();
							}
						} catch (Exception exc) {

							exc.printStackTrace();
						}
					}

				});
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "something went wrong!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.getView().repaint();
	}
	public void readNextLine(String nextLine) {
	    String[] moveMade = nextLine.split(" ");

	    if (moveMade.length < 1) {
	        return; 
	    }

	    String commandKeyword = moveMade[0];

	    switch (commandKeyword) {
	        case "Added->":
	            handleAddCommand(nextLine);
	            break;
	        case "Deleted->":
	            handleDeleteCommand();
	            break;
	        case "Modified->":
	            handleModifiedCommand(nextLine);
	            break;
	        case "Selected->":
	            handleSelectedCommand(nextLine);
	            break;
	        case "Deselected->":
	            handleDeselectedCommand(nextLine);
	            break;
	        case "MOVEUNDO":
	            undo();
	            break;
	        case "MOVEREDO":
	            redo();
	            break;
	        case "Send_to_back:":
	            toBack();
	            break;
	        case "Bring_to_front:":
	            toFront();
	            break;
	        case "Send_front:":
	            front();
	            break;
	        case "Send_back:":
	            back();
	            break;
	        default:
	            break;
	    }
	}
	private void handleDeselectedCommand(String nextLine) {
	    Shape shape = returnShape(nextLine);

	    model.getShapes().stream()
	        .filter(s -> s.getClass() == shape.getClass()) // Filter shapes of the same type
	        .filter(s -> {
	            if (s instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
	                HexagonAdapter hexagon1 = (HexagonAdapter) s;
	                HexagonAdapter hexagon = (HexagonAdapter) shape;
	                return hexagon1.getNewHexagon().equals(hexagon.getNewHexagon());
	            }
	            return s.equals(shape);
	        })
	        .findFirst()
	        .ifPresent(selectedShape -> {
	            selectedShape.setSelected(false);
	            model.removeSelected(selectedShape);
	            frame.getTextArea().append("Deselected-> " + selectedShape.toString() + "\n");
	            clearRedo();
	            frame.getView().repaint();
	        });
	}
	private void handleSelectedCommand(String nextLine) {
	    Shape shape = returnShape(nextLine);

	    model.getShapes().stream()
	        .filter(s -> s.getClass() == shape.getClass()) // Filter shapes of the same type
	        .filter(s -> {
	            if (s instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
	                HexagonAdapter hexagon1 = (HexagonAdapter) s;
	                HexagonAdapter hexagon = (HexagonAdapter) shape;
	                return hexagon1.getNewHexagon().equals(hexagon.getNewHexagon());
	            }
	            return s.equals(shape);
	        })
	        .findFirst()
	        .ifPresent(selectedShape -> {
	            selectedShape.setSelected(true);
	            model.addSelected(selectedShape);
	            frame.getTextArea().append("Selected-> " + selectedShape.toString() + "\n");
	            clearRedo();
	            frame.getView().repaint();
	        });
	}
private void handleModifiedCommand(String nextLine) {
	String[] moveMade = nextLine.split(" ");
	Shape shape = returnShape(nextLine);
	Shape old = model.getSelectedShapes().get(0);

	if (moveMade[1].equals("Point:")) {
		frame.getTextArea().append("Modified-> " + shape.toString() + "\n");
		Command command = new UpdatePointCommand((Point) old, (Point) shape);
		command.execute();
		activities.push(command);
	}
	if (moveMade[1].equals("Line:")) {
		frame.getTextArea().append("Modified-> " + shape.toString() + "\n");
		Command command = new UpdateLineCommand((Line) old, (Line) shape);
		command.execute();
		activities.push(command);
	}
	if (moveMade[1].equals("Rectangle:")) {
		frame.getTextArea().append("Modified-> " + shape.toString() + "\n");
		Command command = new UpdateRectangleCommand((Rectangle) old, (Rectangle) shape);
		command.execute();
		activities.push(command);
	}
	if (moveMade[1].equals("Hexagon:")) {
		frame.getTextArea().append("Modified-> " + shape.toString() + "\n");
		Command command = new UpdateHexagonCommand((HexagonAdapter) old, (HexagonAdapter) shape);
		command.execute();
		activities.push(command);
	}
	if (moveMade[1].equals("Circle:")) {
		frame.getTextArea().append("Modified-> " + shape.toString() + "\n");
		Command command = new UpdateCircleCommand((Circle) old, (Circle) shape);
		command.execute();
		activities.push(command);
	}
	if (moveMade[1].equals("Donut:")) {
		frame.getTextArea().append("Modified-> " + shape.toString() + "\n");
		Command command = new UpdateDountCommand((Donut) old, (Donut) shape);
		command.execute();
		activities.push(command);
	}
	clearRedo();
	old.setSelected(true);
	frame.getView().repaint();
	}

private void handleDeleteCommand() {
	  frame.getTextArea().append("Deleted-> " + model.getSelectedShapes().toString() + "\n");
	    Command command = new RemoveShapeCommand(model, model.getSelectedShapes());
	    command.execute();
	    activities.push(command);

	    setSelectedShape(null);
	    clearRedo();
	    frame.getView().repaint();
	    frame.getTglbtnSelect().setSelected(false);
	}

private void handleAddCommand(String nextLine) {
	Shape shape = returnShape(nextLine);

	if (shape != null) {
	frame.getTextArea().append("Added->" + shape.toString() + "\n");
	Command command = new AddShapeCommand((Shape) shape, model);
	command.execute();
	activities.push(command);
	}
	if (!getActivities().isEmpty()) {
		frame.getBtnUndo().setEnabled(true);
	}
	clearRedo();
	frame.getView().repaint();
}

	private Shape returnShape(String nextLine) {
		Shape shape = null;
		String[] moveMade = nextLine.split(" ");

		if (moveMade[1].equals("Point:")) {
			Point point = new Point(Integer.parseInt(moveMade[2]), Integer.parseInt(moveMade[3]), false,
					Color.decode(moveMade[5]));
			shape = point;
		} else if (moveMade[1].equals("Line:")) {
			Line line = new Line(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					new Point(Integer.parseInt(moveMade[6]), Integer.parseInt(moveMade[7])), false,
					Color.decode(moveMade[9]));
			shape = line;
		} else if (moveMade[1].equals("Rectangle:")) {
			Rectangle rectangle = new Rectangle(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					Integer.parseInt(moveMade[6]), Integer.parseInt(moveMade[8]), false, Color.decode(moveMade[12]),
					Color.decode(moveMade[10]));
			shape = rectangle;
		} else if (moveMade[1].equals("Hexagon:")) {
			HexagonAdapter hexagon = new HexagonAdapter(
					new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					Integer.parseInt(moveMade[6]), false, Color.decode(moveMade[8]), Color.decode(moveMade[10]));
			shape = hexagon;
		} else if (moveMade[1].equals("Donut:")) {
			Donut donut = new Donut(new Point(Integer.parseInt(moveMade[4]), Integer.parseInt(moveMade[5])),
					Integer.parseInt(moveMade[7]), Integer.parseInt(moveMade[13]), false, Color.decode(moveMade[9]),
					Color.decode(moveMade[11]));
			shape = donut;
		} else if (moveMade[1].equals("Circle:")) {
			Circle circle = new Circle(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					Integer.parseInt(moveMade[6]), false, Color.decode(moveMade[8]), Color.decode(moveMade[10]));
			shape = circle;
		}

		return shape;
	}

	public void saveDrawing() {
		FileLoader file = new FileLoader(new FileLog(model));

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = chooser.showSaveDialog(frame);

		if (dialog == JFileChooser.APPROVE_OPTION) {
			String filepath = chooser.getSelectedFile().getPath();
			file.save(filepath);
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void loadDrawing() {
	    JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	    int dialog = chooser.showOpenDialog(frame);
	    File file = chooser.getSelectedFile();

	    if (dialog == JFileChooser.APPROVE_OPTION) {
	        clearAll();
	        frame.getTextArea().setText(" ");
	        
	        try {
	            ArrayList<Shape> list = loadShapesFromFile(file);
	            model.getShapes().addAll(list);
	            frame.getBtnUndo().setEnabled(false);
	            frame.getView().repaint();
	        } catch (IOException e) {
	            handleLoadError("There was an error while trying to load drawing. Please try again!");
	        } catch (ClassNotFoundException e) {
	            handleLoadError("File not found!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

	private ArrayList<Shape> loadShapesFromFile(File file) throws IOException, ClassNotFoundException {
	    FileInputStream stream = new FileInputStream(file);
	    ArrayList<Shape> list = new ArrayList<Shape>();
	    
	    try (ObjectInputStream inputStream = new ObjectInputStream(stream)) {
	        list = (ArrayList<Shape>) inputStream.readObject();
	    } 
	    
	    return list;
	}

	private void handleLoadError(String errorMessage) {
	    JOptionPane.showMessageDialog(null, errorMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public void clearAll() {

		model.getSelectedShapes().clear();
		model.getShapes().clear();
		activities.clear();
		undoActivities.clear();
		frame.getTextArea().setText(" ");

	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	public ShapeObserver getObserver() {
		return observer;
	}

	public void setObserver(ShapeObserver observer) {
		this.observer = observer;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Stack<Command> getActivities() {
		return activities;
	}

	public void setActivities(Stack<Command> activities) {
		this.activities = activities;
	}

	public Stack<Command> getUndoActivities() {
		return undoActivities;
	}

	public void setUndoActivities(Stack<Command> undoActivities) {
		this.undoActivities = undoActivities;
	}

}
