package model;

/**
 *
 * @author lara_
 */
public class CupomDescontoEntrega {
    private final String nomeMetodo;
    private final double valorDesconto;
    
    //Construtor
    public CupomDescontoEntrega(String nomeMetodo, double valorDesconto){
        if(nomeMetodo == null || valorDesconto < 0.0){
            throw new IllegalArgumentException("Dados do cupom de desconto de entrega inválidos!");
        }
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }
    
    //Getters
    public String getNomeMetodo(){
        return nomeMetodo;
    }
    
    public double getValorDesconto(){
        return valorDesconto;
    }
    
    
    @Override
    public String toString() {
        return "CupomDescontoEntrega: " + "Nome do Método =" + nomeMetodo + ", Valor Desconto =" + valorDesconto;
    } 
    
}
