package edu.neu.info6205;

import edu.neu.info6205.player.menace.Matchbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Board {

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    private char[][] board;
    private int turn;



    public Board(){
        board = new char[3][3];

        // initialise the board (can be done more easily, this is also to illustrate the double for loop)
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = TicTacToeLauncher.EMPTY;
            }
        }

        turn = 0;
    }


    public Board(Board board) {
        this.board = new char[3][3];

        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[row].length; column++) {
                this.board[row][column] = board.getCoordinate(row, column);

            }
        }

        turn = board.getTurn();
    }

    public List<Integer> availableMoves() {
        List<Integer> availableMoves = new ArrayList<>();
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[row].length; column++) {
                if (this.board[row][column] == ' ') {
                    availableMoves.add((row * 3) + column + 1);
                }
            }
        }
        return availableMoves;
    }

    public void makeMove(int move, char player) {
        // translate single number to coordinate
        int moveX = (move - 1) / 3;
        int moveY = (move - 1) % 3;
        //Matchbox.learnHashtable.;
        makeMove(moveX, moveY, player);
    }

    public void makeMove(int x, int y, char player) {
        if (board[x][y] == ' ') {
            board[x][y] = player;
            turn++;
        } else {
            System.out.println(availableMoves());
        }
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        String returnString = "-------------\n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                returnString += "| " + board[i][j] + " ";
            }
            returnString += "|\n-------------\n";
        }
        return returnString;
    }

    public char winner() {

        // rows and columns (assuming square board)
        for (int rowcol = 0; rowcol < board.length; rowcol++) {
            // rows
            if (board[rowcol][0] != ' ' && board[rowcol][0] == board[rowcol][1] && board[rowcol][0] == board[rowcol][2]) {
                return board[rowcol][0];
            }

            //columns
            if (board[0][rowcol] != ' ' && board[0][rowcol] == board[1][rowcol] && board[0][rowcol] == board[2][rowcol]) {
                return board[0][rowcol];
            }
        }

        //diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2];
        }

        // no winner, if there are no more moves it is a draw.
        if (availableMoves().isEmpty()) { // alternative: turn == 9
            return TicTacToeLauncher.DRAW;
        }

        // no winner yet
        return TicTacToeLauncher.EMPTY;
    }

    public char getCoordinate(int x, int y) {
        return board[x][y];
    }

    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (getCoordinate(row, col) != ((Board) o).getCoordinate(row, col)){ return false; }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
