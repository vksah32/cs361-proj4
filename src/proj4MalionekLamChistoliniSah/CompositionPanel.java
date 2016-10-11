/**
 *
 * File: CompositionPanel.java
 * @author Victoria Chistolini
 * @author Tiffany Lam
 * @author Joseph Malionek
 * @author Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import java.util.ArrayList;

/**
 * Extends the Pane class and is the parent node of
 * all of composition notes and lines.
 */
public class CompositionPanel extends Pane {

    ArrayList<NoteRectangle> rectangles;

    /**
     * Constructs the Panel and draws the appropriate lines.
     */
    public CompositionPanel()
    {
        this.drawLines();
        rectangles = new ArrayList<>();
    }


    public void addRectangle(NoteRectangle rectangle, boolean selected){
        this.getChildren().add(rectangle);
        rectangles.add(rectangle);
        if(selected){
            rectangle.setSelected(true);
        }
    }

    /**
     * Draws 127 lines with the specified spacing and colors.
     */
    private void drawLines()
    {
        for(int i = 1; i < 128; i++)
        {
            Line line = new Line(0, i*10+1, 2000,i*10+1);
            line.setId("lines");
            this.getChildren().add(line);
        }
    }

    public ArrayList<NoteRectangle> getRectangles(){
        return this.rectangles;
    }

    public ArrayList<NoteRectangle> getSelectedRectangles(){
        ArrayList<NoteRectangle> selectedList = new ArrayList<>();
        for(NoteRectangle rectangle:this.rectangles){
            if(rectangle.isSelected()){
                selectedList.add(rectangle);
            }
        }
        return selectedList;
    }

    /**
     * unselects the rectangles
     */
    public void clearSelected(){
        for(NoteRectangle rectangle:this.rectangles){
            if(rectangle.isSelected()){
                rectangle.setSelected(false);
            }
        }
    }

    public boolean inARectangle(double x, double y){
        for(NoteRectangle rectangle: this.rectangles){
            if(rectangle.contains(x,y)){
                return true;
            }
        }
        return false;
    }

    /**
     * removes the selected rectangles
     */
    public void deleteSelectedNotes(){

        ArrayList<NoteRectangle> rectangles = this.getSelectedRectangles();
        for (NoteRectangle r: rectangles){ //first remove from the panel
            this.getChildren().remove(r);
        }
        this.rectangles.removeAll(rectangles); //then remove from Arraylist of rectangles
    }

    public void selectAllNotes(){
        for (NoteRectangle r: this.rectangles){
            r.setSelected(true);
        }
    }

}
