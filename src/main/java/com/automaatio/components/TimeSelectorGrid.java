package com.automaatio.components;

import com.dlsc.gemsfx.TimePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 * 2x2 grid component containing two time pickers and labels
 */

public class TimeSelectorGrid {
    public GridPane create(TimePicker startTime, TimePicker endTime) {
        GridPane grid = new GridPane();
        grid.add(new Label("From"), 0, 0);
        grid.add(new Label("To"), 0, 1);
        grid.add(startTime, 1, 0);
        grid.add(endTime, 1, 1);
        grid.setHgap(40);
        grid.setVgap(10);
        return grid;
    }
}
