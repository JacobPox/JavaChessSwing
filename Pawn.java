package projectoverlord.ProjectOverlord;

import java.lang.*;

public class Pawn extends Piece
{   
    protected boolean hasMoved = false;
    
    public Pawn(ChessBoard board, String player, String piece, int x, int y)
    {
        //setting initial board position

        super(board, player, piece, x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        /* Check to see if the pawn is on the starting row. If it is on the starting row, it can move forward twice. It doesn't matter 
        which starting row it is on (row 2 or 6) because it would move out of bounds anyways if it was on the enemies home row and tried
        to move twice. 
        */
        
        if (!(this.curY == 2 || this.curY == 6))
        {
            hasMoved = true;
        }
        
        int deltaX = finX - this.curX;
        int deltaY = finY - this.curY;
        
        /* Cases
        1. Moved forward once
        2. Moved forward twice
        3. Moved diagonally once.
        */
        
        return
        (
            (Math.abs(deltaY) == 1 && deltaX == 0 && board.isEmpty(finX, finY)) ||
            (Math.abs(deltaY) == 2 && deltaX == 0 && hasMoved == false && board.isEmpty(finX, finY)) ||
            (Math.abs(deltaY) == 1 && Math.abs(deltaX) == 1 && isTakeable(finX, finY))
        );
        
    }
}
