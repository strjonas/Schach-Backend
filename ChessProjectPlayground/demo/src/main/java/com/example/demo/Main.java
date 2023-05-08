package com.example.demo;

import static java.lang.Character.isUpperCase;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        //Board board = game.getBoard();
        //board.getBoard()[0][3] = new King(false);
        /*
        System.out.println(isUpperCase('K'));
        System.out.println(game.getBoard().toFenString());
        System.out.println(game.getBoard().get_KING());
        System.out.println(game.getBoard().get_king());
        System.out.println(game.getBoard().isEmpty(0, 5));
        System.out.println(game.getBoard().isEmpty(0, 6));
        System.out.println(((King) game.getBoard().getBoard()[0][4]).isChecked(0, 4, game.getBoard(), game.getBoard().getBoard()[0][4].getIsBlack()));
        System.out.println(((King) game.getBoard().getBoard()[0][4]).isCastleValid(0, 4, 2, game.getBoard()));

         */
        System.out.println((char) (3+'1'));
        System.out.println(game.getBoard().toFenString());
        System.out.println( game.getBoard().getChessBoard()[0][0].isMoveValid(0,0,0,8, game.getBoard()));

        //System.out.println( (((King) board.getBoard()[0][4]).isChecked(0,4, board,  board.getBoard()[0][4].getIsBlack())));
    }
}
