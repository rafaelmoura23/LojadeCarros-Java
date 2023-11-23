package Model;

public class Vendas {

    // atributos
    private String data;
    private String cliente;
    private String valor;
    private String carro;
    
    // construtor
    public Vendas(String data, String cliente, String valor, String carro) {
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
        this.carro = carro;
    }

    // getters and setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

}
