package projectoverlord.ProjectOverlord;

import java.lang.*;

public class King extends Piece
{   
    public King(ChessBoard board, String player, String piece, int x, int y)
    {
        super(board, player, piece, x, y);
    }
    
    @Override
    public boolean moveLegal(int x, int y)
    {
        int deltaX = x - this.curX;
        int deltaY = y - this.curY;
        return ((Math.abs(deltaX) == 1 && deltaY == 0) || (Math.abs(deltaY) == 1 && deltaX == 0) || (Math.abs(deltaY) == 1 && Math.abs(deltaX) == 1));

    }
    
}
