package modules.descontoTaxaEntrega;

import java.util.HashMap;
import java.util.Map;
import model.CupomDescontoEntrega;
import model.Pedido;

/**
 *
 * @author lara_
 */

public class MetodoDescontoTaxaPorTipoCliente implements  IMetodoDescontoTaxaEntrega {
    private final Map<String,Double> descontosPorTipoCliente;
    
    //Construtor
    public MetodoDescontoTaxaPorTipoCliente(){
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
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoEntrega("Desconto por tipo de cliente: ", valorDesconto));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        // Verifica se o cliente associado tem um tipo que existe no hash
        return descontosPorTipoCliente.containsKey(pedido.getCliente().getTipo());
    }
}
