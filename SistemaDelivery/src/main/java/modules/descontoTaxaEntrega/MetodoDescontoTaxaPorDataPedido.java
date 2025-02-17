package modules.descontoTaxaEntrega;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import model.CupomDescontoEntrega;
import model.Pedido;

/**
 *
 * @author lara_
 */

public class MetodoDescontoTaxaPorDataPedido implements IMetodoDescontoTaxaEntrega {
     private final Map<LocalDate,Double> descontosPorData;
    
     //Construtor
    public MetodoDescontoTaxaPorDataPedido(){   
        this.descontosPorData = new HashMap<>();
        descontosPorData.put(LocalDate.of(2025, Month.JANUARY, 1), 0.10);
        descontosPorData.put(LocalDate.of(2024, Month.OCTOBER, 12), 0.15);
        descontosPorData.put(LocalDate.of(2024, Month.DECEMBER, 25), 0.20);
    }
    
    @Override
    public void calcularDesconto(Pedido pedido) {
        if(seAplica(pedido)){
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoEntrega("Desconto por data", descontosPorData.get(pedido.getDataPedido())));
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
      return descontosPorData.containsKey(pedido.getDataPedido());
    }
    
}
