# Biblioteca - Sistema de Gerenciamento de Biblioteca

## 📚 Visão Geral do Projeto

O `BibliotecaDevWork` é um sistema de gerenciamento de biblioteca robusto e intuitivo, desenvolvido em Java com interface gráfica JavaFX. Este projeto visa facilitar a administração de livros e utilizadores em ambientes de biblioteca, oferecendo funcionalidades essenciais para registo, pesquisa, empréstimo e devolução de itens. É uma solução ideal para pequenas e médias bibliotecas que procuram uma ferramenta eficiente e de fácil utilização para otimizar as suas operações diárias.

## ✨ Funcionalidades Principais

*   **Gestão de Utilizadores:** Registo, edição e remoção de utilizadores com diferentes níveis de acesso (administrador e comum).
*   **Gestão de Livros:** Adição, atualização e remoção de livros, incluindo detalhes como título, autor, ISBN, ano de publicação e quantidade disponível.
*   **Empréstimo e Devolução:** Sistema para registar empréstimos e devoluções de livros, com controlo de prazos.
*   **Pesquisa Avançada:** Funcionalidades de pesquisa para encontrar livros e utilizadores rapidamente.
*   **Interface Gráfica Intuitiva:** Desenvolvida com JavaFX para uma experiência de utilizador agradável e responsiva.

## 📋 Índice
1. [Pré-requisitos](#pré-requisitos)
2. [Instalação do Java](#instalação-do-java)
3. [Instalação do Maven](#instalação-do-maven)
4. [Instalação do JavaFX](#instalação-do-javafx)
5. [Executando o Projeto](#executando-o-projeto)
6. [Estrutura do Projeto](#estrutura-do-projeto)
7. [Como Contribuir](#como-contribuir)
8. [Licença](#licença)
9. [Solução de Problemas](#solução-de-problemas)

## 🚀 Pré-requisitos
- Windows 10 ou superior
- Java JDK 17 ou superior
- Maven 3.8.0 ou superior
- JavaFX 17 ou superior

## 💻 Instalação do Java
1. Baixe o JDK 17 do site oficial da Oracle ou use o OpenJDK
2. Execute o instalador e siga as instruções
3. Configure a variável de ambiente JAVA_HOME:
   - Abra o Painel de Controle > Sistema > Configurações avançadas do sistema
   - Clique em "Variáveis de Ambiente"
   - Em "Variáveis do Sistema", clique em "Novo"
   - Nome da variável: `JAVA_HOME`
   - Valor da variável: `C:\Program Files\Java\jdk-17` (ou o caminho onde o JDK foi instalado)
4. Adicione o Java ao PATH:
   - Em "Variáveis do Sistema", encontre a variável "Path"
   - Clique em "Editar"
   - Clique em "Novo"
   - Adicione: `%JAVA_HOME%\bin`
5. Verifique a instalação:
   ```bash
   java -version
   ```

## 📦 Instalação do Maven
1. Baixe o Maven do site oficial: https://maven.apache.org/download.cgi
2. Extraia o arquivo baixado para um diretório (ex: `C:\Program Files\Apache\maven`)
3. Configure a variável de ambiente MAVEN_HOME:
   - Abra o Painel de Controle > Sistema > Configurações avançadas do sistema
   - Clique em "Variáveis de Ambiente"
   - Em "Variáveis do Sistema", clique em "Novo"
   - Nome da variável: `MAVEN_HOME`
   - Valor da variável: `C:\Program Files\Apache\maven` (ou o caminho onde o Maven foi extraído)
4. Adicione o Maven ao PATH:
   - Em "Variáveis do Sistema", encontre a variável "Path"
   - Clique em "Editar"
   - Clique em "Novo"
   - Adicione: `%MAVEN_HOME%\bin`
5. Verifique a instalação:
   ```bash
   mvn -version
   ```

## 🎨 Instalação do JavaFX
1. O JavaFX já está incluído como dependência no arquivo `pom.xml`
2. Não é necessário instalação manual

## 🚀 Executando o Projeto

### Método 1: Usando o Terminal
1. Abra o terminal (PowerShell ou CMD)
2. Navegue até a pasta do projeto:
   ```bash
   cd C:\caminho\para\BibliotecaDevWork
   ```
3. Compile o projeto:
   ```bash
   mvn clean install
   ```
4. Execute o projeto:
   ```bash
   mvn javafx:run
   ```

### Método 2: Usando os Scripts Batch
1. Dê duplo clique no arquivo `run.bat` na pasta raiz do projeto
   - Este script compila e executa o projeto automaticamente

### Método 3: Usando uma IDE
1. Abra o projeto na sua IDE (IntelliJ IDEA, Eclipse, etc.)
2. Localize o arquivo `src/main/java/com/biblioteca/Main.java`
3. Execute o arquivo clicando no botão "Run" ou usando o atalho (geralmente Shift + F10)

## 📁 Estrutura do Projeto
```
BibliotecaDevWork/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── biblioteca/
│                   ├── Main.java                 # Ponto de entrada da aplicação
│                   ├── TelaLogin.java            # Tela de login
│                   ├── TelaCadastro.java         # Tela de cadastro
│                   ├── TelaPrincipalUsuario.java # Tela principal do usuário
│                   ├── TelaPrincipalAdmin.java   # Tela principal do administrador
│                   ├── User.java                 # Classe de usuário
│                   └── Livro.java                # Classe de livro
├── pom.xml                                       # Configuração do Maven
├── run.bat                                       # Script para executar o projeto
└── compile.bat                                   # Script para compilar o projeto
```

## 🤝 Como Contribuir

Contribuições são sempre bem-vindas! Se deseja contribuir para o `BibliotecaDevWork`, siga estes passos:

1.  **Faça um Fork** do repositório.
2.  **Clone** o seu fork localmente: `git clone https://github.com/Inspiders/BibliotecaDevWork.git`
3.  **Crie uma Branch** para a sua funcionalidade ou correção: `git checkout -b feature/minha-funcionalidade` ou `git checkout -b bugfix/correcao-erro`.
4.  **Faça as suas Alterações** e teste-as cuidadosamente.
5.  **Commit** as suas alterações com mensagens claras e descritivas.
6.  **Envie** as suas alterações para o seu fork: `git push origin feature/minha-funcionalidade`.
7.  **Abra um Pull Request** para o repositório original, descrevendo as suas alterações e o problema que resolvem ou a funcionalidade que adicionam.

Por favor, certifique-se de que o seu código segue as convenções de estilo do projeto e que todos os testes passam.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## 🔧 Solução de Problemas

### Erro de Compilação
- Verifique se o Java JDK 17 está instalado corretamente:
  ```bash
  java -version
  ```
- Verifique se o Maven está instalado corretamente:
  ```bash
  mvn -version
  ```

### Erro de Execução
- Verifique se todas as dependências foram baixadas:
  ```bash
  mvn clean install
  ```
- Verifique se o JavaFX está configurado corretamente no `pom.xml`

### Interface Gráfica Não Abre
- Verifique se o JavaFX está configurado corretamente
- Verifique se há erros no console
- Tente executar com privilégios de administrador

### Outros Problemas
- Limpe o cache do Maven:
  ```bash
  mvn clean
  ```
- Delete a pasta `target` e recompile:
  ```bash
  rm -rf target
  mvn clean install
  ```

## 📞 Suporte
Se encontrar algum problema, verifique:
1. Se todos os pré-requisitos estão instalados corretamente
2. Se as variáveis de ambiente estão configuradas
3. Se o projeto foi clonado corretamente
4. Se há erros no console

Para mais ajuda, consulte a documentação do Java, Maven e JavaFX. 
