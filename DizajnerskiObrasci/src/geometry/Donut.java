package geometry;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

public class Donut extends Circle {
	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		Ellipse2D outer = new Ellipse2D.Double(this.getcenter().getX() - this.getradius(), this.getcenter().getY() - this.getradius(), this.getradius()*2, this.getradius()*2); 
		Ellipse2D inner = new Ellipse2D.Double(this.getcenter().getX() - this.getInnerRadius() , this.getcenter().getY() - this.getInnerRadius(), this.innerRadius * 2, this.innerRadius * 2);
		
		Area area = new Area(outer);
		Area innerArea = new Area(inner);
		area.subtract(innerArea);
		
		g2d.setColor(getInnerColor());
		g2d.fill(area);
		g2d.setColor(getColor());
		g2d.draw(area);
		
		if (isSelected()) {
			g2d.setColor(Color.RED);
			g2d.drawRect(this.getcenter().getX() - 3, this.getcenter().getY() - 3, 3, 3);
			g2d.drawRect(this.getcenter().getX() - this.getradius() - 3, this.getcenter().getY() - 3, 3, 3);
			g2d.drawRect(this.getcenter().getX() + this.getradius() - 3, this.getcenter().getY() - 3, 3, 3);
			g2d.drawRect(this.getcenter().getX()- 3, this.getcenter().getY() - this.getradius()- 3, 3, 3);
			g2d.drawRect(this.getcenter().getX()- 3,this.getcenter().getY()+ this.getradius()- 3, 3, 3);
		}
	}
	@Override
	public Donut clone() {
		Donut donut = new Donut();

		donut.setcenter(this.getcenter());
		try {
			donut.setradius(this.getradius());
		} catch (Exception e) {
			throw new NumberFormatException("An error occured." + e);
		}
		donut.setInnerRadius(this.getInnerRadius());
		donut.setSelected(this.isSelected());
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		return donut;
	}

	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getcenter().getX() - this.innerRadius, getcenter().getY() - this.innerRadius, this.innerRadius * 2,
				this.innerRadius * 2);
	}

	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getcenter().equals(d.getcenter()) && this.getradius() == d.getradius()
					&& this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		double dFromCenter = this.getcenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = this.getcenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	public int getInnerRadius() {
		return this.innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		return "Donut: " + super.toString() + " inner_radius: " + getInnerRadius();
	}
	 public void setPropertiesFrom(Donut otherDonut) {
	        this.setcenter(otherDonut.getcenter());
	        try {
				this.setradius(otherDonut.getradius());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        this.setColor(otherDonut.getColor());
	        this.setInnerRadius(otherDonut.getInnerRadius());
	        this.setInnerColor(otherDonut.getInnerColor());
	        this.setSelected(otherDonut.isSelected());
	    }
}
