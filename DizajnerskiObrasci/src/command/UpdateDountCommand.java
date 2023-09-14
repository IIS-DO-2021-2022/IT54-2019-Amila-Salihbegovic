package command;

import geometry.Donut;

public class UpdateDountCommand implements Command{

	
	private Donut donut;
	private Donut oldState;
	private Donut newDonut;
	
	
	
	public UpdateDountCommand(Donut donut, Donut newDonut) {
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

}
