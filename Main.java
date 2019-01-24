package ProjectOverlord;

public class Main {
  public static void main(String[] args) {
    ChessBoard myBoard = new ChessBoard();
    myBoard.fillBoard();
    myBoard.presentBoard();
    myBoard.updateBoard(7, 6, 5, 5);
    myBoard.updateBoard(1, 0, 3, 0);
  }
}