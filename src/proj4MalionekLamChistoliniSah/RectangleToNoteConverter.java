package proj4MalionekLamChistoliniSah;

import java.util.ArrayList;

/**
 * Created by joseph on 10/10/16.
 */
public class RectangleToNoteConverter {

    public static Note convertRectangle(NoteRectangle rectangle){
        int duration = (int)rectangle.getWidth();
        int startTick = (int)rectangle.getWidth();
        int pitch = (127-(int)rectangle.getY()/10);
        Note note = new Note(startTick,duration,pitch);
        note.setInstrument(rectangle.getInstrument());
        return note;
    }

    public static ArrayList<Note> convertRectangles(ArrayList<NoteRectangle> rectangles){
        ArrayList<Note> notes = new ArrayList<>();
        for(NoteRectangle rectangle: rectangles){
            notes.add(RectangleToNoteConverter.convertRectangle(rectangle));
        }
        return notes;
    }
}
