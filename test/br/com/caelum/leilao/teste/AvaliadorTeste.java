package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTeste {

	@Test
	public void teste() {
		// cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 500.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorEsperado = 500;
		double menorEsperado = 300;

		assertEquals(maiorEsperado, avaliador.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, avaliador.getMenorLance(), 0.00001);
		assertEquals(400, avaliador.getMediaLance(), 0.0001);
	}

	@Test
	public void testaMediaDeZeroLance() {

		// cenario
		Usuario ewertom = new Usuario("Ewertom");

		// acao
		Leilao leilao = new Leilao("Iphone 7");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// validacao
		assertEquals(0, avaliador.getMediaLance(), 0.0001);

	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 500.0));
		leilao.propoe(new Lance(jose, 600.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		List<Lance> lances = avaliador.getTresMaioresLances();

		assertEquals(3, lances.size());
		assertEquals(600.0, lances.get(0).getValor(), 0.00001);
		assertEquals(500.0, lances.get(1).getValor(), 0.00001);
		assertEquals(400.0, lances.get(2).getValor(), 0.000001);
	}
}