package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape{
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x,y);
		this.selected=selected;
	}
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}

	
	public double distance(int x, int y) {
		int dx= this.x - x;
		int dy= this.y - y;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	
	public boolean contains(int x, int y)
	{
		return this.distance(x, y) <= 3;
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(this.x - 3, this.y - 3, 3, 3);
		}
		
	}
	
	@Override
	public String toString() {
		return "Point-> (x,y): " + getX()+" "+getY()+" "+ "color: " + getColor().getRGB();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point temp = (Point) obj;
			if(x == temp.x && y==temp.y)
			{
				return true;
			}
			
		}
		return false;
	}
	
	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		x += byX;
		y += byY;
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			return (int)(this.distance(0, 0)-((Point)o).distance(0, 0));
		}
		return 0;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point clone() {
		Point point = new Point();
		
		point.setX(this.getX());
		point.setY(this.getY());
		point.setColor(this.getColor());
		
		return point;
	}
	public void setPropertiesFrom(Point newState) {
		this.setX(newState.getX());
		this.setY(newState.getY());
		this.setColor(newState.getColor());
	}
}
