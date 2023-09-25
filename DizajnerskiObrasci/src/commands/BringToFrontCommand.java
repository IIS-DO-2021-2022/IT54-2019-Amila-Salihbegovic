package commands;

import geometricShapes.Shape;
import mvc.DrawingModel;

public class BringToFrontCommand implements Command{
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public BringToFrontCommand(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
		this.index = model.getShapes().indexOf(shape);
	}
	

	@Override
	public void execute() {	
			model.getShapes().remove(shape);
			model.getShapes().add(shape);		

	}

	@Override
	public void unexecute() {
		model.getShapes().remove(shape);
		model.getShapes().add(index,shape);

	}


	@Override
	public String toString() {
		return "Bring to front->" + shape.toString();
	}
}
