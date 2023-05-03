package com.example.demo.Pieces;

import com.example.demo.Board;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public King( boolean isBlack) {
        setBlack(isBlack);
    }

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
        return true;
    }

    public boolean isChecked(int posY, int posX, Board board, boolean isBlack){
        //Check for Rooks(and the Queen)
        boolean isInYDirection = false;
        boolean isInXDirection = false;
        for(int i = 0;i<8;i++){
            if(!isInYDirection && (board.getBoard()[i][posX] instanceof Rook || board.getBoard()[i][posX] instanceof Queen) && board.getBoard()[i][posX].getIsBlack() != isBlack){
                return true;
            } else if(board.getBoard()[i][posX] != null &&  !(board.getBoard()[i][posX] instanceof King)){
                    isInYDirection = true;
                }

            if(!isInXDirection && (board.getBoard()[posY][i] instanceof Rook || board.getBoard()[posY][i] instanceof Queen) && board.getBoard()[posY][i].getIsBlack() != isBlack){
                return true;
            } else if(board.getBoard()[i][posX] != null && !(board.getBoard()[i][posX] instanceof King)){
                isInXDirection = true;
            }

        }
        int i = 1;
         boolean plusplus = false;
         boolean plusminus = false;
         boolean minusminus = false;
         boolean minusplus = false;
        //check for Pawns and Bishops(and the Queen)
        while(i <7) {
            if ((posY + 1 == posY + i) && ((board.getBoard()[posY + i][posX - 1] instanceof Pawn && board.getBoard()[posY + 1][posX - 1].getIsBlack() != isBlack) ||
                    (board.getBoard()[posY + i][posX + 1] instanceof Pawn && board.getBoard()[posY + 1][posX + 1].getIsBlack() != isBlack))) {
                return true;
            }

            if (!plusplus && (posY + i < 8 && posX + i < 8) && board.getBoard()[posY + i][posX + i] != null ) {
                if ((board.getBoard()[posY + i][posX + i] instanceof Bishop || board.getBoard()[posY + i][posX + i] instanceof Queen)
                        && board.getBoard()[posY + i][posX + i].getIsBlack() != isBlack) {
                    return true;
                } else{
                    plusplus = true;
                }
            }

            if (!minusplus &&(posY - i > 0 && posX + i < 8) && board.getBoard()[posY - i][posX + i] != null ) {
                if ( (board.getBoard()[posY - i][posX + i] instanceof Bishop || board.getBoard()[posY - i][posX + i] instanceof Queen)
                        && board.getBoard()[posY - i][posX + i].getIsBlack() != isBlack) {
                    return true;
                }else{
                    minusplus = true;
                }
            }

            if (!plusminus &&(posY + i < 8 && posX - i > 0) && board.getBoard()[posY + i][posX - i] != null ) {
                if ( (board.getBoard()[posY + i][posX - i] instanceof Bishop || board.getBoard()[posY + i][posX - i] instanceof Queen)
                        && board.getBoard()[posY + i][posX - i].getIsBlack() != isBlack) {
                    return true;
                }else{
                    plusminus = true;
                }
            }
            if (!minusminus &&(posY - i > 0 && posX - i > 0) && board.getBoard()[posY - i][posX - i] != null ) {
                if ( (board.getBoard()[posY - i][posX - i] instanceof Bishop || board.getBoard()[posY - i][posX - i] instanceof Queen)
                        && board.getBoard()[posY - i][posX - i].getIsBlack() != isBlack) {
                    return true;
                }
                else{
                    minusminus = true;
                }
            }
            i++;
            }
        //check for Knights
        if((posY+2 < 8 && posX+1 <8) &&board.getBoard()[posY+2][posX+1] instanceof Knight && board.getBoard()[posY+2][posX+1].getIsBlack() != isBlack){
            return true;
        }
        if((posY+2 < 8 && posX-1 >0) && board.getBoard()[posY+2][posX-1] instanceof Knight && board.getBoard()[posY+1][posX-1].getIsBlack() != isBlack){
            return true;
        }
        if((posY-2 > 0 && posX+1 <8) &&board.getBoard()[posY-2][posX+1] instanceof Knight && board.getBoard()[posY-2][posX+1].getIsBlack() != isBlack){
            return true;
        }
        if((posY-2 > 0 && posX-1 >0) &&board.getBoard()[posY-2][posX-1] instanceof Knight && board.getBoard()[posY-2][posX-1].getIsBlack() != isBlack){
            return true;
        }
        if((posY+1 < 8 && posX+2 <8) &&board.getBoard()[posY+1][posX+2] instanceof Knight && board.getBoard()[posY+1][posX+2].getIsBlack() != isBlack){
            return true;
        }
        if((posY+1 < 8 && posX-2 >0) &&board.getBoard()[posY+1][posX-2] instanceof Knight && board.getBoard()[posY+1][posX-2].getIsBlack() != isBlack){
            return true;
        }
        if((posY-1 > 0 && posX+2 <8) &&board.getBoard()[posY-1][posX+2] instanceof Knight && board.getBoard()[posY-1][posX+2].getIsBlack() != isBlack){
            return true;
        }
        if((posY-1 > 0 && posX-2>0) &&board.getBoard()[posY-1][posX-2] instanceof Knight && board.getBoard()[posY-1][posX-2].getIsBlack() != isBlack){
            return true;
        }
        return false;
        }
        //Maybe checking for the enemy King is necessary if we want to validate if a move that was made, makes sense
    }

