package com.example.demo.Pieces;

public class King extends Pieces{

    private boolean isChecked;

    public King() {
        this.isChecked = false;
    }

    public boolean isMoveValid(){
        return true;
    }
}

