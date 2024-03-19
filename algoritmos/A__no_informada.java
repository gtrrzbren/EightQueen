package algoritmos;

public class A__no_informada extends EightQueen {
  
    public boolean Queens_Placing(int Board_Column) {
      if (Board_Column >= SIZE_OF_BOARD) {
        return true;
      } else {
        boolean Queen_Placed = false;
        int Board_Row = 0;
  
        while (!Queen_Placed && Board_Row < SIZE_OF_BOARD) {
          if (Queen_UnderAttack(Board_Row, Board_Column)) {
            ++Board_Row;
          } else {
            Set_Queen(Board_Row, Board_Column);
            Queen_Placed = Queens_Placing(Board_Column + 1);
            if (!Queen_Placed) {
              Remove_Queen(Board_Row, Board_Column);
              ++Board_Row;
            }
          }
        }
        return Queen_Placed;
      }
    }
  
    private void Remove_Queen(int Board_Row, int Board_Column) {
      BOARD_BOOLEAN[Board_Row][Board_Column] = EMPTY_PLACE;
      // Remove the comment from the code below to check which place the queen was removed.
      // System.out.printf("The queen is REMOVED from [%d][%d]\n", Board_Row, Board_Column);
      --Queens;
    }
  
    private void Set_Queen(int Board_Row, int Board_Column) {
      BOARD_BOOLEAN[Board_Row][Board_Column] = QUEEN_PLACE;
      // Remove the comments from the code below to check where the queen was placed
      // System.out.printf("The queen is PLACED in [%d][%d]\n", Board_Row, Board_Column);
      ++Queens;
    }
  
    public boolean Queen_UnderAttack(int Board_Row, int Board_Column) {
      boolean Queen_Condition = false;
      // check the row
      for (int Column = 0; Column < SIZE_OF_BOARD; Column++) {
        if ((BOARD_BOOLEAN[Board_Row][Column] == true)) {
          Queen_Condition = true;
        }
      }
  
      // check the column
      for (int Row = 0; Row < BOARD_BOOLEAN.length; Row++) {
        if (BOARD_BOOLEAN[Row][Board_Column] == true) {
          Queen_Condition = true;
        }
      }
  
      // check the diagonal
      for (int Row = Board_Row, Column = Board_Column; Row >= 0 && Column < 8;
           Row += Horizontal_Moves[0], Column += Vertical_Moves[0]) {
        if (BOARD_BOOLEAN[Row][Column] == true) {
          Queen_Condition = true;
        }
      }
      for (int Row = Board_Row, Column = Board_Column; Row < 8 && Column >= 0;
           Row += Horizontal_Moves[1], Column += Vertical_Moves[1]) {
        if (BOARD_BOOLEAN[Row][Column] == true) {
          Queen_Condition = true;
        }
      }
      for (int Row = Board_Row, Column = Board_Column; Row >= 0 && Column >= 0;
           Row += Horizontal_Moves[2], Column += Vertical_Moves[2]) {
        if (BOARD_BOOLEAN[Row][Column] == true) {
          Queen_Condition = true;
        }
      }
      for (int Row = Board_Row, Column = Board_Column; Row < 8 && Column < 8;
           Row += Horizontal_Moves[3], Column += Vertical_Moves[3]) {
        if (BOARD_BOOLEAN[Row][Column] == true) {
          Queen_Condition = true;
        }
      }
  
      return Queen_Condition;
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
}
