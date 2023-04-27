package com.example.demo.Pieces;

public class Queen extends Pieces{
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX){

        return true;
    }

    private boolean isQueenMovement(int posY, int posX, int newPosY, int newPosX){
        return (Math.abs(newPosX-posX) == Math.abs(newPosY-posY) )||
                ((posY == newPosY && posX != newPosX) || (posY != newPosY && posX == newPosX));
    }
}
