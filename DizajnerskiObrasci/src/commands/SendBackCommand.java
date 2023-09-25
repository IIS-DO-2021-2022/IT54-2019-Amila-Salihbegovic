package commands;

import java.util.Collections;

import geometricShapes.Shape;
import mvc.DrawingModel;

public class SendBackCommand implements Command{
	private DrawingModel model;
	private int index;
	private Shape shape;

	public SendBackCommand(DrawingModel model, int index, Shape shape) {
		this.model = model;
		this.index = index;
		this.shape = shape;
	}

	@Override
	public void execute() {
		Collections.swap(model.getShapes(), index-1, index);
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), index, index-1);
	}
	
	@Override
	public String toString() {
		return "To back->" + shape.toString();
	}

}
