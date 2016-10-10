/**
 * Names: Victoria Chistolini, Tiffany Lam, Joseph Malionek, Vivek Sah
 * Class: CS361
 * Project: 4
 * Date: October 11, 2016
 */

package proj4MalionekLamChistoliniSah;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Handles all user GUI interactions and coordinates with the MidiPlayer
 * and Composition.
 */
public class Controller {

    @FXML public CompositionPanel compositionPanel;
    @FXML public ToggleGroup instrumentPanel;

    private Composition composition;

    private ClickInPanelHandler clickInPanelHandler;
    private ClickInNoteHandler clickInNoteHandler;
    private DragInNoteHandler dragInNoteHandler;
    private DragInPanelHandler dragInPanelHandler;

    private Line line;
    private TranslateTransition transition;

    private boolean dragStartedInPanel;
    private boolean isPlaying;



    @FXML
    public void initialize(){
        this.composition = new Composition();
        this.clickInPanelHandler = new ClickInPanelHandler(this.compositionPanel);
        this.clickInNoteHandler = new ClickInNoteHandler(this.compositionPanel);
        this.dragInNoteHandler = new DragInNoteHandler(this.compositionPanel);
        this.dragInPanelHandler = new DragInPanelHandler(this.compositionPanel);
        //this.compositionPanel.addEventFilter(MouseEvent.DRAG_DETECTED, this::handleDragDetected);
        //this.compositionPanel.addEventFilter(MouseEvent.MOUSE_DRAGGED, this::handleDragged);
        //this.compositionPanel.addEventFilter(MouseEvent.MOUSE_RELEASED, this::handleDragReleased);
    }


    /**
     * Gets the name of the instrument from the selected RadioButton
     * @return instrument name
     */
    @FXML
    public String getInstrument()
    {
        RadioButton b = (RadioButton)instrumentPanel.getSelectedToggle();
        return (b.getText());
    }

    /**
     * Handles mouse click events, extracts x,y coordinates
     * relative to note, gets the name of the instrument, and creates a new
     * note and adds it to the composition and compositionPanel.
     *
     * @param event a mouse click event.
     */
    @FXML
    public void handleMouseClick(MouseEvent event)
    {
        if(event.isStillSincePress()) { //differentiate from drag and drop
            if (isPlaying) {
                this.stopComposition();
            }
            else {
                this.clickInPanelHandler.handle(event,this.getInstrument());
            }
        }
    }

    @FXML
    public void handleMousePressed(MouseEvent event){
        if(this.compositionPanel.inARectangle(event.getX(),event.getY())) {
            this.dragInNoteHandler.handleMousePressed(event);
            this.dragStartedInPanel = false;
            System.out.println("DO WE GO HERE");
        }
        else{
            this.dragInPanelHandler.handleMousePressed(event);
            this.dragStartedInPanel = true;
        }
    }

    @FXML
    public void handleDragged(MouseEvent event){
        if(this.dragStartedInPanel){
            dragInPanelHandler.handleDragOver(event);
        }

    }

    @FXML
    public void handleDragReleased(MouseEvent event){

        if(this.dragStartedInPanel){
            dragInPanelHandler.handleDragReleased(event);
        }
        else{
            dragInNoteHandler.handleDragReleased(event);
        }
    }

    /**
     * Instantiates the line and transition fields
     * and begins the animation based on the length
     * of the composition.
     */
    public void beginAnimation(double maxX){
        this.line = new Line(0, 0, 0, 1280);
        this.line.setStroke(Color.RED);
        this.compositionPanel.getChildren().add(this.line);
        this.line.setStrokeWidth(1);
        this.transition = new TranslateTransition(new Duration(maxX * 10), this.line);
        this.transition.setToX(maxX);
        this.transition.setInterpolator(Interpolator.LINEAR);
        this.transition.play();
        this.transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                compositionPanel.getChildren().remove(line);
            }
        });
        //this.composition.buildSequence();
    }

    /**
     * Stops the animation and removes the line from the composition panel.
     */
    public void stopAnimation(){
        this.transition.stop();
        this.compositionPanel.getChildren().remove(line);
    }

    /**
     * Plays the composition and initiates the animation.
     * Stops the current animation and plays a new one if
     * one already exists.
     */
    @FXML
    public void playComposition() {
        this.isPlaying = true;

        if (this.transition != null) {
            this.composition.stop();
        }

        if(this.compositionPanel.getRectangles().size() > 0){
            double maxX = 0;
            ArrayList<NoteRectangle> rectangles = compositionPanel.getRectangles();
            for(NoteRectangle rectangle: rectangles){
                maxX = Math.max(maxX,rectangle.getX()+rectangle.getWidth());
                int startTick = (int)rectangle.getX();
                int pitch = 128 - (int)rectangle.getY()/10;
                int duration = (int)rectangle.getWidth();
                int instrument = rectangle.getInstrument();
                this.composition.addNote(startTick,duration,pitch,instrument);
            }
            this.beginAnimation(maxX);
            this.composition.play();
        }
    }

    /**
     * Stops and clears the composition and destroys the animation
     * if there is one.
     */
    @FXML
    public void stopComposition()
    {
        this.isPlaying = false;

        if (this.transition != null) {
            this.stopAnimation();
        }
        this.composition.stop();
    }

    /**
     * Ensures that all processes are killed on the
     * destruction of the window.
     */
    @FXML
    public void cleanUpOnExit()
    {
        Platform.exit();
        System.exit(0);
    }

    public void deleteSelectedNotes(){
        this.compositionPanel.deleteSelectedNotes();

    }

    public void selectAllNotes(){
        this.compositionPanel.selectAllNotes();
    }

}
