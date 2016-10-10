package proj4MalionekLamChistoliniSah;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by joseph on 10/9/16.
 */
public class DragInNoteHandler implements EventHandler<MouseEvent> {

    private CompositionPanel panelToEdit;

    public DragInNoteHandler(CompositionPanel panelToEdit){
        this.panelToEdit = panelToEdit;
    }

    public void handle(MouseEvent event){

    }

    public void handleMousePressed(MouseEvent event) {

    }

    public void handleDragOver(DragEvent event) {
    }

    public void handleDragReleased(MouseEvent event) {
    }
}
