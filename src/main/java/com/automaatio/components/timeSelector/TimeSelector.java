package com.automaatio.components.timeSelector;

import com.dlsc.gemsfx.TimePicker;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * The TimeSelector class is used to create a TimePicker component
 * from the GemsFX library.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class TimeSelector {
    private final TimePicker timePicker;

    /**
     * Class constructor
     */
    public TimeSelector() {
        this.timePicker = new TimePicker();
    }

    /**
     * Creates a TimePicker component
     * @return      A TimePicker component
     */
    public TimePicker getTimePicker() {
        CheckBox rollOverBox = new CheckBox("Rollover");
        rollOverBox.selectedProperty().bindBidirectional(timePicker.rolloverProperty());

        CheckBox linkFieldsBox = new CheckBox("Link Fields");
        linkFieldsBox.selectedProperty().bindBidirectional(timePicker.linkingFieldsProperty());

        CheckBox fullWidth = new CheckBox("Full Width");
        fullWidth.selectedProperty().addListener(it -> {
            if (fullWidth.isSelected()) {
                timePicker.setMaxWidth(Double.MAX_VALUE);
            } else {
                timePicker.setMaxWidth(Region.USE_PREF_SIZE);
            }
        });

        CheckBox showPopupButtonBox = new CheckBox("Show Button");
        showPopupButtonBox.selectedProperty().bindBidirectional(timePicker.showPopupTriggerButtonProperty());

        Button showOrHidePopupButton = new Button("Show Popup");
        showOrHidePopupButton.setOnAction(evt -> timePicker.show());

        Label valueLabel = new Label();
        valueLabel.textProperty().bind(Bindings.createStringBinding(() -> {
            LocalTime time = timePicker.getTime();
            if (time != null) {
                return "Time: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(time) + " (adjusted: " + (timePicker.isAdjusted() ? "yes" : "no") + ")";
            }
            return "empty";
        }, timePicker.timeProperty(), timePicker.adjustedProperty()));

        VBox box0 = new VBox(20, timePicker, valueLabel);
        VBox box = new VBox(box0);
        box.setFillWidth(true);
        return timePicker;
    }
}
