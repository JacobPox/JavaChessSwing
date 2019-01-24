public class ChessBoard {
  /*
  The board stores pieces in a 2D array, ranging from [0][0] to [7][7]
  These positions correspond to the chess board positions H1 to A8

  Below is a reference for each index and its corresponding position.

  board[0][x] --> H row
  board[1][x] --> G row
  board[2][x] --> F row
  board[3][x] --> E row
  board[4][x] --> D row
  board[5][x] --> C row
  board[6][x] --> B row
  board[7][x] --> A row

  */

    String[][] board = new String[8][8];
    public void fillBoard() {
        //Fills the empty spaces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = " ";
            }
        }

        // Rooks
        board [0][0] = "R";
        board [0][7] = "R";
        board [7][0] = "R";
        board [7][7] = "R";

        // Knights
        board [0][1] = "N";
        board [0][6] = "N";
        board [7][1] = "N";
        board [7][6] = "N";

        //Bishops
        board [0][2] = "B";
        board [0][5] = "B";
        board [7][2] = "B";
        board [7][5] = "B";

        //Queens
        board [0][3] = "Q";
        board [7][3] = "Q";

        //Kings
        board [0][4] = "K";
        board [7][4] = "K";


        //Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = "P";
            board[6][i] = "P";
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
}