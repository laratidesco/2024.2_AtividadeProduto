package model;

/**
 *
 * @author lara_
 */
public class CupomDescontoPedido {
    private final String nomeMetodo;
    private final double valorDesconto;
    
    //Construtor
    public CupomDescontoPedido(String nomeMetodo, double valorDesconto){
         if(nomeMetodo == null || valorDesconto < 0.0){
            throw new IllegalArgumentException("Dados do cupom de desconto inválidos!");
        }
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }
    
    //Getters
    public String getNomeMetodo() {
        return nomeMetodo;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    
    @Override
    public String toString() {
        return "CupomDescontoPedido: " + "Nome do Método= " + nomeMetodo + ", Valor de Desconto= " + valorDesconto;
    }
    
}
