package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCommand implements Command{

	private DrawingModel drawingModel;
	private Shape shape;
	private int i;
	
	
	public BringToBackCommand(DrawingModel drawingModel, Shape shape) {
		super();
		this.drawingModel = drawingModel;
		this.shape = shape;
	}

	@Override
	public void execute() {
		drawingModel.getShapes().remove(shape);
		drawingModel.addOnIndex(0, shape);
		
	}

	@Override
	public void unexecute() {
		drawingModel.getShapes().remove(shape);
		drawingModel.addOnIndex(i, shape);
		
	}

}
