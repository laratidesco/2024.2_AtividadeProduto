package log;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Log;

/**
 *
 * @author lara_
 */

public class XMLLog implements ILog {

    @Override
    public void escrever(Log log) {
        // Criando caminho para a pasta "log"
        File logDirectory = new File("log");
        // Verificando se a pasta "log" existe
        if (!logDirectory.exists()) {
            logDirectory.mkdir(); // Se não existe, cria a pasta
        }

        // Caminho completo do arquivo XML dentro da pasta "log"
        File logFile = new File(logDirectory, "log.xml");

        // Criando um documento XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;

        try {
            builder = factory.newDocumentBuilder();
            if (logFile.exists()) {
                // Se o arquivo XML já existe, faz o parse do arquivo existente
                doc = builder.parse(logFile);
            } else {
                // Se o arquivo XML não existe, cria um novo documento
                doc = builder.newDocument();
                Element root = doc.createElement("log");
                doc.appendChild(root);
            }
        } catch (Exception e) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, e);
        }

        // Obtendo o nó raiz
        Element root = doc.getDocumentElement();

        // Criando um novo elemento <registro> para o log
        Element registro = doc.createElement("registro");

        // Criando os elementos de dados do log
        Element usuario = doc.createElement("usuario");
        usuario.appendChild(doc.createTextNode(log.getNomeUsuario()));
        registro.appendChild(usuario);

        Element data = doc.createElement("data");
        data.appendChild(doc.createTextNode(log.getData().toString()));
        registro.appendChild(data);

        Element hora = doc.createElement("hora");
        hora.appendChild(doc.createTextNode(log.getHora().toString()));
        registro.appendChild(hora);

        Element codigoPedido = doc.createElement("codigo_pedido");
        codigoPedido.appendChild(doc.createTextNode(String.valueOf(log.getCodigoPedido())));
        registro.appendChild(codigoPedido);

        Element nomeOperacao = doc.createElement("nome_operacao");
        nomeOperacao.appendChild(doc.createTextNode(log.getNomeOperacao()));
        registro.appendChild(nomeOperacao);

        Element nomeCliente = doc.createElement("nome_cliente");
        nomeCliente.appendChild(doc.createTextNode(log.getNomeCliente()));
        registro.appendChild(nomeCliente);

        // Adicionando o <registro> à raiz <log>
        root.appendChild(registro);

        // Salvando no arquivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, ex);
        }

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(logFile); // Usando o caminho para o arquivo XML dentro de "log"
        try {
            transformer.transform(source, result);
            System.out.println("Log registrado no arquivo XML: " + logFile.getAbsolutePath());
        } catch (TransformerException ex) {
            Logger.getLogger(XMLLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}