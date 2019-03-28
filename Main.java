package projectoverlord;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        ChessBoard myBoard = new ChessBoard();
        Scanner keyboard = new Scanner(System.in);
        String input;
        //Used if someone promotes their pawn
        String promotion = "z";
        String promotionTypes = "qrbn";
        //default values
        int xi = -1;
        int yi = -1;
        int xf = -1;
        int yf = -1;

        //To check if a move given contains only the allowed characters
        String allowedLetters = "abcdefgh";
        String allowedNums = "12345678";

        //Checking if kings are in play to determine end of game
        Piece whiteKing = myBoard.board[4][7];
        Piece blackKing = myBoard.board[4][0];

        //getting teams to switch turns
        String whitePlayer = whiteKing.getPlayer();
        String blackPlayer = blackKing.getPlayer();

        String player1 = whitePlayer;
        String player2 = blackPlayer;

        boolean team1Going = true;

        while (whiteKing.getInPlay() && blackKing.getInPlay()) //really will be while either king is not in check mate or taken
        {
            if (team1Going) {
                System.out.println("White's turn.");
            } else {
                System.out.println("Black's turn.");
            }

            System.out.print("Enter starting and ending position for your move (Example: e2e4): ");
            try {
                input = keyboard.nextLine();

                // Check to make sure that the format follows letter, number, letter, number (Example: e2e4)
                if (!(allowedLetters.contains(Character.toString(input.charAt(0)))
                        && allowedNums.contains(Character.toString(input.charAt(1)))
                        && allowedLetters.contains(Character.toString(input.charAt(2)))
                        && allowedNums.contains(Character.toString(input.charAt(3))))) {
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
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Invalid move, did you format your move correctly? Example of correct move: g1f3");
                continue;
            } catch (Exception e) {
                System.out.println("Invalid move.");
                continue;
            }
            try {
                //checking if the player moves their own piece------------------------------------------
                if ((myBoard.board[xi][yi].getPlayer().equals("white") && team1Going)
                        || (myBoard.board[xi][yi].getPlayer().equals("black") && !team1Going)) {
                    myBoard.board[xi][yi].updatePos(xf, yf);
                    myBoard.repaint();
                } else {
                System.out.println("No piece here to move.");
                continue;
                }
                
                if (myBoard.whiteKingInCheck(whiteKing) || myBoard.blackKingInCheck(blackKing)) {
                System.out.println("Check!");
                }

                

                if (myBoard.isEmpty(xi, yi)) {

                    // Check to see if pawn moved onto last file, and thus can be promoted.
                    if ((yf == 0 || yf == 7) && myBoard.board[xf][yf].pieceType.toLowerCase().equals("p")) {

                        // Keep asking until they give a Q, R, B, or N (case doesn't matter)
                        while (!promotionTypes.contains(promotion)) {
                            System.out.println("Pawn promotion. Choose which piece to turn your pawn into.");
                            System.out.println("Q for queen, R for rook, B for bishop, or N for knight.");
                            Scanner promotionChoice = new Scanner(System.in);
                            promotion = promotionChoice.nextLine().toLowerCase();
                            // Prevent user from giving more than one character (like if they said qrbn, it will just take q as the intented promotion.
                            if (promotion.length() != 1) {
                                promotion = String.valueOf(promotion.charAt(0));
                            }
                        }

                        // List of options which create a new object onto that position, replacing it.
                        switch (promotion) {
                            case "q":
                                if (yf == 0) {
                                    myBoard.setThisPiece(new Queen(myBoard, "white", "q", xf, yf));
                                } else {
                                    myBoard.setThisPiece(new Queen(myBoard, "black", "Q", xf, yf));
                                }
                                break;
                            case "r":
                                if (yf == 0) {
                                    myBoard.setThisPiece(new Rook(myBoard, "white", "r", xf, yf));
                                } else {
                                    myBoard.setThisPiece(new Rook(myBoard, "black", "R", xf, yf));
                                }
                                break;
                            case "b":
                                if (yf == 0) {
                                    myBoard.setThisPiece(new Bishop(myBoard, "white", "b", xf, yf));
                                } else {
                                    myBoard.setThisPiece(new Bishop(myBoard, "black", "B", xf, yf));
                                }
                                break;
                            case "n":
                                if (yf == 0) {
                                    myBoard.setThisPiece(new Knight(myBoard, "white", "n", xf, yf));
                                } else {
                                    myBoard.setThisPiece(new Knight(myBoard, "black", "N", xf, yf));
                                }
                                break;

                        }
                        myBoard.repaint();
                    }
                    //Switch which team is going white -> black -> white -> black etc
                    team1Going = !team1Going;
                } else {
                    System.out.println("You must move your own piece");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("That move could not be understood.");
                continue;
            }

        }

        System.out.println("Game Over!");
        if (blackKing.getInPlay()) {
            System.out.println("Black Wins!");
        }
        if (whiteKing.getInPlay()) {
            System.out.println("White Wins!");
        }
    }
}
