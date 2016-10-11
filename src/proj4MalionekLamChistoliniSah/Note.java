package proj4MalionekLamChistoliniSah;

/**
 * Created by joseph on 10/10/16.
 */
public class Note {
    private int instrument;
    private int duration;
    private int pitch;
    private int startTick;
    private final int TRACK=0;
    private final int CHANNEL=0;

    public Note(int startTick, int duration, int pitch){
        this.startTick = startTick;
        this.duration = duration;
        this.pitch = pitch;
    }

    /**
     * Returns this notes instrument
     * @return this notes instrument
     */
    public int getInstrument() {
        return instrument;
    }

    /**
     * Sets the instrument this note will be played with
     * @param instrument the instrument that this note will be played with
     */
    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    /**
     * this notes duration
     * @return the duration of the note
     */
    public int getDuration() {
        return duration;
    }

    /**
     * sets how long this note will play for
     * @param duration how long this note will play
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public int getStartTick() {
        return startTick;
    }

    public void setStartTick(int startTick) {
        this.startTick = startTick;
    }
}
