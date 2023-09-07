package command;

import geometry.Rectangle;

public class UpdateRectangleCommand implements Command{

	private Rectangle rectangle;
	private Rectangle newRectangle;
	private Rectangle oldRectangle;
	
	
	
	public UpdateRectangleCommand(Rectangle rectangle, Rectangle newRectangle) {
		super();
		this.rectangle = rectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
	
		oldRectangle = rectangle.clone();
		
		rectangle.setupperletf(newRectangle.getupperleft());
		rectangle.setheight(newRectangle.getheight());
		rectangle.setwidth(newRectangle.getwidth());
		rectangle.setColor(newRectangle.getColor());
		rectangle.setInnerColor(newRectangle.getInnerColor());
		rectangle.setSelected(newRectangle.isSelected());
	}

	@Override
	public void unexecute() {
		rectangle.setupperletf(oldRectangle.getupperleft());
		rectangle.setheight(oldRectangle.getheight());
		rectangle.setwidth(oldRectangle.getwidth());
		rectangle.setColor(oldRectangle.getColor());
		rectangle.setInnerColor(oldRectangle.getInnerColor());
		rectangle.setSelected(oldRectangle.isSelected());
	}

}
