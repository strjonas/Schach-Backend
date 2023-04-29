package com.example.demo.Pieces;

public class Rook extends Pieces{
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX){
        isRookMovement( posY, posX,  newPosY,  newPosX);


        return true;
    }
    public boolean isRookMovement(int posY, int posX, int newPosY, int newPosX){
        return (posY == newPosY && posX != newPosX) || (posY != newPosY && posX == newPosX);
    }
}
