/**
 * 
 */
package org.otojunior.visitor.visitor;

import java.util.StringTokenizer;

import org.jdom.Element;
import org.otojunior.visitor.item.ItemCampo;
import org.otojunior.visitor.item.ItemLinha;

/**
 * @author otojunior
 *
 */
public class LinhaVisitor {
	private Element root;
	
	/**
	 * 
	 * @param root
	 */
	public LinhaVisitor(Element root) {
		this.root = root;
	}

	/**
	 * 
	 * @param item
	 */
	public void visit(ItemLinha item) {
		/*
		 * Vide comentario abaixo
		 */
		boolean primeiro = true;
		String idreg = null;
		
		int ordemCampo = 0;
		CampoVisitor visitor = new CampoVisitor(root);
		StringTokenizer tk = new StringTokenizer(item.getConteudoLinha(), "|");
		while (tk.hasMoreTokens()) {
			int numeroLinha = item.getNumeroLinha();
			++ordemCampo;
			String valorCampo = tk.nextToken();
			
			/*
			 * Primeiro campo da linha devera ser o id do registro.
			 * Codigo especifico para obtencao desse ID que será usado
			 * no CampoVisitor para todos os outros campos da linha.
			 */
			if (primeiro) {
				idreg = valorCampo;
				primeiro = false;
			}
			
			
			ItemCampo itemCampo = new ItemCampo(
				numeroLinha,
				idreg,
				ordemCampo, 
				valorCampo);
			itemCampo.accept(visitor);
		}
	}
}
