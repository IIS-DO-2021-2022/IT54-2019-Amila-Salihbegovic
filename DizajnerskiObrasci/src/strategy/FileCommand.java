package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import mvc.DrawingFrame;

public class FileCommand implements FileChooser {

    private DrawingFrame frame;

    public FileCommand(DrawingFrame frame) {
        this.frame = frame;
    }

    @Override
    public void save(String path) {
        File file = new File(path);

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(frame.getTextArea().getText());
            fileWriter.close();

            JOptionPane.showMessageDialog(null, "File saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong while saving!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	@Override
	public void open(String filePath) {
		// TODO Auto-generated method stub
		
	}
}

