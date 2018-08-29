package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

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
		double menorEsperado = 250.0;
		double maiorEsperado = 400.0;

		Assert.assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
	}

	@Test
	public void deveCalcularAMedia() {

		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 500.0));

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		// parte 3: validacao
		double media = 400.0;

		Assert.assertEquals(media, leiloeiro.getMediaDeTodos(), 0.00001);
	}

	@Test
	public void deveTestarMediaDeLanceZero() {

		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		// parte 3: validacao
		Assert.assertEquals(0, leiloeiro.getMediaDeTodos(), 0.00001);
	}
}
