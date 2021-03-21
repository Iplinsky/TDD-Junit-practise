package br.com.caelum.leilao.teste.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriarLeilao {

	private Leilao leilao;

	public CriarLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}

	public CriarLeilao registrarLance(Usuario usuario, double valorLance) {
		this.leilao.propoe(new Lance(usuario, valorLance));
		return this;
	}

	public Leilao constroi() {
		return leilao;
	}

}
