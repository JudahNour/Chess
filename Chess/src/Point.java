public class Point {
    private boolean hasPiece;
    private Piece piece;
    private boolean hasRemnant = false;
    public Point(Piece a)
    {
        piece = a;
    }
    public Piece getPiece()
    {
        return piece;
    }
    public void setHasRemnant(boolean b)
    {
        hasRemnant = b;
    }
}
