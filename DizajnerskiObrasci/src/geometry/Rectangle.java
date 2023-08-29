package geometry;

import java.awt.Graphics;
import java.awt.Color;


public class Rectangle extends Shape {
	private Point upperLeft;
	private int width;
	private int height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeft, int width, int height) {
		this.upperLeft=upperLeft;
		this.width=width;
		this.height=height;
	}
	
	public Rectangle(Point upperLeft, int width, int height, boolean selected) {
		this(upperLeft, width, height);
		this.selected=selected;
	}
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return width * height;
	}
	public int circumference() {
		return 2*(width + height);
	}
	
	public boolean contains(int x, int y) {
		return (upperLeft.getX() < x && upperLeft.getX() + width > x
				&& upperLeft.getY() < y && upperLeft.getY() + height > y);
	}
	
	
	public boolean contains(Point p) {
		return this.contains(p.getX(), p.getY());
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeft.getX(), this.upperLeft.getY(), this.width, this.height);
		
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getupperleft().getX() - 3, getupperleft().getY() - 3, 6, 6);
			g.drawRect(getupperleft().getX() + getwidth() - 3, getupperleft().getY() - 3, 6, 6);
			g.drawRect(getupperleft().getX() - 3, getupperleft().getY() + getheight() - 3, 6, 6);
			g.drawRect(getupperleft().getX() + getwidth() - 3, getupperleft().getY() + getheight() - 3, 6, 6);
		}

	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeft.getX()+1, this.getupperleft().getY()+1, width-1, height-1);
	}
	
	@Override
	public String toString() {
		return "UpperLeft: " + upperLeft + "\nwidth: " + width + "\nheight: " + height;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle rct=(Rectangle) obj;
			if(upperLeft.equals(rct.upperLeft) && width == rct.width && height == rct.height) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
		
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return (int)(this.area()-((Rectangle)o).area());
		}
		return 0;
	}
	
	public Point getupperleft() {
		return upperLeft;
	}
	public void setupperletf(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getwidth() {
		return width;
	}
	public void setwidth(int width) {
		this.width = width;
	}
	public int getheight() {
		return height;
	}
	public void setheight(int height) {
		this.height=height;
	}
}
