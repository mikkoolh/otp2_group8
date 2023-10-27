package com.automaatio.components;

import javafx.scene.control.*;
import javafx.scene.text.Font;
import static javafx.scene.text.FontWeight.BOLD;

public class WeekdayLabel {
    private final Label label;
    public WeekdayLabel(String s) {
        label = new Label(s.substring(0, 3).toLowerCase());
        label.setFont(Font.font("Andale Mono", BOLD, 24));
    }

    public Label create() {
        return label;
    }
}