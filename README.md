# 📊 Relatórios Dinâmicos de Catálogo com Java Streams e Lambda

Aplicação desenvolvida em Java para a **Atividade Prática 2** (Streams e Lambda para Relatórios de Dados), demonstrando o processamento funcional de coleções com a Streams API, expressões Lambda, operações de redução e exportação de relatórios formatados.

---

## 🎯 Funcionalidades Implementadas

- **Estatísticas do Acervo (`DoubleSummaryStatistics` + `Optional`):** Cálculo do total de livros, média de preços, livro mais caro e livro menos caro utilizando `Comparator` com expressões lambda.
- **Filtragem Funcional (`filter` + `sorted`):** Consultas dinâmicas por autor e por intervalo de ano de publicação.
- **Redução e Agrupamento (`reduce` + `Collectors.groupingBy`):** Soma total do valor do catálogo via operação `reduce` e contagem de títulos agrupados por categoria.
- **Exportação de Relatório Dinâmico:** Formatação estruturada com `StringBuilder` e gravação em arquivo texto (`relatorio_catalogo.txt`) via `BufferedWriter` com `try-with-resources`.

---

## 📂 Estrutura do Projeto

text
src/
├── model/
│   └── Livro.java                      # Entidade representando cada obra do catálogo
│
├── service/
│   └── RelatorioCatalogoService.java   # Operações com Streams, Lambdas, reduce e exportação
│
└── application/
    └── Main.java                       # Massa de dados e execução dos relatórios

---

## 🚀 Como Executar no Terminal

1. **Clone o repositório:**
   bash
   git clone [https://github.com/Thallitasp/catalogo-streams-lambda.git](https://github.com/Thallitasp/catalogo-streams-lambda.git)
   cd catalogo-streams-lambda

2. **Compile os arquivos:**
   bash
   javac -d bin src/model/*.java src/service/*.java src/application/*.java

3. **Execute a aplicação:**
   bash
   java -cp bin application.Main

---

## 👩‍💻 Autora

**Thallita da Silva Pedreira**  
GitHub: @Thallitasp