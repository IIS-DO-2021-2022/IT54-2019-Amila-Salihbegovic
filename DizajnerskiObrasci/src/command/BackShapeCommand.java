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
		if(i>0) {
			model.getShapes().remove(i);
			model.addOnIndex(i-1, shape);
		}
	}

	@Override
	public void unexecute() {
		if(i>0) {
			model.getShapes().remove(i-1);
			model.addOnIndex(i, shape);
		}
	}
	@Override
	public String toString() {
		return "To back->" + shape.toString();
	}
}
