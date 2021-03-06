package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {
		if (lances.isEmpty() || realizouNoMaximoCincoLances(lance.getUsuario()))
			lances.add(lance);
	}

	public void dobraLance(Usuario usuario) {
		if (realizouNoMaximoCincoLances(usuario)) {
			propoe(new Lance(usuario, recuperaUltimoLanceDado().getValor() * 2));
		}
	}

	public boolean realizouNoMaximoCincoLances(Usuario usuario) {
		return !recuperaUltimoLanceDado().getUsuario().equals(usuario) && QuantidadeDeLancesDadoPorUsuario(usuario) < 5;
	}

	public int QuantidadeDeLancesDadoPorUsuario(Usuario usuario) {
		int total = 0;
		for (Lance lance : lances) {
			if (lance.getUsuario().equals(usuario))
				total++;
		}
		return total;
	}

	private Lance recuperaUltimoLanceDado() {
		return (lances.get(lances.size() - 1));
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
}
