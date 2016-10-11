/**
 * File: Composition.java
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * The central logic for creating and playing back a
 * composition.
 */
public class Composition {
    public static final int VOLUME = 127;
    public static final int CHANNEL = 0;
    public static final int TRACK_INDEX = 0;


    private MidiPlayer player = new MidiPlayer(100, 60);


    /**
     * This will translate a rectangle into a midi event
     * Add Midi Events to the Sequencer.
     */
    public void addNote(int startTick, int duration, int pitch, int instrument) {
        this.player.addNote(pitch, this.VOLUME, startTick,
                duration, this.CHANNEL, this.TRACK_INDEX,
                instrument);

    }

    public void play() {
        this.player.play();
    }

    public void stop() {
        this.player.clear();
        this.player.stop();
    }


}
