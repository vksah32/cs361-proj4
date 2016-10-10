package proj4MalionekLamChistoliniSah;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by joseph on 10/9/16.
 */
public class ClickInNoteHandler implements EventHandler<MouseEvent> {
    private CompositionPanel compositionPanel;
    private boolean metaDown;

    public ClickInNoteHandler (CompositionPanel compositionPanel){
        this.compositionPanel = compositionPanel;
    }

    public void handle(MouseEvent event) {
//        this.metaDown = event.isShortcutDown();
        NoteRectangle r = ((NoteRectangle)event.getSource());
        if(event.isShortcutDown()){
            if (r.isSelected()){
                r.setSelected(false);
            } else {
                r.setSelected(true);
            }
        } else {
            if (! r.isSelected()){
                this.compositionPanel.clearSelected();
                r.setSelected(true);
            }
        }



    }
}
