import java.io.*;
import java.util.*;

// Classe que representa uma palavra e as linhas onde ela aparece
class Palavra {
    String palavra; // A palavra em si
    LinkedList<Integer> linhas; // Lista de linhas onde a palavra aparece

    // Construtor para inicializar a palavra e a lista de linhas
    public Palavra(String palavra, int linha) {
        this.palavra = palavra;
        this.linhas = new LinkedList<>();
        this.linhas.add(linha);
    }

    // Método para adicionar uma nova linha à lista de ocorrências
    public void adicionarLinha(int linha) {
        if (!linhas.contains(linha)) {
            linhas.add(linha);
        }
    }

    // Método para representar a palavra e suas linhas como uma string
    @Override
    public String toString() {
        return palavra + " " + linhas.toString();
    }
}

// Classe que gerencia o índice remissivo
class IndiceRemissivo {
    private static final int TAMANHO_TABELA = 26; // Tamanho da tabela hash (uma letra para cada posição)
    private LinkedList<Palavra>[] tabelaHash; // Tabela hash para armazenar as palavras

    // Construtor para inicializar a tabela hash
    @SuppressWarnings("unchecked")
    public IndiceRemissivo() {
        tabelaHash = (LinkedList<Palavra>[]) new LinkedList[TAMANHO_TABELA];
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            tabelaHash[i] = new LinkedList<>();
        }
    }

    // Função hash para mapear a primeira letra da palavra a um índice na tabela
    private int hash(char letra) {
        return Character.toLowerCase(letra) - 'a';
    }

    // Método para adicionar uma palavra à tabela hash
    public void adicionarPalavra(String palavra, int linha) {
        if (palavra == null || palavra.trim().isEmpty()) return; // Ignorar palavras vazias
        char primeiraLetra = palavra.charAt(0);
        int indice = hash(primeiraLetra);

        // Verificar se a palavra já existe na lista encadeada correspondente
        for (Palavra p : tabelaHash[indice]) {
            if (p.palavra.equalsIgnoreCase(palavra)) { // Comparação case-insensitive
                p.adicionarLinha(linha); // Adicionar a linha à palavra existente
                return;
            }
        }

        // Se a palavra não existir, criar uma nova entrada na lista encadeada
        tabelaHash[indice].add(new Palavra(palavra.toLowerCase(), linha)); // Armazenar em minúsculas
    }

    // Método para gerar o índice remissivo e salvar em um arquivo
    public void gerarIndiceRemissivo(Set<String> palavrasChave, String arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (String palavra : palavrasChave) {
                if (palavra == null || palavra.trim().isEmpty()) continue; // Ignorar palavras-chave vazias
                char primeiraLetra = palavra.charAt(0);
                int indice = hash(primeiraLetra);

                // Buscar a palavra na tabela hash e escrever no arquivo de saída
                for (Palavra p : tabelaHash[indice]) {
                    if (p.palavra.equalsIgnoreCase(palavra)) { // Comparação case-insensitive
                        writer.write(p.palavra + " " + p.linhas.toString());
                        writer.newLine();
                        break;
                    }
                }
            }
        }
    }
}

// Classe principal do programa
public class Main {
    public static void main(String[] args) {
        IndiceRemissivo indice = new IndiceRemissivo();

        // Ler o texto e processar as palavras
        try (BufferedReader reader = new BufferedReader(new FileReader("texto.txt"))) {
            String linha;
            int numeroLinha = 1;
            while ((linha = reader.readLine()) != null) {
                // Dividir a linha em palavras, considerando pontuação e espaços
                String[] palavras = linha.split("[^a-zA-ZÀ-ú]+");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        indice.adicionarPalavra(palavra, numeroLinha); // Adicionar cada palavra ao índice
                    }
                }
                numeroLinha++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de texto: " + e.getMessage());
            return;
        }

        // Ler as palavras-chave
        Set<String> palavrasChave = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("palavras_chave.txt"))) {
            String palavra;
            while ((palavra = reader.readLine()) != null) {
                palavrasChave.add(palavra.toLowerCase()); // Armazenar palavras-chave em minúsculas
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de palavras-chave: " + e.getMessage());
            return;
        }

        // Gerar o índice remissivo
        try {
            indice.gerarIndiceRemissivo(palavrasChave, "indice_remissivo.txt");
            System.out.println("Índice remissivo gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o índice remissivo: " + e.getMessage());
        }
    }
}