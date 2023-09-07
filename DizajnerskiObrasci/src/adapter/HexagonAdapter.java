package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Moveable;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Moveable{
	
	Hexagon newHexagon = new Hexagon(0,0,0);
	
	

	public HexagonAdapter() {
		super();
	}
	
	public HexagonAdapter(Point point) {
		newHexagon.setX(point.getX());
		newHexagon.setY(point.getY());
	}
	
	public HexagonAdapter(Point point, int radius) {
		newHexagon.setX(point.getX());
		newHexagon.setY(point.getY());
		newHexagon.setR(radius);
	}
	public HexagonAdapter(Point point, int radius, Color color, boolean isSelected) {
		newHexagon.setX(point.getX());
		newHexagon.setY(point.getY());
		newHexagon.setR(radius);
		newHexagon.setSelected(isSelected);
		newHexagon.setAreaColor(color);
	}
	public HexagonAdapter(Point point, int radius, Color innerColor, Color borderColor) {
		newHexagon.setX(point.getX());
		newHexagon.setY(point.getY());
		newHexagon.setR(radius);
		newHexagon.setAreaColor(innerColor);
		newHexagon.setBorderColor(borderColor);
	}
	public HexagonAdapter(Point point, int radius, boolean isSelected, Color innerColor, Color borderColor) {
		newHexagon.setX(point.getX());
		newHexagon.setY(point.getY());
		newHexagon.setR(radius);
		newHexagon.setSelected(isSelected);
		newHexagon.setAreaColor(innerColor);
		newHexagon.setBorderColor(borderColor);
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
			g.setColor(Color.BLUE);
			g.drawRect(this.newHexagon.getX()-3, this.newHexagon.getY()-3, 6,6);
			g.drawRect(this.newHexagon.getX()-this.newHexagon.getR()-3, this.newHexagon.getY()-3, 6,6);
			g.drawRect(this.newHexagon.getX()+this.newHexagon.getR()-3, this.newHexagon.getR()-3, 6,6);
			g.drawRect(this.newHexagon.getX()-3, this.newHexagon.getY()-this.newHexagon.getR()-3, 6,6);
			g.drawRect(this.newHexagon.getX()-3,  this.newHexagon.getY()-this.newHexagon.getR()+3, 6,6);
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
//	@Override
//	public String toString() {
//		return String.format("Hexagon(X=%d,Y=%d,r=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)", this.newHexagon.getX(),
//				this.newHexagon.getY(), this.newHexagon.getR(), getColor().getRed(), getColor().getGreen(), getColor().getBlue(),
//				getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
//	}

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

}
