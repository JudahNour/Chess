import java.util.ArrayList;
import java.util.Scanner;

public abstract class Piece {
    private boolean isWhite;
    private boolean isKing;
    private boolean isPawn;
    private boolean isRook;
    private boolean enPassant = false;

    /**
     * constructor for the piece
     * @param iW boolean value that isWhite is set to
     * @param iK boolean value that isKing is set to
     * @param iP boolean value that isPawn is set to
     * @param iR boolean value that isRook is set to
     */
    public Piece(boolean iW, boolean iK, boolean iP, boolean iR)
    {
        isWhite = iW;
        isKing = iK;
        isPawn = iP;
        isRook = iR;
    }

    /**
     * getter method for isWhite
     * @return boolean value of isWhite
     */
    public boolean isWhite()
    {
        return isWhite;
    }

    /**
     * preconditions: The piece to move is not null and is actually on the board and is the color of the player
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @return boolean value of whether the piece actually moves in that manner
     */
    public abstract boolean isValidMove(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard);

    /**
     * Overridden toString method
     * @return String. W if the piece is white and B if it is black
     */
    public String toString() {
        if(isWhite)
            return("W");
        return("B");
    }

    /**
     * preconditions: the coordinates actually exist on the board
     * @param coordinates int[] of the coordinates of the piece that maybe is threatening the king
     * @param theBoard Board that represents the board
     * @param blackMate boolean. true if we are checking if black is threatening the white king
     * @return boolean. true if the piece is threatening the opposing king and false otherwise
     */
    public boolean isThreatening(int[] coordinates, Board theBoard, boolean blackMate) {
        if(blackMate) //checks if it would be a valid move to go from the coordinates to the kings coordinates
        {
            return(isValidMove(coordinates[0],coordinates[1], theBoard.getWhiteKingCoord()[0],theBoard.getWhiteKingCoord()[1],false, theBoard));
        }
        else
        {
            return(isValidMove(coordinates[0],coordinates[1], theBoard.getBlackKingCoord()[0],theBoard.getBlackKingCoord()[1],true, theBoard));
        }
    }

    /**
     * preconditions: the point in question has a piece on it that is not null
     * @param coordinates int[] of the coordinates of the piece in question
     * @param theBoard Board that represents the board
     * @param isWhiteTurn boolean that is true if it is white's turn and false if it is black's turn
     * @return ArrayList<int[]> the ArrayList of valid moves for the piece on the board
     */
    public ArrayList<int[]> validMoves(int[] coordinates, Board theBoard, boolean isWhiteTurn)
    {
        ArrayList<int[]> piecesCoordinates = new ArrayList<int[]>();
        for(int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                boolean attackSameColor = false;
                if(theBoard.getTheBoard()[i][j].getPiece() != null && theBoard.getTheBoard()[i][j].getPiece().isWhite == isWhiteTurn)
                {
                    attackSameColor = true;
                }
                if(!attackSameColor && isValidMove(coordinates[0],coordinates[1],i,j,isWhiteTurn,theBoard))
                {
                    piecesCoordinates.add(new int[] {i,j});
                }
            }
        }
        return piecesCoordinates;
    }

    /**
     * getter method for isKing
     * @return boolean that is true if the piece is a King
     */
    public boolean isKing() {
        return isKing;
    }

    /**
     * getter method for isPawn
     * @return boolean that is true if the piece is a Pawn
     */
    public boolean isPawn() {
        return isPawn;
    }

    /**
     * getter method for isRook
     * @return boolean that is true if the piece is a Rook
     */
    public boolean isRook() {
        return isRook;
    }

    /**
     * preconditions: the move was a valid move that occurred
     * @param secondX int final X position of the pawn
     * @param secondY int final Y position of the pawn
     * @param isWhiteTurn boolean value of whether it is white's turn or not
     * @param theBoard the Board that represents the game board.
     * The method asks for a piece and changes it into the type desired if a pawn is being promoted
     */
    public void promote(int secondX, int secondY, boolean isWhiteTurn, Board theBoard)
    {
        if(isPawn && (secondX == 0 || secondX == 7)) //if it is a pawn that is in a promotion square
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Queen, Rook, Bishop, or Knight");
            String piece = keyboard.nextLine();
            if(piece.equals("Queen")) //replaces the pawn with a new piece
            {
                theBoard.getTheBoard()[secondX][secondY] = new Point(new Queen(isWhiteTurn));
            }
            else if(piece.equals("Rook"))
            {
                theBoard.getTheBoard()[secondX][secondY] = new Point(new Rook(isWhiteTurn));
            }
            else if(piece.equals("Bishop"))
            {
                theBoard.getTheBoard()[secondX][secondY] = new Point(new Bishop(isWhiteTurn));
            }
            else if(piece.equals("Knight"))
            {
                theBoard.getTheBoard()[secondX][secondY] = new Point(new Knight(isWhiteTurn));
            }
            else
            {
                System.out.println("Typo Detected: Automatically Assigned Queen");
                theBoard.getTheBoard()[secondX][secondY] = new Point(new Queen(isWhiteTurn));
            }
        }
    }

    /**
     * setter method for enPassant
     * @param b boolean that represents if a pawn is enPassanting at a point in time
     */
    public void setEnPassant(boolean b)
    {
        enPassant = b;
    }

    /**
     * preconditions: coordinates are on the board and the piece to move is not null
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param theBoard not null Board that represents the gameBoard
     * @param isWhiteTurn boolean value that is true if it's whites turn and false otherwise
     * @return true if castling occurs. false otherwise. The board will be updated with the new position if castling occurs.
     */
    public abstract boolean isCastling(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard);
}
