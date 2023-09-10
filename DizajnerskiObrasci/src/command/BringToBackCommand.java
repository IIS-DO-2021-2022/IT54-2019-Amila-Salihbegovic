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
		this.i = drawingModel.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		drawingModel.getShapes().remove(i);
		drawingModel.addOnIndex(0, shape);
		
	}

	@Override
	public void unexecute() {
		drawingModel.getShapes().remove(0);
		drawingModel.addOnIndex(i, shape);
		
	}

}
