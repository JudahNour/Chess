public class Point {
    private boolean hasPiece;
    private Piece piece;
    private int remnantMoveCount = 0;

    /**
     * constructor for the Point object
     * @param a Piece that represents the Piece on the Point
     */
    public Point(Piece a)
    {
        piece = a;
    }

    /**
     * preconditions: Point is not null
     * getter method for the piece.
     * @return Piece instance variable for the Point.
     */
    public Piece getPiece()
    {
        return piece;
    }

    /**
     * Increases the value of remnantMoveCount by one
     */
    public void incrementRemnantMoveCount()
    {
        remnantMoveCount++;
    }

    /**
     * getter method for remnantMoveCount
     * @return int value of the remnantMoveCount
     */
    public int getRemnantMoveCount() {
        return remnantMoveCount;
    }
    /**
     * setter method for remnantMoveCount
     * @param remnantMoveCount int value that is set to be the instance variable remnantMoveCount
     */
    public void setRemnantMoveCount(int remnantMoveCount) {
        this.remnantMoveCount = remnantMoveCount;
    }
}
