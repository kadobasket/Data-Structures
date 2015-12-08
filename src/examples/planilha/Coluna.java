package examples.planilha;

import java.util.Iterator;

import datastructures.SortedLinkedList;

public class Coluna implements Comparable<Coluna>, Iterable<Celula> {
	private SortedLinkedList<Celula> celulas;
	private String indice;
	
	public Coluna(String indice) {
		this.indice = indice;
		this.celulas = new SortedLinkedList<>();
	}

	public void addCelula(Celula cell) {
		if (cell != null) {
			celulas.insert(cell);
		}
	}
	
	@Override
	public String toString() {
		String res = indice + ":\n";
		for (Celula c : celulas)
			res += c + " | ";
		return res;
	}

	@Override
	public int compareTo(Coluna cmp) {
		return this.indice.compareTo(cmp.indice);
	}

	public Object getIndex() {
		return indice;
	}

	@Override
	public Iterator<Celula> iterator() {
		return celulas.iterator();
	}
}






