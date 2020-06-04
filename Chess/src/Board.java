import java.util.ArrayList;

public class Board {
    private Point[][] theBoard;
    private int[] blackKingCoord;
    private int[] whiteKingCoord;
    private boolean isCheck;
    public Board()
    {
        isCheck = false;

        theBoard = new Point[8][8];
        theBoard[0][0] = new Point(new Rook(false));
        theBoard[0][2] = new Point(new Bishop(false));
        theBoard[0][1] = new Point(new Knight(false));
        theBoard[0][3] = new Point(new Queen(false));
        theBoard[0][4] = new Point(new King(false));
        theBoard[0][6] = new Point(new Knight(false));
        theBoard[0][5] = new Point(new Bishop(false));
        theBoard[0][7] = new Point(new Rook(false));
        blackKingCoord = new int[] {0,4};

        for(int i = 0;i<8;i++)
        {
            theBoard[1][i] = new Point(new Pawn(false));
            theBoard[6][i] = new Point(new Pawn(true));
        }

        theBoard[7][0] = new Point(new Rook(true));
        theBoard[7][2] = new Point(new Bishop(true));
        theBoard[7][1] = new Point(new Knight(true));
        theBoard[7][4] = new Point(new King(true));
        theBoard[7][3] = new Point(new Queen(true));
        theBoard[7][6] = new Point(new Knight(true));
        theBoard[7][5] = new Point(new Bishop(true));
        theBoard[7][7] = new Point(new Rook(true));
        whiteKingCoord = new int[] {0,4};

        for(int i = 2; i<6;i++)
        {
            for(int j = 0; j<8;j++)
            {
                theBoard[i][j] = new Point(null);
            }
        }

    }
    public void printTheBoard()
    {
        System.out.println("     0    1    2    3    4    5    6    7  ");
        System.out.println("___________________________________________");
        for(int i = 0; i<8; i++)
        {
            System.out.print(i + " |");
            for(int j = 0; j<8; j++)
            {
                if(theBoard[i][j].getPiece() == null)
                {
                    System.out.print("    |");
                }
                else
                {
                    System.out.print(" " + theBoard[i][j].getPiece() + " |");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
        if(isCheck)
        {
            System.out.println("CHECK");
        }
    }
    public Point[][] getTheBoard()
    {
        return theBoard;
    }

    public int[] getBlackKingCoord() {
        return blackKingCoord;
    }

    public int[] getWhiteKingCoord() {
        return whiteKingCoord;
    }

    public ArrayList<int[]> getPiecesOfAColor(boolean lookForWhite)
    {
        ArrayList<int[]> piecesCoordinates = new ArrayList<int[]>();
        for(int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                Piece a = getTheBoard()[i][j].getPiece();
                if(a != null && a.isWhite() == lookForWhite)
                {
                    piecesCoordinates.add(new int[] {i,j});
                }
            }
        }
        return piecesCoordinates;
    }

    public void setIsCheck(boolean a)
    {
        isCheck = a;
    }
}
