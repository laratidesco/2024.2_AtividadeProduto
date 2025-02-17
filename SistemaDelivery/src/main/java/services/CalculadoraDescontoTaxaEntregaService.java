package services;

import modules.descontoTaxaEntrega.IMetodoDescontoTaxaEntrega;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import modules.descontoTaxaEntrega.MetodoDescontoTaxaPorBairro;
import modules.descontoTaxaEntrega.MetodoDescontoTaxaPorDataPedido;
import modules.descontoTaxaEntrega.MetodoDescontoTaxaPorTipoCliente;
import modules.descontoTaxaEntrega.MetodoDescontoTaxaPorTipoItem;
import modules.descontoTaxaEntrega.MetodoDescontoTaxaPorValorTotal;


/**
 *
 * @author lara_
 */
public class CalculadoraDescontoTaxaEntregaService {
     private final List<IMetodoDescontoTaxaEntrega> metodosDeDescontoTaxaEntrega;
    
    //Construtor
    public CalculadoraDescontoTaxaEntregaService(){
        metodosDeDescontoTaxaEntrega = new ArrayList<>();
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorBairro());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorDataPedido());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorValorTotal(2000.00)); 
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorTipoCliente());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorTipoItem());
    }
    
    public void calcularDescontoTaxaEntrega(Pedido pedido){
        if(pedido == null){
            throw new IllegalArgumentException("Não existe um pedido!");
        }
        
        for(IMetodoDescontoTaxaEntrega metodoDescontoTaxaEntrega : metodosDeDescontoTaxaEntrega){   
            metodoDescontoTaxaEntrega.calcularDesconto(pedido);
        }   
    }
    
    public void adicionarMetodoDescontoTaxaEntrega(IMetodoDescontoTaxaEntrega metodoDescontoTaxaEntrega){
        if(metodoDescontoTaxaEntrega == null){
            throw new IllegalArgumentException("Método de desconto de taxa de entrega inválido!");
        }
        metodosDeDescontoTaxaEntrega.add(metodoDescontoTaxaEntrega);
    }
}
