public class Pawn extends Piece {
    public Pawn(boolean iW) {
        super(iW);
    }

    @Override
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(isWhiteTurn)
        {
            if(firstY == secondY && secondX == firstX -1)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null;
            }
            else if(firstY == secondY && secondX == firstX -2 && firstX == 6)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null && theBoard.getTheBoard()[secondX + 1][firstY].getPiece() == null;
            }
            else return firstY - secondY == 1 && Math.abs(firstX - secondX) == 1;
        }
        else
        {
            if(firstY == secondY && secondX == firstX +1)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null;
            }
            else if(firstY == secondY && secondX == firstX +2 && firstX == 1)
            {
                return theBoard.getTheBoard()[secondX][firstY].getPiece() == null && theBoard.getTheBoard()[secondX - 1][firstY].getPiece() == null;
            }
            else
            {
                return secondY-firstY == 1 && Math.abs(firstX-secondX) == 1;
            }
        }
    }

    @Override
    public String toString() {
        return(super.toString() + "P");
    }
}
