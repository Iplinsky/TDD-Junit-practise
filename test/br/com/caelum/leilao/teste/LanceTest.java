package br.com.caelum.leilao.teste;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class LanceTest {

	private Usuario usuario;

	@Before
	public void setUp() {
		this.usuario = new Usuario("James Lillard");
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveReceberLanceIgualAZero() {
		Lance lance = new Lance(usuario, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveReceberLanceNegativo() {
		Lance lance = new Lance(usuario, -1000.0);
	}
}
