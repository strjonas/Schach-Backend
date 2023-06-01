package com.example.demo;


import java.util.Arrays;
import java.util.UUID;

public class Game {
    private Board board;
    private final String gameId;

    public Game() {
        this.gameId = UUID.randomUUID().toString();
        this.board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"); // "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
    }

    public String getGameId() {
        return gameId;
    }

    public Board getBoard() {

        return board;
    }

    public void setBoard(Board board) {

        this.board = board;
    }

    public void boardStarter() {
    }


    public int[] convertChessNotationToMove(String move) {
        int[] finishedMove = new int[4];
        char[] moveArray = move.toCharArray();

        for (int i = 5; i < moveArray.length; i++) {
            switch (moveArray[i]) {
                case 'a' -> finishedMove[i - 5] = 0;
                case 'b' -> finishedMove[i - 5] = 1;
                case 'c' -> finishedMove[i - 5] = 2;
                case 'd' -> finishedMove[i - 5] = 3;
                case 'e' -> finishedMove[i - 5] = 4;
                case 'f' -> finishedMove[i - 5] = 5;
                case 'g' -> finishedMove[i - 5] = 6;
                case 'h' -> finishedMove[i - 5] = 7;
                case '1', '2', '3', '4', '5', '6', '7', '8' -> finishedMove[i - 5] =   Character.getNumericValue(moveArray[i])-1;
            }

        }
        return finishedMove;
        //Special treatment for Castling needed
    }
    public boolean makeAMove(String move) {

        int[] moveArray = convertChessNotationToMove(move);
        //.getnumericvalue?

        if (board.moveValidation(moveArray[1], moveArray[0], moveArray[3], moveArray[2])) {
            board.setEnPassant(null);
            board.moveAPiece(moveArray[1], moveArray[0],moveArray[3], moveArray[2]);
            board.toggleWhitesMove();
            board.get_history().add(move.substring(5));
            return true;
        }
        return false;



    }





    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(Arrays.toString(game.convertChessNotationToMove("move e2e4")));
    }

}



