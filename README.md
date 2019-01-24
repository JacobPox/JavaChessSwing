# ProjectOverlord
CMPSC 221 Project building a chess game using Java.

## Class Structure

* Piece
  * King
  * Queen
  * Rook
  * Bishop
  * Knight
  * Pawn
* ChessBoard
* Move/ValidateMove

### Piece
This class will provide the framework for each of the pieces. A piece should have a name and a loation on the board. Each piece (e.g. King) will be a subclass that is extended from the Piece class. They will have their own name such as "K" and the location on the board. They will also have to contain what their allowed moves are.

### ChessBoard
This class provides a visual representation of the board. Each piece on the board will have a location and can be shown usin the ChessBoard class. ChessBoard should update after each move is made.

### Move/ValidateMove
This class will do a lot of heavy lifting. In chess, there are many moves and even more illegal moves. This class will have to check to see if a move is legitimate before the move goes through.

