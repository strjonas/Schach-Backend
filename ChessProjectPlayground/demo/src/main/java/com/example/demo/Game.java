package com.example.demo;


import java.util.UUID;

public class Game {
    private Board board;
    private final String gameId;

    public Game() {
        this.gameId = UUID.randomUUID().toString();
        this.board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R"); // "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
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

    public void moveValidation(String fenstring, String move) {

    }

    private String convertChessNotationToMove(String move) {
        StringBuilder finishedMove = new StringBuilder();
        char[] moveArray = move.toCharArray();

        for (int i = 0; i < moveArray.length; i++) {
            switch (moveArray[i]) {
                case 'a' -> moveArray[i] = '0';
                case 'b' -> moveArray[i] = '1';
                case 'c' -> moveArray[i] = '2';
                case 'd' -> moveArray[i] = '3';
                case 'e' -> moveArray[i] = '4';
                case 'f' -> moveArray[i] = '5';
                case 'g' -> moveArray[i] = '6';
                case 'h' -> moveArray[i] = '7';
                case '1', '2', '3', '4', '5', '6', '7', '8' -> moveArray[i] = (char) (((int) moveArray[i]) - 1);
            }
            finishedMove.append(moveArray[i]);
        }
        return finishedMove.toString();
        //Special treatment for Castling needed
    }
    public void makeAMove(String move){
        char[] moveArray= move.toCharArray();
        //.getnumericvalue?
        if(board.moveValidation(moveArray[1], moveArray[0], moveArray[3], moveArray[2])){

        }
    }




    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.board.getChessBoard()[0][0].getIsBlack());
        System.out.println(game.convertChessNotationToMove("e2e4"));
    }

}




