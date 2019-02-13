# ProjectOverlord
Creating chess with Java.

## Class Structure

* Piece
  * King
  * Queen
  * Rook
  * Bishop
  * Knight
  * Pawn
  * Empty
* ChessBoard

### Piece
This class will provide the framework for each of the pieces. A piece should have a name and a loation on the board. Each piece (e.g. King) will be a subclass that is extended from the Piece class. They will have their own name such as "K" and the location on the board. They will also have to contain what their allowed moves are.

The Empty class is a subclass of Piece and is used to denote a spot where no piece is on.

### ChessBoard
This class provides a visual representation of the board. Each piece on the board will have a location and can be shown usin the ChessBoard class. ChessBoard should update after each move is made.

## To Do List
* Add in check and check mate features.
* Add en passant
* Add castling

## Known Bugs
* Wrong message sent during an invalid move. This is caused by the following steps:
  * It is your turn.
  * You do an illegal move, like attacking an enemy in a way that you can't move. (Like a pawn attacking horizontally).
  * Message displays "It is not your turn". But the message should be "Invalid move."
