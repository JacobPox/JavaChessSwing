package ProjectOverlord;

/*
TO DO:
- Give the algebraic notation of move made.
- Give selection of choices such as:
  - Make next move.
  - See log of moves so far (algebraic notation)
  - Quit.
*/

public class ChessBoard {
  /*
  The board stores pieces in a 2D array, ranging from [0][0] to [7][7]
  These positions correspond to the chess board positions A8 to H1

  Below is a reference for each index and its corresponding position.
    
  First position is row, second position is column.

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
    
    White's King starts on board[7][4]
    
    Black's Queen starts on board[0][3]

  */

    Piece[][] board = new Piece[8][8];
    
    /*
    String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
    String[] numbers = new String[]{"8","7","6","5","4","3","2","1"};
    */
    
    public void fillBoard() {
        //Fills the empty spaces
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[x][y] = null;
            }
        }

        // Declare all the pieces that will be used

        
        // Uppercase for black pieces, lowercase for white pieces.

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
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            System.out.print(board[i][j] + " | ");
        }
        System.out.println();
    }
}

  public void updateBoard(int startI, int startJ, int endI, int endJ) {
    /*
    First two parameters are the coordinates of the piece being moved. The second parameters are the coordinates of the piece's new location.

    This method will first get the piece on the starting position and then store that. The position will then be replaced with an "X" (could be subject to change) denoting that a piece moved from that position. After that, the piece is placed on the new position (assuming it is a legal move). The "X" will disappear afterwards (so the board doesn't get cluttered.)
    */
    System.out.println();
    String pieceName = board[startI][startJ];
    board[startI][startJ] = "X";
    board[endI][endJ] = pieceName;
    presentBoard();
    board[startI][startJ] = " ";

  }
  /*
  public String getNotation(int pos1, int pos2) {
      Returns the standard location on the board back.
      return letters[pos1-1] + numbers[pos2];
  } 
  */
  

  
  /*
  
  Quick note to self so I don't freak out and think we need an ArrayList:
  
  if every board[x][y] will hold a Piece object therefore it will know who
  owns them (Piece.getPlayer()) and it's type (Piece.getType() will be overridden
  and just return the type of piece)
  
  The only other thing is to translate Piece's (x, y) into this class's coordinate system 
  
  
  Methods needed for Piece:

  */

  public String playerAt(int x, int y)
  {
    return board[x][y].getPlayer();
  }
  
  /*
  checks if all of the spaces (excluding (xi, yi) and (xf, yf) themselves) in the horizontal, vertical, or diagonal direction
  from (xi, yi) to (xf, yf) are empty. returns false if any player's piece is in the way
  */

  //checks if a straight or diagonal path is clear along the board
  public boolean pathClear(int xi, int yi, int xf, int yf)
  {
    /*
    if(xf < xi)
    {
      //maybe switch it so that the smaller x value's ordered pair is always the initial values
      //that way the casesalways move with an increasing x value
    }
    int deltaX = xf-xi;
    int deltaY = yf-yi;
    */
    //rook style move in the y direction
    if(deltaX == 0 && deltaY != 0)
    {
      if(yf > yi)
      {
        //check each space with increasing y values
        for(int i = yi + 1; i < yf; i++)
        {
          if(board[xf][i] != null)
          {
            //if board[xf][i] != null a piece is there
            return false
          }
        }
        return true;
      }
      else //looping from yf to yi if yf is a smaller number to make y always increase
      {
        for(int i = yf + 1; i < yi; i++)
        {
          if(board[xf][i] != null)
            return false;
        }
      }
      
    //rook style move in the x direction  
    if(deltaY == 0 && deltaX != 0)
    {
      if(xf > xi)
      {
        //check each space with increasing y values
        for(int i = xi + 1; i < xf; i++)
        {
          if(board[yf][i] != null)
          {
            //if board[yf][i] != null a piece is there
            return false
          }
        }
        return true;
      }
      else //looping from xf to xi if xf is a smaller number to make x always increase
      {
        for(int i = xf + 1; i < xi; i++)
        {
          if(board[yf][i] != null)
            return false;
        }
      }
      
      //bishop style move
      if(deltaX == deltaY || deltaX == -deltaY)
      {
        
      }
    }

   // have a final return value if necessary, probably false;
  }
  
    
  //sets piece at its newly set location. is the last action (after all the checks) of updatePos in Piece.java 
  public void setThisPiece(Piece instanceOfPiece)
  {
    board[instanceOfPiece.getX()][instanceOfPiece.getY()] = instanceOfPiece;
  }
  
  public void removePiece(int x, int y)
  {
    board[x][y].changeinPlay(false);
  }
  
  public boolean isEmpty(int x, int y)
  {
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
