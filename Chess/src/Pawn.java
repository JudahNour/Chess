public class Pawn extends Piece {
    /**
     * Pawn constructor
     * @param iW boolean that isWhite is set to.
     * isPawn = true
     * isKing = false
     * isRook = false
     */
    public Pawn(boolean iW) {
        super(iW, false,true,false);
    }


    /**
     *
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return boolean that is true if the move is valid
     */
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(isWhiteTurn)
        {
            if(firstY == secondY && secondX == firstX -1)
            {

                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null; //there is nothing in front of it and it goes up one
            }
            else if(firstY == secondY && secondX == firstX -2 && firstX == 6)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null && theBoard.getTheBoard()[secondX + 1][firstY].getPiece() == null;
                //It goes up 2 as its first move
            }
            else
            {
                if(Math.abs(firstY - secondY) == 1 && (firstX - secondX) == 1) {
                    return  theBoard.getTheBoard()[secondX][secondY].getRemnantMoveCount() == 2 || theBoard.getTheBoard()[secondX][secondY].getPiece() != null;
                    //It is capturing a piece of the other color (en passant or otherwise)
                }
            }
        }
        else
        {
            if(firstY == secondY && secondX == firstX +1)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null;  //there is nothing in front of it and it goes up one
            }
            else if(firstY == secondY && secondX == firstX +2 && firstX == 1)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null && theBoard.getTheBoard()[secondX - 1][firstY].getPiece() == null;
                //It goes up 2 as its first move
            }
            else if(Math.abs(firstY - secondY) == 1 && (secondX - firstX) == 1)
            {
                return theBoard.getTheBoard()[secondX][secondY].getRemnantMoveCount() == 2 || theBoard.getTheBoard()[secondX][secondY].getPiece() != null;
                //It is capturing a piece of the other color (en passant or otherwise)
            }
        }
        return false;
    }

    /**
     * toString method
     * @return The String value of the color of the piece + P such as WP or BP
     */
    public String toString() {
        return(super.toString() + "P");
    }

    /**
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return false as pawns can't castle
     */
    public boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return false;
    }

}
