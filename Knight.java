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
        int deltaX = finX - curX;
        int deltaY = finY - curY;
        
        return (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1) || (Math.abs(deltaY) == 2 && Math.abs(deltaX) == 1);
    }
    
    //knights dont care if the path is clear
    @Override
    public boolean pathClear(int x, int y)
    {
        return true;
    }
}
