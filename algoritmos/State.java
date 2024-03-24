package algoritmos;

import java.util.ArrayList;
import java.util.List;

public class State {
    int[] queens;
    int h;

    public State(int[] queens) {
        this.queens = queens;
        this.h = heuristic(queens);
    }

    private int heuristic(int[] queens) {
        int h = 0;
        for (int i = 0; i < queens.length; i++) {
            for (int j = i + 1; j < queens.length; j++) {
                if (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == j - i) {
                    h++;
                }
            }
        }
        return h;
    }

    public List<State> generateSuccessors() {
        List<State> successors = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            for (int j = 1; j < queens.length; j++) {
                int[] newQueens = queens.clone();
                newQueens[i] = (newQueens[i] + j) % queens.length;
                successors.add(new State(newQueens));
            }
        }
        return successors;
    }

    public void printBoard() {
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens.length; j++) {
                if (queens[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

