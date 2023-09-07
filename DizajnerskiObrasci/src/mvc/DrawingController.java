package mvc;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;


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

		Shape newShape = null;
		Point click = new Point(me.getX(), me.getY());

		if (frame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			Iterator<Shape> iterator = model.getShapes().iterator();

			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				if (shape.contains(click.getX(), click.getY()))
					selectedShape = shape;

			}

			if (selectedShape != null) {

				if (!selectedShape.isSelected()) {

					selectedShape.setSelected(true);
					model.addSelected(selectedShape);
					frame.getTextArea().append("Selected: " + selectedShape.toString() + "\n");

				} else {

					selectedShape.setSelected(false);
					model.removeSelected(selectedShape);
					frame.getTextArea().append("Deselected: " + selectedShape.toString() + "\n");

				}
			}

		} else if (frame.getTglbtnPoint().isSelected()) {

			DialogPoint dialog = new DialogPoint();
			dialog.getTxtX().setText(""+Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);
			if (innerColor != null)
				dialog.getBtnColor().setBackground(innerColor);
			dialog.setVisible(true);

			if (dialog.isOK()) {
				newShape = dialog.getP();
			}

		} else if (frame.getTglbtnLine().isSelected()) {

			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(me.getX(), me.getY(), false, Color.black));
				startPoint = null;
			}

		} else if (frame.getTglbtnCircle().isSelected()) {

			DialogCircle dialog = new DialogCircle();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);

			dialog.setVisible(true);

			if (dialog.isOK()) {
				newShape = dialog.getCircle();
			}

		} else if (frame.getTglbtnDonut().isSelected()) {

			DialogDonut dialog = new DialogDonut();
			dialog.setModal(true);
			dialog.getTxtDX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtDX().setEditable(false);
			dialog.getTxtDY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtDY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOuterColor().setBackground(outerColor);

			dialog.setVisible(true);

			if (dialog.isOK()) {

				newShape = dialog.getDonut();
			}

		} else if (frame.getTglbtnHexagon().isSelected()) {

			DialogHexagon dialog = new DialogHexagon();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);

			dialog.setVisible(true);

			if (dialog.isOK()) {
				try {
					newShape = dialog.getHexagon();
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else if (frame.getTglbtnRectangle().isSelected()) {

			DialogRectangle dialog = new DialogRectangle();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(me.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(me.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outerColor != null)
				dialog.getBtnOutlineColor().setBackground(outerColor);

			dialog.setVisible(true);

			if (dialog.isOK()) {
				// return;

				try {
					newShape = dialog.getRect();
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (newShape != null) {
			if(newShape.getColor() != null && newShape.getInnerColor()!=null)
			{
				frame.getTextArea().append("Added: " + (newShape.toString() + "Border Color RGB:"+ newShape.getColor().getRed() +","+newShape.getColor().getGreen() +","+newShape.getColor().getBlue()+"."+ "Color RGB:"+ newShape.getInnerColor().getRed() +","+newShape.getInnerColor().getGreen() +","+newShape.getInnerColor().getBlue()+"." + "\n"));
			}
			else {
				frame.getTextArea().append("Added: " + (newShape.toString() + "Colors:"+ newShape.getColor().getRGB()  + newShape.getInnerColor().getRGB() + "\n"));
			}
			Command cmd = new AddShapeCommand((Shape) newShape, model);
			cmd.execute();
			activities.push(cmd);
		}

		if (!getActivities().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}

		clearRedo();
		frame.getView().repaint();

	}
	private void clearRedo() {
		getUndoActivities().clear();
		frame.getBtnRedo().setEnabled(false);
	}

	public void modify() {
		if(model.getSelectedShapes().get(0)==null) {
			JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getTglbtnSelect().setSelected(true);
		}
		Shape selectedShape = model.getSelectedShapes().get(0);
		Command command;
		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				Point p = (Point) selectedShape;
				DialogPoint dglpoint = new DialogPoint();

				dglpoint.getTxtX().setText("" + Integer.toString(p.getX()));
				dglpoint.getTxtY().setText("" + Integer.toString(p.getY()));
				dglpoint.getBtnColor().setBackground(p.getColor());
				dglpoint.setModal(true);
				dglpoint.setVisible(true);

				if (dglpoint.isOK()) {
					frame.getTextArea().append("Modified point: " + ((Point) dglpoint.getP()).toString() + "\n");
					command = new UpdatePointCommand((Point) selectedShape, dglpoint.getP());
					command.execute();
					activities.push(command);
				}

			} else if (selectedShape instanceof Donut) {

				Donut donut = (Donut) selectedShape;
				DialogDonut dgldonut = new DialogDonut();

				dgldonut.getTxtDX().setText("" + Integer.toString(donut.getcenter().getX()));
				dgldonut.getTxtDY().setText("" + Integer.toString(donut.getcenter().getY()));
				dgldonut.getTxtDR().setText("" + Integer.toString(donut.getradius()));
				dgldonut.getTxtDIR().setText("" + Integer.toString(donut.getInnerRadius()));
				dgldonut.getBtnInnerColor().setBackground(donut.getInnerColor());
				dgldonut.getBtnOuterColor().setBackground(donut.getColor());
				dgldonut.setModal(true);
				dgldonut.setVisible(true);

				if (dgldonut.isOK()) {
					frame.getTextArea().append("Modified donut: " + ((Donut) dgldonut.getDonut()).toString() + "\n");
					command = new UpdateDountCommand((Donut) selectedShape, dgldonut.getDonut());
					command.execute();
					activities.push(command);
				}
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {

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
					frame.getTextArea().append("Modified circle: " + ((Circle) dglcircle.getCircle()).toString() + "\n");
					command = new UpdateCircleCommand((Circle) selectedShape, dglcircle.getCircle());
					command.execute();
					activities.push(command);
				}

			} else if (selectedShape instanceof Line) {

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
					frame.getTextArea().append("Modified line: " + ((Line) dlgline.getLine()).toString() + "\n");
					command = new UpdateLineCommand((Line) selectedShape, dlgline.getLine());
					command.execute();
					activities.push(command);
				}

			} else if (selectedShape instanceof Rectangle) {

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
					frame.getTextArea().append("Modified rect: " + ((Rectangle) dlgrectangle.getRect()).toString() + "\n");
					command = new UpdateRectangleCommand((Rectangle) selectedShape, dlgrectangle.getRect());
					command.execute();
					activities.push(command);
				}
			} else if(selectedShape instanceof HexagonAdapter) {
				HexagonAdapter hex = (HexagonAdapter) selectedShape;
				DialogHexagon dlghexagon = new DialogHexagon();
				
				dlghexagon.getTxtX().setText("" + Integer.toString(hex.getNewHexagon().getX()));
				dlghexagon.getTxtY().setText("" + Integer.toString(hex.getNewHexagon().getY()));
				dlghexagon.getTxtR().setText("" + Integer.toString(hex.getNewHexagon().getR()));
				dlghexagon.getBtnInnerColor().setBackground(hex.getNewHexagon().getAreaColor());
				dlghexagon.getBtnOutlineColor().setBackground(hex.getNewHexagon().getBorderColor());
				dlghexagon.setModal(true);
				dlghexagon.setVisible(true);
				
				if(dlghexagon.isOK()) {
					frame.getTextArea().append("Modified hexagon: "+((HexagonAdapter) dlghexagon.getHexagon()).toString() + "\n");
					command = new UpdateHexagonCommand((HexagonAdapter) selectedShape, dlghexagon.getHexagon());
					command.execute();
					activities.push(command);
				}
			}

		}
		clearRedo();
		model.getSelectedShapes().get(0).setSelected(true);

		frame.getView().repaint();
	}

	public void delete() {

		if (model.getSelectedShapes() != null) 
		{
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) 
			{
				frame.getTextArea().append("Deleted: " + model.getSelectedShapes().toString() + "\n");
				Command command = new RemoveShapeCommand(model, model.getSelectedShapes());
				command.execute();
				activities.push(command);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error", JOptionPane.WARNING_MESSAGE);
		}
		this.setSelectedShape(null);
		clearRedo();
		frame.getView().repaint();
		frame.getTglbtnSelect().setSelected(false);
	}


	public void undo() {
		if(!activities.isEmpty()) {
			Command command = activities.pop();
			command.unexecute();
			undoActivities.push(command);
			if(!undoActivities.isEmpty()) {
				frame.getBtnRedo().setEnabled(true);
			}
			
			if(activities.isEmpty()) {
				frame.getBtnUndo().setEnabled(false);
			}
			frame.getTextArea().append("MOVE UNDO \n");
		}
		frame.getView().repaint();
	}

	public void redo() {
		if(!undoActivities.isEmpty()) {
			Command command = undoActivities.pop();
			command.execute();
			activities.push(command);
			frame.getBtnUndo().setEnabled(true);
			
			if(undoActivities.isEmpty()) {
				frame.getBtnRedo().setEnabled(false);
			}
			frame.getTextArea().append("MOVE REDO \n");
		}
		frame.getView().repaint();

	}

	public void toBack() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new BringToBackCommand(model, shape);
		command.execute();
		activities.add(command);
		clearRedo();
		frame.getTextArea().append("Send to back: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void toFront() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new BringToFrontCommand(model, shape);
		command.execute();
		activities.add(command);
		clearRedo();
		frame.getTextArea().append("Bring to front: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void front() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new FrontShapeCommand(shape, model);
		command.execute();
		activities.add(command);
		clearRedo();
		frame.getTextArea().append("Send front: " + shape.toString() + "\n");
		frame.getView().repaint();
	}
	public void back() {
		Shape shape = model.getSelectedShapes().get(0);
		Command command = new BackShapeCommand(shape, model);
		command.execute();
		activities.add(command);
		clearRedo();
		frame.getTextArea().append("Send back: " + shape.toString() + "\n");
		frame.getView().repaint();
	}

	public void addinnercolor() {
		innerColor = JColorChooser.showDialog(null, "Choose inner color", frame.getTglbtnInnerColor().getBackground());
		if(innerColor != null) {
			frame.getTglbtnInnerColor().setBackground(innerColor);
		}
	}

	public void addOuterColor() {
		outerColor = JColorChooser.showDialog(null, "Choose outer color", frame.getTglbtnNOuterColor().getBackground());
		if(outerColor !=null) {
			frame.getTglbtnNOuterColor().setBackground(outerColor);
		}
	}
	public void saveCommands() {
		// TODO Auto-generated method stub
		
	}

	public void loadCommands() {
		// TODO Auto-generated method stub
		
	}

	public void saveDrawing() {
		
	}

	public void loadDrawing() {
		// TODO Auto-generated method stub
		
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
