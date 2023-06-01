package com.example.demo.Pieces;

import com.example.demo.Board;

public class Rook extends Pieces {

    public Rook(boolean black) {
        super(black);
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

    @Override
    boolean isSomethingInTheWay(int posY, int posX, int newPosY, int newPosX, Board board) {
        int stepX = (newPosX - posX) / (newPosX != posX ? Math.abs(newPosX - posX) : 1);
        int stepY = (newPosY - posY) / (newPosY != posY ? Math.abs(newPosY - posY) : 1);
        posX  += stepX;
        posY += stepY;
        while (posX != newPosX && posY != newPosY) {
            if (board.isEmpty(posY,posX)) {
                posX += stepX;
                posY += stepY;
            } else return false;
        }
        return board.isEmpty(newPosY, newPosX) || board.getChessBoard()[newPosY][newPosX].getIsBlack() == !getIsBlack();
    }

    @Override
    public Character toChar() {
        return getIsBlack() ? 'r' : 'R';
    }
}
