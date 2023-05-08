package com.example.demo.Pieces;
import com.example.demo.Board;

public class Queen extends Pieces {
    public Queen(boolean black) {
        super(black);
    }
    @Override
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {

        return super.isMoveValid(posY, posX, newPosY, newPosX,board) && isQueenMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(posY,posX,newPosY,newPosX,board);
    }

    private boolean isQueenMovement(int posY, int posX, int newPosY, int newPosX) {
        return (Math.abs(newPosX - posX) == Math.abs(newPosY - posY)) ||
                ((posY == newPosY && posX != newPosX) || (posY != newPosY && posX == newPosX));
    }

    @Override
    public Character toChar() {
        return getIsBlack() ? 'q' : 'Q';
    }
}
