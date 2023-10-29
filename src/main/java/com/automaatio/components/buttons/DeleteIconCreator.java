package com.automaatio.components.buttons;

import javafx.scene.control.Button;

public class DeleteIconCreator extends IconCreator {
    public Button create() {
        return super.create("images/delete.png", resourceBundle.getString("deleteBtnTxt"));
    }
}
