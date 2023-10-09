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

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import commands.AddCircleCommand;
import commands.AddDonutCommand;
import commands.AddHexagonCommand;
import commands.AddLineCommand;
import commands.AddPointCommand;
import commands.AddRectangleCommand;
import commands.Command;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
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
	private Color innerColor;
	private Color outerColor;
	private ButtonsObserver observer;
	private Shape selectedShape;
	private Point startPoint;
	private Stack<Command> actions = new Stack<Command>();
	private Stack<Command> undoactions = new Stack<Command>();


	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		observer = new ButtonsObserver(frame);
		model.addNewPCL(observer);
	}

	private void showError(String message) {
	    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showMessage(String message) {
	    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	public void modify() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}

	public void SendFront() {
		// TODO Auto-generated method stub
		
	}

	public void SendBack() {
		// TODO Auto-generated method stub
		
	}

	public void BringToFront() {
		// TODO Auto-generated method stub
		
	}

	public void BringToBack() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	public void AreaColor() {
		// TODO Auto-generated method stub
		
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
		        Rectangle newShape = dialog.getRect();
		        frame.getTextArea().append("Added-> "+newShape.toString()+"\n");
		        Command newRectangleCmd = new AddRectangleCommand(newShape, model);
		        newRectangleCmd.execute();
		        actions.push(newRectangleCmd);
		        frame.getBtnUndo().setEnabled(true);
				clearRedo();
				frame.getView().repaint();
		    }else {
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
		
		if(dialog.isOK()) {
			try {
				HexagonAdapter newHexagon = dialog.getHexagon();
				frame.getTextArea().append("Added-> "+newHexagon.toString()+ "\n");
				Command newHexagonCmd = new AddHexagonCommand(newHexagon, model);
				newHexagonCmd.execute();
				actions.push(newHexagonCmd);
				
				frame.getBtnUndo().setEnabled(true);
				clearRedo();
				frame.getView().repaint();
			}catch (Exception ex) {
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
		
		if(dialog.isOK()) {
			Donut newShape = dialog.getDonut();
			frame.getTextArea().append("Added-> "+newShape.toString()+ "\n");
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
		
		if(dialog.isOK()) {
			Circle newCircle = dialog.getCircle();
			frame.getTextArea().append("Added-> " +newCircle.toString() + "\n");
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
	    if (innerColor != null) {
	        dialog.setInnerColor(innerColor);
	    }
	    if (outerColor != null) {
	        dialog.setOuterColor(outerColor);
	    }
	}
}
