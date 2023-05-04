package com.example.demo.Pieces;

import java.util.Scanner;

public class Rook extends Pieces{

    private boolean hasMoved;

    public Rook(boolean black) {
        super(black);
        hasMoved = false;
    }

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX){
        return super.isMoveValid(posY,posX,newPosY,newPosX) && isRookMovement( posY, posX,  newPosY,  newPosX) ;
    }
    public boolean isRookMovement(int posY, int posX, int newPosY, int newPosX){
        return (posY == newPosY && posX != newPosX) || (posY != newPosY && posX == newPosX);
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean testRookMovement() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        boolean test;
        for(int i = 0; i<input;i++) {
            int newX = (int) (Math.random() * (16));
            int newY = (int) (Math.random() * (16));
            int oldX = (int) (Math.random() * (7));
            int oldY = (int) (Math.random() * (7));
            test = ((Math.abs(newX - oldX) < 8 && (newY == oldY && newX != oldX)) || (Math.abs(newY - oldY) < 8  && (newX == oldX && newY != oldY) ))&& (newX < 8 && newY <8);
            if(test && !isMoveValid(oldY, oldX, newY, newX) || !test && isMoveValid(oldY, oldX, newY, newX)){
                System.out.println("test failed");
                System.out.println("oldY=" + oldY + " ,oldX=" +oldX + " ,newY=" + newY + " ,newX" + newX +
                        " , isMoveValid" + isMoveValid(oldY, oldX, newY, newX) + "test" + test);
                return false;
            }
            else{
                System.out.println("testing"+ i);
            }
        }
        System.out.println("All tests successful");
        return true;
    }

    @Override
    public Character toChar() {
        return getIsBlack() ? 'r' : 'R';
    }

    public static void main(String[] args) {

    }
}
