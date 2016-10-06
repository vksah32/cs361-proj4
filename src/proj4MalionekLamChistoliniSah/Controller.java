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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Handles all user GUI interactions and coordinates with the MidiPlayer
 * and Composition.
 */
public class Controller {

    @FXML
    public CompositionPanel compositionPanel;
    @FXML public MenuBar menuBar;
    @FXML public Menu fileMenu;
    @FXML public Menu actionMenu;
    @FXML public ToggleGroup instrumentPanel;

    private Composition composition = new Composition();

    private Line line;
    private TranslateTransition transition;

    @FXML
    public String getInstrument()
    {
        RadioButton b = (RadioButton)instrumentPanel.getSelectedToggle();
        return (b.getText());
    }

    /**
     * Handles mouse click events, extracts x,y coordinates
     * relative to note, and creates a new note and adds it
     * to the composition and compositionPanel.
     *
     * @param event a mouse click event.
     */
    @FXML
    public void handleMouseClick(MouseEvent event)
    {
        double x = event.getX();
        double y = event.getY();

        String inst = getInstrument();

        Note note = this.composition.addNote(x, y, inst);
        this.compositionPanel.addNoteRectangle(note.getRectangle());
    }

    /**
     * Instantiates the line and transition fields
     * and begins the animation based on the length
     * of the composition.
     */
    public void beginAnimation(){
        this.line = new Line(0, 0, 0, 1280);
        this.line.setStroke(Color.RED);
        this.compositionPanel.getChildren().add(this.line);
        this.line.setStrokeWidth(1);
        this.transition = new TranslateTransition(new Duration(
                this.composition.getMaxX() * 10), this.line);
        this.transition.setToX(this.composition.getMaxX());
        this.transition.setInterpolator(Interpolator.LINEAR);
        this.transition.play();
        this.transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                compositionPanel.getChildren().remove(line);
            }
        });
        this.composition.buildSequence();
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
            this.stopComposition();
        }
        if(this.composition.getComposition().size() > 0){
            this.beginAnimation();
            this.composition.getPlayer().play();
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
        this.composition.getPlayer().stop();
        this.composition.getPlayer().clear();
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
