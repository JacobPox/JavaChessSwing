package ProjectOverlord;
public class Bishop extends Piece
{
    public Bishop(String player, int x, int y)
    {
        //setting initial board position
        super(player, "bishop", x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        //changes in x and y
        int deltaX = finX - this.curX;
        int deltaY = finY - this.curX;
        
        //to be a diagonal line the change in x must be +/- the change in y
        return (deltaX == deltaY || deltaX == -deltaY) && (deltaX != 0);
    }
}
