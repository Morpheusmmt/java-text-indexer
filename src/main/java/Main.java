import java.io.*;
import java.util.*;

// Classe que representa uma palavra e as linhas onde ela aparece
class Palavra {
    String palavra; 
    LinkedList<Integer> linhas; 

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
    private static final int TAMANHO_TABELA = 26; 
    private LinkedList<Palavra>[] tabelaHash; 

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
        int index = Character.toLowerCase(letra) - 'a';
        return (index >= 0 && index < TAMANHO_TABELA) ? index : 0;
    }

    // Método para adicionar uma palavra à tabela hash
    public void adicionarPalavra(String palavra, int linha) {
        if (palavra == null || palavra.trim().isEmpty()) return; 
        char primeiraLetra = palavra.charAt(0);
        int indice = hash(primeiraLetra);

        // Verificar se a palavra já existe na lista encadeada correspondente
        for (Palavra p : tabelaHash[indice]) {
            if (p.palavra.equalsIgnoreCase(palavra)) { 
                p.adicionarLinha(linha);
                return;
            }
        }

        // Se a palavra não existir, criar uma nova entrada na lista encadeada
        tabelaHash[indice].add(new Palavra(palavra.toLowerCase(), linha)); 
    }

    // Método para gerar o índice remissivo e salvar em um arquivo
    public void gerarIndiceRemissivo(Set<String> palavrasChave, String arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (String palavra : palavrasChave) {
                if (palavra == null || palavra.trim().isEmpty()) continue; 
                char primeiraLetra = palavra.charAt(0);
                int indice = hash(primeiraLetra);

                // Buscar a palavra na tabela hash e escrever no arquivo de saída
                for (Palavra p : tabelaHash[indice]) {
                    if (p.palavra.equalsIgnoreCase(palavra)) { 
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
                
                String[] palavras = linha.split("[^a-zA-ZÀ-ú]+");
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        indice.adicionarPalavra(palavra, numeroLinha); 
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