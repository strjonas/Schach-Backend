package com.example.demo.Pieces;

import com.example.demo.Board;

public class King extends Pieces {
    private boolean canCastleQ;
    private boolean canCastleK;

    public King(boolean isBlack) {
        super(isBlack);
        canCastleK = true;
        canCastleQ = true;
    }

    public boolean isCanCastleQ() {
        return canCastleQ;
    }

    public void setCanCastleQ(boolean canCastleQ) {
        this.canCastleQ = canCastleQ;
    }

    public boolean isCanCastleK() {
        return canCastleK;
    }

    public void setCanCastleK(boolean canCastleK) {
        this.canCastleK = canCastleK;
    }

    @Override
    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        if (Math.abs(newPosX - posX) == 2) {
            return super.isMoveValid(posY, posX, newPosY, newPosX, board) && newPosY == posY && isSomethingInTheWay(newPosY, newPosX, board) && isCastleValid(posY, posX, newPosX, board);
        } else return super.isMoveValid(posY, posX, newPosY, newPosX, board) && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX - posX) < 2 && Math.abs(newPosY - posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        if (board.getChessBoard()[newPosY][newPosX] != null) {
            return board.getChessBoard()[newPosY][newPosX].getIsBlack() == !getIsBlack();
        }
        return true;
    }

    public boolean isCastleValid(int posY, int posX, int newPosX, Board board) {
        if (newPosX - posX > 0 && canCastleK && !isChecked(posY, posX, board)) {
            //kingside
            return board.isEmpty(posY, posX + 1) && board.isEmpty(posY, posX + 2) && !isChecked(posY, posX + 1, board) && !isChecked(posY, posX + 2, board);
        } else if (newPosX - posX < 0 && canCastleQ && !isChecked(posY, posX, board)) {
            return board.isEmpty(posY, posX - 1) && board.isEmpty(posY, posX - 2) && !isChecked(posY, posX - 1, board) && !isChecked(posY, posX - 2, board);
            //queenside
        }
        return false;
    }

    public boolean isChecked(int posY, int posX, Board board) {

        return isDiagonallyChecked(posY, posX, board) || isHorizontallyOrVerticallyChecked(posY, posX, board) || isCheckedByKnight(posY, posX, board);


    }

    private boolean isCheckedByKnight(int posY, int posX, Board board) {
        if ((posY + 2 < 8 && posX + 1 < 8) && board.getChessBoard()[posY + 2][posX + 1] instanceof Knight && board.getChessBoard()[posY + 2][posX + 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY + 2 < 8 && posX - 1 >= 0) && board.getChessBoard()[posY + 2][posX - 1] instanceof Knight && board.getChessBoard()[posY + 1][posX - 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY - 2 >= 0 && posX + 1 < 8) && board.getChessBoard()[posY - 2][posX + 1] instanceof Knight && board.getChessBoard()[posY - 2][posX + 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY - 2 >= 0 && posX - 1 >= 0) && board.getChessBoard()[posY - 2][posX - 1] instanceof Knight && board.getChessBoard()[posY - 2][posX - 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY + 1 < 8 && posX + 2 < 8) && board.getChessBoard()[posY + 1][posX + 2] instanceof Knight && board.getChessBoard()[posY + 1][posX + 2].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY + 1 < 8 && posX - 2 >= 0) && board.getChessBoard()[posY + 1][posX - 2] instanceof Knight && board.getChessBoard()[posY + 1][posX - 2].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY - 1 >= 0 && posX + 2 < 8) && board.getChessBoard()[posY - 1][posX + 2] instanceof Knight && board.getChessBoard()[posY - 1][posX + 2].getIsBlack() != isBlack) {
            return true;
        }
        return (posY - 1 >= 0 && posX - 2 >= 0) && board.getChessBoard()[posY - 1][posX - 2] instanceof Knight && board.getChessBoard()[posY - 1][posX - 2].getIsBlack() != isBlack;
    }

    private boolean isDiagonallyChecked(int posY, int posX, Board board) {
        int i = 1;
        boolean plusplus = false;
        boolean plusminus = false;
        boolean minusminus = false;
        boolean minusplus = false;
        //check for Pawns and Bishops(and the Queen)
        while (i < 7) {
            if (!isBlack) {

                if ((posY + i < 8 && posX - i >= 0 && posX + i < 8) && (posY + 1 == posY + i) && ((board.getChessBoard()[posY + i][posX - 1] instanceof Pawn && board.getChessBoard()[posY + i][posX - 1].getIsBlack()) || (board.getChessBoard()[posY + i][posX + 1] instanceof Pawn && board.getChessBoard()[posY + i][posX + 1].getIsBlack()))) {
                    return true;
                }
            } else {
                if ((posY - i >= 0 && posX - i >= 0 && posX + i < 8) && (posY - 1 == posY - i) && ((board.getChessBoard()[posY - i][posX - 1] instanceof Pawn) && !board.getChessBoard()[posY - i][posX - 1].getIsBlack() || (board.getChessBoard()[posY - i][posX + 1] instanceof Pawn) && !board.getChessBoard()[posY - i][posX + 1].getIsBlack())) {
                    return true;

                }
            }


            if (!plusplus && (posY + i < 8 && posX + i < 8) && board.getChessBoard()[posY + i][posX + i] != null) {
                if ((isQueenOrBishop(posY + i, posX + i, board) || (i == 1 && board.getChessBoard()[posY + i][posX + i] instanceof King)) && board.getChessBoard()[posY + i][posX + i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    plusplus = true;
                }
            }

            if (!minusplus && (posY - i >= 0 && posX + i < 8) && board.getChessBoard()[posY - i][posX + i] != null) {
                if ((isQueenOrBishop(posY - i, posX + i, board) || (i == 1 && board.getChessBoard()[posY - i][posX + i] instanceof King)) && board.getChessBoard()[posY - i][posX + i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    minusplus = true;
                }
            }

            if (!plusminus && (posY + i < 8 && posX - i >= 0) && board.getChessBoard()[posY + i][posX - i] != null) {
                if ((isQueenOrBishop(posY + i, posX - i, board) || (i == 1 && board.getChessBoard()[posY + i][posX - i] instanceof King)) && board.getChessBoard()[posY + i][posX - i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    plusminus = true;
                }
            }
            if (!minusminus && (posY - i >= 0 && posX - i >= 0) && board.getChessBoard()[posY - i][posX - i] != null) {
                if ((isQueenOrBishop(posY - i, posX - i, board) || (i == 1 && board.getChessBoard()[posY - i][posX - i] instanceof King)) && board.getChessBoard()[posY - i][posX - i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    minusminus = true;
                }
            }
            i++;
        }
        return false;
    }


    private boolean isHorizontallyOrVerticallyChecked(int posY, int posX, Board board) {
        boolean isInPositiveYDirection = true;
        boolean isInNegativeYDirection = true;
        boolean isInNegativeXDirection = true;
        boolean isInPositiveXDirection = true;
        for (int i = 1; i < 8; i++) {
            if (isInPositiveXDirection && posX + i < 8 && (isQueenOrRook(posY, posX + 1, board) || (i == 1 && board.getChessBoard()[posY][posX + i] instanceof King)) && board.getChessBoard()[posY][posX + i].getIsBlack() != isBlack) {
                return true;
            } else if (posX + i < 8 && board.getChessBoard()[posY][posX + i] != null) {
                isInPositiveXDirection = false;
            }

            if (isInNegativeXDirection && posX - i >= 0 && (isQueenOrRook(posY, posX - 1, board) || (i == 1 && board.getChessBoard()[posY][posX - i] instanceof King)) && board.getChessBoard()[posY][posX - i].getIsBlack() != isBlack) {
                return true;
            } else if (posX - i >= 0 && board.getChessBoard()[posY][posX - i] != null) {
                isInNegativeXDirection = false;
            }

            if (isInPositiveYDirection && posY + i < 8 && (isQueenOrRook(posY + 1, posX, board) || (i == 1 && board.getChessBoard()[posY + i][posX] instanceof King)) && board.getChessBoard()[posY + 1][posX].getIsBlack() != isBlack) {
                return true;
            } else if (posY + i < 8 && board.getChessBoard()[posY + 1][posX] != null) {
                isInPositiveYDirection = false;
            }

            if (isInNegativeYDirection && posY - i >= 0 && (isQueenOrRook(posY - 1, posX, board) || (i == 1 && board.getChessBoard()[posY - i][posX] instanceof King)) && board.getChessBoard()[posY - 1][posX].getIsBlack() != isBlack) {
                return true;
            } else if (posY - i >= 0 && board.getChessBoard()[posY - 1][posX] != null) {
                isInNegativeYDirection = false;
            }


        }
        return false;


    }

    private boolean isQueenOrRook(int posY, int posX, Board board) {
        return board.getChessBoard()[posY][posX] instanceof Queen || board.getChessBoard()[posY][posX] instanceof Rook;
    }

    private boolean isQueenOrBishop(int posY, int posX, Board board) {
        return board.getChessBoard()[posY][posX] instanceof Queen || board.getChessBoard()[posY][posX] instanceof Bishop;
    }


    @Override
    public Character toChar() {
        return getIsBlack() ? 'k' : 'K';
    }

}

