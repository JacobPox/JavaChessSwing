package projectoverlord.ProjectOverlord;

public class Knight extends Piece
{   
    public Knight(ChessBoard board, String player, String piece, int x, int y)
    {
        //setting initial board position
        super(board, player, piece, x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        //eight legal moves
        if((finX == this.curX+1) && (finY == this.curY + 2))
            return true;
        if((finX == this.curX+1) && (finY == this.curY - 2))
            return true;
        if((finX == this.curX-1) && (finY == this.curY + 2))
            return true;
        if((finX == this.curX-1) && (finY == this.curY - 2))
            return true;
        
        if((finY == this.curY+1) && (finX == this.curX + 2))
            return true;
        if((finY == this.curY+1) && (finX == this.curX - 2))
            return true;
        if((finY == this.curY-1) && (finX == this.curX + 2))
            return true;
        if((finY == this.curY-1) && (finX == this.curX - 2))
            return true;
        return false;
    }
    
    //knights dont care if the path is clear
    @Override
    public boolean pathClear(int x, int y)
    {
        return true;
    }
}
