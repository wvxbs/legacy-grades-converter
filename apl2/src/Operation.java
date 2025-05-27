//*************************** ATENÇÃO! *****************************
// As assinaturas dos métodos desta classe não devem ser alteradas!
//*************************** ATENÇÃO! *****************************
// arquivo: src/apl2/Operation.java

// TODO: Colocar a identificação dos(as) integrantes aqui.
// Gabriel Ferreira
// RA: 10442043
// Gian Lucca Campanha Ribeiro
// RA: 10438361
// Pedro Henrique Saraiva Arruda
// RA: 10437747

package apl2.src;

public class Operation {

    /**
     * <p>Recebe como parâmetro uma lista encadeada do tipo {@code LinkedListOriginal}, sendo que os nós da lista estão
     * populados com o conteúdo da base de dados original (conteúdo do arquivo dados.txt).</p>
     * <p>A operação {@code map()} deve mapear os dados originais para uma lista encadeada do tipo {@code DLinkedList} e
     * retornar a referência da {@code DLinkedList} que possui os dados mapeados para a nova estrutura usada pelo sistema de notas.</p>
     *
     * @param original Base de dados original carregada em uma {@code LinkedListOriginal}.
     * @return Uma nova {@code DLinkedList} que contém o mapeamento da coleção de dados {@code original} para a nova estrutura usada pelo sistema de notas.
     */
    public static DLinkedList map(final LinkedListOriginal original) {
        DLinkedList novaLista = new DLinkedList();
        NodeOriginal atual = original.getHead();

        while (atual != null) {
            String novoId = "23.S1-" + String.format("%03d", atual.getId());
            String novoNome = atual.getNome();
            float novaNota = atual.getInteiro() + (float) atual.getDecimo() / 10.0f;
            novaLista.append(novoId, novoNome, (int) novaNota, (double) (novaNota - (int) novaNota));
            atual = atual.getNext();
        }

        return novaLista;
    }

    /**
     * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
     * populados com o resultado da operação {@code map()}.</p>
     * <p>A operação {@code filterRemoveNonGraded()} deve filtrar os nós que não possuem notas válidas (caso de "ausência de nota")
     * e retornar uma nova lista do tipo {@code DLinkedList} contendo apenas os nós com notas válidas.</p>
     *
     * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
     * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada com nós que possuem apenas pessoas com notas válidas.
     */
    public static DLinkedList filterRemoveNonGraded(final DLinkedList data) {
        DLinkedList novaLista = new DLinkedList();
        Node atual = data.getHead();

        while (atual != null) {
            if (atual.getGrade() >= 0.0f && atual.getGrade() <= 10.0f) {
                novaLista.append(atual.getId(), atual.getName(), (int) atual.getGrade(), (double) (atual.getGrade() - (int) atual.getGrade()));
            }
            atual = atual.getNext();
        }

        return novaLista;
    }

    /**
     * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
     * populados com o resultado da operação {@code map()}.</p>
     * <p>A operação {@code filterRemoveGraded()} deve filtrar os nós que possuem notas válidas e retornar uma nova lista do
     * tipo {@code DLinkedList} contendo apenas os nós com notas inválidas (caso de "ausência de nota").</p>
     *
     * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
     * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada com nós que possuem apenas pessoas com notas inválidas.
     */
    public static DLinkedList filterRemoveGraded(final DLinkedList data) {
        DLinkedList novaLista = new DLinkedList();
        Node atual = data.getHead();

        while (atual != null) {
            if (atual.getGrade() < 0.0f || atual.getGrade() > 10.0f) {
                novaLista.append(atual.getId(), atual.getName(), -99, -0.9); // Using a distinct invalid representation
            }
            atual = atual.getNext();
        }

        return novaLista;
    }

    /**
     * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
     * populados com o resultado da operação {@code filterRemoveNonGraded()}, e a média de notas válidas, calculadas com a
     * operação {@code reduce()}.</p>
     * <p>A operação {@code filterRemoveBelowAverage()} deve filtrar os nós que possuem notas abaixo da média e retornar uma
     * nova lista do tipo {@code DLinkedList} contendo apenas os nós com notas acima da média.
     *
     * @param data Base de dados filtrada com a operação {@code filterRemoveNonGraded()}.
     * @param average Média de notas válidas calculada com a operação {@code reduce()}.
     * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada somente com pessoas com notas maiores do que {@code average}.
     */
    public static DLinkedList filterRemoveBelowAverage(final DLinkedList data, float average) {
        DLinkedList novaLista = new DLinkedList();
        Node atual = data.getHead();

        while (atual != null) {
            if (atual.getGrade() > average) {
                novaLista.append(atual.getId(), atual.getName(), (int) atual.getGrade(), (double) (atual.getGrade() - (int) atual.getGrade()));
            }
            atual = atual.getNext();
        }

        return novaLista;
    }

    /**
     * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
     * populados com o resultado da operação {@code filterRemoveNonGraded()}.</p>
     * <p>A operação {@code reduce()} deve calcular a média das notas contidas na coleção de dados passada como parâmetro e
     * retornar a média calculada.
     *
     * @param data Base de dados filtrada com a operação {@code filterRemoveNonGraded()}.
     * @return Média das notas ({@code float}) contidas na coleção de dados ({@code data}).
     */
    public static float reduce(final DLinkedList data) {
        if (data.isEmpty()) {
            return 0.0f;
        }

        float soma = 0.0f;
        int contador = 0;
        Node atual = data.getHead();

        while (atual != null) {
            soma += atual.getGrade();
            contador++;
            atual = atual.getNext();
        }

        return soma / contador;
    }

    /**
     * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
     * populados com o resultado da operação {@code map()}.</p>
     * <p>A operação {@code mapToString()} deve mapear todos os nós da coleção de dados passada como parâmetro para uma única
     * {@code String}, sendo que cada dado de uma pessoa é separado por ponto-e-vírgula (;) e cada pessoa é separada por uma
     * quebra de linha.</p>
     *
     * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
     * @return {@code String} com a coleção de dados separada por ponto-e-vírgula (dados de cada pessoa) e quebras de linha (cada pessoa).
     */
    public static String mapToString(final DLinkedList data) {
        StringBuilder resultado = new StringBuilder();
        Node atual = data.getHead();

        while (atual != null) {
            resultado.append(atual.getId()).append(";");
            resultado.append(atual.getName()).append(";");
            resultado.append(String.format("%.1f", atual.getGrade())).append("\n");
            atual = atual.getNext();
        }

        return resultado.toString();
    }
}