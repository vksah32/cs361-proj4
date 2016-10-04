
/**
 * File: Note.java
 * Names: Alex Skrynnyk, Mike Remondi, Vivek Sah, Edward Zhou
 * Class: CS361
 * Project: 3
 * Date: October 2, 2016
 */

package proj3RemondiSkrynnykSahZhou;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class holding pitch and ticks and the rectangle to be drawn.
 */
public class Note {
    public static final int NOTE_DURATION = 100;
    private int pitch;
    private int tick;

    private Rectangle rectangle;

    /**
     * Makes a note.
     *
     * @param pitch int value for the note pitch
     * @param tick int value for the tick pitch
     */
    public Note(int pitch, int tick) {
        this.pitch = pitch;
        this.tick = tick;
        this.generateRectangle();
    }

    /**
     * gets the pitch
     * @return pitch
     */
    public int getPitch() { return this.pitch; }

    /**
     * gets the ticks
     * @return ticks
     */
    public int getTick() { return this.tick; }

    /**
     * Creates a rectangle object with the appropriate style
     * and coordinates.
     * @return the rectangle
     */
    public void generateRectangle() {
        Rectangle r = new Rectangle();
        r.setX(this.tick);
        r.setY(1280 - this.pitch * 10);
        r.setWidth(NOTE_DURATION);
        r.setHeight(10);
        r.setFill(Color.BLUE);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(1);
        this.rectangle = r;
    }

    /**
     * gets the rectangle
     * @return rectangle
     */
    public Rectangle getRectangle() { return this.rectangle; }
}
