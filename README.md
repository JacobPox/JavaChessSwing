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
* ChessBoard

### Piece
This class will provide the framework for each of the pieces. A piece should have a name and a loation on the board. Each piece (e.g. King) will be a subclass that is extended from the Piece class. They will have their own name such as "K" and the location on the board. They will also have to contain what their allowed moves are.

### ChessBoard
This class provides a visual representation of the board. Each piece on the board will have a location and can be shown usin the ChessBoard class. ChessBoard should update after each move is made.

## To Do List
* ~Fix documentation of how the indexing works.~
* pathClear method in ChessBoard needs worked on.
* ~King and Pawn classes need moveLegal methods.~
* Add in check and check mate features.
* Allow user input
* Translate notation into index position and vice versa.
* Change pieceType to enum instead of strings.
* Add variables keeping track of the number of each piece type (Eg: 2 Rooks)
* ~Fix board orientation~
