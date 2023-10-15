package commands;

import adapter.HexagonAdapter;

public class UpdateHexagonCommand implements Command{

	
	private HexagonAdapter hexagon;
	private HexagonAdapter oldHex;
	private HexagonAdapter newHex;
	
	
	
	public UpdateHexagonCommand(HexagonAdapter hexagon, HexagonAdapter newHex) {
		super();
		this.hexagon = hexagon;
		this.newHex = newHex;
	}

	@Override
	public void execute() {
		
		oldHex = (HexagonAdapter) hexagon.clone();
		hexagon.setParametersFrom(newHex);
	}

	@Override
	public void unexecute() {
		hexagon.setParametersFrom(oldHex);
	}

	@Override
	public String toString() {
		return "Modified->" + hexagon.toString();
	}
}
