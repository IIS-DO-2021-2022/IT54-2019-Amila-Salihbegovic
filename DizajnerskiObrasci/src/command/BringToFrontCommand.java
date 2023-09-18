package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCommand implements Command{

	private DrawingModel drawingModel;
	private Shape shape;
	private int i;
	
	
	public BringToFrontCommand(DrawingModel drawingModel, Shape shape) {
		super();
		this.drawingModel = drawingModel;
		this.shape = shape;
		this.i = drawingModel.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		drawingModel.remove(shape);
		drawingModel.add(shape);
		
	}

	@Override
	public void unexecute() {
		drawingModel.getShapes().remove(shape);
		drawingModel.position(i, shape);
	}

}
