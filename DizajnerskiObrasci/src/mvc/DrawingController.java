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
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JColorChooser;
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

//			DialogPoint dialog = new DialogPoint();
//			dialog.getTxtX().setText(""+Integer.toString(click.getX()));
//			dialog.getTxtX().setEditable(false);
//			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
//			dialog.getTxtY().setEditable(false);
//			if (innerColor != null)
//				dialog.getBtnColor().setBackground(innerColor);
//			dialog.setVisible(true);
//
//			if (dialog.isOK()) {
//				newShape = dialog.getP();
//			}
			
			newShape = new Point(click.getX(), click.getY(), false, Color.BLACK);
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
			frame.getTextArea().append("Added: " + (newShape.toString() + "\n"));
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
					frame.getTextArea().append("Modified: " + ((Point) dglpoint.getP()).toString() + "\n");
					command = new UpdatePointCommand((Point) selectedShape, dglpoint.getP());
					command.execute();
					activities.push(command);
					selectedShape.setSelected(true);
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
					frame.getTextArea().append("Modified: " + ((Donut) dgldonut.getDonut()).toString() + "\n");
					command = new UpdateDountCommand((Donut) selectedShape, dgldonut.getDonut());
					command.execute();
					selectedShape.setSelected(true);
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
					frame.getTextArea().append("Modified: " + ((Circle) dglcircle.getCircle()).toString() + "\n");
					command = new UpdateCircleCommand((Circle) selectedShape, dglcircle.getCircle());
					command.execute();
					activities.push(command);
					selectedShape.setSelected(true);
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
					frame.getTextArea().append("Modified: " + ((Line) dlgline.getLine()).toString() + "\n");
					command = new UpdateLineCommand((Line) selectedShape, dlgline.getLine());
					command.execute();
					activities.push(command);
					selectedShape.setSelected(true);
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
					frame.getTextArea().append("Modified: " + ((Rectangle) dlgrectangle.getRect()).toString() + "\n");
					command = new UpdateRectangleCommand((Rectangle) selectedShape, dlgrectangle.getRect());
					command.execute();
					activities.push(command);
					selectedShape.setSelected(true);
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
					frame.getTextArea().append("Modified: "+((HexagonAdapter) dlghexagon.getHexagon()).toString() + "\n");
					command = new UpdateHexagonCommand((HexagonAdapter) selectedShape, dlghexagon.getHexagon());
					command.execute();
					activities.push(command);
					selectedShape.setSelected(true);
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
			frame.getTextArea().append("MOVEUNDO \n");
		}
		frame.getView().repaint();
		//model.getSelectedShapes().get(0).setSelected(true);
		
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
			frame.getTextArea().append("MOVEREDO \n");
		}		
		model.getSelectedShapes().get(0).setSelected(true);
		frame.getView().repaint();
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
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			String filepath = chooser.getSelectedFile().getPath();
			file.save(filepath);
		}else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void loadCommands() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = chooser.showOpenDialog(frame);
		File file = chooser.getSelectedFile();
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
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
							if(scan.hasNextLine()) {
								String line = scan.nextLine();
								readNextLine(line);
							}else {
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
		}else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.getView().repaint();
	}
	public void readNextLine(String nextLine) {
		String[] moveMade = nextLine.split(" ");
		Command command;
		
		if(moveMade[0].equals("Added:")) {
			Shape shape = returnShape(nextLine);
			
			if(shape!=null) {
				frame.getTextArea().append("Added:" + shape.toString() + "\n");
				command = new AddShapeCommand((Shape) shape, model);
				command.execute();
				activities.push(command);
			}
			if(!getActivities().isEmpty()) {
				frame.getBtnUndo().setEnabled(true);
			}
			clearRedo();
			frame.getView().repaint();
		}else if(moveMade[0].equals("Deleted:")) {
			frame.getTextArea().append("Deleted: " + model.getSelectedShapes().toString() + "\n");
			command = new RemoveShapeCommand(model, model.getSelectedShapes());
			command.execute();
			activities.push(command);
			
			this.setSelectedShape(null);
			clearRedo();
			frame.getView().repaint();
			frame.getTglbtnSelect().setSelected(false);
		}else if(moveMade[0].equals("Modified:")) {
			Shape shape = returnShape(nextLine);
			Shape old = model.getSelectedShapes().get(0);
			
			if(moveMade[1].equals("Point:")) {
				frame.getTextArea().append("Modified: " + shape.toString() + "\n");
				command = new UpdatePointCommand((Point) old, (Point) shape);
				command.execute();
				activities.push(command);
			}
			if(moveMade[1].equals("Line:")) {
				frame.getTextArea().append("Modified: " + shape.toString() + "\n");
				command = new UpdateLineCommand((Line)old, (Line) shape);
				command.execute();
				activities.push(command);
			}
			if(moveMade[1].equals("Rectangle:")) {
				frame.getTextArea().append("Modified: " + shape.toString() + "\n");
				command = new UpdateRectangleCommand((Rectangle) old, (Rectangle)shape);
				command.execute();
				activities.push(command);
			}
			if(moveMade[1].equals("Hexagon:")) {
				frame.getTextArea().append("Modified: " + shape.toString() + "\n");
				command = new UpdateHexagonCommand((HexagonAdapter)old, (HexagonAdapter)shape);
				command.execute();
				activities.push(command);
			}
			if(moveMade[1].equals("Circle:")) {
				frame.getTextArea().append("Modified: " + shape.toString() + "\n");
				command = new UpdateCircleCommand((Circle)old, (Circle)shape);
				command.execute();
				activities.push(command);
			}
			if(moveMade[1].equals("Donut:")) {
				frame.getTextArea().append("Modified: " + shape.toString() + "\n");
				command = new UpdateDountCommand((Donut) old, (Donut)shape);
				command.execute();
				activities.push(command);
			}
			clearRedo();
			old.setSelected(true);
			frame.getView().repaint();
		}else if(moveMade[0].equals("Selected:")) {
			Shape shape = returnShape(nextLine);
			for(int i=0; i<model.getShapes().size(); i++) {
				if(model.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter hexagon1 = (HexagonAdapter) model.getShapes().get(i);
					HexagonAdapter hexagon = (HexagonAdapter) shape;
					
					if(hexagon1.getNewHexagon().getX() == hexagon.getNewHexagon().getX()
							&& hexagon1.getNewHexagon().getY()== hexagon.getNewHexagon().getY()
							&& hexagon1.getNewHexagon().getR()== hexagon.getNewHexagon().getR()
							&& hexagon1.getNewHexagon().getAreaColor().equals(hexagon.getNewHexagon().getAreaColor())
							&& hexagon1.getNewHexagon().getBorderColor().equals(hexagon.getNewHexagon().getBorderColor())) {
						shape = model.getShapes().get(i);
					}
				}
				else if((model.getShapes().get(i)).equals(shape)) {
					shape = model.getShapes().get(i);
				}
			}
			shape.setSelected(true);
			model.addSelected(shape);
			frame.getTextArea().append("Selected: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();
		}else if(moveMade[0].equals("Deselected:")) {
			Shape shape = returnShape(nextLine);
			for(int i=0; i<model.getShapes().size(); i++) {
				if(model.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter hexagon1 = (HexagonAdapter) model.getShapes().get(i);
					HexagonAdapter hexagon = (HexagonAdapter) shape;
					
					if(hexagon1.getNewHexagon().getX() == hexagon.getNewHexagon().getX()
							&& hexagon1.getNewHexagon().getY()== hexagon.getNewHexagon().getY()
							&& hexagon1.getNewHexagon().getR()== hexagon.getNewHexagon().getR()
							&& hexagon1.getNewHexagon().getAreaColor().equals(hexagon.getNewHexagon().getAreaColor())
							&& hexagon1.getNewHexagon().getBorderColor().equals(hexagon.getNewHexagon().getBorderColor())) {
						shape = model.getShapes().get(i);
					}
				}
				else if((model.getShapes().get(i)).equals(shape)) {
					shape = model.getShapes().get(i);
				}
			}
			shape.setSelected(false);
			model.removeSelected(shape);
			frame.getTextArea().append("Deselected: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();
		}
		else if(moveMade[0].equals("MOVEUNDO")) {
			undo();
		}
		else if(moveMade[0].equals("MOVEREDO")) {
			redo();
		}
		else if(moveMade[0].equals("Send_to_back:")) {
			toBack();
		}
		else if(moveMade[0].equals("Bring_to_front:")) {
			toFront();
		}
		else if(moveMade[0].equals("Send_front:")) {
			front();
		}else if(moveMade[0].equals("Send_back:")) {
			back();
		}
	}
	private Shape returnShape(String nextLine) {
		Shape shape = null;
		String[] moveMade = nextLine.split(" ");
		
		if(moveMade[1].equals("Point:")) {
			Point point = new Point(Integer.parseInt(moveMade[2]), Integer.parseInt(moveMade[3]), false, Color.decode(moveMade[5]));
			shape=point;
		}else if(moveMade[1].equals("Line:")) {
			Line line = new Line(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					new Point(Integer.parseInt(moveMade[6]), Integer.parseInt(moveMade[7])), false, Color.decode(moveMade[9]));
			shape = line;
		}else if(moveMade[1].equals("Rectangle:")) {
			Rectangle rectangle = new Rectangle(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					Integer.parseInt(moveMade[6]), Integer.parseInt(moveMade[8]), false, Color.decode(moveMade[12]), Color.decode(moveMade[10]));
			shape = rectangle;
		}else if(moveMade[1].equals("Hexagon:")) {
			HexagonAdapter hexagon = new HexagonAdapter(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])),
					Integer.parseInt(moveMade[6]), false, Color.decode(moveMade[8]), Color.decode(moveMade[10]));
			shape=hexagon;
		}else if(moveMade[1].equals("Donut:")) {
			Donut donut = new Donut(new Point(Integer.parseInt(moveMade[4]), Integer.parseInt(moveMade[5])), Integer.parseInt(moveMade[7]),
					Integer.parseInt(moveMade[13]), false, Color.decode(moveMade[9]), Color.decode(moveMade[11]));
			shape=donut;
		}else if(moveMade[1].equals("Circle:")) {
			Circle circle = new Circle(new Point(Integer.parseInt(moveMade[3]), Integer.parseInt(moveMade[4])), Integer.parseInt(moveMade[6]),
					false, Color.decode(moveMade[8]), Color.decode(moveMade[10]));
			shape = circle;
		}
		
		return shape;
	}

	public void saveDrawing() {
		FileLoader file = new FileLoader(new FileLog(model));
		
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = chooser.showSaveDialog(frame);
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			String filepath = chooser.getSelectedFile().getPath();
			file.save(filepath);
		}else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void loadDrawing() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = chooser.showOpenDialog(frame);
		File file = chooser.getSelectedFile();
		
		if(dialog == JFileChooser.APPROVE_OPTION) {
			clearAll();
			frame.getTextArea().setText(" ");
			
			try {
				FileInputStream stream = new FileInputStream(file);
				ArrayList<Shape> list = new ArrayList<Shape>();
				ObjectInputStream inputStream = new ObjectInputStream(stream);
				
				list = (ArrayList<Shape>) inputStream.readObject();
				model.getShapes().addAll(list);
				
				stream.close();
				inputStream.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "There was an error while trying to load drawing. Please try again!", "Message", JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File not found!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			frame.getBtnUndo().setEnabled(false);
			frame.getView().repaint();
		}else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
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
