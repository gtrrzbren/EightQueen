package algoritmos;

public class EightQueen {

  // El tamaño del tablero debe ser 8x8 para el problema de las ocho reinas
  public static final int SIZE_OF_BOARD = 8;
  
  boolean[][] BOARD_BOOLEAN;
  // Para un lugar vacío
  public static final boolean EMPTY_PLACE = false;
  // Para un lugar que contiene una reina
  public static final boolean QUEEN_PLACE = true;
  // El número de movimientos
  public static final int MOVES_NUMBER = 4;
  // Los movimientos horizontales
  int[] Horizontal_Moves;
  // Los movimientos verticales
  int[] Vertical_Moves;
  
  public int Queens = 0;
  
  public EightQueen() {
    // El constructor crea un tablero vacío
    BOARD_BOOLEAN = new boolean[SIZE_OF_BOARD][SIZE_OF_BOARD];
    for (int row = 0; row < BOARD_BOOLEAN.length; row++) {
      for (int col = 0; col < BOARD_BOOLEAN[row].length; col++) {
        BOARD_BOOLEAN[row][col] = EMPTY_PLACE;
      }
    }
    Horizontal_Moves = new int[MOVES_NUMBER];
    Vertical_Moves = new int[MOVES_NUMBER];
    // mover hacia arriba a la derecha
    Horizontal_Moves[0] = -1;
    Vertical_Moves[0] = 1;
    // mover hacia abajo a la izquierda
    Horizontal_Moves[1] = 1;
    Vertical_Moves[1] = -1;
    // mover hacia arriba a la izquierda
    Horizontal_Moves[2] = -1;
    Vertical_Moves[2] = -1;
    // mover hacia abajo a la derecha
    Horizontal_Moves[3] = 1;
    Vertical_Moves[3] = 1;
}

public void Display_Board() {
  int Count = 0;
  for (int Board_Row = 0; Board_Row < BOARD_BOOLEAN.length; Board_Row++) {
    for (int Board_Column = 0; Board_Column < BOARD_BOOLEAN[Board_Row].length; Board_Column++) {
      if (BOARD_BOOLEAN[Board_Row][Board_Column] == true) {
        System.out.printf("|%s| ", " Q ");
        Count++;
      } else {
        System.out.printf("|%s| ", " X ");
      }
    }
    System.out.println();
  }

  System.out.printf("%d queens problem is solved, the queens are placed.\n", Count);
}

  public static void main(String[] arg) {
    //
    //A__no_informada enProfundidad = new A__no_informada();
    //enProfundidad.Queens_Placing(0);
    //enProfundidad.Display_Board();

    //
    B__informada aasterico = new B__informada();
       /*if (aasterico.solve()) {
            aasterico.printBoard();
        } else {
            System.out.println("No solution found.");
        }*/
    aasterico.solve(8);

    //
    //C__local escaladorColinas = new C__local();
    //escaladorColinas.hillClimbing();
    //escaladorColinas.printBoard();
  }
}