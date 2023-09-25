package commands;

import geometricShapes.Line;
import mvc.DrawingModel;

public class AddLineCommand implements Command{
	private Line line;
	private DrawingModel model;
	
	
	
	public AddLineCommand(Line line, DrawingModel model) {
		super();
		this.line = line;
		this.model = model;
	}
	@Override
	public void execute() {
		model.add(line);
		
	}
	@Override
	public void unexecute() {
		model.remove(line);
	}
	@Override
	public String toString() {
		return "Added->" + line.toString();
	}
}
