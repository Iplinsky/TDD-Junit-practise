package br.com.caelum.leilao.teste;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
				.registrarLance(jose, 500.0)
				.constroi();

		avaliador.avalia(leilao);

		double maiorEsperado = 500;
		double menorEsperado = 300;

		assertEquals(maiorEsperado, avaliador.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, avaliador.getMenorLance(), 0.00001);
		assertEquals(400, avaliador.getMediaLance(), 0.0001);		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// 1ª Etapa - Construindo o cenário
		Leilao leilao = new CriarLeilao().para("Notebook")
				.registrarLance(joao, 250.0)
				.registrarLance(maria, 300.0)
				.registrarLance(jose, 400.00)
				.constroi();
		
		// 2ª Etapa - Execução
		avaliador.avalia(leilao);
		
		// 3ª Etapa - Validação
		assertThat(avaliador.getMaiorLance(), equalTo(400.0));
		assertThat(avaliador.getMenorLance(), equalTo(250.0));
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriarLeilao().para("Playstation 3 Novo")
				.registrarLance(maria, 300.0)
				.registrarLance(joao, 400.0)
				.registrarLance(jose, 500.0)
				.registrarLance(jose, 600.0)
				.constroi();

		avaliador.avalia(leilao);

		List<Lance> lances = avaliador.getTresMaioresLances();
		assertEquals(3, lances.size());		
		
		assertEquals(500.0, lances.get(0).getValor(), 0.00001);
		assertEquals(400.0, lances.get(1).getValor(), 0.00001);
		assertEquals(300.0, lances.get(2).getValor(), 0.000001);
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemLanceInformado() {
		Leilao leilao = new CriarLeilao().para("Moto").constroi();
		avaliador.avalia(leilao);
	}

	@AfterClass
	public static void testandoAfterClass() {
		// System.out.println("Finalizado!");
	}
}