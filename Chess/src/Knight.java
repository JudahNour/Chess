public class Knight extends Piece {
    public Knight(boolean iW) {
        super(iW);
    }

    @Override
    public boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {

        return (Math.abs(firstX-secondX) == 2 && Math.abs(firstY-secondY) == 1) || (Math.abs(firstX - secondX) == 1 && Math.abs(firstY - secondY) == 2);

    }

    public String toString() {
        return(super.toString() + "N");
    }
}
