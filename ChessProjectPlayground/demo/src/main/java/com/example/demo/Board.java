package com.example.demo;
import com.example.demo.Pieces.*;

public class Board {
    private Pieces[][] board;
    public Board(Pieces[][] board) {
        this.board = board;
    }

    public  Pieces[][] getBoard() {
        return board;
    }

    public void setBoard(Pieces[][] board) {
        this.board = board;
    }
}


