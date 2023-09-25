package commands;

import geometricShapes.Point;

public class UpdatePointCommand implements Command{

	private Point point;
	private Point newState;
	private Point oldState;
	
	
	public UpdatePointCommand(Point point, Point newState) {
		super();
		this.point = point;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState = point.clone();
		
		point.setPropertiesFrom(newState);
		
	}

	@Override
	public void unexecute() {
		point.setPropertiesFrom(oldState);
		
	}

}