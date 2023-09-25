package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObserverUpdate {
	private int listSize;
	private PropertyChangeSupport changeSupport;
	public ObserverUpdate(PropertyChangeSupport changeSupport) {
		this.changeSupport = changeSupport;
	}
	public ObserverUpdate() {
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
