# Índice Remissivo em Java

Este projeto é uma implementação de um gerador de índice remissivo em Java. Ele cria um índice remissivo a partir de um arquivo de texto e um conjunto de palavras-chave, indicando em quais linhas as palavras aparecem.

## Funcionalidades

- Lê um arquivo de texto e extrai as palavras.
- Cria um índice remissivo utilizando uma tabela hash.
- Gera um índice remissivo para um conjunto de palavras-chave a partir de um arquivo de entrada.
- Salva o índice remissivo em um arquivo de saída.

## Estrutura do Projeto

O projeto é composto pelas seguintes classes:

- **Palavra**: Representa uma palavra e as linhas onde ela aparece.
- **IndiceRemissivo**: Gerencia o índice remissivo, incluindo a adição de palavras e a geração do arquivo de índice.
- **Main**: Classe principal que lê o arquivo de texto, processa as palavras e gera o índice remissivo.

## Como Usar

### Pré-requisitos

- Java 8 ou superior instalado.
- Arquivo de texto (`texto.txt`) com o conteúdo para gerar o índice.
- Arquivo de palavras-chave (`palavras_chave.txt`) com uma lista de palavras que você deseja encontrar no índice.

### Passos

1. Clone este repositório para o seu computador:
   ```bash
   git clone https://github.com/seu-usuario/indice-remissivo.git
Coloque o arquivo texto.txt com o conteúdo do texto no diretório do projeto.

Coloque o arquivo palavras_chave.txt com as palavras-chave desejadas (uma por linha).

Compile e execute o programa:

bash

Copiar

Editar

javac Main.java

java Main

O índice remissivo será gerado no arquivo indice_remissivo.txt.


Arquivos de Entrada
texto.txt: O arquivo de texto que será analisado para criar o índice remissivo.
palavras_chave.txt: Arquivo com as palavras-chave para serem incluídas no índice. Cada palavra deve estar em uma linha separada.
Exemplo de Arquivo palavras_chave.txt

bash

Copiar

Editar

java

hash

index

Exemplo de Saída (indice_remissivo.txt)
O índice gerado será salvo no arquivo indice_remissivo.txt e terá o seguinte formato:

css

Copiar

Editar

java [1, 5, 7]

hash [3, 10]

index [2, 8, 15]

Onde:

Cada linha mostra a palavra e as linhas onde ela ocorre no texto.
Contribuições
Se você deseja contribuir para este projeto, fique à vontade para fazer um fork e enviar um pull request.

