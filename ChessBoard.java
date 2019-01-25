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

    String[][] board = new String[8][8];
    
    /*
    String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
    String[] numbers = new String[]{"8","7","6","5","4","3","2","1"};
    */
    
    public void fillBoard() {
        //Fills the empty spaces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = " ";
            }
        }
        
        // Uppercase for black pieces, lowercase for white pieces.

        // Rooks
        board [0][0] = "R";
        board [0][7] = "R";
        board [7][0] = "r";
        board [7][7] = "r";

        // Knights
        board [0][1] = "N";
        board [0][6] = "N";
        board [7][1] = "n";
        board [7][6] = "n";

        //Bishops
        board [0][2] = "B";
        board [0][5] = "B";
        board [7][2] = "b";
        board [7][5] = "b";

        //Queens
        board [0][3] = "Q";
        board [7][3] = "q";

        //Kings
        board [0][4] = "K";
        board [7][4] = "k";


        //Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = "P";
            board[6][i] = "p";
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
  
  public String playerAt(x, y)
  {
    returns a string of the player's name at (x, y) if a player is there
  }
  
  public boolean pathClear(xi, yi, xf, yf)
  {
    checks if all of the spaces (excluding (xi, yi) and (xf, yf) themselves) in the horizontal, vertical, or diagonal direction
    from (xi, yi) to (xf, yf) are empty. returns false if any player's piece is in the way
  }
  
  public void setThisPiece(instanceOfPiece, x, y)
  {
    have the piece class send instance of the piece it wants at (x, y) (it currently sends the players name
    and where they want the piece, but not what type of piece it is) and then place that piece at (x, y)
  }
  
  public void removePiece(x, y)
  {
    tell the piece at (x, y) to set inPlay to false and make this spot empty
  }
  
  public boolean isEmpty(x, y)
  {
    returns true if no piece is at location (x, y)
  }
  */
    
}
