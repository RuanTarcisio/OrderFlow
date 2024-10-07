package com.rtarcisio.usernotification.enums;

public enum TodoTagsEnum {
    REACT("React"),
    HTML("HTML"),
    CSS("CSS"),
    DOCKER("Docker"),
    SPRING("Spring"),
    JAVASCRIPT("JavaScript"),
    OTHERS("Others");

    private final String tag;

    TodoTagsEnum(String tag) {
        this.tag = tag;
    }

    String getTag() {
        return tag;
    }

}