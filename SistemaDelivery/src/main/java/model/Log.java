
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lara_
 */

public class Log {

    private String nomeUsuario;
    private String data;
    private String hora;
    private int codigoPedido;
    private final String nomeOperacao = "Calculo do valor total do pedido";
    private String nomeCliente;

    
    public Log(String usuario, Pedido pedido) {
        // Formatar a data e a hora
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        this.nomeUsuario = usuario;
        this.data = LocalDate.now().format(dataFormatter);
        this.hora = LocalTime.now().format(horaFormatter);
        this.codigoPedido = pedido.getCodigoPedido();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    //Getters
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
    public String getData() {
        return data;
    }
    
    public String getHora() {
        return hora;
    }
        
    public int getCodigoPedido() {
        return codigoPedido;
    }

    public String getNomeOperacao() {
        return nomeOperacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

}
