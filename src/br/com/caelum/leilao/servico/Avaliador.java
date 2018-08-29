package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDeTodos;

	public void avaliar(Leilao leilao) {
		double total = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > this.maiorDeTodos) {
				this.maiorDeTodos = lance.getValor();
			}
			if (lance.getValor() < this.menorDeTodos) {
				this.menorDeTodos = lance.getValor();
			}
			total += lance.getValor();
		}
		if (total == 0) {
			this.mediaDeTodos = 0;
			return;
		}
		this.mediaDeTodos = total / leilao.getLances().size();
	}

	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}

	public double getMenorDeTodos() {
		return menorDeTodos;
	}

	public double getMediaDeTodos() {
		return mediaDeTodos;
	}
}
