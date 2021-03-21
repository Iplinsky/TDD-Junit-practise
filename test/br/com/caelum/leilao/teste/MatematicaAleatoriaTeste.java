package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.MatematicaAleatoria;

public class MatematicaAleatoriaTeste {

	@Test
	public void deveMultiplicarNumerosMaioresQue30() {
		MatematicaAleatoria matematica = new MatematicaAleatoria();
		assertEquals(50 * 4, matematica.contaMaluca(50));
	}

	@Test
	public void deveMultiplicarNumerosMaioresQue10EMenoresQue30() {
		MatematicaAleatoria matematica = new MatematicaAleatoria();
		assertEquals(30 * 3, matematica.contaMaluca(30));
	}

	@Test
	public void deveMultiplicarNumerosMenoresQue10() {
		MatematicaAleatoria matematica = new MatematicaAleatoria();
		assertEquals(5 * 2, matematica.contaMaluca(5));
	}
}