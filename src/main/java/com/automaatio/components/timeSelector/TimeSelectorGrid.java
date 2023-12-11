package com.automaatio.components.timeSelector;

import com.automaatio.utils.BundleLoader;
import com.dlsc.gemsfx.TimePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ResourceBundle;

/**
 * The TimeSelectorGrid class is used to create a grid component
 * including two TimePickers for selecting the start and end time
 * of an event. The TimePicker component has been imported from the GemsFX library.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class TimeSelectorGrid {

    /**
     * Creates a GridPane with two time pickers and two corresponding labels.
     *
     * @param startTime     The TimePicker for selecting the start time
     * @param endTime       The TimePicker for selecting the end time
     * @return              A GridPane with two time pickers and two corresponding labels
     */
    public GridPane create(TimePicker startTime, TimePicker endTime) {
        BundleLoader bundleLoader = new BundleLoader();
        ResourceBundle resourceBundle = bundleLoader.loadResourceByUsersLocale();
        GridPane grid = new GridPane();
        grid.add(new Label(resourceBundle.getString("startingFromTxt")), 0, 0);
        grid.add(new Label(resourceBundle.getString("endingAtTxt")), 0, 1);
        startTime.getStyleClass().add("timepicker-start");
        endTime.getStyleClass().add("timepicker-end");
        grid.add(startTime, 1, 0);
        grid.add(endTime, 1, 1);
        grid.setHgap(40);
        grid.setVgap(10);
        return grid;
    }
}
