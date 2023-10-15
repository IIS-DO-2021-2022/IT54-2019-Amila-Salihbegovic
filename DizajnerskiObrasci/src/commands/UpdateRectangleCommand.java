package commands;

import geometricShapes.Rectangle;

public class UpdateRectangleCommand implements Command{
	 private Rectangle rectangle;
	    private Rectangle oldRectangle;
	    private Rectangle newRectangle;

	    public UpdateRectangleCommand(Rectangle rectangle, Rectangle newRectangle) {
	        this.rectangle = rectangle;
	        this.newRectangle = newRectangle;
	    }

	    @Override
	    public void execute() {
	        oldRectangle = rectangle.clone();

	        rectangle.setPropertiesFrom(newRectangle);
	    }

	    @Override
	    public void unexecute() {
	        rectangle.setPropertiesFrom(oldRectangle);
	    }
	    @Override
		public String toString() {
			return "Modified->" + rectangle.toString();
		}
}
