package command;

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
		hexagon.getNewHexagon().setX(newHex.getNewHexagon().getX());
		hexagon.getNewHexagon().setY(newHex.getNewHexagon().getY());
		try {
			hexagon.getNewHexagon().setR(newHex.getNewHexagon().getR());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		hexagon.getNewHexagon().setSelected(newHex.getNewHexagon().isSelected());
		hexagon.getNewHexagon().setBorderColor(newHex.getNewHexagon().getBorderColor());
		hexagon.getNewHexagon().setAreaColor(newHex.getNewHexagon().getAreaColor());
	}

	@Override
	public void unexecute() {
		hexagon.getNewHexagon().setX(oldHex.getNewHexagon().getX());
		hexagon.getNewHexagon().setY(oldHex.getNewHexagon().getY());
		try {
			hexagon.getNewHexagon().setR(oldHex.getNewHexagon().getR());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		hexagon.getNewHexagon().setSelected(oldHex.getNewHexagon().isSelected());
		hexagon.getNewHexagon().setBorderColor(oldHex.getNewHexagon().getBorderColor());
		hexagon.getNewHexagon().setAreaColor(oldHex.getNewHexagon().getAreaColor());
		
	}

}
