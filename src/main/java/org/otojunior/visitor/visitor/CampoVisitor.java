package org.otojunior.visitor.visitor;

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

	/**
	 * 
	 * @param elementoCampo
	 * @param itemCampo
	 */
	private void validarDominio(Element elementoCampo, ItemCampo itemCampo) {
		String tipoEsperado = elementoCampo.getAttributeValue("tipo");
		String dominioEsperado = elementoCampo.getAttributeValue("dominio");
		String valorAtual = itemCampo.getValorCampo();
		
		if (dominioEsperado != null) {
			if ("string".equals(tipoEsperado)) {
				if (!valorAtual.matches(dominioEsperado)) {
					throw new RuntimeException("Erro de dominio. Linha: " +
						itemCampo.getNumeroLinha() + " Ordem: " +
						itemCampo.getOrdemCampo() + " Campo: " +
						itemCampo.getValorCampo());
				}
			} else if ("numerico".equals(tipoEsperado)) {
				StringTokenizer tk = new StringTokenizer(dominioEsperado, "..");
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
	}

	/**
	 * 
	 * @param elementoCampo
	 * @param itemCampo
	 */
	private void validarTamanho(Element elementoCampo, ItemCampo itemCampo) {
		String tamanoEsperadoStr = elementoCampo.getAttributeValue("tamanho");
		if (tamanoEsperadoStr != null) {
			Integer tamanhoEsperado = Integer.valueOf(tamanoEsperadoStr);
			int tamanhoAtual = itemCampo.getValorCampo().length();
			if (tamanhoAtual > tamanhoEsperado) {
				throw new RuntimeException("Erro de tamanho. Linha: " +
						itemCampo.getNumeroLinha() + " Ordem: " +
						itemCampo.getOrdemCampo() + " Campo: " +
						itemCampo.getValorCampo());
			}
		}
	}

	/**
	 * 
	 * @param elementoCampo
	 * @param itemCampo
	 */
	private void validarTipo(Element elementoCampo, ItemCampo itemCampo) {
		String tipoEsperado = elementoCampo.getAttributeValue("tipo");
		
		if (tipoEsperado != null) {
			if ("string".equals(tipoEsperado)) {
				
			} else if ("numerico".equals(tipoEsperado)) {
				try {
					Double.parseDouble(itemCampo.getValorCampo());
				} catch (NumberFormatException e) {
					throw new RuntimeException("Erro de tipo. Linha: " +
						itemCampo.getNumeroLinha() + " Ordem: " +
						itemCampo.getOrdemCampo() + " Campo: " +
						itemCampo.getValorCampo());
				}
			} else if ("booleano".equals(tipoEsperado)) {
				String valor = itemCampo.getValorCampo();
				if (!"true".equals(valor) || !"false".equals(valor)) {
					throw new RuntimeException("Erro de tipo. Linha: " +
						itemCampo.getNumeroLinha() + " Ordem: " +
						itemCampo.getOrdemCampo() + " Campo: " +
						itemCampo.getValorCampo());
				}
			}
		}
	}
}
