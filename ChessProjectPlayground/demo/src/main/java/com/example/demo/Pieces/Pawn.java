package com.example.demo.Pieces;

import com.example.demo.Board;

public class Pawn extends Pieces {

    public Pawn(boolean black) {
        super(black);
    }

    @Override
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        return super.isMoveValid(posY, posX, newPosY, newPosX, board) && isPawnMovement(posY, posX, newPosY, newPosX, board) && isSomethingInTheWay(posY, posX, newPosY, newPosX, board);

    }

    private boolean isPawnMovement(int posY, int posX, int newPosY, int newPosX, Board board) {
        if (posX == newPosX) {
            return newPosY - posY == (isBlack ? -1 : 1) || (posY == (isBlack ? 6 : 1) && newPosY - posY == (isBlack ? -2 : 2));
        } else if (Math.abs(newPosX - posX) == 1) {
            return newPosY - posY == (isBlack ? -1 : 1);
        } else return false;
        /*if (isBlack) {
            if (posX == newPosX) {
                switch (posY) {
                    case 6 -> {
                        return 0 < posY - newPosY && posY - newPosY < 3;
                    }
                    case 5, 4, 3, 2, 1 -> {
                        return posY - newPosY == 1;
                    }
                }
            } else {
                return isSomethingInTheWay(posY, posX, newPosY, newPosX, board);


            }
        } else {
            if (newPosX == posX) {
                switch (posY) {
                    case 1 -> {
                        return -3 < posY - newPosY && posY - newPosY < 0;
                    }
                    case 2, 3, 4, 5, 6 -> {
                        return posY - newPosY == -1;
                    }
                }
            } else {
                return isSomethingInTheWay(posY, posX, newPosY, newPosX, board);
            }

        }
        return false;*/
    }

    @Override
    boolean isSomethingInTheWay(int posY, int posX, int newPosY, int newPosX, Board board) {
        if (posX == newPosX) {
            while (isBlack ? newPosY >= posY : newPosY <= posY) {
                if (board.isEmpty(posY, posX)) {
                    posY += ((newPosY - posY) / Math.abs(newPosY - posY));
                } else return false;
            }
            return true;
        } else {
            return (!board.isEmpty(newPosY, newPosX) && board.getChessBoard()[newPosY][newPosX].getIsBlack() != isBlack) || (newPosX == board.getEnPassant().x && posY == board.getEnPassant().y && board.getChessBoard()[posY][newPosX].getIsBlack() != isBlack);
        }
        /*int stepY;
        if (getIsBlack()) {
            stepY = -1;
        } else {
            stepY = 1;
        }
        if (newPosX == posX) {

            while (posY <= newPosY) {
                if (board.isEmpty(newPosY, newPosX)) {
                    posY += stepY;
                } else {
                    return true;
                }
            }
        } else if ((newPosY - posY) * stepY == 1 && Math.abs(newPosX - posX) == 1) {
            return !board.isEmpty(newPosY, newPosX) && board.getChessBoard()[newPosY][newPosX].getIsBlack() != isBlack;
        }
        return true;*/
    }

    @Override
    public Character toChar() {
        return getIsBlack() ? 'p' : 'P';
    }


}