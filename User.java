package tictactoe;
import java.util.Scanner;

public class User {

    public void makeMove(String[][] twoD_arr, String symbol){
        while (true) {

            Scanner input = new Scanner(System.in);

            System.out.print("\nEnter the coordinates: > ");

            String position = input.nextLine();
            String[] position2 = position.split(" ");

            try {
                int X = Integer.parseInt(position2[0]);
                int Y = Integer.parseInt(position2[1]);

                if (X - 1 > 2 || Y - 1 > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (twoD_arr[X - 1][Y - 1] != " ") {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    twoD_arr[X - 1][Y - 1] = symbol;
                    break;
                }

            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
