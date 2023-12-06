package com.automaatio.controller.mainpage.menu;

/**
 * The Menu interface defines the contract for implementing menus in the HomeAutomation application's main page.
 * The show() method is responsible for showing the menu.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public interface Menu {
    /**
     * Displays specific menu.
     * Implementing classes define the behavior for showing the menu.
     */
    void show();
}
