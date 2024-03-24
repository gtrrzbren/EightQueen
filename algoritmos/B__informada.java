package algoritmos;

import java.util.PriorityQueue;
import java.util.Arrays;

public class B__informada extends EightQueen {

    int [][]tablero = new int[8][8];
	int inicio = 1;

	int reynas[] = new int[8]; 

	public void imprimir(){
		//relleno el tablero con los valores resultantes
		for(int t = 0; t < reynas.length; t++){
			tablero[t][reynas[t]] = 1;
		}
		System.out.println("Problema 8 Reinas. 1 Solución.");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print("| "+tablero[i][j]);
			}
			System.out.print("");
			System.out.println("|");
		}


	}

	public boolean creaReynas(int fila){
		boolean encontrado = false;
		if(fila<8){
			while( (reynas[fila] < 7) && !encontrado){//Mientras el valor posicion en que va la fila sea menor a 7 y no este encontrado
				reynas[fila]  = reynas[fila] + 1;//reyna en la posicion de la fila va a ser igual a ella misma mas uno.
				if(casillaUsable(fila)){ // compruebo el si la casilla esta atacada o se puede usar.
					encontrado = creaReynas(fila+1); //se llama al metodo creaReynas que hace al metodo recursivo

				}
			}
			if(!encontrado){ //si no se encontro se disminuye la casilla en uno. de esa fila.
				reynas[fila] = -1;
			}
		}else{
			encontrado = true;
		}
		return encontrado;
	}

	boolean casillaUsable(int fila){
		for(int i = 0; i<fila; i++){
			//valida si la casilla se puede usar, Usa la operacion matematica de obtener el valor absoluto para saber si esta diagonalmente iguales.
			if( (reynas[i] == reynas[fila]) || Math.abs(fila-i) == Math.abs(reynas[fila]-reynas[i])){
				return false;
			}

		}
		return true;
	}


}