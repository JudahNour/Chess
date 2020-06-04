import java.util.ArrayList;
import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args)
    {
        boolean isWhiteTurn = true;
        Board theBoard = new Board();
        while(!checkmate(theBoard, isWhiteTurn)) //checks if checkmate has occurred. White checkmates if !isWhiteTurn, black if isWhiteTurn
        {
            theBoard.printTheBoard();
            move(isWhiteTurn,theBoard);
            isWhiteTurn = !isWhiteTurn;
        }
    }

    public static void move(boolean isWhiteTurn, Board theBoard) {
        Scanner keyboard = new Scanner(System.in);
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
            move(isWhiteTurn, theBoard);
            //make sure to get rid of remnants of last turn before move
        }

    }

    public static boolean move(int firstX, int firstY, int secondX, int secondY, boolean isWhiteTurn, Board theBoard) {
        if(secondX >7 || secondX<0 || secondY >7 || secondY<0) {
            return true;
        }
        if(firstX >7 || firstX<0 || firstY >7 || firstY<0) {
            return true;
        }
        Point[][] grid = theBoard.getTheBoard();
        Piece current = grid[firstX][firstY].getPiece();
        Piece next = grid[secondX][secondY].getPiece();
        if(current == null || current.isWhite() != isWhiteTurn || (next != null && next.isWhite() == isWhiteTurn))
        {

            return true;
        }
        if(current.isValidMove(firstX, firstY, secondX, secondY, isWhiteTurn, theBoard))
        {

            Point tempSecond = grid[secondX][secondY];
            Point tempFirst = grid[firstX][firstY];

            grid[secondX][secondY] = grid[firstX][firstY];
            grid[firstX][firstY] = new Point(null);
            if(!isCheck(theBoard, isWhiteTurn)) {
                return false;
            }
            else {
                grid[secondX][secondY] = tempSecond;
                grid[firstX][firstY] = tempFirst;

                return true;
            }
        }

        return true;
    }

    public static boolean checkmate(Board theBoard, boolean blackMate ) {
        boolean checking = false;
        boolean mating = false;
        if(isCheck(theBoard,blackMate))
        {
            theBoard.setIsCheck(true);
            checking = true;
        }
        if(isMate(theBoard,blackMate))
        {
            mating = true;
        }
        return(checking && mating);
    }

    public static boolean isCheck(Board theBoard, boolean blackIsChecking) {
        for(int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                Piece piece = theBoard.getTheBoard()[i][j].getPiece();
                if(piece != null && (piece.isWhite() != blackIsChecking) && piece.isThreatening(new int[] {i,j}, theBoard, blackIsChecking))
                    return true;
            }
        }
        return false;
    }

    public static boolean isMate(Board theBoard, boolean blackMate) {
        ArrayList<int[]> pieceIndicies = theBoard.getPiecesOfAColor(blackMate);
        for(int[] coord: pieceIndicies)
        {
            Piece piece = theBoard.getTheBoard()[coord[0]][coord[1]].getPiece();
            ArrayList<int[]> moves = piece.validMoves(coord, theBoard, blackMate);
            for(int[] move : moves)
            {
                Point[][] grid = theBoard.getTheBoard();
                Point tempSecond = grid[move[0]][move[1]];
                Point tempFirst = grid[coord[0]][coord[1]];

                grid[move[0]][move[1]] = grid[coord[0]][coord[1]];
                grid[coord[0]][coord[1]] = new Point(null);

                if(!isCheck(theBoard,blackMate))
                {
                    return false;
                }
                grid[move[0]][move[1]] = tempSecond;
                grid[coord[0]][coord[1]] = tempFirst;

            }
        }
        return true;
        //Make arrayList of all indicies of color white if blackMate and color black if !blackMate
        //Make arrayList of all possible moves for each index and check if for each move they are in check, if not return false.
    }
}
