import java.util.ArrayList;

public class Board {
    private Point[][] theBoard;
    private int[] blackKingCoord;
    private int[] whiteKingCoord;
    private boolean isCheck;
    private boolean whiteKingSideCastle = true;
    //these 4 represent whether these types of castling are allowed based on prior moves
    private boolean blackKingSideCastle = true;
    private boolean whiteQueenSideCastle = true;
    private boolean blackQueenSideCastle = true;

    /**
     * Board constructor
     * Creates a board with all the initial positions in chess
     */
    public Board()
    {
        isCheck = false;
        //creates black pieces
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

        for(int i = 0;i<8;i++) //creates the two rows of pawns
        {
            theBoard[1][i] = new Point(new Pawn(false));
            theBoard[6][i] = new Point(new Pawn(true));
        }
        //creates white pieces
        theBoard[7][0] = new Point(new Rook(true));
        theBoard[7][2] = new Point(new Bishop(true));
        theBoard[7][1] = new Point(new Knight(true));
        theBoard[7][4] = new Point(new King(true));
        theBoard[7][3] = new Point(new Queen(true));
        theBoard[7][6] = new Point(new Knight(true));
        theBoard[7][5] = new Point(new Bishop(true));
        theBoard[7][7] = new Point(new Rook(true));
        whiteKingCoord = new int[] {7,4};

        //creates blank spots
        for(int i = 2; i<6;i++)
        {
            for(int j = 0; j<8;j++)
            {
                theBoard[i][j] = new Point(null);
            }
        }

    }

    /**
     * Method that prints the current state of the board and whether a check is occurring
     */
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

    /**
     * getter method for the point array instance variable
     * @return Point[][] theBoard instance variable
     */
    public Point[][] getTheBoard()
    {
        return theBoard;
    }

    /**
     * getter method for the black king's coordinates
     * @return int[] the black king's coordinates
     */
    public int[] getBlackKingCoord() {
        return blackKingCoord;
    }
    /**
     * getter method for the white king's coordinates
     * @return int[] the white king's coordinates
     */
    public int[] getWhiteKingCoord() {
        return whiteKingCoord;
    }

    /**
     * preconditions: Board is not null
     * @param lookForWhite boolean value that is true if we should look for white pieces and false for black pieces
     * @return ArrayList<int[]> that holds all the coordinates of the pieces of the given color
     */
    public ArrayList<int[]> getPiecesOfAColor(boolean lookForWhite)
    {
        ArrayList<int[]> piecesCoordinates = new ArrayList<int[]>();
        for(int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                Piece a = getTheBoard()[i][j].getPiece(); //gets the piece
                if(a != null && a.isWhite() == lookForWhite) //if the piece isn't null and has the correct color
                {
                    piecesCoordinates.add(new int[] {i,j}); //adds to the ArrayList
                }
            }
        }
        return piecesCoordinates;
    }

    /**
     * setter method for isCheck
     * @param a boolean that holds the new value that isCheck is updated to
     */
    public void setIsCheck(boolean a)
    {
        isCheck = a;
    }

    /**
     * precondition: the board is not null
     * @param theBoard the Board in question to remove the  en passant remnants of
     * updates the remnant count after the turn is done, by getting rid of remnants and getting rid of en passant rights
     */
    public void removeRemnants(Board theBoard) {
        for(int i = 0; i<8;i++)
        {
            //if the remnant count is 1 it goes to 2 and otherwise it resets back to 0. This allows us to keep en passant remnants for 1 turn
            int j = 2;
            if(theBoard.getTheBoard()[j][i].getRemnantMoveCount() == 1)
                theBoard.getTheBoard()[j][i].incrementRemnantMoveCount();
            else
            {
                theBoard.getTheBoard()[j][i].setRemnantMoveCount(0);
            }
            if (theBoard.getTheBoard()[j][i].getPiece() != null)
            {
                theBoard.getTheBoard()[j][i].getPiece().setEnPassant(false); //removes the en passant from each piece
            }
            j = 5;
            if(theBoard.getTheBoard()[j][i].getRemnantMoveCount() == 1)
                theBoard.getTheBoard()[j][i].incrementRemnantMoveCount();
            else
            {
                theBoard.getTheBoard()[j][i].setRemnantMoveCount(0);
            }
            if (theBoard.getTheBoard()[j][i].getPiece() != null)
            {
                theBoard.getTheBoard()[j][i].getPiece().setEnPassant(false);
            }

        }
    }

    /**
     * preconditions: Board is not null and a turn has just occurred and was allowed
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param tempSecond Point that is being overtook by the pawn
     * After the method has ran the en passant rights are updated so remnants are added and pieces
     * that were taken by en passant are removed
     */
    public void enPassant(int firstX, int secondX, int firstY, int secondY, Board theBoard, Point tempSecond) {
        if(theBoard.getTheBoard()[secondX][secondY].getPiece().isPawn())
        {
            if(Math.abs(firstX-secondX) == 2) //checks if the pawn has jumped 2 so a remnant should be left
                theBoard.getTheBoard()[(secondX+firstX)/2][secondY].incrementRemnantMoveCount();
            else if(tempSecond.getPiece() == null && firstY != secondY) { //if en passant occurred delete the pawn it took
                theBoard.getTheBoard()[firstX][secondY] = new Point(null);
            }
        }

    }

    /**
     * getter method for isCheck instance variable
     * @return boolean value of isCheck
     */
    public boolean isCheck() {
        return isCheck;
    }

    /**
     * setter method for whiteKingSideCastle
     * @param b the boolean value that whiteKingSideCastle is set to
     */
    public void setWhiteKingSideCastle(boolean b)
    {
        whiteKingSideCastle = b;
    }
    /**
     * setter method for blackKingSideCastle
     * @param b the boolean value that blackKingSideCastle is set to
     */
    public void setBlackKingSideCastle(boolean b)
    {
        blackKingSideCastle = b;
    }
    /**
     * setter method for whiteQueenSideCastle
     * @param b the boolean value that whiteQueenSideCastle is set to
     */
    public void setWhiteQueenSideCastle(boolean b)
    {
        whiteQueenSideCastle = b;
    }
    /**
     * setter method for blackQueenSideCastle
     * @param b the boolean value that blackQueenSideCastle is set to
     */
    public void setBlackQueenSideCastle(boolean b)
    {
        blackQueenSideCastle = b;
    }

    /**
     * getter method for blackKingSideCastle
     * @return boolean value of blackKingSideCastle
     */
    public boolean isBlackKingSideCastle() {
        return blackKingSideCastle;
    }
    /**
     * getter method for whiteKingSideCastle
     * @return boolean value of whiteKingSideCastle
     */
    public boolean isWhiteKingSideCastle() {
        return whiteKingSideCastle;
    }
    /**
     * getter method for blackQueenSideCastle
     * @return boolean value of blackQueenSideCastle
     */
    public boolean isBlackQueenSideCastle() {
        return blackQueenSideCastle;
    }
    /**
     * getter method for whiteQueenSideCastle
     * @return boolean value of whiteQueenSideCastle
     */
    public boolean isWhiteQueenSideCastle() {
        return whiteQueenSideCastle;
    }

    /**
     * method that updates the castling rights after a move
     * preconditions: move has occurred
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     */
    public void updateCastling(int firstX, int secondX, int firstY, int secondY, boolean isWhiteTurn) {
        Piece piece = theBoard[secondX][secondY].getPiece();
        if(piece.isKing()) //if a king moves, the player can't castle anymore
        {
            if(isWhiteTurn)
            {
                whiteKingSideCastle = false;
                whiteQueenSideCastle = false;
            }
            else
            {
                blackKingSideCastle =false;
                blackQueenSideCastle = false;
            }
        }
        if(piece.isRook()) //if the rooks on either side move, that side cannot castle anymore
        {
            if(isWhiteTurn)
            {
                if(firstX == 7 && firstY == 0)
                    whiteQueenSideCastle =false;
                else if(firstX == 7 && firstY == 7)
                    whiteKingSideCastle = false;
            }
            else
            {
                if(firstX == 0 && firstY ==0)
                    blackQueenSideCastle =false;
                else if(firstX == 0 && firstY == 7)
                     blackKingSideCastle = false;
            }
        }
    }
}
