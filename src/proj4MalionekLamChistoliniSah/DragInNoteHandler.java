/**
 * File: DragInNoteHandler.java
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by joseph on 10/9/16.
 */
public class DragInNoteHandler implements EventHandler<MouseEvent> {
    /**
     * The previous mouse
     */
    private double previousX;
    private double previousY;
    private boolean extendEventHappening;


    private CompositionPanel panelToEdit;
    private NoteRectangle sourceRectangle;

    public DragInNoteHandler(CompositionPanel panelToEdit, NoteRectangle sourceRectangle){
        this.sourceRectangle = sourceRectangle;
        this.panelToEdit = panelToEdit;
    }

    public void handle(MouseEvent event){

    }

    public void handleMousePressed(MouseEvent event) {
        if(event.getX()>=this.sourceRectangle.getX()+this.sourceRectangle.getWidth()-5){
            this.extendEventHappening = true;
        }
        else{
            this.extendEventHappening = false;
        }
        this.previousX = event.getX();
        this.previousY = event.getY();
        event.consume();
    }



    public void handleDragged(MouseEvent event) {
        if (!this.sourceRectangle.isSelected()){
            this.panelToEdit.clearSelected();
            this.sourceRectangle.setSelected(true);
        }
        else{
            if(this.extendEventHappening) {
                this.handleNoteExtend(event);
            }
            else{
                this.handleNoteTranslate(event);
            }
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

    public void handleMouseReleased(MouseEvent event) {
        ArrayList<NoteRectangle> selectedRectangles = this.panelToEdit.getSelectedRectangles();
        for(NoteRectangle rectangle: selectedRectangles){
            double newPitch = Math.floor((rectangle.getY() - 1) / 10) * 10 + 1;
            rectangle.setY(newPitch);
        }
    }
}



