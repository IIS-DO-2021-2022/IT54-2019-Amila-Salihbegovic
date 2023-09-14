package command;
import java.util.Collections;
import geometry.Shape;
import mvc.DrawingModel;

public class FrontShapeCommand implements Command{

	private Shape shape;
	private DrawingModel model;
	private int i;
	
	
	
	public FrontShapeCommand(Shape shape, DrawingModel model) {
		super();
		this.shape = shape;
		this.model = model;
		this.i = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if(i!=model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), i+1, i);
		}
	}

	@Override
	public void unexecute() {
		if(i!=model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), i, i+1);
		}
	}
	@Override
	public String toString() {
		return "To front->" + shape.toString();
	}
}
