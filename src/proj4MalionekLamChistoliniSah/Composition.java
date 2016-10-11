/**
 * File: Composition.java
 * @author Victoria Chistolini
 * @author Tiffany Lam
 * @author Joseph Malionek
 * @author Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

/**
 * The central logic for creating and playing back a composition.
 */
public class Composition {

    /** sets the volume */
    public static final int VOLUME = 127;

    /** sets the channel */
    public static final int CHANNEL = 0;

    /** sets the track index */
    public static final int TRACK_INDEX = 0;

    /** creates the MidiPlayer */
    private MidiPlayer player = new MidiPlayer(100, 60);


    /**
     * This will translate a rectangle into a midi event
     * Add Midi Events to the Sequencer.
     * @param startTick
     * @param duration
     * @param pitch
     * @param instrument
     */
    public void addNote(int startTick, int duration, int pitch, int instrument) {
        this.player.addNote(pitch, this.VOLUME, startTick,
                duration, this.CHANNEL, this.TRACK_INDEX,
                instrument);
    }

    /**
     * plays the composition
     */
    public void play() {
        this.player.play();
    }

    /**
     * stops the composition from playing
     */
    public void stop() {
        this.player.clear();
        this.player.stop();
    }


}
