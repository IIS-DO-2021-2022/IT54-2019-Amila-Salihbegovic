package command;

import geometry.Line;

public class UpdateLineCommand implements Command {

    private Line line;
    private Line oldState;
    private Line newState;

    public UpdateLineCommand(Line line, Line newState) {
        this.line = line;
        this.newState = newState;
    }

    @Override
    public void execute() {
        oldState = line.clone();

        line.setPropertiesFrom(newState);
    }

    @Override
    public void unexecute() {
        line.setPropertiesFrom(oldState);
    }
}
