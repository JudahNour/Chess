public class Queen extends Piece {
    public Queen(boolean iW) {
        super(iW);
    }

    @Override
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        return(isValidBishop(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard) || isValidRook(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard));
    }

    public boolean isDiagonalBlocked(int firstX, int firstY, int secondX, int secondY, Board theBoard, int xIncrement, int yIncrement)
    {
        for(int i = xIncrement; i< Math.abs(secondX-firstX);i = i +xIncrement)
        {
            for(int j = yIncrement; j< Math.abs(secondY-firstY);j = j +yIncrement)
            {
                if(theBoard.getTheBoard()[firstX+i][firstY+j].getPiece() != null)
                    return true;
            }
        }
        return false;
    }
    public boolean isStripBlocked(int initialPlace, int nextPlace, int constant, Board theBoard)
    {
        boolean increasing = nextPlace>initialPlace;
        if(increasing)
        {
            for(int i = initialPlace + 1; i<nextPlace; i++)
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
    public boolean isValidRook(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard)
    {
        if((firstX-secondX)*(firstY-secondY) !=0) //one of the coordinates must change, the other cannot.
            return false;
        boolean didXChange = firstX - secondX !=0;
        if(didXChange)
        {
            return !isStripBlocked(firstX, secondX, firstY, theBoard);
        }
        else
        {
            return !isStripBlocked(firstY, secondY, firstX, theBoard);
        }
    }
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

    public String toString() {
        return(super.toString() + "Q");
    }
}
