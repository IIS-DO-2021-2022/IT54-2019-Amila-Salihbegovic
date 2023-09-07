package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCommand implements Command{

	private DrawingModel drawingModel;
	private ArrayList<Shape> shapesList;
	private ArrayList<Shape> modelShapes = new ArrayList<Shape>();
	
	
	
	
	public RemoveShapeCommand(DrawingModel drawingModel, ArrayList<Shape> shapesList) {
		super();
		this.drawingModel = drawingModel;
		this.shapesList = new ArrayList<Shape>(shapesList);
	}

	@Override
	public void execute() {
		modelShapes.addAll(drawingModel.getShapes());
		for(Shape shape:shapesList) {
			drawingModel.remove(shape);
			drawingModel.removeSelected(shape);
		}
		
	}

	@Override
	public void unexecute() {
		drawingModel.getShapes().clear();
		drawingModel.getShapes().addAll(modelShapes);
		
		for(Shape shape:shapesList) {
			shape.setSelected(true);
			drawingModel.addSelected(shape);
		}
	}

}
