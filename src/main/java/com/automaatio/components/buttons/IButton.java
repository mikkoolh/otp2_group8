package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import javafx.scene.Node;

public interface IButton {
    Node create(Object object, ClickActions clickActions);
}
