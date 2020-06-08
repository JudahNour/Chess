public class Queen extends Piece {
    /**
     * Queen constructor
     * @param iW boolean that isWhite is set to.
     * isPawn = false
     * isKing = false
     * isRook = false
     */
    public Queen(boolean iW) {
        super(iW, false, false,false);
    }

    /**
     * preconditions: The piece to move is not null and is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @return boolean value of whether the Queen can move in that direction (bishop fashion/rook fashion)
     */
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return(isValidBishop(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard) || isValidRook(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard));
    }

    /**
     * preconditions: theBoard is not null
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param xIncrement int the amount that the x increments from the firstX to the secondX (-1 or +1)
     * @param yIncrement int the amount that the y increments from the firstY to the secondY (-1 or +1)
     * @return boolean that is true if the diagonal is blocked and false otherwise.
     */
    public boolean isDiagonalBlocked(int firstX, int firstY, int secondX, int secondY, Board theBoard, int xIncrement, int yIncrement)
    {
        int difference = Math.abs(firstX-secondX);
        for(int i = 1; i< difference;i++)
        {

                if(theBoard.getTheBoard()[firstX+i*xIncrement][firstY+i*yIncrement].getPiece() != null)
                    return true;
        }

        return false;
    }

    /**
     * preconditions: the board is not null
     * @param initialPlace int initial value of the changing variable on the strip
     * @param nextPlace int value of the changing variable on the strip's ending place
     * @param constant int coordinate that stays the same on the strip
     * @param theBoard Board that represents the game board
     * @param didXChange boolean that is true if the X value is the one that changed
     * @return boolean true if the strip is blocked and false otherwise
     */
    public boolean isStripBlocked(int initialPlace, int nextPlace, int constant, Board theBoard, boolean didXChange)
    {
        boolean increasing = nextPlace>initialPlace;
        if(didXChange) { //we just go from the initial place to the final place and check if each spot along the way is null. If so, the strip is blocked
            if (increasing) {
                for (int i = initialPlace + 1; i < nextPlace; i++) {
                    if (theBoard.getTheBoard()[i][constant].getPiece() != null)
                        return true;

                }
            } else {
                for (int i = initialPlace-1; i > nextPlace; i--) {
                    if (theBoard.getTheBoard()[i][constant].getPiece() != null)
                        return true;
                }
            }
        }
        else
        {
            if (increasing) {
                for (int i = initialPlace + 1; i < nextPlace; i++) {
                    if (theBoard.getTheBoard()[constant][i].getPiece() != null)
                        return true;

                }
            } else {
                for (int i = initialPlace - 1; i > nextPlace; i--) {
                    if (theBoard.getTheBoard()[constant][i].getPiece() != null)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * preconditions: board is not null and the coordinates refer to actual points on the board.
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param isWhiteTurn boolean that is true if it is white's turn and false if it is black's
     * @return boolean true if the move was a valid rook move
     */
    public boolean isValidRook(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard)
    {
        if((firstX-secondX)*(firstY-secondY) !=0) //one of the coordinates must change, the other cannot.
            return false;
        boolean didXChange = firstX - secondX !=0;
        if(didXChange)
        {
            return !isStripBlocked(firstX, secondX, firstY, theBoard, true); //sees whether the strip for the looks move is blocked
        }
        else
        {
            return !isStripBlocked(firstY, secondY, firstX, theBoard, false);
        }
    }
    /**
     * preconditions: board is not null and the coordinates refer to actual points on the board.
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param isWhiteTurn boolean that is true if it is white's turn and false if it is black's
     * @return boolean true if the move was a valid bishop move
     */
    public boolean isValidBishop(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard)
    {
        if (Math.abs(firstX - secondX) != Math.abs(firstY - secondY))
            return false;
        boolean isXIncreasing = secondX > firstX;
        boolean isYIncreasing = secondY > firstY;
        int xIncrement = -1;
        if(isXIncreasing)
            xIncrement = 1;
        int yIncrement = -1;
        if(isYIncreasing)
            yIncrement = 1;
        return !isDiagonalBlocked(firstX, firstY, secondX, secondY, theBoard, xIncrement, yIncrement);
    }

    /**
     * toString method
     * @return String that is the color of the Queen  + "Q" such as WQ or BQ
     */
    public String toString() {
        return(super.toString() + "Q");
    }

    /**
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return boolean false. Queens can't castle
     */
    public boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return false;
    }
}
