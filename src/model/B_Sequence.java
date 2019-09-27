package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B_Sequence {
	private static int sizeOfS;
	private static int greater;
	private static int positionGreater;
	private static List<Integer> toReturn;

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

		toReturn = new ArrayList<Integer>();
		for (int i = 0; i < elementsToAdd.length; i++) {
			toReturn.add(i, elementsOfS[i]);
		}

		for (int i = 0; i < numberOfOperations; i++) {
			if (elementsToAdd[i] == greater) {
				System.out.println(sizeOfS);
			} else {
				if (isRight(elementsOfS, elementsToAdd[i])) {
					System.out.println(sizeOfS);
				} else {
					if (isLeft(elementsOfS, elementsToAdd[i])) {
						// Add derecha
						addRight(toReturn, elementsToAdd[i]);
						sizeOfS++;
						System.out.println(sizeOfS);
					} else {
						// Add izquierda
						addLeft(toReturn, elementsToAdd[i]);
						System.out.println(sizeOfS);
						sizeOfS++;
					}
				}
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

	private static boolean isRight(int[] array, int toSearch) {
		return is(array, positionGreater + 1, array.length - 1, toSearch);
	}

	private static boolean isLeft(int[] array, int toSearch) {
		return is(array, 0, positionGreater - 1, toSearch);
	}

	private static boolean is(int[] array, int i, int j, int toSearch) {
		boolean izq = false;
		boolean der = false;
		int m = (i + j) / 2;

		if (i == j) {
			if (array[i] == toSearch) {
				return true;
			} else {
				return false;
			}
		} else {
			izq = is(array, i, m, toSearch);

			der = is(array, m + 1, j, toSearch);

			if (izq == true || der == true) {
				return true;
			} else {
				return false;
			}
		}
	}

	private static void addRight(List<Integer> toReturn, int toAdd) {
		add(toReturn, positionGreater + 1, toReturn.size() - 1, toAdd);
	}

	private static void addLeft(List<Integer> toReturn, int toAdd) {
		add(toReturn, 0, positionGreater - 1, toAdd);
	}

	private static void add(List<Integer> toReturn, int i, int j, int toAdd) {
		int middle = (i + j) / 2;

		if (i == j) {
			if (toAdd > toReturn.get(i)) {
				toReturn.add(i, toAdd);
			}else {
			//	add(toReturn,i+1,middle,toAdd)
			}
		} else {
		//	add(toReturn,i,middle,toAdd);
			//add(toReturn,i,middle,toAdd);
			
			
		}
	}
}