package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Sequence {
	private static int sizeOfS;
	private static int greater;
	private static int positionGreater;
	private static Tree root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		sizeOfS = Integer.parseInt(line);

		line = br.readLine();
		String[] elements = line.split(" ");

		int[] elementsOfS = new int[sizeOfS];
		for (int i = 0; i < sizeOfS; i++) {
			elementsOfS[i] = Integer.parseInt(elements[i]);
		}

		line = br.readLine();
		int numberOfOperations = Integer.parseInt(line);

		int[] elementsToAdd = new int[numberOfOperations];

		for (int i = 0; i < numberOfOperations; i++) {
			line = br.readLine();
			elementsToAdd[i] = Integer.parseInt(line);
		}

		positionGreater = findGreater(elementsOfS);
		greater = elementsOfS[positionGreater];
		
		root = new Tree(greater, null, null);

		System.out.println("The : 	"+positionGreater +"     " +greater);
		
		
		for (int i = 0; i < elementsOfS.length; i++) {
			if (elementsOfS[i] != greater) {
				addNumber(root);
			}
		}
		
		//Tomar medio
		//agregar primera mitad
		//luego segunda mitad

		for (int i = 0; i < elementsToAdd.length; i++) {
			// Aqui se decide si se va agregando cada elemento al arrelgo

			if (elementsToAdd[i] != greater) {

			}
		}

	}

	public static int findGreater(int[] array) {
		return findGreater(array, 0, array.length - 1);
	}

	private static int findGreater(int[] array, int low, int high) {
		int izq;
		int der;
		int middle = (low + high) / 2;

		if (low == high) {
			return low;
		} else {
			izq = findGreater(array, low, middle);
			der = findGreater(array, middle + 1, high);

			if (array[izq] < array[der]) {
				return der;
			} else if (array[izq] > array[der]) {
				return izq;
			} else {
				return izq;
			}
		}
	}

	// Buscar mayor y ponerlo de root
	// RellenarArbol
	// agregar a arbol izq si hay valor menor cambiarlo si ya esta intentar
	// agregarlo a la der de la misma manera si ya esta no agregar
	// repetir maximo dos veces mismo numero
	// Convertir arbol en arreglo y retornar tamaño

	public static void addNumber(Tree toAdd) {
		addNumber(toAdd, root);
	}

	private static void addNumber(Tree toAdd, Tree current) {
		boolean growing = true;

		if (root.getValue() == greater) {
			growing = false;
		}

		if(growing) {
			
			
			
			
		}else {
			
		}
		
		
		
		
		
	}
}

class Tree {
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