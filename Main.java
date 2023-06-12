import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static void initBoard (HashMap<Integer, Character> board) {
        for (int i = 1; i <= 9; i++ ) {
            board.put(i, '_');
        }
    }

    static void displayBoard (HashMap<Integer, Character> board) {

        for (Integer i : board.keySet()) {
            if (board.get(i) == '_') {
                System.out.print(i);
            } else {
                System.out.print(board.get(i));
            }

            if (i % 3 != 0) {
                System.out.print(" | ");
            } else {
                System.out.println();
                if (i != 9) {
                    System.out.println("---------");
                }
            }
        }

        System.out.println();
    }

    static void greet() {
        System.out.println("WELCOME TO TIC-TAC-TOE");
    }

    static void showInstructions() {
        System.out.println("Instructions:");
        System.out.println("1. The game is played on a grid that's 3 squares by 3 squares.");
        System.out.println("2. You are X, your friend is O. Players take turns putting their marks in empty numbers.");
        System.out.println("3. The first player to get 3 of his marks in a row (up, down, across, or diagonally) is the winner.");
        System.out.println("4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
    }

    static boolean isFilled (int key, HashMap<Integer, Character> board) {
        if(board.get(key) != '_') {
            return true;
        }

        return false;
    }

    static void fillBoard (int pos, HashMap<Integer, Character> board, char element) {
        board.replace(pos,element);
    }

    static char checkWinner(HashMap<Integer, Character> board) {
        int[][] winningConditions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

        for (int[] numbers: winningConditions) {
            if (board.get(numbers[0]) == board.get(numbers[1]) && board.get(numbers[1]) == board.get(numbers[2])) {
                return board.get(numbers[0]);
            }
        }

        return '_';
    }

    static boolean checkDraw(HashMap<Integer, Character> board) {

        for (char value : board.values()) {
            if (value == '_') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // initialisation
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Character> board = new HashMap<Integer, Character>();
        initBoard(board);

        // greeting and displaying the board
        greet();
        System.out.println();
        displayBoard(board);

        System.out.println("Do you need any instructions(Y/N)?");
        System.out.print("=");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("Y")) {
            showInstructions();
        }

        System.out.println("First turn goes to X");

        char activePlayer = 'X';

        while (true) {

            System.out.println("Enter the place where you want to place " + activePlayer);
            System.out.print("= ");

            int pos = scanner.nextInt();

            if( pos > 9 && pos < 1) {
                System.out.println("Oopps try again");
            } else if (isFilled(pos, board)) {
                System.out.println("Already Filled!");
            } else {
                fillBoard(pos, board, activePlayer);
                activePlayer = activePlayer == 'X' ? 'O' : 'X';

                displayBoard(board);

                if (checkWinner(board) == 'X') {
                    System.out.println("X WINS!");
                    break;
                }

                if (checkWinner(board) == 'O') {
                    System.out.println("O WINS!");
                    break;
                }

                if (checkDraw(board)) {
                    System.out.println("Its a DRAW!");
                    break;
                }
            }
        }


        scanner.close();
        System.out.println("See you again!");

    }
}