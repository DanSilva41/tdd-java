package br.com.caelum.desafio;

public class AnoBissexto {

	boolean ehBissexto(int ano) {
		if (((ano % 4) == 0) && ((ano % 100) != 0))
			return true;
		else if ((ano % 400) == 0)
			return true;
		else
			return false;
	}

}
