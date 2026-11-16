package application;

import java.util.Arrays;
import java.util.List;
import model.Livro;
import service.RelatorioCatalogoService;

public class Main {
    public static void main(String[] args) {
        List<Livro> catalogo = Arrays.asList(
            new Livro("978-8576082675", "Codigo Limpo", "Robert C. Martin", "Engenharia de Software", 2009, 115.90),
            new Livro("978-8550804620", "Java Efetivo", "Joshua Bloch", "Linguagens de Programacao", 2019, 149.90),
            new Livro("978-8573076103", "Padroes de Projetos", "Erich Gamma et al.", "Arquitetura de Software", 2000, 165.00),
            new Livro("978-8550800653", "Arquitetura Limpa", "Robert C. Martin", "Arquitetura de Software", 2019, 98.50),
            new Livro("978-8575225639", "Estruturas de Dados e Algoritmos com JavaScript", "Loiane Groner", "Algoritmos", 2019, 89.00),
            new Livro("978-8576089629", "Refatoracao", "Martin Fowler", "Engenharia de Software", 2020, 134.00)
        );

        RelatorioCatalogoService relatorioService = new RelatorioCatalogoService();

        System.out.println("=== FILTRO POR AUTOR (ROBERT C. MARTIN) ===");
        relatorioService.filtrarPorAutor(catalogo, "Robert C. Martin")
                .forEach(System.out::println);

        System.out.println("\n=== FILTRO POR INTERVALO DE PUBLICACAO (2018 a 2020) ===");
        relatorioService.filtrarPorIntervaloAnos(catalogo, 2018, 2020)
                .forEach(System.out::println);

        System.out.println("\n=== GERACAO E EXPORTACAO DO RELATORIO DINAMICO ===");
        relatorioService.exportarRelatorioCompleto(catalogo, "relatorio_catalogo.txt");
    }
}
