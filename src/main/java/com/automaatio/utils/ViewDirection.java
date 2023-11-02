package com.automaatio.utils;

public enum ViewDirection {
    RTL("Right-to-Left"),
    LTR("Left-to-Right");

    private String name;

    ViewDirection(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
