package modules.descontoPedido;

import java.util.HashMap;
import java.util.Map;
import model.CupomDescontoPedido;
import model.Pedido;

/**
 *
 * @author lara_
 */
public class MetodoDescontoPedidoPorCodigo implements IMetodoDescontoPedido {
    private final Map<String, Double> codigosDeDesconto;
    
    //Construtor
    public MetodoDescontoPedidoPorCodigo(){
        codigosDeDesconto = new HashMap<>();
        codigosDeDesconto.put("DESC10", 0.10);
        codigosDeDesconto.put("DESC20", 0.20);
        codigosDeDesconto.put("DESC30", 0.30);
    }
    
    @Override
    public void calcularDesconto(Pedido pedido) {  
        double desconto;
        if(seAplica(pedido)){
            desconto = codigosDeDesconto.get(pedido.getCodigoDeCupom());
            pedido.aplicarDescontoPedido(new CupomDescontoPedido("Desconto no pedido pelo codigo de cupom", desconto));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        // Verifica se o código de cupom associado ao pedido existe no hash
        return codigosDeDesconto.containsKey(pedido.getCodigoDeCupom());
    }
}
