package com.example.demo;

import com.example.demo.Pieces.*;

import java.awt.*;
import java.util.Arrays;

import static java.lang.Character.isUpperCase;

public class Board {
    private Pieces[][] chessBoard;
    private Point king;
    private Point KING;
    private boolean whitesMove;
    private Point enPassant;
    private int halfMoveCounter = 0;
    private int moveCounter = 0;

    public Board(Pieces[][] chessBoard) {
        this.chessBoard = chessBoard;
        KING = new Point();
        king = new Point();
        //set enpassant position after pawnmove, set null after nextmove
    }

    public Board() {

    }

    public static Board copy(Board board){
        Board newBoard = new Board();
        newBoard = new Board(board.toFenStringSimple());
        newBoard.king = board.king;
        newBoard.KING = board.KING;
        return newBoard;
    }

    //8/8/4Rp2/5P2/1PP1pkP1/7P/1P1r4/7K b - - 0 40
    public Board(String fenstring) {
        KING = new Point();
        king = new Point();
        enPassant = new Point(4, 5);
        //set enpassant position after pawnmove, set null after nextmove
        int y = 7;
        int x = 0;
        chessBoard = new Pieces[8][8];
        for (char character : fenstring.toCharArray()) {
            switch (character) {
                case '8', '7', '6', '5', '4', '3', '2', '1' -> {
                    for (int i = 0; i < Character.getNumericValue(character); i++) {
                        chessBoard[y][x++] = null;
                    }
                }
                case 'R', 'r', 'K', 'k', 'Q', 'q', 'B', 'b', 'N', 'n', 'P', 'p' -> {
                    switch (character) {//board[column][line++];
                        case 'R', 'r' -> chessBoard[y][x++] = new Rook(!isUpperCase(character));
                        case 'K', 'k' -> {
                            chessBoard[y][x] = new King(!isUpperCase(character));
                            if (isUpperCase(character))
                                KING.setLocation(x, y);
                            else king.setLocation(x, y);
                            x++;
                        }
                        case 'Q', 'q' -> chessBoard[y][x++] = new Queen(!isUpperCase(character));
                        case 'B', 'b' -> chessBoard[y][x++] = new Bishop(!isUpperCase(character));
                        case 'N', 'n' -> chessBoard[y][x++] = new Knight(!isUpperCase(character));
                        case 'P', 'p' -> chessBoard[y][x++] = new Pawn(!isUpperCase(character));
                    }
                }
                case '/' -> {
                    x = 0;
                    y--;
                }
            }

        }
        for (int i = 0; i < 8; i++) {
            for (int b = 0; b < 8; b++) {
                System.out.print(chessBoard[i][b]);
            }
            System.out.println();
        }
    }

    public Pieces[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Pieces[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Point get_KING() {
        return KING;
    }

    public void set_KING(Point KING) {
        this.KING = KING;
    }

    public Point get_king() {
        return king;
    }

    public void set_king(Point king) {
        this.king = king;
    }

    public boolean isEmpty(int newPosY, int newPosX) {
        return chessBoard[newPosY][newPosX] == null;
    }


    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(chessBoard) +
                ", king=" + king +
                ", KING=" + KING +
                '}';
    }

    public String toFenString() {
        StringBuilder fenstring = new StringBuilder();
        for (int y = 7; y >= 0; y--) {
            int emptyCount = 0;
            for (int x = 0; x < 8; x++) {
                if (isEmpty(y, x)) {
                    if (x < 7) {
                        emptyCount++;
                    } else {
                        fenstring.append(++emptyCount);
                    }
                } else {
                    if (emptyCount > 0)
                        fenstring.append(emptyCount);
                    fenstring.append(chessBoard[y][x].toChar());
                    emptyCount = 0;
                }
            }
            fenstring.append(y > 0 ? '/' : ' ');
        }
        fenstring.append(whitesMove ? "w " : "b ")
                .append(((King) chessBoard[KING.y][KING.x]).isCanCastleQ() ?  "Q" : "")
                .append(((King) chessBoard[king.y][king.x]).isCanCastleK() ? "k" : "")
                .append(((King) chessBoard[king.y][king.x]).isCanCastleQ() ? "q" : "")
                .append(enPassant != null ? " " + ((char) (enPassant.x + 'a')) + ((char) (enPassant.y + '1')) : " -")
                .append(" " + halfMoveCounter + " " + moveCounter);
        //fenstring = new StringBuilder((String) fenstring.subSequence(0, fenstring.length() - 1));
        //System.out.println(fenstring);
        return fenstring.toString();
    }
    public String toFenStringSimple() {
        StringBuilder fenstring = new StringBuilder();
        for (int y = 7; y >= 0; y--) {
            int emptyCount = 0;
            for (int x = 0; x < 8; x++) {
                if (isEmpty(y, x)) {
                    if (x < 7) {
                        emptyCount++;
                    } else {
                        fenstring.append(++emptyCount);
                    }
                } else {
                    if (emptyCount > 0)
                        fenstring.append(emptyCount);
                    fenstring.append(chessBoard[y][x].toChar());
                    emptyCount = 0;
                }
            }
            fenstring.append(y > 0 ? '/' : ' ');

        }
        return fenstring.toString();
    }


    public boolean moveValidation(int posY, int posX, int newPosY, int newPosX) throws CloneNotSupportedException {
        //altes piece
        if(chessBoard[posY][posX].getIsBlack()){
            if (chessBoard[posY][posX].isMoveValid(posY,posX,newPosY,newPosX,this)) {
                Board cpy = Board.copy(this);
                cpy.moveAPiece(posY, posX, newPosY, newPosX);

                return !((King) cpy.getChessBoard()[cpy.get_king().y][cpy.get_king().x]).isChecked(cpy.get_king().y, cpy.get_king().x, cpy);
            } else return false;
        }
        else{
            if (chessBoard[posY][posX].isMoveValid(posY,posX,newPosY,newPosX,this)) {
                Board cpy = Board.copy(this);
                cpy.moveAPiece(posY, posX, newPosY, newPosX);
                return !((King) cpy.getChessBoard()[cpy.get_KING().y][cpy.get_KING().x]).isChecked(cpy.get_KING().y, cpy.get_KING().x, cpy);
            } else return false;
        }

    }
    public void moveAPiece(int posY, int posX, int newPosY, int newPosX){
        //set new king position, lose castle right
        if (chessBoard[posY][posX] instanceof King) {
            if (chessBoard[posY][posX].getIsBlack()) {
                set_king(new Point(newPosX, newPosY));
            } else {
                set_KING(new Point(newPosX, newPosY));
            }
            ((King) chessBoard[posY][posX]).setCanCastleK(false);
            ((King) chessBoard[posY][posX]).setCanCastleQ(false);
        }

        //lose castle right to rook
        if (chessBoard[posY][posX] instanceof Rook && posX == 0 || posX == 7 && posY == 0 || posY == 7) {
            //posY == 0 ? ( posX == 0  && ((King) chessBoard[posY][posX]).setCanCastleK(false) ? )
        }

        //promotion
        if (chessBoard[posY][posX] instanceof Pawn && (chessBoard[posY][posX].getIsBlack() ? newPosY == 0 : newPosY == 7)) {
            chessBoard[posY][posX] = new Queen(chessBoard[posY][posX].getIsBlack());
        }


        this.chessBoard[newPosY][newPosX] = this.chessBoard[posY][posX];
        this.chessBoard[posY][posX] = null;
    }
}

