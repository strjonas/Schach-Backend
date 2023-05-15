package com.example.demo;


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


    private int[] convertChessNotationToMove(String move) {
        int[] finishedMove = new int[4];
        char[] moveArray = move.toCharArray();

        for (int i = 0; i < moveArray.length; i++) {
            switch (moveArray[i]) {
                case 'a' -> finishedMove[i] = 0;
                case 'b' -> finishedMove[i] = 1;
                case 'c' -> finishedMove[i] = 2;
                case 'd' -> finishedMove[i] = 3;
                case 'e' -> finishedMove[i] = 4;
                case 'f' -> finishedMove[i] = 5;
                case 'g' -> finishedMove[i] = 6;
                case 'h' -> finishedMove[i] = 7;
                case '1', '2', '3', '4', '5', '6', '7', '8' -> finishedMove[i] =   Character.getNumericValue(moveArray[i])-1;
            }

        }
        return finishedMove;
        //Special treatment for Castling needed
    }
    public void makeAMove(String move) throws CloneNotSupportedException{

        int[] moveArray = convertChessNotationToMove(move);
        //.getnumericvalue?

        if (board.moveValidation(moveArray[1], moveArray[0], moveArray[3], moveArray[2])) {
                board.setEnPassant(null);
                board.moveAPiece(moveArray[1], moveArray[0],moveArray[3], moveArray[2]);
                board.toggleWhitesMove();
        }else{
            //ToDo return "move not valid" to the server
        }



    }





    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.board.getChessBoard()[0][0].getIsBlack());
        System.out.println(game.convertChessNotationToMove("e2e4"));
    }

}




