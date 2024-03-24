package algoritmos;

import java.util.Comparator;
import java.util.PriorityQueue;

public class B__informada extends EightQueen {
    public static void solve(int n) {
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.h));
        openSet.add(new State(new int[n]));

        while (!openSet.isEmpty()) {
            State current = openSet.poll();
            current.printBoard();
            if (current.h == 0) {
                System.out.println("Se encuentra como solución final:");
                current.printBoard();
                return;
            }
            openSet.addAll(current.generateSuccessors());
        }
    }
 
}