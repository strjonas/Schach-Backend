package com.example.demo.Pieces;

import com.example.demo.Board;

public class Pieces {
    private boolean isBlack;

    public Pieces(boolean black) {
        isBlack = black;
    }

    public boolean getIsBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }


    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        return newPosY < 8 && newPosX < 8 && !(newPosX == posX && newPosY == posY);

    }

    public Character toChar() {
        return null;
    }


}

