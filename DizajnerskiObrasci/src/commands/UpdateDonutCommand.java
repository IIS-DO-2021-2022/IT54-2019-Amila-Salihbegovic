package commands;

import geometricShapes.Donut;

public class UpdateDonutCommand implements Command{

	
	private Donut donut;
	private Donut oldState;
	private Donut newDonut;
	
	
	
	public UpdateDonutCommand(Donut donut, Donut newDonut) {
		super();
		this.donut = donut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		oldState = donut.clone();
		donut.setPropertiesFrom(newDonut);	
	}

	@Override
	public void unexecute() {
		donut.setPropertiesFrom(oldState);
	}
	@Override
	public String toString() {
		return "Modified->" + donut.toString();
	}

}
