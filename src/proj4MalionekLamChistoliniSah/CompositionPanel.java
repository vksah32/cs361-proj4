
/**
 * File: CompositionPanel.java
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Extends the Pane class and is the parent node of
 * all of composition notes and lines.
 */
public class CompositionPanel extends Pane {

    /**
     * Constructs the Panel and draws the appropriate lines.
     */
    public CompositionPanel()
    {
        this.drawLines();
    }

    /**
     * Draws 128 lines with the specified spacing and colors.
     */
    private void drawLines()
    {
        for(int i = 0; i < 127; i++)
        {
            Line line = new Line(0, i*10+1, 2000,i*10+1);
            line.setStrokeWidth(1);
            line.setStroke(Color.LIGHTGRAY);

            this.getChildren().add(line);
        }
    }

    /**
     * Adds a rectangle for the note.
     *
     * @param note a rectangle from the Note object.
     */
    public void addNoteRectangle(Rectangle note) {
        this.getChildren().add(note);
    }
}
