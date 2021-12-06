package geometry;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Shape implements Moveable, Comparable{
	protected boolean selected;
	private Color color;
	private Color innerColor;
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	public abstract double area();
	public abstract void fill(Graphics g);

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
