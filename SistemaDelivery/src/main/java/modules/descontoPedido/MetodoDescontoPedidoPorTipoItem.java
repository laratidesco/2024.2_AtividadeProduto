package modules.descontoPedido;

import java.util.HashMap;
import java.util.Map;
import model.CupomDescontoPedido;
import model.Item;
import model.Pedido;

/**
 *
 * @author lara_
 */
public class MetodoDescontoPedidoPorTipoItem implements IMetodoDescontoPedido {
    private final Map<String, Double> descontosPorTipoItem;
    
    //Construtor
    public MetodoDescontoPedidoPorTipoItem(){
        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("Alimentação", 0.05);
        descontosPorTipoItem.put("Educação", 0.20);
        descontosPorTipoItem.put("Lazer", 0.15);
    }
            
    @Override
    public void calcularDesconto(Pedido pedido) {
         double valorDesconto = 0.0;
        
        if(seAplica(pedido)){
            for(Item item : pedido.getItens()){
                // O if verifica se o tipo do item está no hash, e soma apenas se estiver para não dar erro com null
               if (descontosPorTipoItem.containsKey(item.getTipo())) {
                    valorDesconto += descontosPorTipoItem.get(item.getTipo());
                }
            }
               pedido.aplicarDescontoPedido(new CupomDescontoPedido("Desconto no pedido pelo tipo de item", valorDesconto));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        boolean podeAplicar = false;
        
        // Se já houver algum cupom de desconto pelo código ou pelo tipo já não se aplica e retorna!
        for(CupomDescontoPedido cupomDescontoPedido : pedido.getCuponsDescontoPedido()){
            if(cupomDescontoPedido.getNomeMetodo().equals("Desconto no pedido pelo codigo de cupom") || cupomDescontoPedido.getNomeMetodo().equals("Desconto no pedido pelo tipo de cliente")){
                return podeAplicar;
            }
        }
        
        // Verifica se algum tipo de item possui desconto, se sim muda para true antes do retorno
        for(Item item: pedido.getItens()){
            if(descontosPorTipoItem.containsKey(item.getTipo())){
                podeAplicar = true;
            }
        }
        
        return podeAplicar;
    }
}
