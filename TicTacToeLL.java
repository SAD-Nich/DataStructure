import java.util.Scanner;
public class TicTacToeLL {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            char[] board = new char[9];
            //initializing board
            for(int i = 0; i < 9; i++)
                board[i] = ' ';

            Node head = null;   // head of linked list 
            Node tempPtr;

            int moveNumber = 0;
            char turn = 'X';
            int cell = 0;
            displayBoard(board);//displays the empty board
            while(moveNumber < 9) {
                displayList(head);
                System.out.println("*****************************");
                System.out.println("Move number: " + moveNumber);
                System.out.print("TURN: " + turn + " make your move: ");
                cell = scanner.nextInt(); //this is the simple version. only input numbers here. does not check non-numeric characters..
                //is input correct?
                if(rangeCorrect(cell)) { //checking if inside the range 1-9
                    if(cellIsEmpty(cell-1, board)){ //checking if cell is empty. subtracting 1, because array starts from 0.
                        board[cell-1] = turn;//storing the move in the array
                        if(three(turn, board)) {//checking if there are three in a row or column or diagonal
                            displayBoard(board);
                            System.out.println("Congratulations player " + turn + " You win!!");
                            scanner.nextInt(); //to stop execution on windows 
                            return;
                        }
                        //creating a new Node, filling its fields and adding to the list.
                        tempPtr = new Node();
                        // Insert at head of list 
                        tempPtr.moveNumber = moveNumber; //copying moveNumber inside the node
                        for(int i=0;i<9;i++) { //copying the elements of the board inside the board in the node
                            tempPtr.board[i]=board[i];
                        }
                        tempPtr.nextPtr = head; //the pointer of the node points to the first element of the list
                        head = tempPtr; //the head points to the new node just created
                        moveNumber++; //preparing for next move.
                        if(turn == 'X'){
                            turn = 'O';
                        }
                        else if(turn == 'O'){
                            turn = 'X';
                        }
                    }
                    else { //cell was full
                        System.out.println("Sorry...cell " + cell + " has already been used, try again: ");
                    }
                }
                else {
                    System.out.println("Sorry...cell " + cell + " is outside the range 1-9, try again: ");
                }
            }
            System.out.println("Game Ended: No Winners");
        }
    }

    static void displayList(Node head) {
        Node tempPtr = head;  // Used to traverse list without destroying head
        System.out.println("\n List contains: ");
        while( tempPtr != null) {
            displayBoard(tempPtr.board);
            System.out.println("Move Number: " + tempPtr.moveNumber + "\n");
            tempPtr = tempPtr.nextPtr;
        }
        System.out.println("\n\n");
    }

    static void displayBoard(char[] board) {
        System.out.println(" ___   ___   ___ ");
        System.out.println("| " + board[0] + " |" + " " + "| " + board[1] + " |" + " " + "| " + board[2] + " |");
        System.out.println("|___| |___| |___| ");
        System.out.println(" ___   ___   ___ ");
        System.out.println("| " + board[3] + " |" + " " + "| " + board[4] + " |" + " " + "| " + board[5] + " |");
        System.out.println("|___| |___| |___| ");
        System.out.println(" ___   ___   ___ ");
        System.out.println("| " + board[6] + " |" + " " + "| " + board[7] + " |" + " " + "| " + board[8] + " |");
        System.out.println("|___| |___| |___| ");
    }

    //returns true if cell is between 1 and 9
    static boolean rangeCorrect(int cell) {
        //checking if cell is outside the range 1 to 9.
        if(cell >= 1 && cell <= 9)
            return true;
        else 
            return false;
    }

    //returns true if the cell has already been used
    static boolean cellIsEmpty(int cell, char[] board) {
        if(board[cell]!=' ')
            return false;
        else 
            return true;
    }

    static boolean three(char turn, char[] board) {
        if(threeInARow(turn, board) || threeInAColumn(turn,board) || threeInADiagonal(turn,board))
            return true;
        else
            return false;
    }

    static boolean threeInARow(char turn, char[] board) {
        if((board[0] == turn && board[1] == turn && board[2] == turn) ||
           (board[3] == turn && board[4] == turn && board[5] == turn) ||
           (board[6] == turn && board[7] == turn && board[8] == turn))
           return true;
        else 
            return false;
    }

    static boolean threeInAColumn(char turn, char[] board) {
        if((board[0] == turn && board[3] == turn && board[6] == turn) ||
           (board[1] == turn && board[4] == turn && board[7] == turn) ||
           (board[2] == turn && board[5] == turn && board[8] == turn))
           return true;
        else 
            return false;
    }

    static boolean threeInADiagonal(char turn, char[] board) {
        if((board[0] == turn && board[4] == turn && board[8] == turn) ||
           (board[2] == turn && board[4] == turn && board[6] == turn))
           return true;
        else 
            return false;
    }

    static class Node {
        int moveNumber;
        char[] board = new char[9];
        Node nextPtr;
    }
}


