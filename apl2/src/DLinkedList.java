// arquivo: src/apl2/DLinkedList.java

// TODO: Colocar a identificação dos(as) integrantes aqui.
// Gabriel Ferreira
// RA: 10442043
// Gian Lucca Campanha Ribeiro
// RA: 10438361
// Pedro Henrique Saraiva Arruda
// RA: 10437747

package apl2.src;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {

    // TODO: Implementar a classe conforme o enunciado da atividade Apl2.

    private Node head;
    private Node tail;
    private int count;

    // OPERAÇÃO:       Método construtor
    // COMPORTAMENTO:   Cria uma lista vazia.
    public DLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    // OPERAÇÃO:       insert(<dados da pessoa>)
    // COMPORTAMENTO:   Aloca um Node que contém os <dados da pessoa> e insere o
    //              novo nó no início da lista.
    public void insert(String id, String name, int integerPart, double decimalPart) {
        float grade = integerPart + (float) decimalPart;
        Node node = new Node(id, name, grade);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }

        count++;
    }

    // OPERAÇÃO:       append(<dados da pessoa>)
    // COMPORTAMENTO:   Aloca um Node que contém os <dados da pessoa> e insere o
    //              novo nó no final da lista.
    public void append(String id, String name, int integerPart, double decimalPart) {
        float grade = integerPart + (float) decimalPart;
        Node node = new Node(id, name, grade);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        count++;
    }

    // OPERAÇÃO:       removeHead()
    // COMPORTAMENTO:   Remove o nó do início da lista e retorna a referência do
    //              nó removido.
    //              Ou retorna null caso a lista esteja vazia.
    public Node removeHead() {
        if (isEmpty()) {
            return null;
        }

        Node toRemove = head;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        toRemove.next = null;
        toRemove.prev = null;
        count--;
        return toRemove;
    }

    // OPERAÇÃO:       removeTail()
    // COMPORTAMENTO:   Remove o nó do final da lista e retorna a referência do
    //              nó removido.
    //              Ou retorna null caso a lista esteja vazia.
    public Node removeTail() {
        if (isEmpty()) {
            return null;
        }

        Node toRemove = tail;
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        toRemove.next = null;
        toRemove.prev = null; // Added: Clear prev reference
        count--;
        return toRemove;
    }

    // OPERAÇÃO:       removeNode(<ID da pessoa>)
    // COMPORTAMENTO:   Remove o nó que contém o <ID da pessoa> da lista e retorna
    //              a referência do nó removido.
    //              Ou retorna null caso não exista um nó com <ID da pessoa>.
    public Node removeNode(String id) {
        if (isEmpty()) {
            return null;
        }

        Node current = head;
        while (current != null && !current.getId().equals(id)) {
            current = current.next;
        }

        if (current == null) {
            return null;
        }

        if (current == head) {
            return removeHead();
        }

        if (current == tail) {
            return removeTail();
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.next = null;
        current.prev = null;
        count--;
        return current;
    }

    // OPERAÇÃO:       getHead()
    // COMPORTAMENTO:   Retorna uma referência para o nó do início da lista.
    //              Ou retorna null caso a lista esteja vazia.
    public Node getHead() {
        return head;
    }

    // OPERAÇÃO:       getTail()
    // COMPORTAMENTO:   Retorna uma referência para o nó do final da lista.
    //              Ou retorna null caso a lista esteja vazia.
    public Node getTail() {
        return tail;
    }

    // OPERAÇÃO:       getNode(<ID da pessoa>)
    // COMPORTAMENTO:   Retorna uma referência para o nó que contém o <ID da pessoa>
    //              da lista.
    //              Ou retorna null caso não exista um nó com <ID da pessoa>.
    public Node getNode(String id) {
        Node current = head;
        while (current != null) {
            if (current.getId().equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // OPERAÇÃO:       count()
    // COMPORTAMENTO:   Retorna a quantidade de nós da lista.
    public int count() {
        return count;
    }

    // OPERAÇÃO:       isEmpty()
    // COMPORTAMENTO:   Retorna true se a lista estiver vazia ou false, caso contrário.
    public boolean isEmpty() {
        return head == null;
    }

    // OPERAÇÃO:       clear()
    // COMPORTAMENTO:   Esvazia a lista, liberando a memória de todos os nós da lista.
    public void clear() {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = null;
        tail = null;
        count = 0;
    }

    // OPERAÇÃO:       toString()
    // COMPORTAMENTO:   Retorna uma string com o conteúdo da lista (caso queira, use o
    //              exemplo do método toString() da classe LinkedListOriginal).
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(count).append(") \n");

        Node current = head;
        if (current == null) {
            sb.append("null");
        } else {
            sb.append("null");

            while (current != null) {
                sb.append(" <- (")
                        .append(current.getId()).append("; ")
                        .append(current.getName()).append("; ")
                        .append(String.format("%.1f", current.getGrade()))
                        .append(")");
                current = current.next;
                if (current != null) {
                    sb.append(" ->");
                }
            }
            sb.append(" -> null");
        }
        return sb.toString();
    }
}