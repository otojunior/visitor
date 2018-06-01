/**
 * 
 */
package org.otojunior.visitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.otojunior.visitor.item.ItemLinha;
import org.otojunior.visitor.visitor.LinhaVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author otojunior
 *
 */
public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		/*
		 * O layout depende do tipo de organismo baseado no cabecalho do arquivo
		 * de entrada/exemplo. Por enquanto só tem do S. mansoni.
		 */
		File layout = new File("layout_sch.xml");
		File exemplo = new File("exemplo_sch.txt");
		
		Element root = new SAXBuilder().
			build(layout).
			getRootElement();
		
		/*
		 * A instanciacao depende do tipo de organismo baseado no cabecalho do arquivo.
		 * por enquanto só tem do S. mansoni.
		 */
		LinhaVisitor visitor = new LinhaVisitor(root);

		// iteracao
		
		BufferedReader in = new BufferedReader(new FileReader(exemplo));
		int numeroLinha = 0;
	
		
		String conteudoLinha = in.readLine();
		numeroLinha++;
		
		while (conteudoLinha != null) {
			ItemLinha itemLinha = new ItemLinha(numeroLinha, conteudoLinha);
			itemLinha.accept(visitor);
			
			conteudoLinha = in.readLine();
			numeroLinha++;
		}
		in.close();
	}
}
