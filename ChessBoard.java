package projectoverlord.ProjectOverlord;

public class ChessBoard
{
    /*
    The board stores pieces in a 2D array, ranging from [0][0] to [7][7]
    These positions correspond to the chess board positions A8 to H1
    Below is a reference for each index and its corresponding position.
    
    First index is x coordinate (A-H), second index is y coordinate (starting from the top to bottom, 8-1).
    board[0][] --> 1st row
    board[1][] --> 2nd row
    board[2][] --> 3rd row
    board[3][] --> 4th row
    board[4][] --> 5th row
    board[5][] --> 6th row
    board[6][] --> 7th row
    board[7][] --> 8th row
    
    board[][0] --> A column
    board[][1] --> B column
    board[][2] --> C column
    board[][3] --> D column
    board[][4] --> E column
    board[][5] --> F column
    board[][6] --> G column
    board[][7] --> H column
    
    Example:
    
        White's King starts on board[4][7]
        (4 to the right, 7 down)
    
        Black's Queen starts on board[3][0]
        (3 to the right, 0 down)
    */

    Piece[][] board;
    
    public ChessBoard()
    {
        board = new Piece[8][8];
        
        System.out.println("New game beginning!");
        fillBoard();
        presentBoard();
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
        board [0][0] = new Rook(this, team1, "R", 0, 0);
        board [7][0] = new Rook(this, team1, "R", 7, 0);
        board [0][7] = new Rook(this, team2, "r", 0, 7);
        board [7][7] = new Rook(this, team2, "r", 7, 7);

        // Knights
        board [1][0] = new Knight(this, team1, "N", 1, 0);
        board [6][0] = new Knight(this, team1, "N", 6, 0);
        board [1][7] = new Knight(this, team2, "n", 1, 7);
        board [6][7] = new Knight(this, team2, "n", 6, 7);

        //Bishops
        board [2][0] = new Bishop(this, team1, "B", 2, 0);
        board [5][0] = new Bishop(this, team1, "B", 5, 0);
        board [2][7] = new Bishop(this, team2, "b", 2, 7);
        board [5][7] = new Bishop(this, team2, "b", 5, 7);

        //Queens
        board [3][0] = new Queen(this, team1, "Q", 3, 0);
        board [3][7] = new Queen(this, team2, "q", 3, 7);

        //Kings
        board [4][0] = new King(this, team1, "K", 4, 0);
        board [4][7] = new King(this, team2, "k", 4, 7);

        //Pawns
        for (int x = 0; x < 8; x++)
        {
            board[x][1] = new Pawn(this, team1, "P", x, 1);
            board[x][6] = new Pawn(this, team2, "p", x, 6);
        }
    }

    public void presentBoard()
    {
        //side of the board numbers
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
        
        //Prints board
        System.out.println("  ---------------------------------");
        for (int y = 0; y < 8; y++)
        {
            System.out.print(nums[y] + " | ");
            for (int x = 0; x < 8; x++)
            {
                System.out.print(board[x][y].getPieceType() + " | ");
            }
            System.out.println();
        }
        
        //bottom of board letters
        System.out.println("  ---------------------------------");
        System.out.println("    a   b   c   d   e   f   g   h\n\n");
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
      
        System.out.println("woah nelly, path clear didnt return a real answer");
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
        board[x][y] = new Empty(this, "neutral", " ", x, y);
    }
  
    public boolean isEmpty(int x, int y)
    {
        //Checks to see if the position is occupied by a piece. If it isn't, return true (it is empty).
        return board[x][y].getPieceType().equals(" ");
    }
  
    /*
    public boolean isCheck() {
    Current idea for looking for check (best explained by example)
    Check 1. "Are there any enemy rooks in play?"
    If yes, 
    Check 2. "Are any rooks in the same file as the king?"
    If yes,
    Check 3. "Are there any pieces in between the king and rook?"
  
    This would apply to queen/bishop
  
    Knight is similar in that you check if there are any first, then if there are knights you can see if it can legally get to the king.
    If it can, that's check.
    }
    */
}
