package modules.descontoPedido;

import java.util.HashMap;
import java.util.Map;
import model.CupomDescontoPedido;
import model.Pedido;

/**
 *
 * @author lara_
 */
public class MetodoDescontoPedidoPorTipoCliente implements IMetodoDescontoPedido {
    private final Map<String,Double> descontosPorTipoCliente;
    
    //Construtor
    public MetodoDescontoPedidoPorTipoCliente(){
        descontosPorTipoCliente = new HashMap<>();
        descontosPorTipoCliente.put("Ouro", 0.30);
        descontosPorTipoCliente.put("Prata", 0.20);
        descontosPorTipoCliente.put("Bronze", 0.10);
    }
    
   @Override
    public void calcularDesconto(Pedido pedido) {
        double valorDesconto;
        
        if(seAplica(pedido)){
            valorDesconto = descontosPorTipoCliente.get(pedido.getCliente().getTipo());
            pedido.aplicarDescontoPedido(new CupomDescontoPedido("Desconto no pedido pelo tipo de cliente", valorDesconto));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        // Verifica se o cliente associado ao pedido tem um tipo que existe no hash
        return descontosPorTipoCliente.containsKey(pedido.getCliente().getTipo());
    }
}
