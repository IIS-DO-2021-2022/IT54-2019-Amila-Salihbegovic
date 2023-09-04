package command;

import geometry.Line;

public class UpdateLineCommand implements Command{

	private Line line;
	private Line newState;
	private Line oldState;
	
	
	
	public UpdateLineCommand(Line line, Line newState, Line oldState) {
		super();
		this.line = line;
		this.newState = newState;
		this.oldState = oldState;
	}

	@Override
	public void execute() {
		oldState = line.clone();
		
		line.setstartpoint(newState.getstartpoint());
		line.setendpoint(newState.getendpoint());
		line.setSelected(newState.isSelected());
		line.setColor(newState.getColor());
		line.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		line.setstartpoint(oldState.getstartpoint());
		line.setendpoint(oldState.getendpoint());
		line.setSelected(oldState.isSelected());
		line.setColor(newState.getColor());
		line.setInnerColor(newState.getInnerColor());
	}

}
