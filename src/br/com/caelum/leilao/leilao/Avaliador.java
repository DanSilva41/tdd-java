package br.com.caelum.leilao.leilao;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;

	public void avaliar(Leilao leilao) {
//		Optional<Lance> optionalMaximo = leilao.getLances().stream().max(Comparator.comparing(Lance::getValor));
//		Optional<Lance> optionalMinimo = leilao.getLances().stream().min(Comparator.comparing(Lance::getValor));
//		maiorDeTodos = optionalMaximo.orElseThrow(null).getValor();
//		menorDeTodos = optionalMinimo.orElseThrow(null).getValor();
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > this.maiorDeTodos)
				this.maiorDeTodos = lance.getValor();
			if (lance.getValor() < this.menorDeTodos) 
				this.menorDeTodos = lance.getValor();
		}
	}

	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}

	public double getMenorDeTodos() {
		return menorDeTodos;
	}
}
