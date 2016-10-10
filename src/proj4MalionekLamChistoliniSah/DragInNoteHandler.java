package proj4MalionekLamChistoliniSah;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by joseph on 10/9/16.
 */
public class DragInNoteHandler implements EventHandler<MouseEvent> {
    private double startX;
    private double startY;
    private double startWidth;
    private double previousX;
    private double previousY;


    private CompositionPanel panelToEdit;
    private NoteRectangle sourceRectangle;

    public DragInNoteHandler(CompositionPanel panelToEdit, NoteRectangle sourceRectangle){
        this.sourceRectangle = sourceRectangle;
        this.panelToEdit = panelToEdit;
    }

    public void handle(MouseEvent event){

    }

    public void handleMousePressed(MouseEvent event) {
        this.startX = event.getX();
        this.startY = event.getY();
        this.startWidth = this.sourceRectangle.getWidth();
        this.previousX = event.getX();
        this.previousY = event.getY();

        event.consume();
    }



    public void handleDragged(MouseEvent event) {
        if (!this.sourceRectangle.isSelected()){
            this.panelToEdit.clearSelected();
            this.sourceRectangle.setSelected(true);
        }
        if(this.startX>=this.sourceRectangle.getX()+this.startWidth-5){

            this.handleNoteExtend(event);
        }
        else{
            this.handleNoteTranslate(event);
        }

        event.consume();
    }

    private void handleNoteTranslate(MouseEvent event){
        ArrayList<NoteRectangle> selectedRectangles = this.panelToEdit.getSelectedRectangles();
        double deltaX = event.getX()-this.previousX;
        double deltaY = event.getY()-this.previousY;
        for(NoteRectangle rectangle:selectedRectangles){
            rectangle.setX(rectangle.getX()+deltaX);
            rectangle.setY(rectangle.getY()+deltaY);
        }
        this.previousX=event.getX();
        this.previousY=event.getY();
    }

    private void handleNoteExtend(MouseEvent event){
        ArrayList<NoteRectangle> selectedRectangles = this.panelToEdit.getSelectedRectangles();
        double deltaX = event.getX()-this.sourceRectangle.getWidth()-this.sourceRectangle.getX();
        for(NoteRectangle rectangle: selectedRectangles){
            double width = rectangle.getWidth()+deltaX;
            //makes sure the width is at least 5
            width = Math.max(5,width);
            //makes sure the note does not extend past the end of the player
            //width = Math.min(width, this.panelToEdit.getWidth()-rectangle.getX());
            rectangle.setWidth(width);
        }

    }



    public void handleDragReleased(MouseEvent event) {
    }
}



