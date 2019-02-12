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
* Add pawn promotion.
* Add en passant
* Add castling

## Known Bugs
* Turn order continues if invalid move is made.
  * Example: You try to move your king onto your own piece, this is an invalid move. But then it is the other team's turn to go despite the fact you did not move yet.

