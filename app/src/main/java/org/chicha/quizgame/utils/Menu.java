package org.chicha.quizgame.utils;

public enum Menu {
    HOME(1),
    PREGAME(2),
    STATS(3);

    private final int id;

    Menu(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
