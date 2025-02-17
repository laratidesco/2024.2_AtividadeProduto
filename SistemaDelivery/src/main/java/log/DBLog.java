
package log;

import model.Log;
import dao.DBAdapter;

/**
 *
 * @author lara_
 */
public class DBLog implements ILog{
    private final DBAdapter dbAdapter;

    // Construtor para inicializar o DBAdapter
    public DBLog() {
        dbAdapter = new DBAdapter(); // Instanciando o DBAdapter
    }

    @Override
    public void escrever(Log log) {
        // Usando o DBAdapter para inserir o log no banco de dados
        dbAdapter.inserir(log);
    }
}
