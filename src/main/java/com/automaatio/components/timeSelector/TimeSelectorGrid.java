package com.automaatio.components.timeSelector;

import com.automaatio.utils.BundleLoader;
import com.dlsc.gemsfx.TimePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ResourceBundle;

/**
 * @autohr Matleena Kankaanpää
 *
 * The {@code TimeSelectorGrid} class provides functionality to create a 2x2 grid layout
 * containing two {@link TimePicker} components and their corresponding labels. This
 * component is useful for scenarios where a user needs to select both a start and an
 * end time.
 *
 * The grid layout arranges the time pickers and their labels in a user-friendly manner,
 * making it clear which picker is for the start time and which is for the end time.
 */
public class TimeSelectorGrid {

    /**
     * Creates a {@link GridPane} containing two {@link TimePicker} components and their
     * respective labels for start and end times.
     * <p>
     * The method takes two {@link TimePicker} instances for start and end times and
     * arranges them in a grid with labels indicating their purpose. The labels are
     * localized based on the user's locale.
     * </p>
     *
     * @param startTime The {@link TimePicker} for selecting the start time.
     * @param endTime The {@link TimePicker} for selecting the end time.
     * @return A {@link GridPane} with the configured time pickers and labels.
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
