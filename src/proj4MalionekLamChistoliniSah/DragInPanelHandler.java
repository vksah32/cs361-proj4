package proj4MalionekLamChistoliniSah;

import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by joseph on 10/9/16.
 */
public class DragInPanelHandler {
    private CompositionPanel panelToEdit;
    private double startDragX;
    private double startDragY;


    public DragInPanelHandler(CompositionPanel panelToEdit){
        this.panelToEdit = panelToEdit;

    }

    public void handleDragDetected(MouseEvent event){
        this.startDragX = event.getX();
        this.startDragY = event.getY();
    }

    public void handleDrag(MouseDragEvent event){

    }
}
