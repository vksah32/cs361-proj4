/**
 * File: DragInPanelHandler.java
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Created by joseph on 10/9/16.
 */
public class DragInPanelHandler {

    private CompositionPanel panelToEdit;
    private Rectangle selectionRectangle;
    private double startX;
    private double startY;
    private boolean metaDown;
    

    public DragInPanelHandler(CompositionPanel panelToEdit){
        this.panelToEdit = panelToEdit;
        this.selectionRectangle = new Rectangle();
        this.selectionRectangle.setVisible(false);
        this.selectionRectangle.setId("selectionRectangle");
        this.panelToEdit.getChildren().add(this.selectionRectangle);
    }

    public void handleMousePressed(MouseEvent event){
        this.startX = event.getX();
        this.startY = event.getY();
        this.selectionRectangle.setX(this.startX);
        this.selectionRectangle.setY(this.startY);
        this.metaDown = event.isShortcutDown();
    }

    public void handleDragged(MouseEvent event) {
        this.selectionRectangle.setVisible(true);
        if(!this.metaDown){
            this.panelToEdit.clearSelected();
        }
        double leftX;
        double width;
        if(event.getX()<this.startX){
            leftX = event.getX();
            width = this.startX-event.getX();
        }
        else{
            leftX = this.startX;
            width = event.getX()-this.startX;
        }
        double lowestY;
        double height;
        if(event.getY()<this.startY){
            lowestY = event.getY();
            height = this.startY-event.getY();
        }
        else{
            lowestY = this.startY;
            height = event.getY()-this.startY;
        }

        this.selectionRectangle.setWidth(width);
        this.selectionRectangle.setHeight(height);
        this.selectionRectangle.setX(leftX);
        this.selectionRectangle.setY(lowestY);
        ArrayList<NoteRectangle> rectangles = this.panelToEdit.rectangles;
        for(NoteRectangle rectangle: rectangles){
            if(rectangle.intersects(leftX,lowestY,width,height)){
                rectangle.setSelected(true);
            }
        }
    }

    public void handleDragReleased(MouseEvent event) {
        this.selectionRectangle.setVisible(false);
    }
}
