package ru.fildv.minesweeper.service;

public enum Box {
    ZERO, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8,
    BOMB, OPENED, CLOSED, FLAGED, BOMBED, NOBOMB;

    private Object image;

    public Box nextBox() {
        return Box.values()[this.ordinal() + 1];
    }

    public int getNumber() {
        return this.ordinal();
    }

    public Object getImage() {
        return image;
    }

    public void setImage(final Object image) {
        this.image = image;
    }
}
