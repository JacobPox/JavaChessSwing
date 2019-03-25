import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Queen extends Piece
{
    public Queen(ChessBoard board, String player, String piece, int x, int y)
    {
        super(board, player, piece, x, y);
    }
    
    @Override
    public boolean moveLegal(int finX, int finY)
    {
        //changes in x and y
        int deltaX = finX - this.curX;
        int deltaY = finY - this.curY;
        
        //checks if the queen moves like a bishop, then a rook
        if(!(deltaX == 0 && deltaY == 0))
        {
            if((deltaX == deltaY || deltaX == -deltaY) && (deltaX != 0))
                return true;
            if((finX == this.curX) ^ (finY == this.curY))
                return true;
        }
        //either false because no movement or not rook/bishop style movement
        return false;
    }
    
    @Override
    public BufferedImage getPieceIcon()
    {
        BufferedImage queenIcon = null;
        try
        {
            if (player.equals("black")) {
                queenIcon = ImageIO.read(new File(blackChessIconFilePath + "QueenIcon.Png"));
            } else if (player.equals("white")) {
                queenIcon = ImageIO.read(new File(whiteChessIconFilePath + "QueenIcon.Png"));
            }
        }
        catch (IOException ex)
        {
            System.out.println("Didnt get the file ya bum");
        }
        
        return queenIcon;
    }
}
