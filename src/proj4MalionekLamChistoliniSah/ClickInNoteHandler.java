package proj4MalionekLamChistoliniSah;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by joseph on 10/9/16.
 */
public class ClickInNoteHandler implements EventHandler<MouseEvent> {
    /** The compositionPanel object that this handler modifies*/
    private CompositionPanel compositionPanel;

    /**Creates a new ClickInNoteHandler
     *
     * @param compositionPanel
     */
    public ClickInNoteHandler(CompositionPanel compositionPanel) {

     this.compositionPanel = compositionPanel;
    }

    public void handle(MouseEvent event) {
        NoteRectangle r = ((NoteRectangle) event.getSource());
        if (event.isShortcutDown()) {
            if (r.isSelected()) {
                r.setSelected(false);
            } else {
                r.setSelected(true);
            }
        } else {
            if (!r.isSelected()) {
                this.compositionPanel.clearSelected();
                r.setSelected(true);
            }
        }


    }
}
