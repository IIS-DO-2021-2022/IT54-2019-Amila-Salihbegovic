package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometricShapes.Moveable;
import geometricShapes.Point;
import geometricShapes.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Moveable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Hexagon newHexagon = new Hexagon(0,0,0);
	
	

	public HexagonAdapter() {
		super();
	}
	
	public HexagonAdapter(Point point) {
		this.newHexagon.setX(point.getX());
		this.newHexagon.setY(point.getY());
	}
	
	public HexagonAdapter(Point point, int radius) {
		this.newHexagon.setX(point.getX());
		this.newHexagon.setY(point.getY());
		this.newHexagon.setR(radius);
	}
	public HexagonAdapter(Point point, int radius, Color color, boolean isSelected) {
		this.newHexagon.setX(point.getX());
		this.newHexagon.setY(point.getY());
		this.newHexagon.setR(radius);
		this.newHexagon.setSelected(isSelected);
		this.newHexagon.setAreaColor(color);
	}
	public HexagonAdapter(Point point, int radius, Color innerColor, Color borderColor) {
		this.newHexagon.setX(point.getX());
		this.newHexagon.setY(point.getY());
		this.newHexagon.setR(radius);
		this.newHexagon.setAreaColor(innerColor);
		this.newHexagon.setBorderColor(borderColor);
	}
	public HexagonAdapter(Point point, int radius, boolean isSelected, Color innerColor, Color borderColor) {
		this.newHexagon.setX(point.getX());
		this.newHexagon.setY(point.getY());
		this.newHexagon.setR(radius);
		this.newHexagon.setSelected(isSelected);
		this.newHexagon.setAreaColor(innerColor);
		this.newHexagon.setBorderColor(borderColor);
	}
	@Override
	public void moveTo(int x, int y) {
		newHexagon.setX(x);
		newHexagon.setY(y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		newHexagon.setX(newHexagon.getX()+byX);
		newHexagon.setY(newHexagon.getY()+byY);
		
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Hexagon) {
			Hexagon h = (Hexagon) o;
			return newHexagon.getR()-h.getR();
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		if(newHexagon.doesContain(x,y)) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		newHexagon.paint(g);
		if(isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(this.newHexagon.getX()-3, this.newHexagon.getY()-3, 3,3);
			g.drawRect(this.newHexagon.getX()-this.newHexagon.getR()-3, this.newHexagon.getY()-3, 3,3);
			g.drawRect(this.newHexagon.getX()+this.newHexagon.getR()-3, this.newHexagon.getY()-3, 3,3);
			g.drawRect(this.newHexagon.getX()-3, this.newHexagon.getY()-this.newHexagon.getR()-3, 3,3);
			g.drawRect(this.newHexagon.getX()-3,  this.newHexagon.getY()+this.newHexagon.getR()-3, 3,3);
		}
	}
	@Override
	public void setColor(Color c) {
		super.setColor(c);
		newHexagon.setBorderColor(c);
	}
	
	@Override
	public void setInnerColor(Color c) {
		super.setInnerColor(c);
		newHexagon.setAreaColor(c);
	}
	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Hexagon getNewHexagon() {
		return newHexagon;
	}

	public void setNewHexagon(Hexagon newHexagon) {
		this.newHexagon = newHexagon;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "Hexagon-> center: " + getNewHexagon().getX() + " " + getNewHexagon().getY() + " radius: " + getNewHexagon().getR()
				+ " area_color: " + getNewHexagon().getAreaColor().getRGB() + " border_color: "
				+ getNewHexagon().getBorderColor().getRGB();
	}
	@Override
	public Shape clone() {
		HexagonAdapter hex = new HexagonAdapter();
		
		hex.newHexagon.setX(this.newHexagon.getX());
		hex.newHexagon.setY(this.newHexagon.getY());
		try {
			hex.newHexagon.setR(this.newHexagon.getR());
		}catch(Exception e) {
			throw new NumberFormatException("An error occured."+e);
		}
		hex.newHexagon.setBorderColor(this.newHexagon.getBorderColor());
		hex.newHexagon.setAreaColor(this.newHexagon.getAreaColor());
		hex.setSelected(this.newHexagon.isSelected());
		
		return hex;
	}
	 public void setParametersFrom(HexagonAdapter otherHexagon) {
	        if (otherHexagon != null) {
	            this.newHexagon.setX(otherHexagon.newHexagon.getX());
	            this.newHexagon.setY(otherHexagon.newHexagon.getY());
	            this.newHexagon.setR(otherHexagon.newHexagon.getR());
	            this.selected = otherHexagon.selected;
	            this.newHexagon.setBorderColor(otherHexagon.newHexagon.getBorderColor());
	            this.newHexagon.setAreaColor(otherHexagon.newHexagon.getAreaColor());
	        }
	    }
}
