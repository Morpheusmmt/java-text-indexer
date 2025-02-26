Índice Remissivo
Este projeto implementa a criação de um índice remissivo de palavras-chave presentes em um arquivo de texto. O programa processa o arquivo, coleta as palavras-chave fornecidas em um arquivo específico e gera um índice com as linhas onde cada palavra aparece.

Funcionalidade
Leitura de um arquivo de texto para identificar as palavras e suas respectivas linhas.
Leitura de um arquivo com palavras-chave para gerar o índice remissivo apenas com as palavras-chave desejadas.
Armazenamento de palavras e linhas em uma tabela hash para uma pesquisa eficiente.
Geração de um índice remissivo, salvo em um arquivo de texto.
Estrutura do Projeto
Palavra.java: Representa uma palavra e as linhas onde ela aparece.
IndiceRemissivo.java: Gerencia a tabela hash e contém os métodos para adicionar palavras, gerar o índice remissivo e gravá-lo em um arquivo.
Main.java: Contém o método main para executar o programa. Ele faz a leitura dos arquivos, processa as palavras e gera o índice remissivo.
Dependências
Este projeto utiliza apenas as bibliotecas padrão do Java, então não há necessidade de dependências externas.

Como Usar
Passo 1: Preparar os Arquivos de Entrada
Arquivo de Texto: O arquivo texto.txt deve conter o texto que será analisado. O texto pode ter várias palavras separadas por espaços, pontuação ou quebras de linha.

Arquivo de Palavras-chave: O arquivo palavras_chave.txt deve conter as palavras-chave, uma por linha, que serão pesquisadas no arquivo de texto.

Passo 2: Compilar e Executar
Compilar o código:
bash
Copiar
Editar
javac Main.java IndiceRemissivo.java Palavra.java
Executar o programa:
bash
Copiar
Editar
java Main
Após a execução, o índice remissivo será gerado no arquivo indice_remissivo.txt.

Saída
O arquivo indice_remissivo.txt conterá uma lista das palavras-chave, seguidas das linhas onde elas aparecem. O formato será:

css
Copiar
Editar
palavra [linha1, linha2, linha3]
Exemplo de saída:

css
Copiar
Editar
java [2, 5, 8]
indice [3, 7]
Exemplos de Arquivos
texto.txt
mathematica
Copiar
Editar
Java é uma linguagem de programação poderosa. Muitas pessoas aprendem Java.
O índice remissivo é útil para encontrar palavras rapidamente.
palavras_chave.txt
nginx
Copiar
Editar
java
índice
índice_remissivo.txt (saída)
css
Copiar
Editar
java [1, 2]
índice [3]
Considerações
O índice é gerado apenas para as palavras contidas no arquivo palavras_chave.txt.
A busca no arquivo de texto é feita de forma simples, com base nas palavras separadas por espaços ou pontuações.
O projeto utiliza uma tabela hash para armazenar e buscar rapidamente as palavras e suas ocorrências.
Contribuição
Se desejar contribuir, fique à vontade para abrir issues ou pull requests. Para mais informações, consulte a documentação do GitHub.

Licença
Este projeto está sob a MIT License.

