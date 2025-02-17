package services;

import java.util.List;
import model.CupomDescontoEntrega;
import model.CupomDescontoPedido;
import model.Item;
import model.Pedido;

/**
 *
 * @author lara_
 */

public class CalculadoraValorTotalService {
    private final LogService registros;
    
    //Construtor
    public CalculadoraValorTotalService() {
        this.registros = new LogService();
    }
    
    
    // Obtem a soma dos itens 
    public double calculaValorSomaItens(Pedido pedido){
        double valorItens = pedido.getItens().stream().mapToDouble(Item::getValorTotal).sum();
       /* Para não necessitar de um looping for, utiliza Streams que converte a lista de itens 
          em um fluxo sequencial que pode ser manipulado.
            OBS: stream().mapToDouble(Item::getValorTotal).sum() extrai pelo get o valor de cada 
            posição do List, soma todos pelo sum e devolve como um Double.
       */
       return valorItens;
    } 

    // Calcula a porcentagem de desconto da taxa de entrega 
    public double calculaPorcentagemDescontoTaxaEntrega(Pedido pedido){
       List<CupomDescontoEntrega> auxCupom = pedido.getCuponsDescontoEntrega();
       double descontoTaxaEntregaConcedido = auxCupom.stream().mapToDouble(CupomDescontoEntrega::getValorDesconto).sum();
       // Se o desconto for maior que 1 significa que é maior que 100% e, portanto, o valor é zero.
       if(descontoTaxaEntregaConcedido > 1) {
           return 0.0;
       } else {
           return descontoTaxaEntregaConcedido;
       }
    }
   
    // Calcula a porcentagem de desconto do pedido
    public double calculaPorcentagemDescontoPedido(Pedido pedido){
      List<CupomDescontoPedido> auxCupom = pedido.getCuponsDescontoPedido();
      double descontoPedidoConcedido = auxCupom.stream().mapToDouble(CupomDescontoPedido::getValorDesconto).sum();
      // Se o desconto for maior que 1 significa que é maior que 100% e, portanto, o valor é zero.
      if(descontoPedidoConcedido > 1) {
          return 1;
      } else {
          return descontoPedidoConcedido;
      }
    }

    // Calcula o valor total do pedido com descontos aplicados
    public double calculaValorTotalPedidoComDescontos(Pedido pedido) {
      //Calculadora da TAXA DE ENTREGA irá verificar quais descontos se aplicam, criar o método e adicionar à lista.
      CalculadoraDescontoTaxaEntregaService descEntrega = new CalculadoraDescontoTaxaEntregaService();
      descEntrega.calcularDescontoTaxaEntrega(pedido);
      //Calculadora do desconto do PEDIDO irá verificar quais descontos se aplicam, criar o método e adicionar à lista.
      CalculadoraDescontoPedidoService descPedido = new CalculadoraDescontoPedidoService();
      descPedido.calcularDescontoPedido(pedido);
          
      
      //Executa o cálculo de quantos % de desconto terá em cada caso
      double descontoTaxaEntregaConcedido = calculaPorcentagemDescontoTaxaEntrega(pedido);
      double descontoPedidoConcedido = calculaPorcentagemDescontoPedido(pedido);
      
      //Registra o Log
      registros.registrarLog(pedido);
      
      //Aplica todos os descontos e retorna o valor total
      return (calculaValorSomaItens(pedido)*descontoPedidoConcedido)+(pedido.getTaxaEntrega()*descontoTaxaEntregaConcedido);
    }
}
