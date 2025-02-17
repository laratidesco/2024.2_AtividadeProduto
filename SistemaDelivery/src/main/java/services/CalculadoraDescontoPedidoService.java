
package services;

import modules.descontoPedido.IMetodoDescontoPedido;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import modules.descontoPedido.MetodoDescontoPedidoPorCodigo;
import modules.descontoPedido.MetodoDescontoPedidoPorTipoCliente;
import modules.descontoPedido.MetodoDescontoPedidoPorTipoItem;

/**
 *
 * @author lara_
 */
public class CalculadoraDescontoPedidoService {
     private final List<IMetodoDescontoPedido> metodosDeDescontoPedido;
     
    //Construtor
    public CalculadoraDescontoPedidoService() {
        metodosDeDescontoPedido = new ArrayList<>();
        metodosDeDescontoPedido.add(new MetodoDescontoPedidoPorTipoCliente());
        metodosDeDescontoPedido.add(new MetodoDescontoPedidoPorCodigo());
        metodosDeDescontoPedido.add(new MetodoDescontoPedidoPorTipoItem());
    }
    
    public void calcularDescontoPedido(Pedido pedido){
        if(pedido == null){
            throw new IllegalArgumentException("Pedido inválido!");
        }
        // Aplica o desconto no pedido para cada método
        for(IMetodoDescontoPedido metodoDescontoPedido : metodosDeDescontoPedido){   
            metodoDescontoPedido.calcularDesconto(pedido);      
        }  
    }
    
    public void adicionarMetodoDescontoPedido(IMetodoDescontoPedido metodoDescontoPedido){
        if(metodoDescontoPedido == null) throw new IllegalArgumentException("Método de desconto de pedido inválido!");
        metodosDeDescontoPedido.add(metodoDescontoPedido);
    }
   
    
}
