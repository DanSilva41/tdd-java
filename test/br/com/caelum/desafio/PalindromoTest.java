package br.com.caelum.desafio;

import org.junit.Assert;
import org.junit.Test;

public class PalindromoTest {

	@Test
	public void deveValidarPalindromos() {
		// parte 1:cenario
		Palindromo marrocos = new Palindromo();

		// parte 2: acao
		boolean resultado = marrocos.ehPalindromo("A base do teto desaba");

		// parte 3: validacao
		Assert.assertEquals(true, resultado);
	}

}
