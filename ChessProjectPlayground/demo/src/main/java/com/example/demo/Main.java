package com.example.demo;

import com.example.demo.Pieces.Pawn;

import java.util.Scanner;

import static com.example.demo.FenstringToBoard.boardBuilder;
import static java.lang.Character.isUpperCase;


public class Main {

    public static void commandLineTestGame() throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);
        Game testGame = new Game();
        while(true){
            System.out.println("Write your next move");
            String move = scanner.nextLine();
            testGame.makeAMove(move);
            boardBuilder(testGame.getBoard().toFenStringSimple());
        }
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        commandLineTestGame();
        /*
        Game game = new Game();
        //Board board = game.getBoard();
        //board.getBoard()[0][3] = new King(false);

        System.out.println(isUpperCase('K'));
        System.out.println(game.getBoard().toFenString());
        System.out.println(game.getBoard().get_KING());
        System.out.println(game.getBoard().get_king());
        System.out.println(game.getBoard().isEmpty(0, 5));
        System.out.println(game.getBoard().isEmpty(0, 6));
        System.out.println(((King) game.getBoard().getBoard()[0][4]).isChecked(0, 4, game.getBoard(), game.getBoard().getBoard()[0][4].getIsBlack()));
        System.out.println(((King) game.getBoard().getBoard()[0][4]).isCastleValid(0, 4, 2, game.getBoard()));



        System.out.println((char) (3+'1'));
        System.out.println(game.getBoard().toFenString());
        System.out.println( game.getBoard().getChessBoard()[0][1].isMoveValid(6,5,7,8, game.getBoard()));

        game.getBoard().getChessBoard()[3][4] = new Pawn(false);
        System.out.println(game.getBoard().getChessBoard()[3][4]);
        game.makeAMove("e2e4");
        System.out.println(game.getBoard().getChessBoard()[3][4]);
        System.out.println(game.getBoard().getChessBoard()[1][4]);

        //System.out.println( (((King) board.getBoard()[0][4]).isChecked(0,4, board,  board.getBoard()[0][4].getIsBlack())));

         */
    }
}
