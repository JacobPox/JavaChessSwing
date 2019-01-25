/*
Right now this file has a lot of methods that dont exist yet in ChessBoard
and the x and y need to be changed to row and col and swapped in order
eventually like matrix notation to work with the board (or swap the board to
(x, y) pairs if we decide that that's an easier system to work with) If we keep
matrix notation that will be important to keep in mind when working with the
specific pieces
*/

package piece;

public class Piece
{
    private static Board board;  //every piece shares the same instance of board
    private boolean inPlay;      //in Play is only false when a piece gets taken
    private String player;
    private String pieceType; 
    
    //current x and y positions (indeces of board[x][y]) on the board
    private int curX;
    private int curY;
    
    /*
    constructor gets passed the starting location of each piece because not
    every instance of the same child class will have the same initial location
    */
    public Piece(String player, String pieceType, int x, int y)
    {
        this.player = player;
        this.pieceType = pieceType;
        this.inPlay = true; 
        this.curX = x;
        this.curY = y;
    }
    
    
    //Either moves piece to (x, y) or tells user to pick another location
    public void updatePos(int x, int y)
    {
        /*
        Checks if the path up to (but not inclusing) position (x, y) is clear,
        if the move is legal (regardless of other pieces), and if (x, y)
        is a valid position on the board
        */
        if(inBounds(x, y) && pathClear(x, y) && moveLegal(x, y))
        {
            //if (x, y) itself is clear all other legalities have been checked
            if(spaceEmpty(x, y))
            {
                //update current position
                this.curX = x;
                this.curY = y;
                
                //tell board[x][y] that this piece is here now
                board.placeMyPiece(x, y);
            }
            /*
            if the space isnt empty but everything else checks out
            then the piece is either the player's own piece (in which
            case they can't move there) or it's their opponents and
            they can take the piece
            */
            else
            {
                if(isTakeable(x, y))
                {
                    /*
                    not a real method in ChessBoard yet
                    tell the board to set the opponents inPlay value to false
                    for the piece at (x, y)
                    */
                    board.removePiece(x, y);
                    
                    //update current position
                    this.curX = x;
                    this.curY = y;
                    
                    /*
                    not a real method in ChessBoard yet
                    should set board[x][y] to this player's piece
                    */
                    board.placeMyPiece(x, y);
                }
                else
                {
                    /*
                    raise an error to let the player know that the move they've
                    attempted it illegal and loop their turn again until they
                    select a valid position
                    */
                }
            }
        }
        else
        {
            //raise the same illegal move exception as above and loop turn again
        }
    }
    
    //returns true if (x, y) is on the board and false if not
    public boolean inBounds(int x, int y)
    {
        //checking if (x, y) is on the board
        if(x < 0 || x > 7)
            return false;
        if(y < 0 || y > 7)
            return false;
        return true;
    }
    
    //returns true if no piece is on (x, y)
    public boolean spaceEmpty(int x, int y)
    {
       //not a real method in ChessBoard yet
       return board.isEmpty(x, y);
    }
    
    
    //returns true if the opponents piece is at (x, y)
    public boolean isTakeable(int x, int y)
    {
        //can't move on top of your own piece
        if(board.playerAt(x, y) == this.player)
            return false;
        else
        {
            return true;
        }
    }
    
    
    public boolean pathClear(int finX, int finY)
    {
        /*
        ChessBoard method pathClear checks all spaces from the current position
        to the desired final position. If there is an obstruction (of any piece)
        return false
        */
        if(board.pathIsClear(this.curX, this.curY, finX, finY))
            return true;
        else
            return false;
        //knight's pathClear will always return true
    }
    
    /*
    Every child class will override this method and check if a move is legal
    (regardless of other pieces since obstructions are already being checked by
    pathClear) This method should also be a wrapper for putsSelfInCheck and use
    that as one of the ways to determine if a move is legal
    */
    public boolean moveLegal(int x, int y)
    {
        return false;
    }
    
    /*
    always overridden because of the nature
    of every child Piece's unique movements.
    protected because it shouldn't be called
    by anything other than moveLegal
    */
    protected boolean putsSelfInCheck(int x, int y)
    {
        return false;
    }
    
    //toString
    @override
    public String toString()
    {
        return player + "'s " + pieceType + " is at (" + x + ", " + y + ")";   
    }
}
