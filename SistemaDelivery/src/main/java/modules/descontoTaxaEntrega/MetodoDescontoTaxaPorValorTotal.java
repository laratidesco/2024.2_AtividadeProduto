package modules.descontoTaxaEntrega;

import model.CupomDescontoEntrega;
import model.Item;
import model.Pedido;

/**
 *
 * @author lara_
 */

public class MetodoDescontoTaxaPorValorTotal implements IMetodoDescontoTaxaEntrega{
    private final double limiteValorPedido;
    private static final double VALOR_DESCONTO = 0.15;
    
    //Construtor
    public MetodoDescontoTaxaPorValorTotal(double limiteValorPedido){
        if(limiteValorPedido < 0){
            throw new IllegalArgumentException("Limite do valor do pedido inválido!");
        }
        this.limiteValorPedido = limiteValorPedido;
    }
    
    @Override
    public void calcularDesconto(Pedido pedido) { 
        if(seAplica(pedido)){         
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoEntrega("Desconto por valor do pedido", VALOR_DESCONTO));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        // Se o valor do pedido for maior que o valor definido de limite (que chega pelo construtor) retorna true
        return (pedido.getItens().stream().mapToDouble(Item::getValorTotal).sum() > limiteValorPedido);
    }
}
