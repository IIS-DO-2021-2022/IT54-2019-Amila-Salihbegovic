package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import mvc.DrawingFrame;

public class FileCommand implements FileChooser{

	DrawingFrame frame;
	
	public FileCommand(DrawingFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void save(String path) {
		File file = new File(path);
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(frame.getTextArea().getText());
			fileWriter.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "something went wrong!", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
