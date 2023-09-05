package Observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ListOfSelectedShapes {

	private int listSize;
	private PropertyChangeSupport changeSupport;
	public ListOfSelectedShapes(PropertyChangeSupport changeSupport) {
		super();
		this.changeSupport = changeSupport;
	}
	public ListOfSelectedShapes() {
		changeSupport = new PropertyChangeSupport(this);
	}
	
	public void addNewPCL(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	public void removePCL(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public PropertyChangeSupport getChangeSupport() {
		return changeSupport;
	}
	public void setChangeSupport(PropertyChangeSupport changeSupport) {
		this.changeSupport = changeSupport;
	}
	
	
	
	
}
