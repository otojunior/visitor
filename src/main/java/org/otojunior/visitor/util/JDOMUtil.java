/**
 * 
 */
package org.otojunior.visitor.util;

import java.util.List;

import org.jdom.Element;
import org.otojunior.visitor.item.ItemCampo;

/**
 * @author otojunior
 *
 */
public class JDOMUtil {
	public static Element getElementCampo(Element root, ItemCampo itemCampo) {
		String idRegistroEsperado = itemCampo.getIdRegistro();
		String ordemCampoEsperado = String.valueOf(itemCampo.getOrdemCampo());
		
		List<Element> registros = root.getChildren("registro");
		for (Element registro : registros) {
			String idRegistroAtual = registro.getAttributeValue("id");
			if (idRegistroEsperado.equals(idRegistroAtual)) {
				List<Element> campos = registro.getChildren("campo");
				for (Element campo : campos) {
					String ordemCampoAtual = campo.getAttributeValue("ordem");
					if (ordemCampoEsperado.equals(ordemCampoAtual)) {
						return campo;
					}
				}
			}
		}
		throw new RuntimeException("Erro de layout");
	}
}
