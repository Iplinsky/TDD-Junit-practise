package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Usuario thiago = new Usuario("Thiago");
		Leilao leilaoTV = new Leilao("Televisor");

		leilaoTV.propoe(new Lance(thiago, 2000.0));
		leilaoTV.propoe(new Lance(thiago, 2500.0));

		assertEquals(1, leilaoTV.getLances().size());
		assertEquals(2000.0, leilaoTV.getLances().get(0).getValor(), 0.000001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario thiago = new Usuario("Thiago");
		Usuario izabel = new Usuario("Izabel");

		Leilao leilaoTV = new Leilao("Televisor");

		leilaoTV.propoe(new Lance(thiago, 2000.0));
		leilaoTV.propoe(new Lance(izabel, 3000.0));

		leilaoTV.propoe(new Lance(thiago, 4000.0));
		leilaoTV.propoe(new Lance(izabel, 5000.0));

		leilaoTV.propoe(new Lance(thiago, 6000.0));
		leilaoTV.propoe(new Lance(izabel, 7000.0));

		leilaoTV.propoe(new Lance(thiago, 8000.0));
		leilaoTV.propoe(new Lance(izabel, 9000.0));

		leilaoTV.propoe(new Lance(thiago, 10000.0));
		leilaoTV.propoe(new Lance(izabel, 11000.0));

		leilaoTV.propoe(new Lance(thiago, 12000.0));

		int ultimoLance = leilaoTV.getLances().size() - 1;
		
		assertEquals(10, leilaoTV.getLances().size());
		assertEquals(11000.0, leilaoTV.getLances().get(ultimoLance).getValor(), 0.000001);
	}

}
