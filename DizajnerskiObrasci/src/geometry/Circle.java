package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Circle extends Shape {
	protected Point center;
	protected int radius;
	
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center=center;
		this.radius=radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected=selected;
	}
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}
	
	public boolean contains(Point p) {
		return this.contains(p.getX(), p.getY());
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius	 + 1, this.center.getY() - radius + 1, radius*2 - 2, radius*2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius	, this.center.getY() - radius, radius*2, radius*2); 
		this.fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}
		
	}
	
	@Override
	public String toString() {
		return " Center: " + center + " Radisu: " + radius;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle crc= (Circle) obj;
			if(center.equals(crc.center) && radius == crc.radius) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
		
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (int)(this.area()-((Circle) o).area());
		}
		return 0;
	}	
	

	public double area() {
		return radius*radius*Math.PI;
	}
	
	public double circumference() {
		return 2*radius*Math.PI;
	}

	public Point getcenter() {
		return center;
	}
	public void setcenter(Point center) {
		this.center = center;
	}
	public int getradius() {
		return radius;
	}
	public void setradius(int radius) throws Exception {
		if (radius < 0) {
			throw new Exception ("Radius ne moze biti manji od 0");
		}
		this.radius = radius;
	}
	
}
