package command;

import geometry.Shape;
import mvc.DrawingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveShapeCommand implements Command {

    private DrawingModel drawingModel;
    private Map<Shape, Integer> removedShapes = new HashMap<>();

    public RemoveShapeCommand(DrawingModel drawingModel, List<Shape> shapesList) {
        this.drawingModel = drawingModel;

        // Save the removed shapes and their positions
        for (Shape shape : shapesList) {
            int position = drawingModel.getShapes().indexOf(shape);
            if (position >= 0) {
                removedShapes.put(shape, position);
            }
        }
    }

    @Override
    public void execute() {
        for (Shape shape : removedShapes.keySet()) {
            drawingModel.remove(shape);
            drawingModel.removeSelected(shape);
        }
    }

    @Override
    public void unexecute() {
        for (Map.Entry<Shape, Integer> entry : removedShapes.entrySet()) {
            Shape shape = entry.getKey();
            int position = entry.getValue();

            // Ensure the position is valid
            if (position >= 0 && position <= drawingModel.getShapes().size()) {
                drawingModel.getShapes().add(position, shape);
                shape.setSelected(true);
                drawingModel.addSelected(shape);
            }
        }
    }
}
