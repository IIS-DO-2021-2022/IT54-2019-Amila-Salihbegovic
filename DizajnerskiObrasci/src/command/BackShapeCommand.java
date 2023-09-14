package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class BackShapeCommand implements Command{
	
	private Shape shape;
	private DrawingModel model;
	private int i;
	
	
	public BackShapeCommand(Shape shape, DrawingModel model) {
		super();
		this.shape = shape;
		this.model = model;
		i = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		Collections.swap(model.getShapes(), i-1, i);
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), i, i-1);
	}
	@Override
	public String toString() {
		return "To back->" + shape.toString();
	}
}
