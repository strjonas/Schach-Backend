package com.example.demo.Pieces;

import com.example.demo.Board;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board){
        return super.isMoveValid(posY, posX, newPosY, newPosX) && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX-posX) < 2 && Math.abs(newPosY-posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board.isEmpty(newPosY,newPosX);
    }

    private boolean isCastleValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
    }


}