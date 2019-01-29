
package piece;
public class Rook extends Piece
{
    //private final String player;
    
    public Rook(String player, int x, int y)
    {
        //setting initial board position
        super(player, "rook", x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        //either x or y must remain the same because rooks only move up or to the side
        return this.curX == finX || this.curY == finY;
    }
}
