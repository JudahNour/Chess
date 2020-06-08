public class Knight extends Piece {
    public Knight(boolean iW) {
        /**
         * Knight constructor
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
     * @return boolean true if the move is a valid knight move, false otherwise
     */
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        //checks that there is a change of 2 in one direction and a change in 1 in the other
        return (Math.abs(firstX-secondX) == 2 && Math.abs(firstY-secondY) == 1) || (Math.abs(firstX - secondX) == 1 && Math.abs(firstY - secondY) == 2);

    }
    /**
     * toString method
     * @return The String value of the color of the piece + N such as WN or BN
     */
    public String toString() {
        return(super.toString() + "N");
    }

    /**
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return false as knights do not castle
     */
    public boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return false;
    }
}
