package br.com.caelum.leilao.servico;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("this.maria");
		System.out.println("inicio");
	}

	@After
	public void finaliza() {
		System.out.println("fim");
	}

	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("after class");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Leilao leilao = new CriadorDeLeilao().para("Playstion 3 novo").lance(joao, 250.0).lance(jose, 300.0)
				.lance(maria, 400.0).constroi();

		// parte 2: acao
		leiloeiro.avaliar(leilao);

		// parte 3: validacao
//		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
//		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
	}

	@Test
	public void deveCalcularAMedia() {
		Leilao leilao = new CriadorDeLeilao().para("Playstion 3 novo").lance(joao, 300.0).lance(jose, 400.0)
				.lance(maria, 500.0).constroi();

		leiloeiro.avaliar(leilao);

		assertEquals(400.0, leiloeiro.getMedia(), 0.00001);
	}

	@Test(expected = RuntimeException.class)
	public void deveTestarMediaDeLanceZero() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leiloeiro.avaliar(leilao);

		assertEquals(0, leiloeiro.getMedia(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Playstion 3 novo").lance(joao, 1000.0).constroi();

		leiloeiro.avaliar(leilao);

		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstion 3 novo").lance(joao, 100.0).lance(maria, 200.0)
				.lance(joao, 300.0).lance(maria, 400.0).constroi();

		leiloeiro.avaliar(leilao);

		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		assertEquals(400.0, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, tresMaiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Leilao leilao = new CriadorDeLeilao().para("Playstion 3 novo").lance(joao, 100.0).lance(maria, 500.0)
				.constroi();

		leiloeiro.avaliar(leilao);

		List<Lance> doisEncontrados = leiloeiro.getTresMaiores();
		assertEquals(2, doisEncontrados.size());
		assertEquals(500.0, doisEncontrados.get(0).getValor(), 0.00001);
		assertEquals(100.0, doisEncontrados.get(1).getValor(), 0.00001);
	}

	@Test(expected = RuntimeException.class)
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstion 3 novo").constroi();
		leiloeiro.avaliar(leilao);

		List<Lance> listaVazia = leiloeiro.getTresMaiores();
		assertEquals(0, listaVazia.size());
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliaLeiloesSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation3 novo").constroi();
		leiloeiro.avaliar(leilao);
	}

}
