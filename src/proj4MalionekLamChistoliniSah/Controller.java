


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
    private DragInPanelHandler dragInPanelHandler;

    private Line line;
    private TranslateTransition transition;




    @FXML
    public void initialize(){
        this.composition = new Composition();
        this.clickInPanelHandler = new ClickInPanelHandler(this.compositionPanel);
        this.clickInNoteHandler = new ClickInNoteHandler(this.compositionPanel);
        this.dragInPanelHandler = new DragInPanelHandler(this.compositionPanel);
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
            if(event.getSource() == this.compositionPanel) {
                this.clickInPanelHandler.handle(event,this.getInstrument());
            }
            else if (event.getSource() instanceof NoteRectangle){
                this.clickInNoteHandler.handle(event);
            }
        }
    }


    @FXML
    public void handleMousePressed(MouseEvent event){
        this.dragInPanelHandler.handleMousePressed(event);
    }

    @FXML
    public void handleDragged(MouseEvent event){
        dragInPanelHandler.handleDragged(event);
    }

    @FXML
    public void handleDragReleased(MouseEvent event){
        dragInPanelHandler.handleDragReleased(event);
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
        if (this.transition != null) {
            this.composition.stop();
        }

        if(this.compositionPanel.getRectangles().size() > 0){
            double maxX = 0;
            ArrayList<NoteRectangle> rectangles = compositionPanel.getRectangles();
            for(NoteRectangle rectangle: rectangles){
                maxX = Math.max(maxX,rectangle.getX()+rectangle.getWidth());
                int startTick = (int)rectangle.getX();
                int pitch = (int)rectangle.getY()/10;
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

}
