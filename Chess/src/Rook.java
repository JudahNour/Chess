public class Rook extends Piece {

    public Rook(boolean iW) {
        super(iW);
    }

    @Override
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if((firstX-secondX)*(firstY-secondY) !=0) //one of the coordinates must change, the other cannot.
            return false;
        boolean didXChange = firstX - secondX !=0;
        boolean isValid;
        if(didXChange)
        {
            isValid = !isStripBlocked(firstX, secondX, firstY, theBoard);
        }
        else
        {
            isValid = !isStripBlocked(firstY, secondY, firstX, theBoard);
        }
        return isValid;
        //MAKE SURE TO ADD CASTLING STUFF
    }

    public boolean isStripBlocked(int initialPlace, int nextPlace, int constant, Board theBoard)
    {
        boolean increasing = nextPlace>initialPlace;
        if(increasing)
        {
            for(int i = initialPlace+1; i<nextPlace; i++)
            {
                if(theBoard.getTheBoard()[i][constant].getPiece() != null)
                    return true;
            }
        }
        else
        {
            for(int i = nextPlace+1; i<initialPlace; i++)
            {
                if(theBoard.getTheBoard()[i][constant].getPiece() != null)
                    return true;
            }
        }
        return false;
    }


    public String toString() {
        return(super.toString() + "R");
    }

}
