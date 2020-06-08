public class King extends Piece {
    /**
     * King constructor
     * @param iW boolean that isWhite is set to.
     * isPawn = false
     * isKing = true
     * isRook = false
     */
    public King(boolean iW) {
        super(iW, true,false, false);
    }

    /**
     * The piece to move is not null and is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return boolean true if the move is valid and false otherwise
     */
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(firstX == secondX && firstY == secondY) //can't be in the same position as before
            return false;
        boolean xNotTooFar = false;
        if(Math.abs(firstX-secondX)<=1)
            xNotTooFar = true;
        boolean yNotTooFar = false;
        if(Math.abs(firstY-secondY)<=1)
            yNotTooFar = true;
        return xNotTooFar && yNotTooFar; //must be within one block of his original position

    }

    /**
     * toString method
     * @return String that is the color of the King  + "K" such as WK or BK
     */
    public String toString() {
        return(super.toString() + "K");
    }

    /**
     * preconditions: The piece to move is not null and is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return boolean that is true if the person castled and false otherwise.
     */
    public boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(theBoard.isCheck())//can't castle in check
            return false;
        if(firstX != secondX) //can't be in the wrong x position
            return false;
        if(firstY != 4)  //must be in this position
            return false;
        if(!(firstX == 0 || firstX == 7)) //must be in the correct X position (0 or 7)
            return false;
        if(secondY == 6)
            return kingSideCastle(firstX, secondX, isWhiteTurn, theBoard); //kingside if the secondY is 6
        if(secondY == 2)
            return queenSideCastle(firstX, secondX, isWhiteTurn, theBoard); //queenside if the secondY is 2
        return false;
    }

    /**
     * preconditions: The piece to move is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param secondX int first column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the game Board
     * @return boolean that is true if queenside castling occurs and false otherwise
     */
    private boolean queenSideCastle(int firstX, int secondX, boolean isWhiteTurn, Board theBoard) {
        if(theBoard.getTheBoard()[firstX][0].getPiece() == null ||  !theBoard.getTheBoard()[firstX][0].getPiece().isRook()) //the piece must not be null and there must be a rook
            return false;

        if(theBoard.isWhiteQueenSideCastle() && isWhiteTurn || theBoard.isBlackQueenSideCastle() && !isWhiteTurn)//ability to castle matches color
        {
            int[] coords;
            if(isWhiteTurn)
                coords = theBoard.getWhiteKingCoord();
            else
                coords = theBoard.getBlackKingCoord();
            coords[0] = firstX;
            for(int i = 1; i<3; i++) //we go through and make sure the places between the king's initial and final position do not result in a check and are null
            {
                coords[1] = 4 - i;
                if(theBoard.getTheBoard()[firstX][4-i].getPiece()!= null || GameRunner.isCheck(theBoard,isWhiteTurn)) {
                    coords[1] = 4;
                    return false;
                }
            }
            if(theBoard.getTheBoard()[firstX][1].getPiece() != null)
                return false;
            theBoard.getTheBoard()[firstX][4] = new Point(null);
            theBoard.getTheBoard()[firstX][3] = new Point(new Rook(isWhiteTurn)); //physically castles
            theBoard.getTheBoard()[firstX][2] = new Point(new King(isWhiteTurn));
            theBoard.getTheBoard()[firstX][0] = new Point(null);
            return true;
        }
        return false;
    }

    /**
     * preconditions: The piece to move is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param secondX int first column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the game Board
     * @return boolean that is true if kingside castling occurs and false otherwise
     */
    public boolean kingSideCastle(int firstX, int secondX, boolean isWhiteTurn, Board theBoard) {
        if(theBoard.getTheBoard()[firstX][7].getPiece() == null ||  !theBoard.getTheBoard()[firstX][7].getPiece().isRook()) {  //the piece must not be null and there must be a rook
            return false;
        }
        if(theBoard.isWhiteQueenSideCastle() && isWhiteTurn || theBoard.isBlackQueenSideCastle() && !isWhiteTurn) //the ability to castle must match the piece color
        {

            int[] coords;
            if(isWhiteTurn)
                coords = theBoard.getWhiteKingCoord();
            else
                coords = theBoard.getBlackKingCoord();
            coords[0] = firstX;
            for(int i = 1; i<3; i++)
            { //we go through and make sure the places between the king's initial and final position do not result in a check and are null
                coords[1] = 4 + i;
                if(theBoard.getTheBoard()[firstX][4+i].getPiece()!= null || GameRunner.isCheck(theBoard,isWhiteTurn)) {
                    coords[1] = 4;
                    return false;
                }
            }
            theBoard.getTheBoard()[firstX][4] = new Point(null);
            theBoard.getTheBoard()[firstX][5] = new Point(new Rook(isWhiteTurn)); //physically castles
            theBoard.getTheBoard()[firstX][6] = new Point(new King(isWhiteTurn));
            theBoard.getTheBoard()[firstX][7] = new Point(null);
            return true;
        }
        return false;
    }

}
