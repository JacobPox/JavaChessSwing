package projectoverlord;

import java.awt.image.BufferedImage;

/*
Right now this file has a lot of methods that dont exist yet in ChessBoard
and the x and y need to be changed to row and col and swapped in order
eventually like matrix notation to work with the board (or swap the board to
(x, y) pairs if we decide that that's an easier system to work with) If we keep
matrix notation that will be important to keep in mind when working with the
specific pieces
*/

public class Piece // piece needs to be sent the board!!!!
{
    protected static ChessBoard board;  //every piece shares the same instance of board
    protected boolean inPlay;      //in Play is only false when a piece gets taken
    protected String player;
    protected String pieceType;
    protected BufferedImage pieceIcon;
    protected String whiteChessIconFilePath;
    protected String blackChessIconFilePath;
    
    //current x and y positions (indeces of board[x][y]) on the board
    protected int curX;
    protected int curY;
    
    /*
    constructor gets passed the starting location of each piece because not
    every instance of the same child class will have the same initial location
    */
    public Piece(ChessBoard board, String player, String pieceType, int x, int y)
    {
        this.board = board;
        this.player = player;
        this.pieceType = pieceType;
        this.inPlay = true; 
        this.curX = x;
        this.curY = y;
        this.whiteChessIconFilePath = "src\\whiteTeamIcons\\";
        this.blackChessIconFilePath = "src\\blackTeamIcons\\";
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
            if(board.isEmpty(x, y))
            {
                //leaving an empty space where we move from
                board.setThisPiece(new Empty(board, "neutral", " ", curX, curY));
      
                //update current position
                this.curX = x;
                this.curY = y;
                
                //tell board[x][y] that this piece is here now
                board.setThisPiece(this);
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
                    /* Checks to see if the piece is on the opponent's team.
                    */
                    
                    //leaving an empty space where we move from
                    board.removePiece(x, y);
                    board.setThisPiece(new Empty(board, "neutral", " ", curX, curY));
                    
                    //update current position
                    this.curX = x;
                    this.curY = y;

                    board.setThisPiece(this);
                }
                else
                {
                    /*
                    raise an error to let the player know that the move they've
                    attempted it illegal and loop their turn again until they
                    select a valid position
                    */
                    System.out.println("Can't move on your own team");
                }
            }
        }
        else
        {
            //raise the same illegal move exception as above and loop turn again
            System.out.println("Invalid move");
        }
    }
    
    //returns true if (x, y) is on the board and false if not
    public boolean inBounds(int x, int y)
    {
        //checking if (x, y) is on the board
        return !(x < 0 || x > 7 || y < 0 || y > 7);
    }
    
    //this method looks redundant but is necessary for subclass knight
    public boolean pathClear(int x, int y)
    {
        return board.pathClear(this.curX, this.curY, x, y);
    }
    
    //returns true if the opponents piece is at (x, y)
    public boolean isTakeable(int x, int y)
    {
        //can't move on top of your own piece
        return !board.playerAt(x, y).equals(this.player);
    }

    
    /*
    Every child class will override this method and check if a move is legal
    (regardless of other pieces since obstructions are already being checked by
    pathClear) This method should also be a wrapper for putsSelfInCheck and use
    that as one of the ways to determine if a move is legal
    */
    public boolean moveLegal(int x, int y)
    {
        System.out.println("I ( moveLegal() ) should be overridden");
        return true; //change this to false, but for now rook's move legal isnt getting overridden for some reason
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
    
    public void changeInPlay(boolean value)
    {
        this.inPlay = value;
    }
    
    public String getPlayer()
    {
      return this.player;
    }
    
    public String getPieceType()
    {
        return this.pieceType;
    }

    public int getX()
    {
      return this.curX;
    }

    public int getY()
    {
      return this.curY;
    }
    
    public boolean getInPlay()
    {
        return inPlay;
    }
    
    public BufferedImage getPieceIcon()
    {
        return this.pieceIcon;
    }

    //toString
    @Override
    public String toString()
    {
        return this.pieceType;  
    }
}
