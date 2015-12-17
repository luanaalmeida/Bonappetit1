package br.senac.pi.bonappetit.domain;

/**
 * Created by familia on 16/12/2015.
 */
public class Pedido {
    private long id;
    private String prato;
    private String valor;
    private String quantidade;

    public Pedido(){}

    public Pedido (long id, String prato, String valor, String quantidade) {
        this.prato = prato;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
