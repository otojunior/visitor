package org.otojunior.visitor.item;

import org.otojunior.visitor.visitor.LinhaVisitor;

public class ItemLinha {
	private int numeroLinha;
	private String conteudoLinha;
	
	public ItemLinha(int numeroLinha, String conteudoLinha) {
		this.numeroLinha = numeroLinha;
		this.conteudoLinha = conteudoLinha;
	}

	/**
	 * 
	 * @param visitor
	 */
	public void accept(LinhaVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the count
	 */
	public int getNumeroLinha() {
		return numeroLinha;
	}

	/**
	 * @return the linha
	 */
	public String getConteudoLinha() {
		return conteudoLinha;
	}
}
