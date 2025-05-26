// arquivo: src/apl2/Node.java

// TODO: Colocar a identificação dos(as) integrantes aqui.
// Gabriel Ferreira
// RA: 10442043
// Gian Lucca Campanha Ribeiro
// RA: 10438361
// Pedro Henrique Saraiva Arruda
// RA: 10437747

package apl2.src;

// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {

    private String id;
    private String name;
    private int integerPart;
    private double decimalPart;
    public Node next;
    public Node prev;

    public Node(String id, String name, int integerPart, double decimalPart) {
        this.id = id;
        this.name = name;
        this.integerPart = integerPart;
        this.decimalPart = decimalPart;
        this.next = null;
        this.prev = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntegerPart() {
        return integerPart;
    }

    public void setIntegerPart(int integerPart) {
        this.integerPart = integerPart;
    }

    public double getDecimalPart() {
        return decimalPart;
    }

    public void setDecimalPart(double decimalPart) {
        this.decimalPart = decimalPart;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "(" + id + "; " + name + "; " + (integerPart + decimalPart) + ")";
    }
}