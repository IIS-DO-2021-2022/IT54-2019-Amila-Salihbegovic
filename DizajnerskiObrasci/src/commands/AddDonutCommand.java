package commands;

import geometricShapes.Donut;
import mvc.DrawingModel;

public class AddDonutCommand implements Command{
	private Donut donut;
	private DrawingModel model;
	
	
	
	public AddDonutCommand(Donut donut, DrawingModel model) {
		super();
		this.donut = donut;
		this.model = model;
	}
	@Override
	public void execute() {
		model.add(donut);

	}

	@Override
	public void unexecute() {
		model.remove(donut);

}
	@Override
	public String toString() {
		return "Added->" + donut.toString();
	}
}
