package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import mvc.DrawingModel;

public class FileLog implements FileChooser {

	private DrawingModel model;

	public FileLog(DrawingModel model) {
		super();
		this.model = model;
	}

	@Override
	public void save(String path) {
		File file = new File(path);

		try {
			for (int i = 0; i < model.getShapes().size(); i++) {
				if (model.getShapes().get(i).isSelected() == true) {
					model.getShapes().get(i).setSelected(false);
				}
			}
			FileOutputStream stream = new FileOutputStream(file);
			ObjectOutputStream object = new ObjectOutputStream(stream);
			object.writeObject(model.getShapes());
			object.flush();
			object.close();
			stream.close();
		} catch (FileNotFoundException fnf) {
			JOptionPane.showMessageDialog(null, "File not found!", "Message", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "something went wrong!", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
