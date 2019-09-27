package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Sequence {
	private static int sizeOfS;
	private static int greater;
	private static int positionGreater;
	private static Tree root;
	private static int veces;

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

		root = new Tree(greater, null, null, null);

		// Agregar el arreglo recibido
		boolean isGrowing = true;
		for (int i = 0; i < elementsOfS.length; i++) {
			Tree toAdd = new Tree(elementsOfS[i], null, null, null);

			if (elementsOfS[i] == greater) {
				isGrowing = false;
			}

			addNumber(toAdd, isGrowing);
		}

		// Agregar elementos recibidos
		for (int i = 0; i < elementsToAdd.length; i++) {
			if (elementsToAdd[i] != greater) {
				if (addValue(elementsToAdd[i]) == true) {
					sizeOfS++;
				}
				System.out.println(sizeOfS);
			} else {
				System.out.println(sizeOfS);
			}
		}

		orderTree(root, true);

		System.out.println(printLeft() + " " + printRight());
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

	// Agregar Recibido
	public static void addNumber(Tree toAdd, boolean isGrowing) {
		addNumber(toAdd, isGrowing, root);
	}

	private static void addNumber(Tree toAdd, boolean isGrowing, Tree current) {
		if (toAdd.getValue() != root.getValue()) {
			if (isGrowing) {
				if (current.getLeft() == null) {
					current.setLeft(toAdd);
					toAdd.setPrevious(current);
				} else {
					addNumber(toAdd, true, current.getLeft());
				}
			} else {
				if (current.getRight() == null) {
					current.setRight(toAdd);
					toAdd.setPrevious(current);
				} else {
					addNumber(toAdd, false, current.getRight());
				}
			}
		}
	}

	public static boolean addValue(int value) {
		if (addNumberInTree(value)) {
			return true;
		} else {
			return false;
		}
	}

	// Agregar Entradas
	public static boolean addNumberInTree(int value) {
		Tree toAdd = new Tree(value, null, null, null);
		return addNumberInTree(toAdd, root);
	}

	private static boolean addNumberInTree(Tree toAdd, Tree current) {
		if (toAdd.getValue() == root.getValue()) {
			return false;
		} else {
			boolean izq = false;
			boolean der = false;

			if (toAdd.getValue() == current.getValue()) {
				veces += 1;
				return true;
			} else {
				if (current.getLeft() != null) {
					izq = addNumberInTree(toAdd, current.getLeft());
				}

				if (current.getRight() != null) {
					der = addNumberInTree(toAdd, current.getRight());
				}

				if (current.getLeft() == null) {
					return false;
				}
				if (current.getRight() == null) {
					return false;
				}
			}

			if (der == true) {
				return false;
			} else if (izq == true && der == false) {
				add(toAdd, false);
				return true;
			} else if (veces == 2) {
				return false;
			} else if (izq == false) {
				add(toAdd, true);
				return true;
			} else {
				return false;
			}
		}
	}

	private static void add(Tree toAdd, boolean izq) {
		add(toAdd, root, izq);
	}

	private static void add(Tree toAdd, Tree current, boolean izq) {
		if (izq) {
			if (current.getLeft() == null) {
				current.setLeft(toAdd);
				toAdd.setPrevious(current);
			} else {
				add(toAdd, current.getLeft(), izq);
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(toAdd);
				toAdd.setPrevious(current);
			} else {
				add(toAdd, current.getRight(), izq);
			}
		}
	}

	// Ordenar arbol ascendente la rama izquierda y descendente rama derecha
	private static void orderTree(Tree current, boolean isGrowing) {
		if (current.getValue() != root.getValue()) {
			if (current != null) {
				if (isGrowing) {
					if (current.getLeft() != null) {
						if (current.getLeft().getValue() > current.getValue()) {
							int temp = current.getValue();
							current.setValue(current.getLeft().getValue());
							current.getLeft().setValue(temp);

							orderTree(current.getLeft(), true);
						}

						if (current.getPrevious() != null) {
							if (current.getPrevious().getValue() < current.getValue()) {
								int tmp = current.getValue();
								current.setValue(current.getPrevious().getValue());
								current.getPrevious().setValue(tmp);

								orderTree(current.getPrevious(), true);
							}
						}

						orderTree(current.getLeft(), true);
					}
				} else {
					if (current.getRight() != null) {
						if (current.getRight().getValue() > current.getValue()) {
							int temp = current.getValue();
							current.setValue(current.getRight().getValue());
							current.getRight().setValue(temp);

							orderTree(current.getRight(), false);
						}

						if (current.getPrevious() != null) {
							if (current.getPrevious().getValue() < current.getValue()) {
								int tmp = current.getValue();
								current.setValue(current.getPrevious().getValue());
								current.getPrevious().setValue(tmp);

								orderTree(current.getPrevious(), true);
							}
						}

						orderTree(current.getRight(), false);
					}
				}
			}
		} else {
			orderTree(current.getLeft(), true);
			orderTree(current.getRight(), false);
		}
	}

	private static String printLeft() {
		return printLeft(root);
	}

	public static String printLeft(Tree currentNode) {
		String tmp = "";

		if (currentNode != null) {
			if (currentNode.getValue() != root.getValue()) {
				String left = printLeft(currentNode.getLeft());
				tmp = left + " " + currentNode.getValue();
			} else {
				tmp += currentNode.getValue() + " ";
			}
		}
		return tmp;
	}
	
	private static String printRight() {
		return printLeft(root);
	}

	public static String printRight(Tree currentNode) {
		String tmp = "";

		if (currentNode != null) {
			if (currentNode.getValue() != root.getValue()) {
				String right = printLeft(currentNode.getRight());
				tmp = currentNode.getValue() + " " + right ;
			}
			return printRight(currentNode.getRight());
		}
		return tmp;
	}
}

class Tree {
	private int value;
	private Tree left;
	private Tree right;
	private Tree previous;

	public Tree(int value, Tree left, Tree right, Tree previous) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.previous = previous;
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

	public Tree getPrevious() {
		return previous;
	}

	public void setPrevious(Tree previous) {
		this.previous = previous;
	}
}