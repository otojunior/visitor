/**
 * 
 */
package org.otojunior.visitor.item;

import org.otojunior.visitor.visitor.CampoVisitor;

/**
 * @author otojunior
 *
 */
public class ItemCampo {
	private int numeroLinha;
	private String idRegistro;
	private int ordemCampo;
	private String valorCampo;
	
	public ItemCampo(int numeroLinha, String idRegistro, int ordemCampo, String valorCampo) {
		this.numeroLinha = numeroLinha;
		this.idRegistro = idRegistro;
		this.ordemCampo = ordemCampo;
		this.valorCampo = valorCampo;
	}

	/* (non-Javadoc)
	 * @see org.otojunior.visitor.Item#accept(org.otojunior.visitor.Visitor)
	 */
	public void accept(CampoVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return the numeroLinha
	 */
	public int getNumeroLinha() {
		return numeroLinha;
	}

	/**
	 * @return the idRegistro
	 */
	public String getIdRegistro() {
		return idRegistro;
	}

	/**
	 * @return the ordemCampo
	 */
	public int getOrdemCampo() {
		return ordemCampo;
	}

	/**
	 * @return the valorCampo
	 */
	public String getValorCampo() {
		return valorCampo;
	}
}
