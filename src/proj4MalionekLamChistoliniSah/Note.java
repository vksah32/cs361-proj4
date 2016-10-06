
/**
 * File: Note.java
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A class holding pitch and ticks and the rectangle to be drawn.
 */
public class Note {
    public static final int NOTE_DURATION = 100;
    private int pitch;
    private int tick;
    private String inst;

    private Rectangle rectangle;

    /**
     * Makes a note.
     *
     * @param pitch int value for the note pitch
     * @param tick int value for the tick pitch
     */
    public Note(int pitch, int tick, String inst) {
        this.pitch = pitch;
        this.tick = tick;
        this.inst = inst;
        this.generateRectangle(inst);
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
     * gets the instrument name
     * @return instrument number
     */
    public int getInstrument()
    {
        if (this.inst.equals("Piano"))
            return 0;
        else if (this.inst.equals("Harpsicord"))
            return 6;
        else if (this.inst.equals("Marimba"))
            return 12;
        else if (this.inst.equals("Organ"))
            return 19;
        else if (this.inst.equals("Accordion"))
            return 21;
        else if (this.inst.equals("Guitar"))
            return 24;
        else if (this.inst.equals("Violin"))
            return 40;
        else
            return 60;
    }

    /**
     * Creates a rectangle object with the appropriate style,
     * coordinates and color.
     * @return the rectangle
     */
    public void generateRectangle(String inst) {
        Rectangle r = new Rectangle();
        r.setX(this.tick);
        r.setY(1280 - this.pitch * 10);
        r.setWidth(NOTE_DURATION);
        r.setHeight(10);
        if (inst.equals("Piano"))
            r.setFill(Color.GREY);
        else if (inst.equals("Harpsicord"))
            r.setFill(Color.GREEN);
        else if (inst.equals("Marimba"))
            r.setFill(Color.BLUE);
        else if (inst.equals("Organ"))
            r.setFill(Color.GOLD);
        else if (inst.equals("Accordion"))
            r.setFill(Color.PURPLE);
        else if (inst.equals("Guitar"))
            r.setFill(Color.DEEPSKYBLUE);
        else if (inst.equals("Violin"))
            r.setFill(Color.BLACK);
        else
            r.setFill(Color.SANDYBROWN);

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
