package mvc;

import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import geometricShapes.Shape;

public class DrawingView extends JPanel {
	DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingModel getModel() {
		return model;
	}
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Iterator<Shape> iterator = model.getShapes().iterator();
		while (iterator.hasNext())
			iterator.next().draw(graphics);

	}
}

