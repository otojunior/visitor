package org.otojunior.visitor.visitor;

import java.util.List;
import java.util.StringTokenizer;

import org.jdom.Element;
import org.otojunior.visitor.item.ItemCampo;
import org.otojunior.visitor.util.JDOMUtil;

/**
 * 
 * @author otojunior
 *
 */
public class CampoVisitor {
	private Element root;
	
	/**
	 * 
	 * @param root
	 */
	public CampoVisitor(Element root) {
		this.root = root;
	}

	/**
	 * 
	 * @param itemCampo
	 */
	public void visit(ItemCampo itemCampo) {
		Element elementoCampo = JDOMUtil.getElementCampo(root, itemCampo);
		validarTipo(elementoCampo, itemCampo);
		validarTamanho(elementoCampo, itemCampo);
		validarDominio(elementoCampo, itemCampo);
	}

	private void validarDominio(Element elementoCampo, ItemCampo itemCampo) {
		String tipoEsperado = elementoCampo.getAttributeValue("tipo");
		String dominioEsperado = elementoCampo.getAttributeValue("dominio");
		String valorAtual = itemCampo.getValorCampo();
		
		if ("string".equals(tipoEsperado)) {
			if (!valorAtual.matches(dominioEsperado)) {
				throw new RuntimeException("Erro de dominio. Linha: " +
					itemCampo.getNumeroLinha() + " Ordem: " +
					itemCampo.getOrdemCampo() + " Campo: " +
					itemCampo.getValorCampo());
			}
		} else if ("numerico".equals(tipoEsperado)) {
			StringTokenizer tk = new StringTokenizer(tipoEsperado, "..");
			double rangeInf = Double.valueOf(tk.nextToken());
			double rangeSup = Double.valueOf(tk.nextToken());
			double doubleAtual = Double.valueOf(valorAtual);
			if (rangeInf > doubleAtual || rangeSup < doubleAtual) {
				throw new RuntimeException("Erro de dominio. Linha: " +
					itemCampo.getNumeroLinha() + " Ordem: " +
					itemCampo.getOrdemCampo() + " Campo: " +
					itemCampo.getValorCampo());
			}
		} else if ("boolean".equals(tipoEsperado)) {
			if (!"true".equals(valorAtual) || !"false".equals(valorAtual)) {
				throw new RuntimeException("Erro de dominio. Linha: " +
					itemCampo.getNumeroLinha() + " Ordem: " +
					itemCampo.getOrdemCampo() + " Campo: " +
					itemCampo.getValorCampo());
			}
		}
	}

	private void validarTamanho(Element elementoCampo, ItemCampo itemCampo) {
		int tamanhoEsperado = Integer.valueOf(elementoCampo.getAttributeValue("tamanho"));
		int tamanhoAtual = itemCampo.getValorCampo().length();
		if (tamanhoEsperado != tamanhoAtual) {
			throw new RuntimeException("Erro de tamanho. Linha: " +
				itemCampo.getNumeroLinha() + " Ordem: " +
				itemCampo.getOrdemCampo() + " Campo: " +
				itemCampo.getValorCampo());
		}
		
	}

	private void validarTipo(Element elementoCampo, ItemCampo itemCampo) {
		String tipoEsperado = elementoCampo.getAttributeValue("tipo");
		Class tipoEsperadoClass = null;
		Class tipoAtual = itemCampo.getValorCampo().getClass();
		
		if ("string".equals(tipoEsperado)) {
			tipoEsperadoClass = String.class;
		} else if ("numerico".equals(tipoEsperado)) {
			tipoEsperadoClass = Number.class;
		} else if ("booleano".equals(tipoEsperado)) {
			tipoEsperadoClass = Boolean.class;
		}
		
		if (!tipoEsperadoClass.isAssignableFrom(tipoAtual)) {
			throw new RuntimeException("Erro de tipo. Linha: " +
				itemCampo.getNumeroLinha() + " Ordem: " +
				itemCampo.getOrdemCampo() + " Campo: " +
				itemCampo.getValorCampo());
		}
	}
}
