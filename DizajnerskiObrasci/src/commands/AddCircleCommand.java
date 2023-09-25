package commands;

import geometricShapes.Circle;
import mvc.DrawingModel;

public class AddCircleCommand implements Command{
	private Circle circle;
	private DrawingModel model;
	
	
	public AddCircleCommand(Circle circle, DrawingModel model) {
		super();
		this.circle = circle;
		this.model = model;
	}
	@Override
	public void execute() {
		model.add(circle);
	}
	@Override
	public void unexecute() {
		model.remove(circle);
	}
	@Override
	public String toString() {
		return "Added->" + circle.toString();
	}
}
