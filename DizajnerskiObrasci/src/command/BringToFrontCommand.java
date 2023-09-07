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
		drawingModel.getShapes().remove(shape);
		drawingModel.getShapes().add(shape);
		
	}

	@Override
	public void unexecute() {
		int length = drawingModel.getShapes().size();
		if(length<1) {
			drawingModel.getShapes().remove(length-1);
			drawingModel.addOnIndex(i, shape);
		}
		
	}

}
