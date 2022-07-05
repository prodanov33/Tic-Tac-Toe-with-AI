package tictactoe;
import java.util.concurrent.atomic.AtomicBoolean;


public class AI {

    public void makeEasyMove(String[][] twoD_arr, String symbol) {
        while (true) {

            int randomX = (int) Math.floor(Math.random() * (2 - 0 + 1) + 0);
            int randomY = (int) Math.floor(Math.random() * (2 - 0 + 1) + 0);
            if (twoD_arr[randomX][randomY].equals(" ")) {
                twoD_arr[randomX][randomY] = symbol;
                System.out.println("Making move level \"easy\"");
                break;
            }
        }
    }

    public void makeMediumMove(String[][] twoD_arr, String symbol) {

        int count = 0;
        String symbolToCheck = "";
        boolean noMove = false;
        AtomicBoolean noMove2 = new AtomicBoolean(false);
        boolean move = true;
        AtomicBoolean move2 = new AtomicBoolean(true);

        if (symbol.equals("O")) {
            symbolToCheck = "X";
        } else if (symbol.equals("X")) {
            symbolToCheck = "O";
        }

        while (move == true) {


            //redove ataka
            rowOperations(twoD_arr, symbol, symbol, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //koloni ataka
            columOperation(twoD_arr, symbol, symbol, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //1 diagonal ataka
            diagonalOperation1(twoD_arr, symbol, symbol, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //2 diagonal ataka
            diagonalOperation2(twoD_arr, symbol, symbol, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //redove zashtita
            rowOperations(twoD_arr, symbol, symbolToCheck, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //koloni zashtita
            columOperation(twoD_arr, symbol, symbolToCheck, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //1 diagonal zashtita
            diagonalOperation1(twoD_arr, symbol, symbolToCheck, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //2 diagonal zashtita
            diagonalOperation2(twoD_arr, symbol, symbolToCheck, count, move2, noMove2);
            noMove = noMove2.get();
            move = move2.get();

            //random
            if (noMove == false) {
                makeEasyMove(twoD_arr, symbol);
                move = false;

            }
            count = 0;
        }
    }

    private void rowOperations(String[][] twoD_arr, String symbol, String action, int count, AtomicBoolean move, AtomicBoolean noMove) {

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (twoD_arr[j][i].equals(action)) {
                    count++;
                    if (count == 2 && move.get() == true && noMove.get() == false) {
                        for (int x = 0; x < 3; x++) {
                            if ((twoD_arr[j][x]).equals(" ")) {
                                twoD_arr[j][x] = symbol;
                                noMove.set(true);
                                move.set(false);
                                break;
                            }
                        }
                    }
                }
            }
            count = 0;
        }
    }

    private void columOperation(String[][] twoD_arr, String symbol, String action, int count, AtomicBoolean move, AtomicBoolean noMove) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (twoD_arr[i][j].equals(action)) {
                    count++;
                    if (count == 2 && move.get() == true && noMove.get() == false) {
                        for (int x = 0; x < 3; x++) {
                            if ((twoD_arr[x][j]).equals(" ")) {
                                twoD_arr[x][j] = symbol;
                                noMove.set(true);
                                move.set(false);
                                break;
                            }
                        }
                    }
                }
            }
            count = 0;
        }
    }

    private void diagonalOperation1(String[][] twoD_arr, String symbol, String action, int count, AtomicBoolean move, AtomicBoolean noMove) {
        for (int i = 0; i < 3; i++) {
            if (twoD_arr[i][i].equals(action)) {
                count++;
                if (count == 2 && move.get() == true && noMove.get() == false) {
                    for (int x = 0; x < 3; x++) {
                        if (twoD_arr[x][x].equals(" ")) {
                            twoD_arr[x][x] = symbol;
                            noMove.set(true);
                            move.set(false);
                            break;
                        }
                    }
                }
            }
        }
        count = 0;
    }

    private void diagonalOperation2(String[][] twoD_arr, String symbol, String action, int count, AtomicBoolean move, AtomicBoolean noMove) {
        int col = 2;
        for (int x = 0; x < 3; x++, col--) {
            if (twoD_arr[x][col].equals(action)) {
                count++;
                if (count == 2 && move.get() == true && noMove.get() == false) {
                    col = 2;
                    for (int x1 = 0; x1 < 3; x1++, col--) {
                        if ((twoD_arr[x1][col].equals(" "))) {
                            twoD_arr[x1][col] = symbol;
                            noMove.set(true);
                            move.set(false);
                            break;
                        }
                    }
                }
            }
            col = 2;
        }

        count = 0;
        col = 2;
    }

    static class Move {
        int row, col;
    }

    public void makeHardMove(String[][] twoD_arr, String symbol){

        String symbolToCheck = "";

        if (symbol.equals("O")) {
            symbolToCheck = "X";
        } else if (symbol.equals("X")) {
            symbolToCheck = "O";
        }



        Move bestMove = findBestMove(twoD_arr,symbol,symbolToCheck);;
        twoD_arr[bestMove.row][bestMove.col] = symbol;
    }

    static Boolean isMovesLeft(String board[][]){

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == " ")
                    return true;
        return false;
    }

    static int evaluate(String b[][], String player, String opponent) {

        //diagonals
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Rows
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Columns
        for (int col = 0; col < 3; col++){

            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]){

                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }
        return 0;
    }

    static int minimax(String board[][], int depth, Boolean isMax , String player, String opponent){

        int score = evaluate(board, player, opponent);

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (isMovesLeft(board) == false)
            return 0;

        if (isMax){

            int best = -1000;

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){

                    if (board[i][j]==" "){

                        board[i][j] = player;

                        best = Math.max(best, minimax(board, depth + 1, !isMax, player, opponent));

                        board[i][j] = " ";
                    }
                }
            }
            return best;
        }

        else{
            int best = 1000;

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){

                    if (board[i][j] == " "){

                        board[i][j] = opponent;

                        best = Math.min(best, minimax(board, depth + 1, !isMax, player, opponent));

                        board[i][j] = " ";
                    }
                }
            }
            return best;
        }
    }

    static Move findBestMove(String board[][] ,String player, String opponent){

        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){

                if (board[i][j] == " "){

                    board[i][j] = player;

                    int moveVal = minimax(board, 0, false, player, opponent);

                    board[i][j] = " ";

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}










