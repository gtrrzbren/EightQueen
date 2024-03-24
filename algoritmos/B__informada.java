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
                        if (i + k < SIZE_OF_BOARD && j + k < SIZE_OF_BOARD && board[i + k][j + k]) {
                            attackingPairs++;
                        }
                        if (i + k < SIZE_OF_BOARD && j - k >= 0 && board[i + k][j - k]) {
                            attackingPairs++;
                        }
                        if (i - k >= 0 && j + k < SIZE_OF_BOARD && board[i - k][j + k]) {
                            attackingPairs++;
                        }
                        if (i - k >= 0 && j - k >= 0 && board[i - k][j - k]) {
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

    public void metodoDePrueba(){
        int f = 8;
        int c = 8;
        int[][] tablero = new int[f][c];     
        int[][] heuristica = { {22,22,22,22,22,22,22,22},
                                {22,24,24,24,24,24,24,22},
                                {22,24,26,26,26,26,24,22},
                                {22,24,26,28,28,26,24,22},
                                {22,24,26,28,28,26,24,22},
                                {22,24,26,26,26,26,24,22},
                                {22,24,24,24,24,24,24,22},
                                {22,22,22,22,22,22,22,22} };
       
        int filaActual = 0;
        int columnaActual = 0;
        int reina = 0;
        int mejor = 30;
        int horizontal = 0;
        int vertical = 0;
        int vertical_positivo, vertical_negativo;// variables para la diagonal //
        int casillas = 0; //variable para comprobar si está todo el tablero ocupado //
       
        while (casillas < 64)
        {
            casillas = 0;
            // revisa todo el tablero para ver la mejor posibilidad (se podría hacer función) //
              for(filaActual = 0; filaActual < 8; filaActual++)
              {
                 for(columnaActual = 0; columnaActual < 8; columnaActual++)
                 {
                      if( tablero[filaActual][columnaActual] == 0)
                      {
                          if( heuristica[filaActual][columnaActual] < mejor)
                          {
                              mejor = heuristica[filaActual][columnaActual];
                              horizontal = filaActual;
                              vertical = columnaActual;
                          }
                      }
                 }
              }
              // fin revisar tablero //
             
              tablero[horizontal][vertical] = 5; // situa la reina en el tablero //
             
              for(columnaActual = 0; columnaActual < 8; columnaActual++)// marca el ataque horizontal de la reina actual (deja las casillas//
              {   
                  if(tablero[horizontal][columnaActual] != 5 )
                  {                                                     // horizontales ocupadas) //
                     tablero[horizontal][columnaActual] = 1;
                  }
              }
             
              for(filaActual = 0; filaActual < 8; filaActual++) // marca el ataque vertical de la reina actual (deja las casillas verticales ocupadas) //
              {
                  if ( tablero[filaActual][vertical] != 5 )
                  {
                  tablero[filaActual][vertical] = 1;
                  }
              }
             
              // marca el ataque diagonal de la reina actual (deja las diagonales ocupadas //
             vertical_positivo = vertical;
             vertical_negativo = vertical;
             for(filaActual = horizontal - 1; filaActual >= 0; filaActual--)
              {
                vertical_positivo++;
                vertical_negativo--;
                if (vertical_positivo <= 7 && tablero[filaActual][vertical_positivo] != 5)
                {
                   tablero[filaActual][vertical_positivo] = 1;
                }
                if (vertical_negativo >= 0 && tablero[filaActual][vertical_negativo] != 5)
                {
                   tablero[filaActual][vertical_negativo] = 1;
                } 
              }
             
             vertical_positivo = vertical;
             vertical_negativo = vertical;
             for(filaActual = horizontal + 1; filaActual <= 7; filaActual++)
              {
                vertical_positivo++;
                vertical_negativo--;
                if (vertical_positivo <= 7 && tablero[filaActual][vertical_positivo] != 5)
                {
                   tablero[filaActual][vertical_positivo] = 1;
                }
                if (vertical_negativo >= 0 && tablero[filaActual][vertical_negativo] != 5)
                {
                   tablero[filaActual][vertical_negativo] = 1;
                } 
              }
              // fin ataque diagonal //
             
              // ajustar heuristica//
             
            for(filaActual = 0; filaActual < 8; filaActual++)
            {
              for(columnaActual = 0; columnaActual < 8; columnaActual++)
              {
                 heuristica[filaActual][columnaActual] = 0;
              }
            }
             
              for (horizontal = 0; horizontal <= 7; horizontal++)
              {
                  for(vertical = 0; vertical <= 7; vertical++)
                  {
                     if (tablero[horizontal][vertical] != 1 && tablero[horizontal][vertical] != 5)
                     {
                          for(columnaActual = 0; columnaActual < 8; columnaActual++)// marca el ataque horizontal de la reina actual (deja las casillas horizontales ocupadas) //
                          {   
                              if(tablero[horizontal][columnaActual] != 5 && tablero[horizontal][columnaActual] != 1 )
                              {                                                     
                                 heuristica[horizontal][vertical]++;
                              }
                          }
                         
                          for(filaActual = 0; filaActual < 8; filaActual++) // marca el ataque vertical de la reina actual (deja las casillas verticales ocupadas) //
                          {
                              if ( tablero[filaActual][vertical] != 5 && tablero[filaActual][vertical] != 1 )
                              {
                                heuristica[horizontal][vertical]++;
                              }
                          }
                         
                          // marca el ataque diagonal de la reina actual (deja las diagonales ocupadas //
                         vertical_positivo = vertical;
                         vertical_negativo = vertical;
                         for(filaActual = horizontal - 1; filaActual >= 0; filaActual--)
                          {
                            vertical_positivo++;
                            vertical_negativo--;
                            if (vertical_positivo <= 7 && tablero[filaActual][vertical_positivo] != 5 && tablero[filaActual][vertical_positivo] != 1)
                            {
                               heuristica[horizontal][vertical]++;
                            }
                            if (vertical_negativo >= 0 && tablero[filaActual][vertical_negativo] != 5 && tablero[filaActual][vertical_negativo] != 1)
                            {
                               heuristica[horizontal][vertical]++;
                            } 
                          }
                         
                         vertical_positivo = vertical;
                         vertical_negativo = vertical;
                         for(filaActual = horizontal + 1; filaActual <= 7; filaActual++)
                          {
                            vertical_positivo++;
                            vertical_negativo--;
                            if (vertical_positivo <= 7 && tablero[filaActual][vertical_positivo] != 5 && tablero[filaActual][vertical_positivo] != 1)
                            {
                               heuristica[horizontal][vertical]++;
                            }
                            if (vertical_negativo >= 0 && tablero[filaActual][vertical_negativo] != 5 && tablero[filaActual][vertical_negativo] != 1)
                            {
                               heuristica[horizontal][vertical]++;
                            } 
                          }
                          // fin ataque diagonal //
                     }
                               
                  }
              } // fin ajustar heuristica //
             
             
              mejor = 30; // reinicia 'mejor' para una nueva reina //
              reina++;
              // revisa el tablero para ver si estan todas las casillas ocupadas //
              for(filaActual = 0; filaActual < 8; filaActual++)
              {
                 for(columnaActual = 0; columnaActual < 8; columnaActual++)
                 {
                    if (tablero[filaActual][columnaActual] != 0)
                       casillas++;
                 }
              } // fin revisar tablero//
             
        }
        // muestra el tablero //
        for(filaActual = 0; filaActual < 8; filaActual++)
        {
          for(columnaActual = 0; columnaActual < 8; columnaActual++)
          {
             System.out.printf("%d ",tablero[filaActual][columnaActual] );
          }
          System.out.println("\n");
        }
       
        // muestra la heuristica //
        System.out.println("\n");
       
        for(filaActual = 0; filaActual < 8; filaActual++)
        {
          for(columnaActual = 0; columnaActual < 8; columnaActual++)
          {
            System.out.printf("%2d ",heuristica[filaActual][columnaActual] );
          }
          System.out.println("\n");
        }
    }

}