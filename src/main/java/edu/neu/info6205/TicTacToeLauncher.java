package edu.neu.info6205;

import edu.neu.info6205.player.ComputerPlayer;
import edu.neu.info6205.player.HumanPlayer;
import edu.neu.info6205.player.Player;
import edu.neu.info6205.player.menace.Matchbox;

import java.util.Random;

public class TicTacToeLauncher {

    public static final char EMPTY = ' ';
    public static final char Human = 'X';
    public static final char Computer = 'O';
    public static final char DRAW = '-';

    public static void main(String[] args) {
        Board board = new Board();

        Player humanPlayer = new HumanPlayer(Human);
        Player computerPlayer = new ComputerPlayer(Computer);

        int humanWins = 0;
        int computerWins = 0;
        int draws = 0;
        int iteration = 0;
        Random r= new Random();
        boolean turn= r.nextBoolean();
        //boolean turn=true;
        System.out.println("    Player 1 | Player 2 |    Draws");
        while (humanWins+computerWins+draws < 1000){
            while (board.winner() == EMPTY) {
                if (turn) {
                    board = humanPlayer.makeMove(board);
                }
                else {
                    board = computerPlayer.makeMove(board);
                }

                turn=!turn;
            }
        System.out.println(Matchbox.pattern);
            char winner = board.winner();
            switch (winner) {
                case Human:
                    humanPlayer.winner(Result.Beta);
                    computerPlayer.winner(Result.Gamma);
                    humanWins++;
                    break;
                case Computer:
                    humanPlayer.winner(Result.Gamma);
                    computerPlayer.winner(Result.Beta);
                    computerWins++;
                    break;
                case DRAW:
                default:
                    humanPlayer.winner(Result.Delta);
                    computerPlayer.winner(Result.Delta);
                    draws++;
            }

            System.out.println(humanWins+" "+computerWins+" "+draws);


            if ((humanWins + computerWins + draws) % 2000 == 0) {
                System.out.printf("%3d %8d | %8d | %8d\n", iteration, humanWins, computerWins, draws);
                humanWins = 0;
                computerWins = 0;
                draws = 0;
                iteration++;
            }

            board = new Board();
            Matchbox.pattern="";
            Matchbox.patternList.clear();
            System.out.println(Matchbox.learnHashtable);

        }

        System.out.printf("    %8d | %8d | %8d\n", humanWins, computerWins, draws);

    }

}
