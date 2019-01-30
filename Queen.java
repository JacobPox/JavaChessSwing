package piece;
public class Queen extends Piece
{
    public Queen(String player, int x, int y)
    {
        super(player, "queen", x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        //changes in x and y
        int deltaX = finX - this.curX;
        int deltaY = finY - this.curX;
        
        //checks if the queen moves like a bishop, then a rook
        if(!(finX == 0 && finY == 0))
        {
            if((deltaX == deltaY || deltaX == -deltaY) && (deltaX != 0))
                return true;
            if((finX == this.curX) ^ (finY == this.curY))
                return true;
        }
        //either false because no movement or not rook/bishop style movement
        return false;
    }
}
