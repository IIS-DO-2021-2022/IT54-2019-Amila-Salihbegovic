package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;
	
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.selected= selected;
	}
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public boolean contains(int x, int y) {
		return (startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <=3;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
		}
		
	}
	
	@Override
	public String toString() {
		return startPoint + "-->" + endPoint;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line lin=(Line) obj;
				return startPoint.equals(lin.startPoint) && endPoint.equals(lin.endPoint);
			
			
		}
		return false;
	}
	
	@Override
	public void moveTo(int x, int y) {
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line) {
			return (int)(this.length()-((Line)o).length());
		}
		return 0;
	}
	
	public Point getstartpoint() {
		return startPoint;
	}
	public void setstartpoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getendpoint() {
		return endPoint;
	}
	public void setendpoint(Point endPoint) {
		this.endPoint = endPoint;
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
	public Line clone() {
		Line line = new Line();
		
		line.setstartpoint(this.getstartpoint());
		line.setendpoint(this.getendpoint());
		line.setColor(this.getColor());
		
		return line;
	}

}
