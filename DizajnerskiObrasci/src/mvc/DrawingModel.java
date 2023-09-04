package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	public Shape selectedShape;

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void add(Shape p) {
		shapes.add(p);
	}

	public void remove(Shape p) {
		shapes.remove(p);
	}
	
	public void addSelected(Shape p) {
		selectedShapes.add(p);
	}
	public void removeSelected(Shape p) {
		selectedShapes.remove(p);
	}
	public Shape get(int index) {
		return shapes.get(index);
	}
	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}
	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}

}
