package com.example.demo.Pieces;

public class Pawn extends Pieces {
   /*
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX) {
        isPawnMovement(posY, posX, newPosY, newPosX);
        return true;
    }

    private boolean isPawnMovement(int posY, int posX, int newPosY, int newPosX) {
        if (getIsBlack()) {
            if (posX == newPosX) {
                switch (posY) {
                    case 6 -> {
                        return 0 > posY - newPosY && posY - newPosY < 3;
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
                        return -3 > posY - newPosY && posY - newPosY < 0;
                    }
                    case 2, 3, 4, 5, 6 -> {
                        return posY - newPosY == -1;
                    }
                }
            } else {
                return isSomethingInTheWay(posY, posX, newPosY, newPosX, board);
            }

        }
        return false;
    }

    private boolean isSomethingInTheWay(int posY, int posX, int newPosY, int newPosX, Board board) {
        int stepY;
        if(getIsBlack()){
            stepY = -1;
        } else {stepY = 1;}
        if (newPosX == posX) {

            while (posY != newPosY) {
                if (board[posY][posX].isEmpty()) {
                    posY += stepY;
                }else{
                    return false;
                }
            }
        }
        else if((newPosY-posY)*stepY == 1 && Math.abs(newPosX-posX) == 1){
            return !board[posY][posX].isEmpty();
        }
        return false;
    }

    */
}