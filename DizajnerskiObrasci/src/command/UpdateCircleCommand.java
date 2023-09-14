package command;

import geometry.Circle;

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
}
