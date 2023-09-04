package command;
import java.util.Collections;
import geometry.Shape;
import mvc.DrawingModel;

public class FrontShapeCommand implements Command{

	private Shape shape;
	private DrawingModel model;
	private int i;
	
	
	
	public FrontShapeCommand(Shape shape, DrawingModel model, int i) {
		super();
		this.shape = shape;
		this.model = model;
		this.i = i;
	}

	@Override
	public void execute() {
		int length = model.getShapes().size()-1;
		if(i!=length) {
			Collections.swap(model.getShapes(), i+1, i);
		}
	}

	@Override
	public void unexecute() {
		int length = model.getShapes().size()-1;
		if(i!=length) {
			Collections.swap(model.getShapes(), i, i+1);
		}
		
	}
	@Override
	public String toString() {
		return "To front->" + shape.toString();
	}
}
