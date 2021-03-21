package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	private Leilao carroImportado;
	private Usuario thiago;
	private Usuario izabel;
	private Usuario fulano;

	@Before
	public void instanciaUsuariosLeilao() {
		this.carroImportado = new Leilao("Carro Importado");
		this.thiago = new Usuario("Thiago");
		this.izabel = new Usuario("Izabel");
		this.fulano = new Usuario("fulano");
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		carroImportado.propoe(new Lance(thiago, 12000.0));
		carroImportado.propoe(new Lance(thiago, 13000.0));

		assertEquals(1, carroImportado.getLances().size());
		assertEquals(12000.0, carroImportado.getLances().get(0).getValor(), 0.000001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		carroImportado.propoe(new Lance(thiago, 12000.0));
		carroImportado.propoe(new Lance(izabel, 13000.0));

		carroImportado.propoe(new Lance(thiago, 14000.0));
		carroImportado.propoe(new Lance(izabel, 15000.0));

		carroImportado.propoe(new Lance(thiago, 16000.0));
		carroImportado.propoe(new Lance(izabel, 17000.0));

		carroImportado.propoe(new Lance(thiago, 18000.0));
		carroImportado.propoe(new Lance(izabel, 19000.0));

		carroImportado.propoe(new Lance(thiago, 20000.0));
		carroImportado.propoe(new Lance(izabel, 25000.0));

		carroImportado.propoe(new Lance(thiago, 30000.0));

		carroImportado.dobraLance(fulano);

		int ultimoLance = carroImportado.getLances().size() - 1;

		assertEquals(11, carroImportado.getLances().size());
		assertEquals(50000.0, carroImportado.getLances().get(ultimoLance).getValor(), 0.000001);
	}

}
