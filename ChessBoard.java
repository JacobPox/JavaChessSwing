package projectoverlord;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChessBoard extends JPanel implements MouseListener
{
    Piece[][] board;
    private final JFrame frame;
    private final int iconWidth;
    private final int tileSideLength;
    private final int WIDTH;
    private final int HEIGHT;
    private JButton button;
    private JTextField keyboard;
    private Point eventBoardLocationI;
    private Point eventBoardLocationF;
    
    
    public ChessBoard()
    {
        super();
        
        board = new Piece[8][8];
        frame = new JFrame("Chess");
        iconWidth = 27;
        tileSideLength = iconWidth * 2;
        WIDTH = iconWidth * 16;
        HEIGHT = iconWidth * 16;
        eventBoardLocationI = null;
        eventBoardLocationF = new Point(-1, -1);
        
        setup();
        fillBoard();
        
        addMouseListener(this);
    }
    
    public void setup()
    {
        frame.add(this);
        //+16 and +39 keep the board perfectly, i repeat perfectly, in frame.
        frame.setSize(WIDTH + 16, HEIGHT + 39); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void fillBoard()
    {
        
        //Fills the empty spaces
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                board[x][y] = new Empty(this, "neutral", " ", x, y);
            }
        }

        /* 
        Declare all the pieces that will be used.
        Uppercase for black pieces, lowercase for white pieces.
        */

        String team1 = "black";
        String team2 = "white";

        // Rooks
        board[0][0] = new Rook(this, team1, "R", 0, 0);
        board[7][0] = new Rook(this, team1, "R", 7, 0);
        board[0][7] = new Rook(this, team2, "r", 0, 7);
        board[7][7] = new Rook(this, team2, "r", 7, 7);

        // Knights
        board[1][0] = new Knight(this, team1, "N", 1, 0);
        board[6][0] = new Knight(this, team1, "N", 6, 0);
        board[1][7] = new Knight(this, team2, "n", 1, 7);
        board[6][7] = new Knight(this, team2, "n", 6, 7);

        //Bishops
        board[2][0] = new Bishop(this, team1, "B", 2, 0);
        board[5][0] = new Bishop(this, team1, "B", 5, 0);
        board[2][7] = new Bishop(this, team2, "b", 2, 7);
        board[5][7] = new Bishop(this, team2, "b", 5, 7);

        //Queens
        board[3][0] = new Queen(this, team1, "Q", 3, 0);
        board[3][7] = new Queen(this, team2, "q", 3, 7);

        //Kings
        board[4][0] = new King(this, team1, "K", 4, 0);
        board[4][7] = new King(this, team2, "k", 4, 7);

        //Pawns
        for (int x = 0; x < 8; x++)
        {
            board[x][1] = new Pawn(this, team1, "P", x, 1);
            board[x][6] = new Pawn(this, team2, "p", x, 6);
        }
    }

    
    @Override
    public void paintComponent(Graphics g)
    {
        //drawing board and pieces on top of the board
        super.paintComponent(g);
        
        presentBoard(g);
        
    }
    
    
    //takes in a board (x, y) and returns the location on the screen as a Point
    public Point getScreenLocation(int x, int y)
    {
        //starting at the offset that lands pieces in the center of a tile
        int screenX = tileSideLength/4 + x * tileSideLength;
        int screenY = tileSideLength/4 + y * tileSideLength;
        
        return new Point(screenX, screenY);
    }
    
    /*
    goes through the board[x][y] array and send the pair(x, y)
    to getScreenLocation() which returns the position to draw
    the piece at.
    */
    public void drawPieces(Graphics g)
    {
        //Graphics g = getGraphics();
        Point screenLocation;
        for(int j = 0; j <= 7; j++)
        {
            for(int i = 0; i <= 7; i++)
            {
                BufferedImage icon = board[i][j].getPieceIcon();
                screenLocation = getScreenLocation(i, j);
                g.drawImage(icon, (int)screenLocation.getX(), (int)screenLocation.getY(), this); 
            }
        }
    }
    
    public void presentBoard(Graphics g)
    {
        //Graphics g = getGraphics();
        boolean colorFlag = true;
        
        //top left corner locations of tiles being printed
        int x = 0;
        int y = 0;
        
        //moving down the eight rows
        for(int j = 0; j < 8; j++)
        {
            //starting at the left
            x=0;
            
            //switching the tileColor
            colorFlag = !colorFlag;
            
            //printing one element of each column left to right
            for(int i = 0; i < 8; i++)
            {
                //setting tile as white or black
                if(colorFlag)
                    g.setColor(Color.GRAY); //gray
                else
                    g.setColor(Color.LIGHT_GRAY); //light grqy
                
                g.fillRect(x, y, tileSideLength, tileSideLength);
                colorFlag = !colorFlag;
                
                //moving the rect's top left to the right by the length of a tile
                x += tileSideLength;
            }
            //moving the top left pixel of the next tile down by the length of a tile
            y += tileSideLength;
        }
        drawPieces(g);
    }
    
    public void lightTile(Point tile, Color c)
    {
        int x = 0;
        int y = 0;
        
        Graphics g = getGraphics();
        
        for(int j = 0; j < 8; j++)
        {
            //starting at the left
            x=0;
            
            //printing one element of each column left to right
            for(int i = 0; i < 8; i++)
            {
                if(i == tile.getX() && j == tile.getY())
                {
                    g.setColor(c);
                    g.drawRect(x, y, tileSideLength, tileSideLength);
                    break;
                }
                
                x += tileSideLength;
            }
            y += tileSideLength;
        }
    }
    
    /*
    takes in a screenX and screenY and returns a Point that corresponds to the
    tile you're on in board[x][y]
    */
    public Point getTileAtScreenLocation(Point screenLocation)
    {
        //initializing to -1 to catch errors
        int screenX = (int)screenLocation.getX();
        int screenY = (int)screenLocation.getY();
        
        int tileX = -1;
        int tileY = -1;
        
        for(int i = 0; i < 8; i++)
        {
            if(0 < (screenX - tileSideLength * i) && (screenX - tileSideLength * i) < tileSideLength)
                tileX = i;
            if(0 < (screenY - tileSideLength * i) && (screenY - tileSideLength * i) < tileSideLength)
                tileY = i;
        }
        return new Point(tileX, tileY);
    }
  
    public String playerAt(int x, int y)
    {
        //Returns name of player at position (white or black)
        return board[x][y].getPlayer();
    }

    public String getPieceAt(int x, int y)
    {
        return board[x][y].getPieceType();
    }
    public boolean pathClear(int xi, int yi, int xf, int yf)
    {
        /*
        Checks every position in between (not including) the start and end position.
        */
        int deltaX = xf - xi;
        int deltaY = yf - yi;
        
        /////////////////////////////
        // vertical rook movements //
        /////////////////////////////
        if(deltaY != 0 && deltaX == 0)
        {
            //case where y increases from yi to yf
            if(yf > yi)
            {
                for(int i = yi + 1; i < yf; i++)
                {
                    if(!board[xi][i].getPieceType().equals(" "))
                    {
                        // if there isnt an empty piece at any intermediate position return false
                        return false;
                    }
                }
                return true; // looped through all intermediate positions wihout detecting another piece
            }
            else //case where y increases from yf to yi
            {
                for(int i = yf + 1; i < yi; i++)
                {
                    if(!board[xi][i].getPieceType().equals(" "))
                    {
                        // if there isnt an empty piece at any intermediate position return false
                        return false;
                    }
                }
                return true; // looped through all intermediate positions wihout detecting another piece
            }
        }
      
        ///////////////////////////////
        // horizontal rook movements //
        ///////////////////////////////
        if(deltaX != 0 && deltaY == 0)
        {
            //case where x increases from xi to xf
            if(xf > xi)
            {
                for(int i = xi + 1; i < xf; i++)
                {
                    if(!board[i][yi].getPieceType().equals(" "))
                    {
                        // if there isnt an empty piece at any intermediate position return false
                        return false;
                    }
                }
                return true; // looped through all intermediate positions wihout detecting another piece
            }
            else //case where x increases from xf to xi
            {
                for(int i = xf + 1; i < xi; i++)
                {
                    if(!board[i][yi].getPieceType().equals(" "))
                    {
                        // if there isnt an empty piece at any intermediate position return false
                        return false;
                    }
                }
                return true; // looped through all intermediate positions wihout detecting another piece
            }
        }   
      
        ////////////////////////////
        // Bishop style movements //
        ////////////////////////////
      
        //checking if it's a bishop move
        if(Math.abs(deltaX) == Math.abs(deltaY))
        {  
            //x increases and y increases
            if(deltaX > 0 && deltaY > 0)
            {
                int x, y;
                x = xi + 1;
                y = yi + 1;
          
               while(x < xf && y < yf)
                {
                    if(!board[x][y].getPieceType().equals(" "))
                        return false;
                    x += 1;
                    y += 1;
                }
                return true;
            }
      
            //x decreases and y decreases
            if(deltaX < 0 && deltaY < 0)
            {
                int x, y;
                x = xi - 1;
                y = yi - 1;
          
                while(x > xf && y > yf)
                {
                    if(!board[x][y].getPieceType().equals(" "))
                        return false;
                    x -= 1;
                    y -= 1;
                }
                return true;
            }
        
            //x decreases and y increases
            if(deltaX < 0 && deltaY > 0)
            {
                int x, y;
                x = xi - 1;
                y = yi + 1;
          
                while(x < xf && y < yf)
                {
                    if(!board[x][y].getPieceType().equals(" "))
                        return false;
                    x -= 1;
                    y += 1;
                }
                return true;
            }
        
            //x increases and y decreases
            if(deltaX > 0 && deltaY < 0)
            {
                int x, y;
                x = xi + 1;
                y = yi - 1;
          
                while(x < xf && y < yf)
                {
                    if(!board[x][y].getPieceType().equals(" "))
                        return false;
                    x += 1;
                    y -= 1;
                }
                return true;
            }
        }
      
        return false;
    }
    
    public void setThisPiece(Piece instanceOfPiece)
    {
        //Sets piece at the new location (final method called when moving a piece, if it makes it to this point)
        board[instanceOfPiece.getX()][instanceOfPiece.getY()] = instanceOfPiece;
    }
  
    public void removePiece(int x, int y)
    {
        //Removes piece at location (as in, the piece was taken)
        board[x][y].changeInPlay(false);
    }
  
    public boolean isEmpty(int x, int y)
    {
        //Checks to see if the position is occupied by a piece. If it isn't, return true (it is empty).
        return board[x][y].getPieceType().equals(" ");
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point eventBoardLocation = getTileAtScreenLocation(e.getPoint());
        Graphics g = getGraphics();
        
        if(eventBoardLocationF == null && eventBoardLocationI != null)
        {
            eventBoardLocationF = eventBoardLocation;
            board[(int)eventBoardLocationI.getX()][(int)eventBoardLocationI.getY()].updatePos((int)eventBoardLocationF.getX(), (int)eventBoardLocationF.getY());
            eventBoardLocationI = null;
            presentBoard(g);
            System.out.println("turn2");
        }
        
        if(eventBoardLocationI == null && eventBoardLocationF != null)
        {
            eventBoardLocationI = eventBoardLocation;
            lightTile(eventBoardLocationI, Color.WHITE);
            eventBoardLocationF = null;
            System.out.println("turn1");
        }
    }

    
    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}