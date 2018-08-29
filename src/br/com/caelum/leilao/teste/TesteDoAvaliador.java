package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.leilao.Avaliador;

public class TesteDoAvaliador {

	public static void main(String[] args) {

		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		// parte 3: validacao
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;

		System.out.println(menorEsperado == leiloeiro.getMenorDeTodos());
		System.out.println(maiorEsperado == leiloeiro.getMaiorDeTodos());
	}
}
