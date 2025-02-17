package principal;

import java.time.LocalDate;
import java.time.Month;
import model.Cliente;
import model.Item;
import model.Pedido;
import services.CalculadoraValorTotalService;

/**
 *
 * @author lara_
 */
public class Principal {

    public static void main(String[] args) {
        // Criando um pedido com dois itens de uma cliente,
        Cliente lara = new Cliente("Lara", "Ouro", 1.0, "Praça Três Irmãos", "Centro", "Castelo");
        Pedido pedido = new Pedido(2.00, LocalDate.of(2024, Month.MARCH, 13), lara, "DESC30");    
        Item item1 = new Item("Biscoito", 4, 3.50, "Alimentação");
        pedido.adicionarItem(item1);      
        Item item2 = new Item("Caneta", 2, 2.50, "Educação");
        pedido.adicionarItem(item2);
        
        // Criando outro pedido de outro cliente
        Cliente rosa = new Cliente("Rosa", "Prata", 1.0, "Praça Três Irmãos", "Centro", "Castelo");
        Pedido pedido2 = new Pedido(2.00, LocalDate.of(2024, Month.MARCH, 13), rosa, "DESC30");    
        Item item3 = new Item("Café", 2, 11.50, "Alimentação");
        pedido2.adicionarItem(item1);
        pedido2.adicionarItem(item3);      
        
        
        
        // Mostrando o valor total do pedido sem descontos e, posteriormente, com desconto, e registrando log
        CalculadoraValorTotalService calculadoraValor = new CalculadoraValorTotalService();
        
        //Pedido 1:
        System.out.println("Valor total do pedido: " + calculadoraValor .calculaValorSomaItens(pedido));
        System.out.println("Valor total do pedido (desconto aplicado): " + calculadoraValor.calculaValorTotalPedidoComDescontos(pedido));
        
        //Pedido 2:
        System.out.println("Valor total do pedido: " + calculadoraValor .calculaValorSomaItens(pedido2));
        System.out.println("Valor total do pedido (desconto aplicado): " + calculadoraValor.calculaValorTotalPedidoComDescontos(pedido2));

    }
}
