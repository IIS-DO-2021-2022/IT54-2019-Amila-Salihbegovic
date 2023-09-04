package command;

import geometry.Circle;

public class UpdateCircleCommand implements Command{

	private Circle circle;
	private Circle newCircle;
	private Circle oldCircle;
	
	
	public UpdateCircleCommand(Circle circle, Circle newCircle, Circle oldCircle) {
		super();
		this.circle = circle;
		this.newCircle = newCircle;
		this.oldCircle = oldCircle;
	}

	@Override
	public void execute() {
		
		oldCircle = circle.clone();
		
		circle.setcenter(newCircle.getcenter());
		try {
			circle.setradius(newCircle.getradius());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		circle.setColor(newCircle.getColor());
		circle.setInnerColor(newCircle.getInnerColor());
		circle.setSelected(newCircle.isSelected());
	}

	@Override
	public void unexecute() {
		circle.setcenter(oldCircle.getcenter());
		try {
			circle.setradius(oldCircle.getradius());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		circle.setColor(oldCircle.getColor());
		circle.setInnerColor(oldCircle.getInnerColor());
		circle.setSelected(oldCircle.isSelected());
		
	}

}
