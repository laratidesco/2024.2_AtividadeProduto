package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author lara_
 */
public class Pedido {
    private static int numeroDePedidos = 0;  //variavel estática para contar instancias de pedido e associar códigos
    private double taxaEntrega;
    private LocalDate dataPedido;
    private Cliente cliente;
    private final String codigoDeCupom;
    private final List<Item> itens;
    private final List<CupomDescontoEntrega> cuponsDescontoEntrega;
    private final List<CupomDescontoPedido> cuponsDescontoPedido;
    private int codigoPedido;   // o código será valor n + 1;
    
    public Pedido(double taxaEntrega, LocalDate dataPedido, Cliente cliente, String codigoDeCupom){
        if(taxaEntrega < 0 || dataPedido == null || cliente == null || codigoDeCupom == null){
            throw new IllegalArgumentException("Dados do pedido inválidos!");
        }
        
        this.taxaEntrega = taxaEntrega;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.cuponsDescontoEntrega = new ArrayList<>();
        this.cuponsDescontoPedido = new ArrayList<>();
        this.codigoDeCupom = codigoDeCupom;
        this.codigoPedido = numeroDePedidos + 1;
        
        //atualiza variável
        numeroDePedidos++;
    }
    
    // Getters e Setters
    public double getTaxaEntrega(){
        return taxaEntrega;
    }

    public LocalDate getDataPedido(){
        return dataPedido;
    }
     
    public Cliente getCliente(){
        return cliente;
    }
   
    public List<Item> getItens(){
        return Collections.unmodifiableList(itens);
    }
    
    public List<CupomDescontoEntrega> getCuponsDescontoEntrega(){
       return Collections.unmodifiableList(cuponsDescontoEntrega);
    }

    public List<CupomDescontoPedido> getCuponsDescontoPedido(){
       return Collections.unmodifiableList(cuponsDescontoPedido);
    }
    
    public String getCodigoDeCupom(){
        return codigoDeCupom;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }
    
    // Adicionar item  ao array
    public void adicionarItem(Item item){
        if(item == null) {
            throw new IllegalArgumentException("Item inválido!");
        } else {
            itens.add(item);
        } 
    }    

    // Adiciona o cupom de desconto de entrega no array correspondente
    public void aplicarDescontoTaxaEntrega(CupomDescontoEntrega cupomDescontoEntrega){
        if(cupomDescontoEntrega == null) throw new IllegalArgumentException("Cupom de desconto de entrega inválido!");  
        cuponsDescontoEntrega.add(cupomDescontoEntrega);
    }
    
    // Adiciona o cupom de desconto de pedido no array correspondente
    public void aplicarDescontoPedido(CupomDescontoPedido cupomDescontoPedido){
        if(cupomDescontoPedido == null) throw new IllegalArgumentException("Cupom de desconto de pedido inválido!");  
        cuponsDescontoPedido.add(cupomDescontoPedido);
    }

    @Override
    public String toString() {
        return "Pedido{" + "taxaEntrega=" + taxaEntrega + ", dataPedido=" + dataPedido + ", cliente=" + cliente + ", itens=" + itens + ", cuponsDescontoEntrega=" + cuponsDescontoEntrega + ", cuponsDescontoPedido=" + cuponsDescontoPedido + '}';
    }

}

