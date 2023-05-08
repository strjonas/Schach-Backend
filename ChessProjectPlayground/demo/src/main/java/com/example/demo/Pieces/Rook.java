package com.example.demo.Pieces;

import java.util.Scanner;

public class Rook extends Pieces {

    private boolean hasMoved;

    public Rook(boolean black) {
        super(black);
        hasMoved = false;
    }

    public static void main(String[] args) {

    }

    @Override
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        return super.isMoveValid(posY, posX, newPosY, newPosX, board) && isRookMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(posY,posX,newPosY,newPosX,board);
    }

    private boolean isRookMovement(int posY, int posX, int newPosY, int newPosX) {
        return (posY == newPosY && posX != newPosX) || (posY != newPosY && posX == newPosX);
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }



    @Override
    public Character toChar() {
        return getIsBlack() ? 'r' : 'R';
    }
}
