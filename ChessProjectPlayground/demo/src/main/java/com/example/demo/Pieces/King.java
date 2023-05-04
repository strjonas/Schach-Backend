package com.example.demo.Pieces;

import com.example.demo.Board;

public class King extends Pieces {
    private final boolean canCastleQ;
    private final boolean canCastleK;

    public King(boolean isBlack) {
        super(isBlack);
        canCastleK = true;
        canCastleQ = true;
    }

    public boolean isMoveValid(int posY, int posX, int newPosY, int newPosX, Board board) {
        return super.isMoveValid(posY, posX, newPosY, newPosX) && isKingMovement(posY, posX, newPosY, newPosX) && isSomethingInTheWay(newPosY, newPosX, board);
    }

    private boolean isKingMovement(int posY, int posX, int newPosY, int newPosX) {
        return Math.abs(newPosX - posX) < 2 && Math.abs(newPosY - posY) < 2;
    }

    private boolean isSomethingInTheWay(int newPosY, int newPosX, Board board) {
        return board.isEmpty(newPosY, newPosX);
    }

    public boolean isCastleValid(int posY, int posX, int newPosX, Board board) {
        // todo !!!!!!!!!
        if (newPosX - posX > 0 && canCastleK) {
            //kingside
            //move rook
            return board.getBoard()[posY][7] instanceof Rook && !((Rook) board.getBoard()[posY][7]).getHasMoved() && board.isEmpty(posY, posX + 1) && board.isEmpty(posY, posX + 2)
                    && !isChecked(posY, posX + 1, board, getIsBlack()) && !isChecked(posY, posX + 2, board, getIsBlack());
        } else if (newPosX - posX < 0 && canCastleQ) {
            //move rook
            return board.getBoard()[posY][0] instanceof Rook && !((Rook) board.getBoard()[posY][0]).getHasMoved() && board.isEmpty(posY, posX - 1) && board.isEmpty(posY, posX - 2)
                    && !isChecked(posY, posX - 1, board, getIsBlack()) && !isChecked(posY, posX - 2, board, getIsBlack());
            //queenside
        }
        //todo ( newPosX-posX > 0 ? canCastleK : canCastleQ)
        return false;
    }

    public boolean isChecked(int posY, int posX, Board board, boolean isBlack) {

        return isDiagonallyChecked(posY, posX, board, isBlack) || isHorizontallyOrVerticallyChecked(posY, posX, board, isBlack) || isCheckedByKnight(posY, posX, board, isBlack);


    }

    private boolean isCheckedByKnight(int posY, int posX, Board board, boolean isBlack) {
        if ((posY + 2 < 8 && posX + 1 < 8) && board.getBoard()[posY + 2][posX + 1] instanceof Knight && board.getBoard()[posY + 2][posX + 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY + 2 < 8 && posX - 1 >= 0) && board.getBoard()[posY + 2][posX - 1] instanceof Knight && board.getBoard()[posY + 1][posX - 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY - 2 >= 0 && posX + 1 < 8) && board.getBoard()[posY - 2][posX + 1] instanceof Knight && board.getBoard()[posY - 2][posX + 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY - 2 >= 0 && posX - 1 >= 0) && board.getBoard()[posY - 2][posX - 1] instanceof Knight && board.getBoard()[posY - 2][posX - 1].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY + 1 < 8 && posX + 2 < 8) && board.getBoard()[posY + 1][posX + 2] instanceof Knight && board.getBoard()[posY + 1][posX + 2].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY + 1 < 8 && posX - 2 >= 0) && board.getBoard()[posY + 1][posX - 2] instanceof Knight && board.getBoard()[posY + 1][posX - 2].getIsBlack() != isBlack) {
            return true;
        }
        if ((posY - 1 >= 0 && posX + 2 < 8) && board.getBoard()[posY - 1][posX + 2] instanceof Knight && board.getBoard()[posY - 1][posX + 2].getIsBlack() != isBlack) {
            return true;
        }
        return (posY - 1 >= 0 && posX - 2 >= 0) && board.getBoard()[posY - 1][posX - 2] instanceof Knight && board.getBoard()[posY - 1][posX - 2].getIsBlack() != isBlack;
    }

    private boolean isDiagonallyChecked(int posY, int posX, Board board, boolean isBlack) {
        int i = 1;
        boolean plusplus = false;
        boolean plusminus = false;
        boolean minusminus = false;
        boolean minusplus = false;
        //check for Pawns and Bishops(and the Queen)
        while (i < 7) {
            if (!isBlack) {
                if ((posY + i < 8 && posX + i < 8) && (posY + 1 == posY + i) &&
                        ((board.getBoard()[posY + i][posX - 1] instanceof Pawn && board.getBoard()[posY + i][posX - 1].getIsBlack()) ||
                                (board.getBoard()[posY + i][posX + 1] instanceof Pawn && board.getBoard()[posY + i][posX - 1].getIsBlack()))) {
                    return true;
                }
            } else {
                if ((posY - i >= 0 && posX - i >= 0) && (posY - 1 == posY - i) &&
                        ((board.getBoard()[posY - i][posX - 1] instanceof Pawn && !board.getBoard()[posY - i][posX - 1].getIsBlack()) ||
                                (board.getBoard()[posY - i][posX + 1] instanceof Pawn && !board.getBoard()[posY - i][posX - 1].getIsBlack()))) {
                    return true;
                }
            }


            if (!plusplus && (posY + i < 8 && posX + i < 8) && board.getBoard()[posY + i][posX + i] != null) {
                if ((isQueenOrBishop(posY + i, posX + i, board)
                        || (i == 1 && board.getBoard()[posY + i][posX + i] instanceof King))
                        && board.getBoard()[posY + i][posX + i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    plusplus = true;
                }
            }

            if (!minusplus && (posY - i >= 0 && posX + i < 8) && board.getBoard()[posY - i][posX + i] != null) {
                if ((isQueenOrBishop(posY - 1, posX - 1, board)
                        || (i == 1 && board.getBoard()[posY - i][posX + i] instanceof King))
                        && board.getBoard()[posY - i][posX + i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    minusplus = true;
                }
            }

            if (!plusminus && (posY + i < 8 && posX - i >= 0) && board.getBoard()[posY + i][posX - i] != null) {
                if ((isQueenOrBishop(posY + 1, posX - 1, board)
                        || (i == 1 && board.getBoard()[posY + i][posX - i] instanceof King))
                        && board.getBoard()[posY + i][posX - i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    plusminus = true;
                }
            }
            if (!minusminus && (posY - i >= 0 && posX - i >= 0) && board.getBoard()[posY - i][posX - i] != null) {
                if ((isQueenOrBishop(posY - 1, posX - 1, board)
                        || (i == 1 && board.getBoard()[posY - i][posX - i] instanceof King))
                        && board.getBoard()[posY - i][posX - i].getIsBlack() != isBlack) {
                    return true;
                } else {
                    minusminus = true;
                }
            }
            i++;
        }
        return false;
    }


    private boolean isHorizontallyOrVerticallyChecked(int posY, int posX, Board board, boolean isBlack) {
        boolean isInPositiveYDirection = true;
        boolean isInNegativeYDirection = true;
        boolean isInNegativeXDirection = true;
        boolean isInPositiveXDirection = true;
        for (int i = 1; i < 8; i++) {
            if (isInPositiveXDirection && posX + i < 8 && (isQueenOrRook(posY, posX + 1, board)
                    || (i == 1 && board.getBoard()[posY][posX + i] instanceof King))
                    && board.getBoard()[posY][posX + i].getIsBlack() != isBlack) {
                return true;
            } else if (posX + i < 8 && board.getBoard()[posY][posX + i] != null) {
                isInPositiveXDirection = false;
            }

            if (isInNegativeXDirection && posX - i >= 0 && (isQueenOrRook(posY, posX - 1, board)
                    || (i == 1 && board.getBoard()[posY][posX - i] instanceof King))
                    && board.getBoard()[posY][posX - i].getIsBlack() != isBlack) {
                return true;
            } else if (posX - i >= 0 && board.getBoard()[posY][posX - i] != null) {
                isInNegativeXDirection = false;
            }

            if (isInPositiveYDirection && posY + i < 8 && (isQueenOrRook(posY + 1, posX, board)
                    || (i == 1 && board.getBoard()[posY + i][posX] instanceof King))
                    && board.getBoard()[posY + 1][posX].getIsBlack() != isBlack) {
                return true;
            } else if (posY + i < 8 && board.getBoard()[posY + 1][posX] != null) {
                isInPositiveYDirection = false;
            }

            if (isInNegativeYDirection && posY - i >= 0 && (isQueenOrRook(posY - 1, posX, board)
                    || (i == 1 && board.getBoard()[posY - i][posX] instanceof King))
                    && board.getBoard()[posY - 1][posX].getIsBlack() != isBlack) {
                return true;
            } else if (posY - i >= 0 && board.getBoard()[posY - 1][posX] != null) {
                isInNegativeYDirection = false;
            }


        }
        return false;


    }

    private boolean isQueenOrRook(int posY, int posX, Board board) {
        return board.getBoard()[posY][posX] instanceof Queen || board.getBoard()[posY][posX] instanceof Rook;
    }

    private boolean isQueenOrBishop(int posY, int posX, Board board) {
        return board.getBoard()[posY][posX] instanceof Queen || board.getBoard()[posY][posX] instanceof Bishop;
    }


    @Override
    public Character toChar() {
        return getIsBlack() ? 'k' : 'K';
    }

}

