package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.leilao.Avaliador;

public class TesteDoAvaliador {

	public static void main(String[] args) {
		Usuario joao = new Usuario("Jo�o");
		Usuario jose = new Usuario("Jos�");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		System.out.println("Menor: " + leiloeiro.getMenorDeTodos());
		System.out.println("Maior: " + leiloeiro.getMaiorDeTodos());
	}
}
