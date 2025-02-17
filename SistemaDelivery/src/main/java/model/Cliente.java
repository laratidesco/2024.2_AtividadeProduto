package model;

/**
 *
 * @author lara_
 */
public final class Cliente {
    private final String nome;
    private final String tipo;
    private double fidelidade;
    private final String logradouro;
    private final String bairro;
    private final String cidade;
    
    //Construtor
    public Cliente(String nome, String tipo, double fidelidade, String logradouro, String bairro, String cidade){
        if(nome == null || tipo == null || fidelidade < 0.0 || logradouro == null || bairro == null || cidade == null){
            throw new IllegalArgumentException("Dados do cliente invalidos");
        }
        this.nome = nome;
        this.tipo = tipo;
        this.fidelidade = fidelidade;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    
    //Getters
    public String getNome(){
        return nome;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getLogradouro(){
        return logradouro;
    }
    
    public String getBairro(){
        return bairro;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public double getFidelidade(){
        return fidelidade;
    }


    @Override
    public String toString() {
        return "Cliente: " + "Nome=" + nome + ", Tipo=" + tipo + ", Fidelidade=" + fidelidade + ", Logradouro=" + logradouro + ", Bairro=" + bairro + ", Cidade=" + cidade;
    }
    
}
