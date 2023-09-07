package command;

import geometry.Point;

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
		
		point.setX(newState.getX());
		point.setY(newState.getY());
		point.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		point.setX(oldState.getX());
		point.setY(oldState.getY());
		point.setColor(oldState.getColor());
		
	}

}
