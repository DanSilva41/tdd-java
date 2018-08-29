package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
	}

	@Test
	public void deveCalcularAMedia() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 500.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		assertEquals(400.0, leiloeiro.getMedia(), 0.00001);
	}

	@Test
	public void deveTestarMediaDeLanceZero() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		assertEquals(0, leiloeiro.getMedia(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 1000.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("PlayStation 3 novo");

		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avaliar(leilao);

		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		assertEquals(400.0, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, tresMaiores.get(2).getValor(), 0.00001);
	}
}
