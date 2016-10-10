



package proj4MalionekLamChistoliniSah;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * Created by joseph on 10/9/16.
 */
public class ClickInPanelHandler {
    CompositionPanel panelToEdit;
    private final int DEFAULT_RECTANGLE_WIDTH = 100;
    private boolean isMetaDown;


    public ClickInPanelHandler(CompositionPanel panelToEdit){
        this.panelToEdit = panelToEdit;
    }

    public void handle(MouseEvent event, String instrument) {
        if  (! this.panelToEdit.inARectangle(event.getX(),event.getY())) {
            isMetaDown = event.isShortcutDown();
            addNote(event.getX(), event.getY(), instrument);
        }

    }

    /**
     * Creates a note at the given x and y coordinates
     * and adds the note bar (Rectangle) to the CompositionPanel.
     *
     * @param x mouse x location
     * @param y mouse y location
     */
    public void addNote(double x, double y, String instrument) {
        double pitch = Math.floor((y - 1) / 10) * 10 + 1;
        NoteRectangle rectangle = new NoteRectangle(x, pitch, this.DEFAULT_RECTANGLE_WIDTH, 10, instrument);
        DragInNoteHandler handler = new DragInNoteHandler(this.panelToEdit,rectangle);
        rectangle.setOnMousePressed(handler::handleMousePressed);//These are really cool!
        rectangle.setOnMouseDragged(handler::handleDragged);
        rectangle.setOnMouseDragReleased(handler::handleDragReleased);
        if (! isMetaDown) {
            this.panelToEdit.clearSelected();
        }
        rectangle.setOnMouseClicked(new ClickInNoteHandler(this.panelToEdit));
        this.panelToEdit.addRectangle(rectangle,true);

    }
}



