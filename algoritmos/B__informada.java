package algoritmos;

import java.util.PriorityQueue;
import java.util.Arrays;

public class B__informada extends EightQueen {

    public static final int SIZE_OF_BOARD = 8;
    private PriorityQueue<State> openSet;
    private boolean[][] BOARD_BOOLEAN;

    public B__informada() {
        BOARD_BOOLEAN = new boolean[SIZE_OF_BOARD][SIZE_OF_BOARD];
        openSet = new PriorityQueue<>((a, b) -> a.heuristic - b.heuristic);
        initializeBoard();
    }

    // Método para inicializar el tablero con una configuración aleatoria de reinas
    private void initializeBoard() {
      for (int i = 0; i < SIZE_OF_BOARD; i++) {
        Arrays.fill(BOARD_BOOLEAN[i], Boolean.FALSE); // Simple initial solution with one queen per row
        }
    }

    // Método para calcular la heurística de un estado
    private int calculateHeuristic(boolean[][] board) {
        int attackingPairs = 0;
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                if (board[i][j]) {
                    // Check for attacks in the same row
                    for (int k = 0; k < SIZE_OF_BOARD; k++) {
                        if (board[i][k] && k != j) {
                            attackingPairs++;
                        }
                    }
                    // Check for attacks in the same column
                    for (int k = 0; k < SIZE_OF_BOARD; k++) {
                        if (board[k][j] && k != i) {
                            attackingPairs++;
                        }
                    }
                    // Check for attacks in the diagonals
                    for (int k = 1; k < SIZE_OF_BOARD; k++) {
                        if (i+k < SIZE_OF_BOARD && j+k < SIZE_OF_BOARD && board[i+k][j+k]) {
                            attackingPairs++;
                        }
                        if (i+k < SIZE_OF_BOARD && j-k >= 0 && board[i+k][j-k]) {
                            attackingPairs++;
                        }
                        if (i-k >= 0 && j+k < SIZE_OF_BOARD && board[i-k][j+k]) {
                            attackingPairs++;
                        }
                        if (i-k >= 0 && j-k >= 0 && board[i-k][j-k]) {
                            attackingPairs++;
                        }
                    }
                }
            }
        }
        return attackingPairs / 2; // Each pair is counted twice
    }

    // Método para verificar si el estado es una solución
    private boolean isGoalState(boolean[][] board) {
        return calculateHeuristic(board) == 0;
    }

    // Método para generar sucesores de un estado
    private void generateSuccessors(State currentState) {
        for (int row = 0; row < SIZE_OF_BOARD; row++) {
            for (int col = 0; col < SIZE_OF_BOARD; col++) {
                if (currentState.board[row][col]) {
                    // Move the queen in this column
                    for (int newRow = 0; newRow < SIZE_OF_BOARD; newRow++) {
                        if (newRow != row) {
                            boolean[][] newBoard = deepCloneBoard(currentState.board);
                            newBoard[row][col] = false;
                            newBoard[newRow][col] = true;
                            int newHeuristic = calculateHeuristic(newBoard);
                            openSet.add(new State(newBoard, newHeuristic));
                        }
                    }
                }
            }
        }
    }

    // Método para realizar una clonación profunda de un tablero
    private boolean[][] deepCloneBoard(boolean[][] original) {
        boolean[][] copy = new boolean[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    // Método A* para resolver el problema de las 8 reinas
    public boolean solve() {
        openSet.add(new State(BOARD_BOOLEAN, calculateHeuristic(BOARD_BOOLEAN)));

        while (!openSet.isEmpty()) {
            State currentState = openSet.poll();
            if (isGoalState(currentState.board)) {
                BOARD_BOOLEAN = currentState.board;
                return true;
            }
            generateSuccessors(currentState);
        }
        return false;
    }

    // Clase para representar un estado en A*
    class State {
        boolean[][] board;
        int heuristic;

        public State(boolean[][] board, int heuristic) {
            this.board = board;
            this.heuristic = heuristic;
        }
    }

    // Método para imprimir el tablero
    public void printBoard() {
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                System.out.print(BOARD_BOOLEAN[i][j] ? " Q " : " . ");
            }
            System.out.println();
        }
    }

    

}