package projectoverlord.ProjectOverlord;

public class Empty extends Piece {

    public Empty(ChessBoard board, String player, String piece, int x, int y)
    {
        /*
        This class represents a space not occupied and is treated as nothing.
         */

        super(board, "neutral", " ", x, y);
    }
}
