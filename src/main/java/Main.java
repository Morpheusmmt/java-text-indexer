import java.io.*;
import java.util.*;

class Palavra {
    String palavra;
    LinkedList<Integer> linhas;

    public Palavra(String palavra, int linha) {
        this.palavra = palavra;
        this.linhas = new LinkedList<>();
        this.linhas.add(linha);
    }

    public void adicionarLinha(int linha) {
        if (!linhas.contains(linha)) {
            linhas.add(linha);
        }
    }

    @Override
    public String toString() {
        return palavra + " " + linhas.toString();
    }
}

class IndiceRemissivo {
    private static final int TAMANHO_TABELA = 26; // Uma posição para cada letra do alfabeto
    private LinkedList<Palavra>[] tabelaHash;

    public IndiceRemissivo() {
        tabelaHash = new LinkedList[TAMANHO_TABELA];
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            tabelaHash[i] = new LinkedList<>();
        }
    }

    private int hash(char letra) {
        return Character.toLowerCase(letra) - 'a';
    }

    public void adicionarPalavra(String palavra, int linha) {
        if (palavra.isEmpty()) return;
        char primeiraLetra = palavra.charAt(0);
        int indice = hash(primeiraLetra);

        for (Palavra p : tabelaHash[indice]) {
            if (p.palavra.equals(palavra)) {
                p.adicionarLinha(linha);
                return;
            }
        }

        tabelaHash[indice].add(new Palavra(palavra, linha));
    }

    public void gerarIndiceRemissivo(Set<String> palavrasChave, String arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (String palavra : palavrasChave) {
                if (palavra == null || palavra.trim().isEmpty()) continue;
                char primeiraLetra = palavra.charAt(0);
                int indice = hash(primeiraLetra);

                for (Palavra p : tabelaHash[indice]) {
                    if (p.palavra.equals(palavra)) {
                        writer.write(p.palavra + " " + p.linhas.toString());
                        writer.newLine();
                        break;
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        IndiceRemissivo indice = new IndiceRemissivo();

        // Ler o texto e processar as palavras
        try (BufferedReader reader = new BufferedReader(new FileReader("texto.txt"))) {
            String linha;
            int numeroLinha = 1;
            while ((linha = reader.readLine()) != null) {
                String[] palavras = linha.split("\\W+"); // Divide por caracteres não alfanuméricos
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        indice.adicionarPalavra(palavra, numeroLinha);
                    }
                }
                numeroLinha++;
            }
        }

        // Ler as palavras-chave
        Set<String> palavrasChave = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("palavras_chave.txt"))) {
            String palavra;
            while ((palavra = reader.readLine()) != null) {
                palavrasChave.add(palavra);
            }
        }

        // Gerar o índice remissivo
        indice.gerarIndiceRemissivo(palavrasChave, "indice_remissivo.txt");
    }
}