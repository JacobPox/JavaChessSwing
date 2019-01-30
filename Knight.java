package piece;
public class Knight extends Piece
{   
    public Knight(String player, int x, int y)
    {
        //setting initial board position
        super(player, "knight", x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        //eight legal moves
        if(finX == this.curX++ && finY == this.curY + 2)
            return true;
        if(finX == this.curX++ && finY == this.curY - 2)
            return true;
        if(finX == this.curX-- && finY == this.curY + 2)
            return true;
        if(finX == this.curX-- && finY == this.curY - 2)
            return true;
        
        if(finY == this.curY++ && finX == this.curX + 2)
            return true;
        if(finY == this.curY++ && finX == this.curX - 2)
            return true;
        if(finY == this.curY-- && finX == this.curX + 2)
            return true;
        if(finY == this.curY-- && finX == this.curX - 2)
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
