/*
Chess Game created by Jacob and Nick

To Do:
- Add check and check mate features
- Add pawn promotion

Comments by Jacob:
Currently, the game works pretty well. I'm quite happy with the progress we've made and this has been a fun project.
As of right now, my biggest concern is adding in a check feature in the game. There are a lot of ways to go about it,
but my current thinking is to go through a list of things to check off to see if there is a check in the game.

The order of these checks:
 - Are there any enemy rooks? If yes...
    - Check their row and column, do either match the king? If yes...
        - Are there any pieces in between (path clear)? If no...
            - Check
 - Are there any enemy bishops? If yes...
    - Are they an equal x and y distance away? If yes...
        - Are there any pieces in between (path clear)? If no...
            - Check
- Are there any enemy knights? If yes...
    - Are they 2 x positions and 1 y position or 1 x position and 2 y positions away? If yes...
        - Check
- Are there any enemy queens? If yes...
    - Do a rook check then a bishop check.

- Are there any enemy pawns? If yes...
    - Are they 1 x and 1 y away? If yes...
        - Are they oriented correctly (Facing the king, not behind him) If yes...
            - Check

This is a bit of an exhaustive list and there is probably a better way of doing it. But this is all I can think of.
Not only do we need to know if a check is made after a move is given, we have to prevent a player from putting
themselves in check. This could be remedied by going through the above checks after a requested move is made, then
if it is illegal we don't go through the moves. I'm thinking that chessboard could handle this part and updatePos
will have an additional requirement to move (notCheck) or something like that.

Pawn promotion should be simpler. A pawn can become a queen, knight, bishop, or rook when it reaches the opposite end
(row 1 or 8). We can prompt the player what piece they want to choose.
 */

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        launch(); // run from commandline for better visual
    }
    
    public static void launch()
    {
        ChessBoard myBoard = new ChessBoard();
        Scanner keyboard = new Scanner(System.in);
        String input;
        //default values
        int xi = -1;
        int yi = -1;
        int xf = -1;
        int yf = -1;
        
        //for translating board input to array indeces
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
        char[] letters = {'a', 'b', 'c', 'd',  'e',  'f',  'g', 'h'};

        //To check if a move given contains only the allowed characters
        String allowedLetters = "abcdefgh";
        String allowedNums = "12345678";
        
        //Checking if kings are in play to determine end of game
        Piece whiteKing = myBoard.board[4][7];
        Piece blackKing = myBoard.board[4][0];
        
        while(whiteKing.getInPlay() && blackKing.getInPlay()) //really will be while either king is not in check mate or taken
        {
            System.out.print("Enter starting and ending position for your move (Example: e2e4): ");
            try {
                input = keyboard.nextLine();

                // Check to make sure that the format follows letter, number, letter, number (Example: e2e4)
                if (!(allowedLetters.contains(Character.toString(input.charAt(0))) &&
                        allowedNums.contains(Character.toString(input.charAt(1))) &&
                        allowedLetters.contains(Character.toString(input.charAt(2))) &&
                        allowedNums.contains(Character.toString(input.charAt(3))))) {
                    System.out.println("Invalid move, did you format your move correctly? Example of correct move: g1f3");
                    continue;
                }

                switch (input.charAt(0)) {
                    case 'a':
                        xi = 0;
                        break;
                    case 'b':
                        xi = 1;
                        break;
                    case 'c':
                        xi = 2;
                        break;
                    case 'd':
                        xi = 3;
                        break;
                    case 'e':
                        xi = 4;
                        break;
                    case 'f':
                        xi = 5;
                        break;
                    case 'g':
                        xi = 6;
                        break;
                    case 'h':
                        xi = 7;
                        break;
                }

                switch (input.charAt(1)) {
                    case '8':
                        yi = 0;
                        break;
                    case '7':
                        yi = 1;
                        break;
                    case '6':
                        yi = 2;
                        break;
                    case '5':
                        yi = 3;
                        break;
                    case '4':
                        yi = 4;
                        break;
                    case '3':
                        yi = 5;
                        break;
                    case '2':
                        yi = 6;
                        break;
                    case '1':
                        yi = 7;
                        break;
                }

                //ending location
                switch (input.charAt(2)) {
                    case 'a':
                        xf = 0;
                        break;
                    case 'b':
                        xf = 1;
                        break;
                    case 'c':
                        xf = 2;
                        break;
                    case 'd':
                        xf = 3;
                        break;
                    case 'e':
                        xf = 4;
                        break;
                    case 'f':
                        xf = 5;
                        break;
                    case 'g':
                        xf = 6;
                        break;
                    case 'h':
                        xf = 7;
                        break;
                }

                switch (input.charAt(3)) {
                    case '8':
                        yf = 0;
                        break;
                    case '7':
                        yf = 1;
                        break;
                    case '6':
                        yf = 2;
                        break;
                    case '5':
                        yf = 3;
                        break;
                    case '4':
                        yf = 4;
                        break;
                    case '3':
                        yf = 5;
                        break;
                    case '2':
                        yf = 6;
                        break;
                    case '1':
                        yf = 7;
                        break;
                }
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("Invalid move, did you format your move correctly? Example of correct move: g1f3");
                continue;
        } catch (Exception e) {
                System.out.println("Invalid move.");
                continue;
            }
            try {
                myBoard.board[xi][yi].updatePos(xf, yf);
                myBoard.presentBoard();
            } catch (Exception e) {
                System.out.println("That move could not be understood.");
                continue;
            }
        }
        
        System.out.println("Game Over!");
        if(blackKing.getInPlay())
            System.out.println("Black Wins!");
        if(whiteKing.getInPlay())
            System.out.println("White Wins!");
    }
}
