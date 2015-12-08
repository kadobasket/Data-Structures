package examples.planilha;

import datastructures.SortedLinkedList;

public class Planilha {

	private static SortedLinkedList<Coluna> planilha = new SortedLinkedList<>();

	public static void main(String[] args) {
		Coluna coluna = new Coluna("A");
		
		Celula cell = new Celula("A0");
		cell.setExpressao("3*4");
		coluna.addCelula(cell);
		
		cell = new Celula("A1");
		cell.setExpressao("3+4");
		coluna.addCelula(cell);

		planilha.insert(coluna);

		// segunda coluna
		coluna = new Coluna("B");
		
		cell = new Celula("B3");
		cell.setExpressao("3-4");
		coluna.addCelula(cell);
		
		cell = new Celula("B1");
		cell.setExpressao("3/4");
		coluna.addCelula(cell);

		planilha.insert(coluna);
		
		imprimePlanilha();
		
		cell = procuraCelula("B3");
		System.out.println("Celula encontrada: " + cell);
	}

	private static void imprimePlanilha() {
		for (Coluna coluna : planilha) {
			System.out.println(coluna);
		}
	}

	private static Celula procuraCelula(String indice) {
		for (Coluna coluna : planilha) {
			String indiceColuna = quebraIndice(indice);
			if (coluna.getIndex().equals(indiceColuna)) {
				for (Celula cell : coluna) {
					if (cell.getIndex().equals(indice)) {
						return cell;
					}
				}
			}
		}
		return null;
	}

	private static String quebraIndice(String indice) {
		int index = 0;
		while (!Character.isDigit(indice.charAt(index)))
			index++;
		return indice.substring(0,index);
	}

}







