package tictactoe;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean endgame = true;

        Set<String> modes = new HashSet<String>();
        modes.add("user");
        modes.add("easy");
        modes.add("medium");
        modes.add("hard");

        int j = 0, counter = 0;

        String[][] twoD_arr = new String[3][3];

        User user = new User();
        AI ai = new AI();

        String[] splitInput = new String[10];

        Arrays.stream(twoD_arr).forEach(x -> Arrays.fill(x, " "));

        String symbol = "X";


        while (true) {

            if(endgame==true) {
                while (true) {
                    Arrays.stream(twoD_arr).forEach(x -> Arrays.fill(x, " "));
                    counter =0;
                    symbol = "X";
                    System.out.print("Input command: ");
                    String startInput = input.nextLine();
                    splitInput = startInput.split(" ");

                    try {
                        if (splitInput[0].equals("start")
                                && modes.contains(splitInput[1])
                                && modes.contains(splitInput[2])) {
                            printGame(twoD_arr);
                            break;
                        } else if (splitInput[0].equals("exit")) {
                            System.exit(0);

                        } else {
                            System.out.println("Bad parameters!");
                        }
                    } catch (Exception e) {
                        System.out.println("Bad parameters!");
                    }
                }
            }

            endgame =false;

            if(symbol.equals("X")) {
             pickPlayerDiff(splitInput,user, ai, twoD_arr, symbol, 1 );
                System.out.println();

            }else if(symbol.equals("O")){
                pickPlayerDiff(splitInput,user, ai, twoD_arr, symbol,2);
                System.out.println();
            }

            if (symbol.equals("O")) {
                symbol = "X";
            } else if (symbol.equals("X")) {
                symbol = "O";
            }

            counter= counter+1;
            printGame(twoD_arr);

            //redove
            for (j = 0; j < 3; j++) {

                if (twoD_arr[j][0].equals( twoD_arr[j][1]) && twoD_arr[j][1].equals( twoD_arr[j][2]) && counter >=5 && !twoD_arr[j][0].equals(" ")) {
                    System.out.println("\n"+twoD_arr[j][0]+" wins");
                    endgame =true;
                    continue;
                }

            }

            if(endgame==true){
                continue;
            }

            //koloni
            for ( j = 0; j < 3; j++) {

                if (twoD_arr[0][j].equals(twoD_arr[1][j]) && twoD_arr[1][j].equals(twoD_arr[2][j]) && counter >=5  && !twoD_arr[0][j].equals(" ")  ) {
                    System.out.println("\n"+twoD_arr[0][j] + " wins");
                    endgame =true;
                    continue;
                }
            }

            if(endgame==true){
                continue;
            }

            //diagonali
            if (twoD_arr[0][0].equals(twoD_arr[1][1]) && twoD_arr[1][1].equals(twoD_arr[2][2]) && !twoD_arr[0][0].equals(" ") && counter >=5) {
                System.out.println("\n"+twoD_arr[0][0]+" wins");
                endgame =true;
                continue;
            }

            if (twoD_arr[0][2].equals(twoD_arr[1][1]) && twoD_arr[1][1].equals(twoD_arr[2][0]) && !twoD_arr[0][2].equals(" ")&& counter >=5) {
                System.out.println("\n"+twoD_arr[0][2]+" wins");
                endgame =true;
                continue;

            }

            //draw
            if(counter==9 && endgame==false){
                System.out.println("\n"+"Draw");
                endgame =true;
                continue;
            }
        }
    }

    public static void printGame(String twoD_arr[][]){
        System.out.println("---------");
        System.out.println("| "+twoD_arr[0][0] + " " + twoD_arr[0][1] + " "+  twoD_arr[0][2] + " |");
        System.out.println("| "+twoD_arr[1][0] + " " + twoD_arr[1][1] + " "+  twoD_arr[1][2] + " |");
        System.out.println("| "+twoD_arr[2][0] + " " + twoD_arr[2][1] + " "+  twoD_arr[2][2] + " |");
        System.out.println("---------");
    }

    public static void pickPlayerDiff(String[] splitInput, User user, AI ai, String[][] twoD_arr, String symbol,int x ){
        if(splitInput[x].equals("user")){
            user.makeMove(twoD_arr,symbol);
        }else if (splitInput[x].equals("easy")){
            ai.makeEasyMove(twoD_arr,symbol);
        }else if (splitInput[x].equals("medium")){
            ai.makeMediumMove(twoD_arr, symbol);
        }else if (splitInput[x].equals("hard")){
            ai.makeHardMove(twoD_arr, symbol);
        }
    }


}

