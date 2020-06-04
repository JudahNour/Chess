public class King extends Piece {
    private boolean canQueensideCastle = true;
    private boolean canKingsideCastle = true;
    public King(boolean iW) {
        super(iW);
    }

    @Override
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(firstX == secondX && firstY == secondY)
            return false;
        boolean xNotTooFar = false;
        if(Math.abs(firstX-secondX)<=1)
            xNotTooFar = true;
        boolean yNotTooFar = false;
        if(Math.abs(firstY-secondY)<=1)
            yNotTooFar = true;
        if(xNotTooFar && yNotTooFar)
        {
            canQueensideCastle = false;
            canKingsideCastle = false;
        }
        return xNotTooFar && yNotTooFar;

    }


    public String toString() {
        return(super.toString() + "K");
    }

}
