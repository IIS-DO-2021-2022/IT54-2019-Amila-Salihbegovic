package mvc;

import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import commands.AddCircleCommand;
import commands.AddDonutCommand;
import commands.AddHexagonCommand;
import commands.AddLineCommand;
import commands.AddPointCommand;
import commands.AddRectangleCommand;
import commands.BringToBackCommand;
import commands.BringToFrontCommand;
import commands.Command;
import commands.RemoveShapeCommand;
import commands.SendBackCommand;
import commands.SendFrontCommand;
import commands.UpdateCircleCommand;
import commands.UpdateDonutCommand;
import commands.UpdateHexagonCommand;
import commands.UpdateLineCommand;
import commands.UpdatePointCommand;
import commands.UpdateRectangleCommand;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import dialogs.DialogShape;
import geometricShapes.Circle;
import geometricShapes.Donut;
import geometricShapes.Line;
import geometricShapes.Point;
import geometricShapes.Rectangle;
import geometricShapes.Shape;
import observer.ButtonsObserver;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private ButtonsObserver observer;
	private Shape selectedShape;
	private Point startPoint;
	private Stack<Command> actions = new Stack<Command>();
	private Stack<Command> undoactions = new Stack<Command>();
	private Color borderColor = Color.BLACK;
	private Color fillColor = Color.BLACK;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		observer = new ButtonsObserver(frame);
		model.addNewPCL(observer);
		this.borderColor = frame.getTglbtnNOuterColor().getBackground();
		this.fillColor = frame.getTglbtnInnerColor().getBackground();
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
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
				showError("All fields are required!");
			}
			try {
				if (Integer.parseInt(dglpoint.getTxtX().getText().toString()) < 0
						|| Integer.parseInt(dglpoint.getTxtY().getText().toString()) < 0) {
					showError("Entered values must be greater than 0!");
				} else {
					frame.getTextArea().append("Modified-> " + ((Point) dglpoint.getP()).toString() + "\n");
					command = new UpdatePointCommand((Point) selectedShape, dglpoint.getP());
					command.execute();
					actions.push(command);
					selectedShape.setSelected(true);
				}
			} catch (Exception ex) {
				showError("Please enter valid data");
			}
		} else {
			showMessage("Operation cancelled!");
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

				showError("All fields are required!");
			} else {
				try {
					if (Integer.parseInt(dlghexagon.getTxtR().getText().toString()) <= 0
							|| Integer.parseInt(dlghexagon.getTxtX().getText().toString()) < 0
							|| Integer.parseInt(dlghexagon.getTxtY().getText().toString()) < 0) {
						showError("Entered values must be greater than 0!");
					} else {
						frame.getTextArea()
								.append("Modified-> " + ((HexagonAdapter) dlghexagon.getHexagon()).toString() + "\n");
						command = new UpdateHexagonCommand((HexagonAdapter) selectedShape, dlghexagon.getHexagon());
						command.execute();
						actions.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					showError("Please enter valid data");
				}
			}

		} else {
			showMessage("Operation cancelled!");
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
				showError("All fields are required!");
			} else {
				try {
					if (Integer.parseInt(dlgrectangle.getTxtX().getText().toString()) < 0
							|| Integer.parseInt(dlgrectangle.getTxtY().getText().toString()) < 0
							|| Integer.parseInt(dlgrectangle.getTxtHeight().getText().toString()) < 0
							|| Integer.parseInt(dlgrectangle.getTxtWidth().getText().toString()) < 0) {
						showError("Entered values must be greater than 0!");
					} else {
						frame.getTextArea()
								.append("Modified-> " + ((Rectangle) dlgrectangle.getRect()).toString() + "\n");
						command = new UpdateRectangleCommand((Rectangle) selectedShape, dlgrectangle.getRect());
						command.execute();
						actions.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					showError("Please enter valid data");
				}
			}

		} else {
			showMessage("Operation cancelled!");
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

				showError("All fields are required!");
			} else {
				try {
					if (Integer.parseInt(dlgline.getTxtXStart().getText().toString()) < 0
							|| Integer.parseInt(dlgline.gettxtYStart().getText().toString()) < 0
							|| Integer.parseInt(dlgline.getTxtXEnd().getText().toString()) < 0
							|| Integer.parseInt(dlgline.getTxtYEnd().getText().toString()) < 0) {
						showError("Entered values must be greater than 0!");

					} else {
						frame.getTextArea().append("Modified-> " + ((Line) dlgline.getLine()).toString() + "\n");
						command = new UpdateLineCommand((Line) selectedShape, dlgline.getLine());
						command.execute();
						actions.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					showError("Please enter valid data");
				}
			}

		} else {
			showMessage("Operation cancelled!");
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

				showError("All fields are required!");
			} else {
				try {
					if (Integer.parseInt(dglcircle.getTxtRadius().getText().toString()) <= 0
							|| Integer.parseInt(dglcircle.getTxtX().getText().toString()) < 0
							|| Integer.parseInt(dglcircle.getTxtY().getText().toString()) < 0) {
						showError("Entered values must be greater than 0!");
					} else {
						frame.getTextArea().append("Modified-> " + ((Circle) dglcircle.getCircle()).toString() + "\n");
						command = new UpdateCircleCommand((Circle) selectedShape, dglcircle.getCircle());
						command.execute();
						actions.push(command);
						selectedShape.setSelected(true);
					}
				} catch (Exception ex) {
					showError("Please enter valid data");
				}
			}

		} else {
			showMessage("Operation cancelled!");
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
					showError("All fields are required!");
				} else {
					try {
						if (Integer.parseInt(dgldonut.getTxtX().getText().toString()) < 0
								|| Integer.parseInt(dgldonut.getTxtY().getText().toString()) < 0
								|| Integer.parseInt(dgldonut.getTxtR().getText().toString()) < 0
								|| Integer.parseInt(dgldonut.getTxtDIR().getText().toString()) < 0) {
							showError("Entered values must be greater than 0!");
						} else {
							frame.getTextArea().append("Modified-> " + ((Donut) dgldonut.getDonut()).toString() + "\n");
							command = new UpdateDonutCommand((Donut) selectedShape, dgldonut.getDonut());
							command.execute();
							selectedShape.setSelected(true);
							actions.push(command);
						}
					} catch (Exception ex) {
						showError("Please enter valid data");
					}
				}
			} else {
				showMessage("Operation cancelled!");
			}

		}
	}

	public void delete() {
		ArrayList<Shape> selectedShapes = model.getSelectedShapes();

		if (selectedShapes == null || selectedShapes.isEmpty()) {
			showError("You haven't selected any shape!");
			return;
		}

		int selectedOption = showDeleteConfirmationDialog();

		if (selectedOption == JOptionPane.YES_OPTION) {
			frame.getTextArea().append("Deleted-> " + selectedShapes.toString() + "\n");
			Command command = new RemoveShapeCommand(model, selectedShapes);
			command.execute();
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(false);
			actions.push(command);
		} else {
			showMessage("Operation cancelled!");
		}

		clearRedo();
		frame.getView().repaint();
		frame.getTglbtnSelect().setSelected(false);
	}

	private int showDeleteConfirmationDialog() {
		return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message",
				JOptionPane.YES_NO_OPTION);
	}

	public void redo() {
		if (actions.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		}
		if (!undoactions.isEmpty()) {

			Command redo = undoactions.pop();
			redo.execute();
			actions.push(redo);
			frame.getTextArea().append("Redo->" + actions.peek().toString()+ "\n");
			frame.getBtnUndo().setEnabled(true);

			frame.getBtnRedo().setEnabled(!undoactions.isEmpty());
			frame.getView().repaint();
			System.out.print("Redo stack:" + undoactions);
			if (undoactions.isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
				JOptionPane.showMessageDialog(null, "There is nothing left to redo");

			}
		}
		model.getSelectedShapes().get(0).setSelected(true);
	}

	public void undo() {
		if (undoactions.isEmpty()) {
			frame.getBtnUndo().setEnabled(false);
		}
		if (!actions.isEmpty()) {
			Command undo = actions.pop();
			undo.unexecute();
			undoactions.push(undo);
			frame.getTextArea().append("Undo->" + undoactions.peek().toString() + "\n");
			frame.getBtnRedo().setEnabled(!undoactions.isEmpty());
			frame.getBtnUndo().setEnabled(!actions.isEmpty());
			frame.getView().repaint();

			if (actions.isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
				showMessage("There is nothing to undo!");
			}
		}
		model.getSelectedShapes().get(0).setSelected(true);
	}

	public void SendFront() {
		if(model.getSelectedShapes().size() == 1) {
			
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			
			if(index==model.getShapes().size()-1) {
				
				JOptionPane.showMessageDialog(null, "Element is alrady in front!"); 
				
			} else {
				
				SendFrontCommand toFront = new SendFrontCommand(model,index, shape);
				actions.push(toFront);
				frame.getTextArea().append("To front->" + shape.toString());
				toFront.execute();	
				clearRedo();
			}			
		}
		
		frame.repaint();
		
	}

	public void SendBack() {
		if(model.getSelectedShapes().size() == 1) {
			
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			
			if(index==0) {
				
				JOptionPane.showMessageDialog(null, "Element is alrady in back!"); 		
				
			}else {
				
				SendBackCommand toBack = new SendBackCommand(model,index, shape);
				actions.push(toBack);
				frame.getTextArea().append("To back->" + shape.toString());
				toBack.execute();
				clearRedo();
								
			}
			
		}
		frame.repaint();
		
	}

	public void BringToFront() {
		if(model.getSelectedShapes().size() == 1) {
			
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			
			if(index==model.getShapes().size()-1) {
				
				JOptionPane.showMessageDialog(null, "Element is alrady in front!"); 			
				
			}else {
				
				BringToFrontCommand BringToFront = new BringToFrontCommand(model,shape);
				actions.push(BringToFront);
				frame.getTextArea().append("Bring_to_front->" + shape.toString());
				BringToFront.execute();
				clearRedo();
								
			}
			
		}
		frame.repaint();
				
	}

	public void BringToBack() {
		if(model.getSelectedShapes().size() == 1) {
			
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			Shape shape = model.getShapes().get(index);
			
			if(index==0) {
				
				JOptionPane.showMessageDialog(null, "Element is alrady in back!");				
				
			}else {
				
				BringToBackCommand BringToBack = new BringToBackCommand(model,shape, index);
				actions.push(BringToBack);
				frame.getTextArea().append("Bring_to_back->" + shape.toString());
				BringToBack.execute();
				clearRedo();
								
			}
			
		}
		frame.repaint();
				
	}

	public void saveCommands() {
		// TODO Auto-generated method stub

	}

	public void loadCommands() {
		// TODO Auto-generated method stub

	}

	public void saveDrawing() {
		// TODO Auto-generated method stub

	}

	public void loadDrawing() {
		// TODO Auto-generated method stub

	}

	public void BorderColor() {
		Color choseColor = JColorChooser.showDialog(null, "Chose inside color", Color.BLACK);
		frame.getTglbtnNOuterColor().setBackground(choseColor);
		borderColor = frame.getTglbtnNOuterColor().getBackground();
	}

	public void AreaColor() {
		Color choseColor = JColorChooser.showDialog(null, "Chose inside color", Color.BLACK);
		frame.getTglbtnInnerColor().setBackground(choseColor);
		fillColor = frame.getTglbtnInnerColor().getBackground();
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

	private void createRectangleShape(MouseEvent me) {
		DialogRectangle dialog = createRectangleDialog(me.getX(), me.getY());
		setColors(dialog);

		dialog.setVisible(true);

		if (dialog.isOK()) {
			if (dialog.getTxtHeight().getText().trim().isEmpty()) {
				showError("All fields are required!");
			}
			if (Integer.parseInt(dialog.getTxtWidth().getText().toString()) <= 0
					|| Integer.parseInt(dialog.getTxtHeight().getText().toString()) <= 0) {
				showError("Insert values greater than 0!");
			}
			Rectangle newShape = dialog.getRect();
			frame.getTextArea().append("Added-> " + newShape.toString() + "\n");
			Command newRectangleCmd = new AddRectangleCommand(newShape, model);
			newRectangleCmd.execute();
			actions.push(newRectangleCmd);
			frame.getBtnUndo().setEnabled(true);
			clearRedo();
			frame.getView().repaint();
		} else {
			showMessage("Operation cancelled!");
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

	private void createHexagonShape(MouseEvent me) {
		DialogHexagon dialog = createHexagonDialog(me.getX(), me.getY());
		setColors(dialog);
		dialog.setVisible(true);

		if (dialog.isOK()) {
			try {
				HexagonAdapter newHexagon = dialog.getHexagon();
				frame.getTextArea().append("Added-> " + newHexagon.toString() + "\n");
				Command newHexagonCmd = new AddHexagonCommand(newHexagon, model);
				newHexagonCmd.execute();
				actions.push(newHexagonCmd);

				frame.getBtnUndo().setEnabled(true);
				clearRedo();
				frame.getView().repaint();
			} catch (Exception ex) {
				showError("Wrong data type!");
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

	private void createDonutShape(MouseEvent me) {
		DialogDonut dialog = createDonutDialog(me.getX(), me.getY());
		setColors(dialog);
		dialog.setVisible(true);

		if (dialog.isOK()) {
			if (dialog.getTxtR().getText().trim().isEmpty() || dialog.getTxtDIR().getText().trim().isEmpty()) {
				showError("All fields are required!");
			}
			if (Integer.parseInt(dialog.getTxtDIR().getText().toString()) <= 0
					|| Integer.parseInt(dialog.getTxtR().getText().toString()) <= 0) {
				showError("Insert values greater than 0!");
			}
			Donut newShape = dialog.getDonut();
			frame.getTextArea().append("Added-> " + newShape.toString() + "\n");
			Command newDonutCmd = new AddDonutCommand(newShape, model);
			newDonutCmd.execute();
			actions.push(newDonutCmd);

			frame.getBtnUndo().setEnabled(true);
			clearRedo();
			frame.getView().repaint();
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

	private void createCircleShape(MouseEvent me) {
		DialogCircle dialog = createCircleDialog(me.getX(), me.getY());
		setColors(dialog);
		dialog.setVisible(true);

		if (dialog.isOK()) {
			if (dialog.getTxtRadius().getText().trim().isEmpty()) {
				showError("All fields are required!");
			}
			if (Integer.parseInt(dialog.getTxtRadius().getText().toString()) <= 0) {
				showError("Insert values greater than 0!");
			}
			Circle newCircle = dialog.getCircle();
			frame.getTextArea().append("Added-> " + newCircle.toString() + "\n");
			Command newCircleCmd = new AddCircleCommand(newCircle, model);
			newCircleCmd.execute();
			actions.push(newCircleCmd);

			frame.getBtnUndo().setEnabled(true);
			clearRedo();
			frame.getView().repaint();
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

	private void createLineShape(MouseEvent me) {
		if (startPoint == null) {
			startPoint = new Point(me.getX(), me.getY());
		} else {
			Line newShape = new Line(startPoint, new Point(me.getX(), me.getY(), false, Color.BLACK));
			startPoint = null;
			frame.getTextArea().append("Added-> " + newShape.toString() + "\n");
			Command newLineCmd = new AddLineCommand(newShape, model);
			newLineCmd.execute();
			actions.push(newLineCmd);

			frame.getBtnUndo().setEnabled(true);
			clearRedo();
			frame.getView().repaint();
		}
	}

	private void createPointShape(MouseEvent me) {
		Point newPoint = new Point(me.getX(), me.getY(), false, Color.BLACK);
		frame.getTextArea().append("Added-> " + newPoint.toString() + "\n");
		Command newPointCmd = new AddPointCommand(newPoint, model);
		newPointCmd.execute();
		actions.push(newPointCmd);

		frame.getBtnUndo().setEnabled(true);
		clearRedo();
		frame.getView().repaint();
	}

	private void clearRedo() {
		getUndoactions().clear();
		frame.getBtnRedo().setEnabled(false);
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

	public Color getFillComor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public ButtonsObserver getObserver() {
		return observer;
	}

	public void setObserver(ButtonsObserver observer) {
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

	public Stack<Command> getActions() {
		return actions;
	}

	public void setActions(Stack<Command> actions) {
		this.actions = actions;
	}

	public Stack<Command> getUndoactions() {
		return undoactions;
	}

	public void setUndoactions(Stack<Command> undoactions) {
		this.undoactions = undoactions;
	}

	private void setColors(DialogShape dialog) {
		if (fillColor != null) {
			dialog.setInnerColor(fillColor);
		}
		if (borderColor != null) {
			dialog.setOuterColor(borderColor);
		}
	}
}
