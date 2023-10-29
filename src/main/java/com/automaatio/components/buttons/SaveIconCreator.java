package com.automaatio.components.buttons;

import javafx.scene.control.Button;

import java.util.Locale;
import java.util.ResourceBundle;

public class SaveIconCreator extends IconCreator {
    public Button create() {
        return super.create("images/save.png", resourceBundle.getString("saveBtnTxt"));
    }
}
