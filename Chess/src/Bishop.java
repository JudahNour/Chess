public class Bishop extends Piece {
    public Bishop(boolean iW) {
        /**
         * Bishop constructor
         * @param iW boolean that isWhite is set to.
         * isPawn = false
         * isKing = false
         * isRook = false
         */
        super(iW, false,false, false);
    }

    /**
     * preconditions: The piece to move is not null and is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return boolean true if the move is a valid bishop diagonal move
     */
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if (Math.abs(firstX - secondX) != Math.abs(firstY - secondY))
            return false;
        boolean isXIncreasing = secondX > firstX;
        boolean isYIncreasing = secondY > firstY;
        int xIncrement = -1;
        if(isXIncreasing)
            xIncrement = 1;
        int yIncrement = -1;
        if(isYIncreasing)
            yIncrement = 1; //setting up to see if the diagonal is blocked through whether each value is increasing
        return !isDiagonalBlocked(firstX, firstY, secondX, secondY, theBoard, xIncrement, yIncrement);
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
     * toString method
     * @return The String value of the color of the piece + B such as WB or BB
     */
    public String toString() {
        return(super.toString() + "B");
    }

    /**
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return false as bishops cannot castle
     */
    public boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return false;
    }
}
