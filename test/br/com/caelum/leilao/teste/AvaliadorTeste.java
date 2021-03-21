package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.teste.builder.CriarLeilao;

public class AvaliadorTeste {

	private Avaliador avaliador;
	private Usuario jose;
	private Usuario maria;
	private Usuario joao;

	@Before
	public void setUp() {
		this.avaliador = new Avaliador();
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
		this.joao = new Usuario("Joao");
	}

	@Test
	public void teste() {		
		Leilao leilao = new CriarLeilao().para("Iphone 7")
				.registrarLance(maria, 300.0)
				.registrarLance(joao, 400.0)
				.registrarLance(jose,500.0)
				.constroi();

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

		avaliador.avalia(leilao);

		// validacao
		assertEquals(0, avaliador.getMediaLance(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriarLeilao().para("Playstation 3 Novo")
				.registrarLance(maria, 300.0)
				.registrarLance(joao, 400.0)
				.registrarLance(jose, 500.0)
				.registrarLance(jose, 600.0).constroi();

		avaliador.avalia(leilao);

		List<Lance> lances = avaliador.getTresMaioresLances();
		assertEquals(3, lances.size());
		assertEquals(500.0, lances.get(0).getValor(), 0.00001);
		assertEquals(400.0, lances.get(1).getValor(), 0.00001);
		assertEquals(300.0, lances.get(2).getValor(), 0.000001);
	}
	
	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("Finalizado!");
	}
}