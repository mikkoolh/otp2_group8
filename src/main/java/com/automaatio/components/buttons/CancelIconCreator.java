package com.automaatio.components.buttons;

import javafx.scene.control.Button;

public class CancelIconCreator extends IconCreator {
    public Button create() {
        return super.create("images/cancel.png", "Cancel");
    }
}
