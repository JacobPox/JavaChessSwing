public class Rook extends Piece
{   
    public Rook(String player, String piece, int x, int y)
    {
        //setting initial board position

        super(player, piece, x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        deltaX = finX - this.x;
        deltaY = finY - this.y;
        //x is unchanged xor y is unchanged
        if(!(deltaX == 0 && deltaY == 0))
            return (finX == this.curX) ^ (finY == this.curY);
        //Default to false
        return false;
    }
}
