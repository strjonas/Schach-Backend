package com.example.demo.Pieces;

import com.example.demo.Board;

public class Bishop extends Pieces {

    public Bishop(boolean black) {
        super(black);
    }

    @Override
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        return super.isMoveValid(posY, posX, newPosY, newPosX, board) && isBishopMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(posY, posX, newPosY, newPosX, board);
    }


    private boolean isBishopMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX - posX) == Math.abs(newPosY - posY);
    }

    @Override
    boolean isSomethingInTheWay(int posY, int posX, int newPosY, int newPosX, Board board) {
        int stepX = (newPosX - posX) / Math.abs(newPosX - posX);
        int stepY = (newPosY - posY) / Math.abs(newPosY - posY);
        posX += stepX;
        posY += stepY;
        while (posX != newPosX) {
            if (board.isEmpty(posY, posX)) {
                posX += stepX;
                posY += stepY;
            } else return false;
        }
        if (board.getChessBoard()[newPosY][newPosX] != null) {
            return board.getChessBoard()[newPosY][newPosX].getIsBlack() == !getIsBlack();
        }
        return true;
    }

    @Override
    public Character toChar() {
        return getIsBlack() ? 'b' : 'B';
    }


}
