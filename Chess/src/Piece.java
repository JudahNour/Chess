import java.util.ArrayList;

public abstract class Piece {
    private boolean isWhite;
    public Piece(boolean iW)
    {
        isWhite = iW;
    }
    public boolean isWhite()
    {
        return isWhite;
    }

    public abstract boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard);

    public String toString() {
        if(isWhite)
            return("W");
        return("B");
    }
    public boolean isThreatening(int[] coordinates, Board theBoard, boolean blackMate) {
        if(blackMate)
        {
            return(isValidMove(coordinates[0],coordinates[1], theBoard.getWhiteKingCoord()[0],theBoard.getWhiteKingCoord()[1],false, theBoard));
        }
        else
        {
            return(isValidMove(coordinates[0],coordinates[1], theBoard.getBlackKingCoord()[0],theBoard.getBlackKingCoord()[1],true, theBoard));
        }
    }

    public ArrayList<int[]> validMoves(int[] coordinates, Board theBoard, boolean isWhiteTurn)
    {
        ArrayList<int[]> piecesCoordinates = new ArrayList<int[]>();
        for(int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                if(isValidMove(coordinates[0],coordinates[1],i,j,isWhiteTurn,theBoard))
                {
                    piecesCoordinates.add(new int[] {i,j});
                }
            }
        }
        return piecesCoordinates;
    }
}
