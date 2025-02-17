package services;

import log.DBLog;
import log.ILog;
import log.JSONLog;
import log.XMLLog;
import model.Log;
import model.Pedido;
import static services.UsuarioLogadoService.getNomeUsuario;

/**
 *
 * @author lara_
 */

public class LogService {
    
    private final ILog logDB;  // Para o log no banco de dados
    private final ILog logJSON;  // Para o log em JSON
    private final ILog logXML;  // Para o log em XML
    
    //Construtor
    public LogService() {
        // Inicializando os diferentes tipos de log
        this.logDB = new DBLog();  // Aqui você pode instanciar o log que deseja usar
        this.logJSON = new JSONLog();
        this.logXML = new XMLLog();
    }

    // Método para registrar o log automaticamente após o cálculo do pedido (é chamado na CalculadoraValorTotal)
    public void registrarLog(Pedido pedido) {
        // Criando o objeto Log com os dados do pedido e do usuário
        String usuario = getNomeUsuario();
        Log log = new Log(usuario, pedido);
        
        // Aqui é possível retirar caso deseje registrar em apenas um dos tipos, não são dependentes
        // Registrando no banco de dados
        logDB.escrever(log);

        // Registrando no arquivo JSON
        logJSON.escrever(log);

        // Registrando no arquivo XML
        logXML.escrever(log);
        
        System.out.println("Log registrado com sucesso para o pedido de código: " + pedido.getCodigoPedido());
    }
}