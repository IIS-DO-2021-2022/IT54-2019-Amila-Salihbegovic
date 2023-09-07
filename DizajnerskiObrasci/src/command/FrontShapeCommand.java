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
		int length = model.getShapes().size();
		if(i!=length) {
			model.getShapes().remove(i);
			model.addOnIndex(i+1, shape);
		}
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(i+1);
		model.addOnIndex(i, shape);
	}
	@Override
	public String toString() {
		return "To front->" + shape.toString();
	}
}
