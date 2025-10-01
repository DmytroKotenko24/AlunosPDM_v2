package modelo;

import java.io.Serializable;

public class Pessoa implements Serializable, Comparable<Pessoa> {
    private int numero;
    private String nome;
    private char tipo;
    public Pessoa(int numero, String nome, char tipo) {
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
    }
    public char getTipo() {
        return tipo;
    }
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "Numero: " + numero + "\nNome: '" + nome;
    }
    @Override
    public int compareTo(Pessoa o) {
        /*
        //Se pretendesse ordenar pelo número
        if (numero > o.numero)
        return 1;
        if (numero < o.numero)
        return -1;
        return 0;
        */
        return nome.compareToIgnoreCase(o.nome);
    }
}
