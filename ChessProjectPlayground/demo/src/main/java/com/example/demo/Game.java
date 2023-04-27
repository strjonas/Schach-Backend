package com.example.demo;


import com.example.demo.Pieces.*;

public class Game {
    private static Board board = new Board();

   public static void boardStarter(){
       Pieces[][] newBoard = new Pieces[8][8];
       newBoard[0][0] = new Rook();
       newBoard[0][1] = new Knight();
       newBoard[0][2] = new Bishop();
       newBoard[0][3] = new Queen();
       newBoard[0][4] = new King();
       newBoard[0][5] = new Bishop();
       newBoard[0][6] = new Knight();
       newBoard[0][7] = new Rook();
       board.setBoard(newBoard);


   }


    public static void main(String[] args) {
    }

}
