package projectoverlord;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    
    @Override
    public void changeInPlay(boolean value)
    {
        inPlay = false;
    }
    
    @Override
    public BufferedImage getPieceIcon()
    {
        BufferedImage kingIcon = null;
        try
        {
            if (player.equals("black")) {
                kingIcon = ImageIO.read(new File(blackChessIconFilePath + "KingIcon.Png"));
            } else if (player.equals("white")) {
                kingIcon = ImageIO.read(new File(whiteChessIconFilePath + "KingIcon.Png"));
            }
        }
        catch (IOException ex)
        {
            System.out.println("Didnt get the file ya bum");
        }
        
        return kingIcon;
    }
    
}

