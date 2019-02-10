package projectoverlord.ProjectOverlord;

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
        
        while(true) //really will be while either king is not in check mate or taken
        {
            System.out.print("Enter the piece to move (Letter, number): ");
            input = keyboard.nextLine();
            switch(input.charAt(1))
            {
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
            
            switch(input.charAt(4))
            {
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
            
            //getting input for ending location
            System.out.print("Enter where you want it to go (Letter, number): ");
            input = keyboard.nextLine();
            switch(input.charAt(1))
            {
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
            
            switch(input.charAt(4))
            {
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
             
            myBoard.board[xi][yi].updatePos(xf, yf);
            myBoard.presentBoard();
        }
    }
}
