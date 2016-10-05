/**
 * File: Composition.java
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import java.util.ArrayList;

/**
 * The central logic for creating and playing back a
 * composition.
 */
public class Composition {
    public static final int VOLUME = 127;
    public static final int CHANNEL = 0;
    public static final int TRACK_INDEX = 0;

    private double maxX = 0;
    private ArrayList<Note> composition = new ArrayList<>();
    private CompositionPanel compositionPanel;
    private MidiPlayer player = new MidiPlayer(100, 60);


    /**
     * Creates a note at the given x and y coordinates
     * and adds the note to the composition list.
     * Keeps track of the length of the composition.
     *
     * @param x mouse x location
     * @param y mouse y location
     * @return the created note object
     */
    public Note addNote(double x, double y)
    {
        this.maxX = (x > maxX) ? x + Note.NOTE_DURATION : this.maxX;

        int tick = (int) x;
        int pitch = this.yToPitch(y);

        Note note = new Note(pitch, tick);
        this.composition.add(note);

        return note;
    }

    /**
     * Converts the mouse y position to a pitch value.
     * @param y mouse y location
     * @return the int value of pitch
     */
    public int yToPitch(double y) {
        int ones = (int) y % 10;
        int yWithNoOnes = (int) (y - ones);
        int pitch = 128 - yWithNoOnes/10;

        return pitch;
    }

    /**
     * gets the composition list of notes.
     *
     * @return composition arraylist.
     */
    public ArrayList<Note> getComposition(){
        return this.composition;
    }

    /**
     * gets the length of the composition.
     *
     * @return the int value of the length of the composition.
     */
    public double getMaxX(){return this.maxX;}

    /**
     * Add Midi Events to the Sequencer.
     */
    public void buildSequence(){
        for (Note note: this.composition){
            this.player.addNote(note.getPitch(), VOLUME, note.getTick(),
                                Note.NOTE_DURATION, CHANNEL, TRACK_INDEX);
        }
    }

    /**
     * gets the player object.
     *
     * @return the player object
     */
    public MidiPlayer getPlayer(){
        return this.player;
    }

}
