public class Rook extends Piece {
    /**
     * Rook constructor
     * @param iW boolean that isWhite is set to.
     * isPawn = false
     * isKing = false
     * isRook = true
     */
    public Rook(boolean iW) {
        super(iW, false,false, true);
    }

    /**
     * preconditions: The piece to move is not null and is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return true if the rook is moving in the proper way
     */
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
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
        System.out.println(increasing);
        if(didXChange) {//we just go from the initial place to the final place and check if each spot along the way is null. If so, the strip is blocked
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
                for (int i = initialPlace-1; i > nextPlace; i--) {
                    if (theBoard.getTheBoard()[constant][i].getPiece() != null)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * toString method
     * @return The String value of the color of the piece + R such as WR or BR
     */
    public String toString() {
        return(super.toString() + "R");
    }

    /**
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @param theBoard not null Board that represents the gameBoard
     * @return false as rooks cannot castle
     */
    public boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return false;
    }

}
