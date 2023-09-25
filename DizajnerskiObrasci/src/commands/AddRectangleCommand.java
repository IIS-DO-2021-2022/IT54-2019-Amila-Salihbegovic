package commands;

import geometricShapes.Rectangle;
import mvc.DrawingModel;

public class AddRectangleCommand implements Command{
	private Rectangle rectangle;
	private DrawingModel model;
	
	
	public AddRectangleCommand(Rectangle rectangle, DrawingModel model) {
		super();
		this.rectangle = rectangle;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(rectangle);

	}

	@Override
	public void unexecute() {
		model.remove(rectangle);

}
	@Override
	public String toString() {
		return "Added->" + rectangle.toString();
	}
}
