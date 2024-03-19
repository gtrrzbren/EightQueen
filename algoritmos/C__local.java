package algoritmos;

import java.util.Random;

public class C__local extends EightQueen {
  public static final int SIZE_OF_BOARD = 8;
  private int[] queens; // Representa las posiciones de las reinas en el tablero

  public C__local() {
    queens = new int[SIZE_OF_BOARD];
    initializeBoard();
  }

  // Inicializa el tablero con una solución aleatoria
  private void initializeBoard() {
    Random rand = new Random();
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      queens[i] = rand.nextInt(SIZE_OF_BOARD);
    }
  }

  // Imprime el tablero
  public void printBoard() {
    for (int row = 0; row < SIZE_OF_BOARD; row++) {
      for (int col = 0; col < SIZE_OF_BOARD; col++) {
        if (queens[col] == row) {
          System.out.print(" Q ");
        } else {
          System.out.print(" * ");
        }
      }
      System.out.println();
    }
  }

  // Método para calcular el número de pares de reinas que se atacan
  private int calculateHeuristic() {
    int attackingPairs = 0;
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      for (int j = i + 1; j < SIZE_OF_BOARD; j++) {
        if (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) {
          attackingPairs++;
        }
      }
    }
    return attackingPairs;
  }

  // Método para realizar la búsqueda de escalador de colinas
  public void hillClimbing() {
    int currentHeuristic = calculateHeuristic();
    int bestHeuristic = currentHeuristic;
    int[] bestBoard = queens.clone();

    while (currentHeuristic != 0) {
      for (int col = 0; col < SIZE_OF_BOARD; col++) {
        int initialRow = queens[col];
        for (int row = 0; row < SIZE_OF_BOARD; row++) {
          queens[col] = row;
          int newHeuristic = calculateHeuristic();
          if (newHeuristic < bestHeuristic) {
            bestHeuristic = newHeuristic;
            bestBoard = queens.clone();
          }
        }
        queens[col] = initialRow; // Revertir al estado inicial
      }

      if (bestHeuristic == currentHeuristic) {
        // No se encontró una mejor solución, reiniciar con una nueva solución aleatoria
        initializeBoard();
        currentHeuristic = calculateHeuristic();
        bestHeuristic = currentHeuristic;
        bestBoard = queens.clone();
      } else {
        // Se encontró una mejor solución
        queens = bestBoard.clone();
        currentHeuristic = bestHeuristic;
      }
    }
  }

}
