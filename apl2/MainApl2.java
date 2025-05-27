//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: apl2/MainApl2.java

// TODO: Colocar a identificação dos(as) integrantes aqui.
// Gabriel Ferreira
// RA: 10442043
// Gian Lucca Campanha Ribeiro
// RA: 10438361
// Pedro Henrique Saraiva Arruda
// RA: 10437747

// TODO: Listar todas as referências consultadas para solucionar a atividade.
// https://stackoverflow.com/questions/8557192/linked-list-implementation-java
// https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
// https://javafullstackdev.medium.com/java-nodes-explained-essential-building-blocks-for-linked-lists-trees-and-graphs-93b1163f922e
// https://www.geeksforgeeks.org/doubly-linked-list/
// https://dev.to/vapordev/doubly-linked-list-4fe5
// https://dev.to/vapordev/doubly-linked-list-4fe5
// https://pt.stackoverflow.com/questions/212754/qual-%c3%a9-a-fun%c3%a7%c3%a3o-do-m%c3%a9todo-tostring
// https://www.devmedia.com.br/como-criar-sobreposicoes-usando-o-metodo-tostring-em-java/29042
// https://www.devmedia.com.br/trabalhando-com-metodos-em-java/25917
// https://www.youtube.com/watch?v=N6dOwBde7-M&t=110s
// https://dev.to/antoniorws/explorando-nodes-em-java-5e59


package apl2;

import apl2.src.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainApl2 {

    public static void main(String[] args) {
        LinkedListOriginal originalList = new LinkedListOriginal();

        try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String nome = parts[1];
                    int inteiro = Integer.parseInt(parts[2]);
                    int decimo = Integer.parseInt(parts[3]);
                    originalList.append(id, nome, inteiro, decimo);
                    System.out.println("Read line: " + line);
                    System.out.println("Parsed data: " + id + " " + nome + " " + inteiro + " " + decimo);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo dados.txt: " + e.getMessage());
            return;
        }

        System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
        System.out.println(originalList);
        System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");

        DLinkedList convertedList = Operation.map(originalList);
        System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
        System.out.println(convertedList);
        System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");

        DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(convertedList);
        System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
        System.out.println(filteredGradedList);
        System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");

        DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(convertedList);
        System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
        System.out.println(filteredNonGradedList);
        System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

        float average = Operation.reduce(filteredGradedList);
        System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
        System.out.println(average);
        System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");

        DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
        System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
        System.out.println(aboveAverageList);
        System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");

        System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
        System.out.println(Operation.mapToString(convertedList));
        System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nSistema Conversor de Notas");
            System.out.println("1) Dados originais: lê arquivo dados.txt e apresenta todos os dados do Sistema de Notas Legado;");
            System.out.println("2) Dados convertidos: gera arquivo dados.csv e apresenta todos os dados do Sistema de Notas Atualizado;");
            System.out.println("3) Lista notas filtradas válidas: apresenta os dados somente das notas válidas filtradas;");
            System.out.println("4) Lista notas filtradas inválidas: apresenta os dados somente das notas filtradas pela \"ausência de notas\";");
            System.out.println("5) Média de notas válidas: apresenta a média das notas válidas filtradas;");
            System.out.println("6) Notas acima da média: apresenta os dados para as notas acima da média;");
            System.out.println("7) Lista mapeada para uma única string: apresenta a String contendo os dados mapeados;");
            System.out.println("8) Finaliza sistema.");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
                    System.out.println(originalList);
                    System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
                    break;
                case 2:
                    System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
                    System.out.println(convertedList);
                    System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
                    break;
                case 3:
                    System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
                    System.out.println(filteredGradedList);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
                    break;
                case 4:
                    System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
                    System.out.println(filteredNonGradedList);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");
                    break;
                case 5:
                    System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
                    System.out.println(average);
                    System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
                    break;
                case 6:
                    System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
                    System.out.println(aboveAverageList);
                    System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
                    break;
                case 7:
                    System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
                    System.out.println(Operation.mapToString(convertedList));
                    System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
                    break;
                case 8:
                    System.out.println("Finalizando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (choice != 8);

        scanner.close();
    }
}