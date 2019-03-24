package projectoverlord.ProjectOverlord;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rook extends Piece
{   
    public Rook(ChessBoard board, String player, String piece, int x, int y)
    {
        //setting initial board position

        super(board, player, piece, x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        int deltaX = finX - this.curX;
        int deltaY = finY - this.curY;
        
        //x is unchanged xor y is unchanged
        if(!(deltaX == 0 && deltaY == 0))
            return (finX == this.curX) ^ (finY == this.curY);
        //Default to false
        return false;
    }
    
    @Override
    public BufferedImage getPieceIcon()
    {
        BufferedImage rookIcon = null;
        try
        {
            rookIcon = ImageIO.read(new File(chessIconFilePath + "RookIcon.Png"));
        }
        catch (IOException ex)
        {
            System.out.println("Didnt get the file ya bum");
        }
        return rookIcon;
    }
}

