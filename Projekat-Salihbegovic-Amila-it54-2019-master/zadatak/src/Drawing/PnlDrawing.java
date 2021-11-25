package Drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Iterator;
import geometry.circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.rectangle;
import geometry.Shape;
import Drawing.DlgCircle;
import Drawing.DlgDonut;
import Drawing.DlgLine;
import Drawing.DlgRectangle;
import Drawing.Drawing;


public class PnlDrawing extends JPanel {
	
	private Drawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private Shape selectedShape;

	/**
	 * Create the panel.
	 */
	public PnlDrawing(Drawing frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisMouseClicked(e);

			}
	});
}

	protected void thisMouseClicked(MouseEvent e) {
		Shape newShape = null;
		Point click = new Point(e.getX(), e.getY());

		if (frame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			Iterator<Shape> iterator = shapes.iterator();

			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				shape.setSelected(false);
				if (shape.contains(click.getX(), click.getY()))
					selectedShape = shape;

			}

			if (selectedShape != null)
				selectedShape.setSelected(true);

		} else if (frame.getTglbtnPoint().isSelected()) {

			newShape = new Point(click.getX(), click.getY(), false, Color.black);

		} else if (frame.getTglbtnLine().isSelected()) {

			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(e.getX(), e.getY(), false, Color.black));
				startPoint = null;
			}

		} else if (frame.getTglbtnCircle().isSelected()) {
			
			DlgCircle dialog = new DlgCircle();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);
			dialog.setVisible(true);
			
			if (dialog.isOK()) {
				newShape = dialog.getCircle();
			}
			
	

		} else if (frame.getTglbtnDonut().isSelected()) {
			
			DlgDonut dialog = new DlgDonut();
			dialog.setModal(true);
			dialog.getTxtDX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtDX().setEditable(false);
			dialog.getTxtDY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtDY().setEditable(false);
			dialog.setVisible(true);

			if (dialog.isOK()) {

				newShape = dialog.getDonut();
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {
	
			DlgRectangle dialog = new DlgRectangle();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(e.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(e.getY()));
			dialog.getTxtY().setEditable(false);
			dialog.setVisible(true);
			
			if(dialog.isOK())
			{
				newShape = dialog.getRect();
			}
			
		}

		if (newShape != null)
			shapes.add(newShape);

		repaint();

	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);

	}
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
}