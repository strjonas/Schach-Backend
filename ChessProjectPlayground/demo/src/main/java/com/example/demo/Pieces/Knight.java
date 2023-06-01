package com.example.demo.Pieces;
import com.example.demo.Board;

public class Knight extends Pieces {
    public Knight(boolean black) {
        super(black);
    }

    @Override
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        return super.isMoveValid(posY, posX, newPosY, newPosX, board) && isKnightMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    public boolean isKnightMovement(int posY, int posX, int newPosY, int newPosX) {
        return (Math.abs(posY - newPosY) == 1 && Math.abs(posX - newPosX) == 2) || (Math.abs(posY - newPosY) == 2 && Math.abs(posX - newPosX) == 1);
    }

    private boolean isSomethingInTheWay( int newPosY, int newPosX, Board board) {
        if(!board.isEmpty(newPosY,newPosX)){
            return board.getChessBoard()[newPosY][newPosX].getIsBlack() == !isBlack;
        }
        return  true;
    }

    @Override
    public Character toChar() {
        return getIsBlack() ? 'n' : 'N';
    }
}
