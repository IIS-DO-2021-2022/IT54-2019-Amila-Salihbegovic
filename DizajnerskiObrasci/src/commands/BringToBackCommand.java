package commands;

import geometricShapes.Shape;
import mvc.DrawingModel;

public class BringToBackCommand implements Command{
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	 public BringToBackCommand(DrawingModel model, Shape shape, int index) {
		this.model=model;
		this.shape=shape;
		this.index=index;
	}
	
	

	@Override
	public void execute() {	
			model.getShapes().remove(shape);
			model.getShapes().add(0,shape);	

	}

	@Override
	public void unexecute() { 
		model.getShapes().remove(shape);
		model.getShapes().add(index,shape);

	}



	@Override
	public String toString() { 
		return "Bring to back->" + shape.toString();
	}
	
}
