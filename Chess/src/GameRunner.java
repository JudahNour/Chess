import java.util.ArrayList;
import java.util.Scanner;

public class GameRunner {
    /**
     * Main method that controls the running of the Chess game
     * @param args No args.
     */
    public static void main(String[] args)
    {
        boolean isWhiteTurn = true;
        Board theBoard = new Board(); //creating board
        while(!gameEnd(theBoard, isWhiteTurn)) //checks if mate has occurred. White mates if !isWhiteTurn, black if isWhiteTurn
            //There is a redundant check for the first turn, but this is for increased readability
        {
            theBoard.printTheBoard();
            move(isWhiteTurn,theBoard); //moves the piece
            theBoard.setIsCheck(false); //after the move the player is not in check
            isWhiteTurn = !isWhiteTurn; //flips whose turn
        }
        theBoard.printTheBoard();
        if(theBoard.isCheck())
        {
            if(isWhiteTurn)
                System.out.println("Black Wins: Checkmate");
            else
                System.out.println("White Wins: Checkmate");
        }
        else //if there was a mate with no check its a stalemate
        {
            System.out.println("Tie: Stalemate");
        }

    }

    /**
     * preconditions: theBoard is not null and is initialized
     * @param isWhiteTurn boolean representing if it's whites turn
     * @param theBoard Board that represents the game board
     */
    public static void move(boolean isWhiteTurn, Board theBoard) {
        Scanner keyboard = new Scanner(System.in); //getting the input for the move
        System.out.println("What is the first row value");
        int firstX = Integer.parseInt(keyboard.nextLine());
        System.out.println("What is the first column value");
        int firstY = Integer.parseInt(keyboard.nextLine());
        System.out.println("What is the second row value");
        int secondX = Integer.parseInt(keyboard.nextLine());
        System.out.println("What is the second column value");
        int secondY= Integer.parseInt(keyboard.nextLine());
        if(move(firstX,firstY,secondX,secondY, isWhiteTurn, theBoard)) //returns false if valid move
        {
            System.out.println("Invalid Move: Try Again");
            move(isWhiteTurn, theBoard); //recursive call for another move
        }
        theBoard.removeRemnants(theBoard); //removes the en passant remnants from the board


    }

    /**
     *
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean representing whose turn it is
     * @param theBoard not null Board that represents the gameBoard
     * @return boolean. Returns true if move isn't valid. If the move is valid it moves the piece on the board
     */
    public static boolean move(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(secondX >7 || secondX<0 || secondY >7 || secondY<0) { //checking if the point is even on the board
            return true;
        }
        if(firstX >7 || firstX<0 || firstY >7 || firstY<0) {
            return true;
        }
        Point[][] grid = theBoard.getTheBoard(); //getting the array of points
        Piece current = grid[firstX][firstY].getPiece();
        Piece next = grid[secondX][secondY].getPiece();
        if(current == null || current.isWhite() != isWhiteTurn || (next != null && next.isWhite() == isWhiteTurn))
            //the initial piece must be the same color as the person whose turn it is and the final spot must be not the same color as the piece
        {

            return true;
        }
        if(current.isValidMove(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard)) //if the move is the proper direction and distance it is validd
        {
            Point tempSecond = grid[secondX][secondY];
            Point tempFirst = grid[firstX][firstY]; //backups so we can swap things
            int[] oldKingCoord = updateKingCoord(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard);

            grid[secondX][secondY] = grid[firstX][firstY];
            grid[firstX][firstY] = new Point(null); //gets rid of original place
            if(!isCheck(theBoard, isWhiteTurn)) { //makes sure the next turn doesn't leave you in check
                grid[secondX][secondY].getPiece().promote(secondX,secondY,isWhiteTurn,theBoard);
                theBoard.enPassant(firstX, secondX, firstY, secondY, theBoard, tempSecond); //updates en passant rights
                theBoard.updateCastling(firstX, secondX, firstY, secondY, isWhiteTurn); //fixes castling rights
                return false;
            }
            else { //reverses the move if it would leave in check
                updateKingCoord(secondX,secondY,oldKingCoord[0],oldKingCoord[1],isWhiteTurn,theBoard);
                grid[secondX][secondY] = tempSecond;
                grid[firstX][firstY] = tempFirst;
                return true;
            }
        }
        else if(current.isCastling(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard)) //castling is entirely separate from the other checks as it has vastly different
            //rules than the rest of chess (in that you can't do it in check and you can't pass over a threatened square etc)
        {
            return false;
        }

        return true;
    }

    /**
     * preconditions: Valid move has occurred
     * @param firstX int first row number
     * @param firstY int first column number
     * @param secondX int second row number
     * @param secondY int second column number
     * @param isWhiteTurn boolean representing whose turn it is
     * @param theBoard not null Board that represents the gameBoard
     * @return int[] of the old coordinates of the King. Changes the old coordinates to the new ones
     */
    private static int[] updateKingCoord(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        int[] oldCoord;
        if(isWhiteTurn)
        {
            oldCoord = new int[]{theBoard.getWhiteKingCoord()[0], theBoard.getWhiteKingCoord()[1]};
            if(theBoard.getTheBoard()[firstX][firstY].getPiece() != null && theBoard.getTheBoard()[firstX][firstY].getPiece().isKing())
            {
                int[] kingCoord = theBoard.getWhiteKingCoord();
                kingCoord[0] = secondX;
                kingCoord[1] = secondY;
            }
        }
        else
        {
            oldCoord = new int[]{theBoard.getBlackKingCoord()[0], theBoard.getBlackKingCoord()[1]};
            if(theBoard.getTheBoard()[firstX][firstY].getPiece() != null && theBoard.getTheBoard()[firstX][firstY].getPiece().isKing())
            {
                int[] kingCoord = theBoard.getBlackKingCoord();
                kingCoord[0] = secondX;
                kingCoord[1] = secondY;
            }
        }
        return oldCoord;
    }

    /**
     * preconditions: Board is not null
     * @param theBoard Board that holds the state of the gameBoard
     * @param blackMate boolean that is true if we are checking if black is mating, false if white is
     * @return boolean that is true if the game ends and false if the game should continue. Updates whether check is occurring in the Board
     */
    public static boolean gameEnd(Board theBoard, boolean blackMate ) {
        if(isCheck(theBoard,blackMate))
        {
            theBoard.setIsCheck(true); //sets check to true if in check
        }
        return(isMate(theBoard,blackMate)); //the game ends if mate occurred
    }

    /**
     * preconditions: Board is not null
     * @param theBoard Board that holds the state of the gameBoard
     * @param blackIsChecking boolean that is true if we are checking if black is checking, false if white is
     * @return boolean that is true if a check is occurring and false otherwise
     */
    public static boolean isCheck(Board theBoard, boolean blackIsChecking) {
        for(int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                Piece piece = theBoard.getTheBoard()[i][j].getPiece();
                if(piece != null && (piece.isWhite() != blackIsChecking)  && piece.isThreatening(new int[] {i,j}, theBoard, blackIsChecking)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * preconditions: Board is not null
     * @param theBoard  Board that holds the state of the gameBoard
     * @param blackMate boolean that is true if we are checking if black is mating, false if white is
     * @return boolean. True if there are no valid moves left
     */
    public static boolean isMate(Board theBoard, boolean blackMate) {
        ArrayList<int[]> pieceIndicies = theBoard.getPiecesOfAColor(blackMate); //gets all the pieces of the color being checked
        for(int[] coord: pieceIndicies)
        {
            Piece piece = theBoard.getTheBoard()[coord[0]][coord[1]].getPiece();
            ArrayList<int[]> moves = piece.validMoves(coord, theBoard, blackMate); //gets all the valid moves for the given piece
            for(int[] move : moves)
            {
                Point[][] grid = theBoard.getTheBoard();
                Point tempSecond = grid[move[0]][move[1]];
                Point tempFirst = grid[coord[0]][coord[1]];

                int[] oldKingCoord = updateKingCoord(coord[0], coord[1], move[0], move[1], blackMate, theBoard);


                grid[move[0]][move[1]] = grid[coord[0]][coord[1]];
                grid[coord[0]][coord[1]] = new Point(null);

                if(!isCheck(theBoard,blackMate)) //checks if the move that just occurred results doesn't result in a check
                {
                    updateKingCoord(move[0],move[1],coord[0],coord[1],blackMate, theBoard);
                    grid[move[0]][move[1]] = tempSecond;
                    grid[coord[0]][coord[1]] = tempFirst; //reverses the move
                    return false;
                }
                updateKingCoord(move[0],move[1],coord[0],coord[1],blackMate, theBoard); //reverses the move
                grid[move[0]][move[1]] = tempSecond;
                grid[coord[0]][coord[1]] = tempFirst;

            }
        }
        return true; //if none of the moves could get one out of check then it is mate
    }
}
