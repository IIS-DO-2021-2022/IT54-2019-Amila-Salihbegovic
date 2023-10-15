package commands;

import geometricShapes.Circle;

public class UpdateCircleCommand implements Command {

    private Circle circle;
    private Circle newCircle;
    private Circle oldCircle;

    public UpdateCircleCommand(Circle circle, Circle newCircle) {
        this.circle = circle;
        this.newCircle = newCircle;
    }

    @Override
    public void execute() {
        oldCircle = circle.clone();
        circle.setPropertiesFrom(newCircle);
    }

    @Override
    public void unexecute() {
        circle.setPropertiesFrom(oldCircle);
    }
    @Override
	public String toString() {
		return "Modified->" + circle.toString();
	}
}
