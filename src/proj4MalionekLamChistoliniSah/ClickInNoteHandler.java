package proj4MalionekLamChistoliniSah;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by joseph on 10/9/16.
 */
public class ClickInNoteHandler implements EventHandler<MouseEvent> {
    private CompositionPanel compositionPanel;

    public ClickInNoteHandler (CompositionPanel compositionPanel){
        this.compositionPanel = compositionPanel;
    }

    public void handle(MouseEvent event) {
        System.out.println("rectangle selected");


    }
}
