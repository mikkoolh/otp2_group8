package com.automaatio.components.timeSelector;

import com.dlsc.gemsfx.TimePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ResourceBundle;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 * 2x2 grid component containing two time pickers and labels
 */

public class TimeSelectorGrid {
    private final ResourceBundle resources;
    public TimeSelectorGrid(ResourceBundle resources) {
        this.resources = resources;
    }

    public GridPane create(TimePicker startTime, TimePicker endTime) {
        GridPane grid = new GridPane();
        grid.add(new Label(resources.getString("startingFromTxt")), 0, 0);
        grid.add(new Label(resources.getString("endingAtTxt")), 0, 1);
        grid.add(startTime, 1, 0);
        grid.add(endTime, 1, 1);
        grid.setHgap(40);
        grid.setVgap(10);
        return grid;
    }
}
