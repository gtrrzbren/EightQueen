package algoritmos;

import java.util.PriorityQueue;

public class B__informada extends EightQueen {

    // Método para realizar una clonación profunda de un tablero
    private boolean[][] deepCloneBoard(boolean[][] original) {
      boolean[][] copy = new boolean[original.length][];
      for (int i = 0; i < original.length; i++) {
        copy[i] = original[i].clone();
      }
      return copy;
    }
  
    // Método para calcular la heurística: número de pares de reinas que se atacan
    final int calculateHeuristic(boolean[][] board) {
      int attackingPairs = 0;
      for (int i = 0; i < SIZE_OF_BOARD; i++) {
        for (int j = 0; j < SIZE_OF_BOARD; j++) {
          if (board[i][j] == QUEEN_PLACE) {
            attackingPairs += countAttacks(board, i, j);
          }
        }
      }
      return attackingPairs / 2; // Cada par se cuenta dos veces
    }
  
    // Método para contar los ataques de una reina en (row, col)
    private int countAttacks(boolean[][] board, int Board_Row, int Board_Column) {
      int count = 0;
      // Contar ataques en la misma fila
      for (int i = 0; i < SIZE_OF_BOARD; i++) {
        if (i != Board_Column && board[Board_Row][i]) {
          count++;
        }
      }
      // Contar ataques en la misma columna
      for (int i = 0; i < SIZE_OF_BOARD; i++) {
        if (i != Board_Row && board[i][Board_Column]) {
          count++;
        }
      }
      // Contar ataques en las diagonales
      int[] directions = {-1, 1};
      for (int i = 0; i < directions.length; i++) {
        for (int j = 0; j < directions.length; j++) {
          int x = Board_Row;
          int y = Board_Column;
          while (x >= 0 && x < SIZE_OF_BOARD && y >= 0 && y < SIZE_OF_BOARD) {
            if (x != Board_Row || y != Board_Column) {
              if (board[x][y]) {
                count++;
                break; // Romper el bucle después de encontrar una reina
              }
            }
            x += directions[i];
            y += directions[j];
          }
        }
      }
      return count;
    }
  
    // Método para verificar si es seguro colocar una reina en (row, col)
    private boolean isSafeToPlaceQueen(boolean[][] board, int row, int col) {
      // Verificar ataques en la fila, columna y diagonales
      return countAttacks(board, row, col) == 0;
    }
  
    // Método A* para colocar reinas
    public boolean Queens_Placing_AStar(int Board_Column) {
      if (Board_Column >= SIZE_OF_BOARD) {
        return true;
      } else {
        PriorityQueue<State> openSet = new PriorityQueue<>();
        openSet.add(new State(deepCloneBoard(BOARD_BOOLEAN), calculateHeuristic(BOARD_BOOLEAN), Board_Column));
  
        while (!openSet.isEmpty()) {
          State currentState = openSet.poll();
          if (currentState.heuristic == 0) {
            BOARD_BOOLEAN = currentState.board;
            return true;
          }
  
          for (int i = 0; i < SIZE_OF_BOARD; i++) {
            if (isSafeToPlaceQueen(currentState.board, i, currentState.column)) {
              boolean[][] newBoard = deepCloneBoard(currentState.board);
              newBoard[i][currentState.column] = QUEEN_PLACE;
              int newHeuristic = calculateHeuristic(newBoard);
              openSet.add(new State(newBoard, newHeuristic, currentState.column + 1));
            }
          }
        }
        return false;
      }
    }
  
    // Clase para representar un estado en A*
    class State implements Comparable<State> {
      boolean[][] board;
      int heuristic;
      int column;
  
      public State(boolean[][] board, int heuristic, int column) {
        this.board = board;
        this.heuristic = heuristic;
        this.column = column;
      }
  
      @Override
      public int compareTo(State other) {
        return Integer.compare(this.heuristic, other.heuristic);
      }
    }
  }