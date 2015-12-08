package examples.planilha;

public class Celula implements Comparable<Celula> {
	private String indice;
	private String expressao;
	private double valor;
	
	public Celula(String indice) {
		this.indice = indice;
		this.expressao = null;
		this.valor = 0;
	}
	
	public void setExpressao(String expressao) {
		this.expressao = expressao;
		if (expressao == null || expressao.isEmpty())
			this.valor = 0;
		else
			this.valor=algorithms.ExpressionEvaluator.compute(expressao);
	}
	
	public String getExpressao() {
		return expressao;
	}
	
	public double getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return indice + ": " + valor + " (" + expressao + ")";
	}

	@Override
	public int compareTo(Celula cmp) {
		return this.indice.compareTo(cmp.indice);
	}

	public Object getIndex() {
		return indice;
	}
}









