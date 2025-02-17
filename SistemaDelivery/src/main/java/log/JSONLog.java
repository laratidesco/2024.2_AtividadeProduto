package log;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Log;

/**
 *
 * @author lara_
 */


public class JSONLog implements ILog {
    private ObjectMapper objectMapper;

    //Construtor
    public JSONLog() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void escrever(Log log) {
        // Criando um mapa com os dados do log
        Map<String, String> logEntry = new HashMap<>();
        logEntry.put("usuario", log.getNomeUsuario());
        logEntry.put("data", log.getData());
        logEntry.put("hora", log.getHora());
        logEntry.put("codigo_pedido", String.valueOf(log.getCodigoPedido()));
        logEntry.put("nome_operacao", log.getNomeOperacao());
        logEntry.put("nome_cliente", log.getNomeCliente());

        // Caminho para a pasta "log"
        File logDirectory = new File("log");

        // Verificando se a pasta "log" existe
        if (!logDirectory.exists()) {
            logDirectory.mkdir(); // Se não existe, cria a pasta
        }

        // Caminho completo do arquivo JSON dentro da pasta "log"
        File logFile = new File(logDirectory, "log.json");

        // Lista que armazenará os logs
        List<Map<String, String>> logs = new ArrayList<>();

        try {
            if (logFile.exists()) {
                // Se o arquivo já existe, ler o conteúdo para a lista de logs
                logs = objectMapper.readValue(logFile, ArrayList.class);
            }
        } catch (IOException ex) {
            Logger.getLogger(JSONLog.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Adicionando o novo log à lista
        logs.add(logEntry);

        try {
            // Gravando a lista de logs no arquivo JSON
            objectMapper.writeValue(logFile, logs);
            System.out.println("Log registrado no arquivo JSON: " + logFile.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(JSONLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}