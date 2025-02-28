package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class ButtonsObserver implements PropertyChangeListener{

	private int listSize;
	private int shapeSize;
	private int index;
	private DrawingFrame frame;
	
	
	public ButtonsObserver(DrawingFrame frame) {
		super();
		this.frame = frame;
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("Size")) {
			this.listSize = (int) evt.getNewValue();
		}
		if(evt.getPropertyName().equals("Index")) {
			this.index = (int) evt.getNewValue();
			this.shapeSize = (int) evt.getOldValue();
		}
		
		showButtons();
		
	}
	
	public void showButtons() {
	    frame.getBtnDelete().setEnabled(false);
	    frame.getBtnModify().setEnabled(false);
	    frame.getBtnBringToFront().setEnabled(false);
	    frame.getBtnBringToBack().setEnabled(false);
	    frame.getBtnToFront().setEnabled(false);
	    frame.getBtnToBack().setEnabled(false);
	   
	    if (listSize > 0) {
	        frame.getBtnDelete().setEnabled(true);
	        if (listSize == 1) {
	            frame.getBtnModify().setEnabled(true);
	        }

	        if (index > 0) {
	            frame.getBtnBringToBack().setEnabled(true);
	            frame.getBtnToBack().setEnabled(true);
	        }

	        if (index < shapeSize-1) {
	            frame.getBtnToFront().setEnabled(true);
	            frame.getBtnBringToFront().setEnabled(true);
	        }
	    }
	}

}

