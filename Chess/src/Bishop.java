public class Bishop extends Piece {
    public Bishop(boolean iW) {
        super(iW);
    }

    @Override
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
            yIncrement = 1;
        return !isDiagonalBlocked(firstX, firstY, secondX, secondY, theBoard, xIncrement, yIncrement);
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

    public String toString() {
        return(super.toString() + "B");
    }
}
