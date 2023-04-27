package com.example.demo.Pieces;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board){
        return super.isMoveValid() && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX-posX) < 2 && Math.abs(newPosY-posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board[newPosY][newPosX].isEmpty();
    }

    private boolean isCastleValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
    }
}package com.example.demo.Pieces;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board){
        return super.isMoveValid() && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX-posX) < 2 && Math.abs(newPosY-posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board[newPosY][newPosX].isEmpty();
    }

    private boolean isCastleValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
    }
}package com.example.demo.Pieces;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board){
        return super.isMoveValid() && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX-posX) < 2 && Math.abs(newPosY-posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board[newPosY][newPosX].isEmpty();
    }

    private boolean isCastleValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
    }
}package com.example.demo.Pieces;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board){
        return super.isMoveValid() && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX-posX) < 2 && Math.abs(newPosY-posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board[newPosY][newPosX].isEmpty();
    }

    private boolean isCastleValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
    }
}package com.example.demo.Pieces;

public class King extends Pieces{
    private boolean canCastleQ;
    private boolean canCastleK;

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board){
        return super.isMoveValid() && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX-posX) < 2 && Math.abs(newPosY-posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board[newPosY][newPosX].isEmpty();
    }

    private boolean isCastleValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
    }
}https://github.com/strjonas/Schach-Backend.gitgh repo clone strjonas/Schach-Backend