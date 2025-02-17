package modules.descontoTaxaEntrega;

import java.util.HashMap;
import java.util.Map;
import model.CupomDescontoEntrega;
import model.Item;
import model.Pedido;

/**
 *
 * @author lara_
 */

public class MetodoDescontoTaxaPorTipoItem implements IMetodoDescontoTaxaEntrega {
    private final Map<String, Double> descontosPorTipoItem;
    
    //Construtor
    public MetodoDescontoTaxaPorTipoItem(){
        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("Alimentação", 0.05);
        descontosPorTipoItem.put("Educação", 0.20);
        descontosPorTipoItem.put("Lazer", 0.15);
    }
    
    @Override
    public void calcularDesconto(Pedido pedido) {
        double valorDesconto = 0.0;
        
       // Soma, de forma cumulativa, a porcentagem correspondente à chave (caso ela esteja no map) na variável 'valorDesconto'.
        if(seAplica(pedido)){
            for(Item item : pedido.getItens()){
               // O if verifica se o tipo do item está no hash, e soma apenas se estiver para não dar erro com null
               if (descontosPorTipoItem.containsKey(item.getTipo())) {
                    valorDesconto += descontosPorTipoItem.get(item.getTipo());
               }
            }
            
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoEntrega("Desconto por tipo de item", valorDesconto)); 
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {    
        boolean podeAplicar = false;
        
        // Verifica se pode aplicar alterando para true caso algum item tenha um tipo correspondente a alguma String do map.
        for(Item item: pedido.getItens()){
            podeAplicar = descontosPorTipoItem.containsKey(item.getTipo());   
        }
        return podeAplicar;
    }
}
