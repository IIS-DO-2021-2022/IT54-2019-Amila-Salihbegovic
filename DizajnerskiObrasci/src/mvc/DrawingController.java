package mvc;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import dialogs.DialogCircle;
import dialogs.DialogDonut;
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

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
	}

	public void modify() {
		Shape selectedShape = model.getSelectedShape();

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
					model.getShapes().remove(selectedShape);
					model.getShapes().add(dglpoint.getP());
					frame.repaint();
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
					model.getShapes().remove(selectedShape);
					model.getShapes().add(dgldonut.getDonut());
					frame.repaint();
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
					model.getShapes().remove(selectedShape);
					model.getShapes().add(dglcircle.getCircle());
					frame.repaint();
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

					model.getShapes().remove(selectedShape);
					model.getShapes().add(dlgline.getLine());
					frame.repaint();
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
					model.getShapes().remove(selectedShape);
					model.getShapes().add(dlgrectangle.getRect());
					frame.repaint();
				}
			}

		}
		
	}

	public void delete() {
		Shape selectedShape = model.getSelectedShape();

		if (selectedShape != null) 
		{
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) 
			{
				model.getShapes().remove(selectedShape);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error", JOptionPane.WARNING_MESSAGE);
		}
		model.setSelectedShape(null);
		frame.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}

	public void redo() {
		// TODO Auto-generated method stub
		
	}

	public void toBack() {
		// TODO Auto-generated method stub
		
	}

	public void toFront() {
		// TODO Auto-generated method stub
		
	}

	public void front() {
		// TODO Auto-generated method stub
		
	}
	public void back() {
		// TODO Auto-generated method stub
		
	}

	public void innercolor() {
		// TODO Auto-generated method stub
		
	}

	public void areacolor() {
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

}
