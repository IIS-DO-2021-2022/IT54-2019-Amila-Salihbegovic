package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	public Shape selectedShape;
	private PropertyChangeSupport support;
	
	
	public DrawingModel() {
		support = new PropertyChangeSupport(this);
	}

	public void addNewPCL(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}
	public void removePCL(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void add(Shape p) {
		shapes.add(p);
		support.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(p));
	}

	public void remove(Shape p) {
		support.firePropertyChange("Index", getShapes().size()-1, getShapes().indexOf(p));
		shapes.remove(p);
	}
	
	public void addSelected(Shape p) {
		support.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size()+1);
		support.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(p));
		selectedShapes.add(p);		
	}
	public void removeSelected(Shape p) {
		support.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size()-1);
		support.firePropertyChange("Index", getShapes().size()-1, getShapes().indexOf(p));
		selectedShapes.remove(p);
	}
	public void position(int index, Shape s) {

		shapes.add(index, s);
		support.firePropertyChange("Index", getShapes().size(), index);
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
	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

}
