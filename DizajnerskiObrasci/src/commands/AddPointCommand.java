package commands;

import geometricShapes.Point;
import mvc.DrawingModel;

public class AddPointCommand implements Command{

	private Point point;
	private DrawingModel model;
	
	public AddPointCommand(Point point, DrawingModel model) {
		this.point=point;
		this.model=model;
	}

	@Override
	public void execute() {
		model.add(point);
	}

	@Override
	public void unexecute() {
		model.remove(point);
	}
	
	@Override
	public String toString() {
		return "Added->"+point.toString();
	}

}
