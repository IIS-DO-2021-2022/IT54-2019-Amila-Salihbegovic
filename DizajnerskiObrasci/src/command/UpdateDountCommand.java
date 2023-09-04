package command;

import geometry.Donut;

public class UpdateDountCommand implements Command{

	
	private Donut donut;
	private Donut oldState;
	private Donut newDonut;
	
	
	
	public UpdateDountCommand(Donut donut, Donut oldState, Donut newDonut) {
		super();
		this.donut = donut;
		this.oldState = oldState;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		oldState = donut.clone();
		
		donut.setcenter(newDonut.getcenter());
		try {
			donut.setradius(newDonut.getradius());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		donut.setColor(newDonut.getColor());
		donut.setInnerRadius(newDonut.getInnerRadius());
		donut.setInnerColor(newDonut.getInnerColor());
		donut.setSelected(newDonut.isSelected());
		
	}

	@Override
	public void unexecute() {
		donut.setcenter(oldState.getcenter());
		try {
			donut.setradius(oldState.getradius());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		donut.setColor(oldState.getColor());
		donut.setInnerRadius(oldState.getInnerRadius());
		donut.setInnerColor(oldState.getInnerColor());
		donut.setSelected(oldState.isSelected());
		
	}

}
