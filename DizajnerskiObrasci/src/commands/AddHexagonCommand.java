package commands;

import adapter.HexagonAdapter;
import mvc.DrawingModel;

public class AddHexagonCommand implements Command{

	private HexagonAdapter hexagon;
	private DrawingModel model;
	
	public AddHexagonCommand(HexagonAdapter hexagon, DrawingModel model) {
		super();
		this.hexagon = hexagon;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(hexagon);
	}

	@Override
	public void unexecute() {
		model.remove(hexagon);
	}

}
