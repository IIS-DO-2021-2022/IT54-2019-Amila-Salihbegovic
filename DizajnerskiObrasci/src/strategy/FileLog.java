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

import geometry.Shape;
import mvc.DrawingModel;

public class FileLog implements FileChooser {

    private DrawingModel model;

    public FileLog(DrawingModel model) {
        this.model = model;
    }

    @Override
    public void save(String path) {
        File file = new File(path);

        try {
            // Deselect all shapes before saving
            deselectAllShapes();

            // Write the shapes to the file
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(model.getShapes());
            objectOutputStream.close();

            JOptionPane.showMessageDialog(null, "File saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong while saving!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deselectAllShapes() {
        for (Shape shape : model.getShapes()) {
            if (shape.isSelected()) {
                shape.setSelected(false);
            }
        }
    }
}