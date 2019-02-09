public class ChessBoard {
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

    Piece[][] board = new Piece[8][8];
    
    public void fillBoard() {
        //Fills the empty spaces
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[x][y] = new Empty(x, y);
            }
        }

        /* 
        Declare all the pieces that will be used.
        Uppercase for black pieces, lowercase for white pieces.
        */

        String team1 = "black";
        String team2 = "white";

        // Rooks
        board [0][0] = new Rook(team1, "R", 0, 0);
        board [7][0] = new Rook(team1, "R", 0, 7);
        board [0][7] = new Rook(team2, "r", 7, 0);
        board [7][7] = new Rook(team2, "r", 7, 7);

        // Knights
        board [1][0] = new Knight(team1, "N", 1, 0);
        board [6][0] = new Knight(team1, "N", 6, 0);
        board [1][7] = new Knight(team2, "n", 1, 7);
        board [6][7] = new Knight(team2, "n", 6, 7);

        //Bishops
        board [2][0] = new Bishop(team1, "B", 2, 0);
        board [5][0] = new Bishop(team1, "B", 5, 0);
        board [2][7] = new Bishop(team2, "b", 2, 7);
        board [5][7] = new Bishop(team2, "b", 5, 7);

        //Queens
        board [3][0] = new Queen(team1, "Q", 3, 0);
        board [3][7] = new Queen(team2, "q", 3, 7);

        //Kings
        board [4][0] = new King(team1, "K", 4, 0);
        board [4][7] = new King(team2, "k", 4, 7);


        //Pawns
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(team1, "P", i, 1);
            board[i][6] = new Pawn(team2, "p", i, 1);
        }
    }

    public void presentBoard() {
      //Prints board
    for (int i = 0; i < 8; i++) {
        System.out.print("| ");
        for (int j = 0; j < 8; j++) {
            if(!board[j][i].pieceType.equals(" "))
                System.out.print(board[j][i] + " | ");
            else
                System.out.print("  | ");
        }
        System.out.println();
    }
    //adding a newline between each board update
    System.out.println();
}
  
  public String playerAt(int x, int y)
  {
    //Returns name of player at position (white or black)
    return board[x][y].getPlayer();
  }


  public boolean pathClear(int xi, int yi, int xf, int yf) //start at pos +1 !!!!!!!!!!!!!!!!!!!!!!!!!! not done !!!!!!!!!!!!!!!!!!!
  {
    /*
    Checks every position in between (not including) the start and end position.
    */
    deltaX = xf - xi;
    deltaY = yf - yi;
    
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
          if(!board[xi][i].getPiece().equals(" "))
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
          if(!board[xi][i].getPiece().equals(" "))
          {
            // if there isnt an empty piece at any intermediate position return false
            return false;
          }
        }
        return true; // looped through all intermediate positions wihout detecting another piece
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
          if(!board[i][yi].getPiece().equals(" "))
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
          if(!board[i][yi].getPiece().equals(" "))
          {
            // if there isnt an empty piece at any intermediate position return false
            return false;
          }
        }
        return true; // looped through all intermediate positions wihout detecting another piece
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
            if(!board[x][y].getPiece().equals(" "))
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
            if(!board[x][y].getPiece().equals(" "))
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
            if(!board[x][y].getPiece().equals(" "))
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
            if(!board[x][y].getPiece().equals(" "))
              return false;
            x += 1;
            y -= 1;
          }
          return true;
        }
      }
      
    }
    
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
    if (board[x][y].equals("")) {
      return true;
    }

    return false;
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
