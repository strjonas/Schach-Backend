package com.example.demo;
import com.example.demo.Pieces.*;

public class Board {
    private static Pieces[][] board;


    public static Pieces[][] getBoard() {
        return board;
    }

    public static void setBoard(Pieces[][] board) {
        Board.board = board;
    }
}


