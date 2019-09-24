package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Sequence {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Tree tree;

		String line = br.readLine();
		int sizeOfS = Integer.parseInt(line);

		line = br.readLine();
		String[] elements = line.split(" ");

		int[] elementsOfS = new int[sizeOfS];
		for (int i = 0; i < sizeOfS; i++) {
			elementsOfS[i] = Integer.parseInt(elements[i]);
		}

//Buscar mayor y ponerlo de root
		//RellenarArbol
		//agregar a arbol de manera ordenada 
		//repetir maximo dos veces mismo numero
		//Convertir arbol en arreglo y retornar tamaño
		
		
		
		
		
		
	}

	public class Tree {
		private int value;
		private Tree left;
		private Tree right;

		public Tree(int value, Tree left, Tree right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Tree getLeft() {
			return left;
		}

		public void setLeft(Tree left) {
			this.left = left;
		}

		public Tree getRight() {
			return right;
		}

		public void setRight(Tree right) {
			this.right = right;
		}
	}
}