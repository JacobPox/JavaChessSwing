# Creating chess with Java.

## To Do List
* Improve check features
* Add en passant
* Add castling
* Improve UI
  * Create mouseListeners
  * Add Row and Column Labels

## Known Bugs
* Wrong message sent during an invalid move. This is caused by the following steps:
  * It is your turn.
  * You do an illegal move, like attacking an enemy in a way that you can't move. (Like a pawn attacking horizontally).
  * Message displays "It is not your turn". But the message should be "Invalid move."
  
* Empty pieces can be moved (but shouldn't be allowed to)
