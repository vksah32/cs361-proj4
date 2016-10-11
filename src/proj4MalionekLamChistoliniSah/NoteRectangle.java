package proj4MalionekLamChistoliniSah;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by joseph on 10/9/16.
 */
public class NoteRectangle extends Rectangle {
    private int instrument;
    private boolean selected;

    public NoteRectangle(double x, double y, double width, double height, String instrument){
        super(x,y,width,height);
        this.setInstrument(instrument);
    }

    public void setInstrument(String instrument){
        this.setId(instrument);
        if (instrument.equals("Harpsichord")){
            this.instrument = 6;}
        else if (instrument.equals("Marimba")) {
            this.instrument = 12;
        }
        else if (instrument.equals("Organ")) {
            this.instrument = 19;
        }
        else if (instrument.equals("Accordion")){
            this.instrument = 21;
        }
        else if (instrument.equals("Guitar")) {
            this.instrument = 24;
        }
        else if (instrument.equals("Violin")) {
            this.instrument = 40;
        }
        else if (instrument.equals("French Horn")) {
            this.instrument = 60;
        }
        else{
            this.instrument = 0;
        }
    }


    public int getInstrument(){
        return this.instrument;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected){
        if(selected){
            this.setStrokeWidth(3);
            this.setStroke(Color.RED);
        }
        else{
            this.setStrokeWidth(1);
            this.setStroke(Color.BLACK);
        }
        this.selected = selected;
    }
}
