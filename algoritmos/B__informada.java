package algoritmos;

import java.util.PriorityQueue;

public class B__informada extends EightQueen {

  // Método para calcular la heurística: número de pares de reinas que se atacan
  final int calculateHeuristic() {
    int attackingPairs = 0;
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      for (int j = 0; j < SIZE_OF_BOARD; j++) {
        if (BOARD_BOOLEAN[i][j] == QUEEN_PLACE) {
          // Incrementa el contador por cada ataque detectado
          attackingPairs += countAttacks(i, j);
        }
      }
    }
    return attackingPairs / 2; // Cada par se cuenta dos veces
  }

  // Método para contar los ataques de una reina en (row, col)
  private int countAttacks(int Board_Row, int Board_Column) {
    int count = 0;
  
    // Contar ataques en la misma fila
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      if (i != Board_Column && BOARD_BOOLEAN[Board_Row][i]) {
        count++;
      }
    }
  
    // Contar ataques en la misma columna
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      if (i != Board_Row && BOARD_BOOLEAN[i][Board_Column]) {
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
            if (BOARD_BOOLEAN[x][y]) {
              count++;
            }
          }
          x += directions[i];
          y += directions[j];
        }
      }
    }
  
    return count;
  }
  

  // Método A* para colocar reinas
  /**
 * @param Board_Column
 * @return
 */
public boolean Queens_Placing_AStar(int Board_Column) {
    if (Board_Column >= SIZE_OF_BOARD) {
      return true;
    } else {
      PriorityQueue<State> openSet = new PriorityQueue<>();
      // Agregar el estado inicial al conjunto abierto
      openSet.add(new State(BOARD_BOOLEAN, calculateHeuristic(), Board_Column));

      while (!openSet.isEmpty()) {
        State currentState = openSet.poll();
        // Si el estado actual tiene heurística 0, todas las reinas están colocadas correctamente
        if (currentState.heuristic == 0) {
          BOARD_BOOLEAN = currentState.board;
          return true;
        }

        // Generar sucesores del estado actual
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
          if (countAttacks(i, currentState.column) == 0) {
            boolean[][] newBoard = currentState.board.clone();
            newBoard[i][currentState.column] = QUEEN_PLACE;
            int newHeuristic = calculateHeuristic();
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

