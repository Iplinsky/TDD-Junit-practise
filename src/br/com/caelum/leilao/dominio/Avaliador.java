package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

	private double maiorValor = Double.NEGATIVE_INFINITY;
	private double menorValor = Double.POSITIVE_INFINITY;
	private double mediaValor = 0;
	private List<Lance> tresMaioresLances;

	public void avalia(Leilao leilao) {
		double valorTotal = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorValor)
				maiorValor = lance.getValor();
			if (lance.getValor() < menorValor)
				menorValor = lance.getValor();
			valorTotal += lance.getValor();
		}
		mediaValor = valorTotal == 0 ? 0 : valorTotal / leilao.getLances().size();
		tresMaioresLances = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(tresMaioresLances, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor())
					return 1;
				if (o1.getValor() > o2.getValor())
					return -1;
				return 0;
			}
		});
		tresMaioresLances = tresMaioresLances.subList(0, tresMaioresLances.size() > 3 ? 3 : tresMaioresLances.size());
	}

	public List<Lance> getTresMaioresLances() {
		return tresMaioresLances;
	}

	public double getMaiorLance() {
		return maiorValor;
	}

	public double getMenorLance() {
		return menorValor;
	}

	public double getMediaLance() {
		return mediaValor;
	}

}
