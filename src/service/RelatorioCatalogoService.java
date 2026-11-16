package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Livro;

public class RelatorioCatalogoService {

    public DoubleSummaryStatistics calcularEstatisticasPreco(List<Livro> livros) {
        return livros.stream()
                .mapToDouble(Livro::getPreco)
                .summaryStatistics();
    }

    public Optional<Livro> buscarLivroMaisCaro(List<Livro> livros) {
        return livros.stream()
                .max(Comparator.comparingDouble(l -> l.getPreco()));
    }

    public Optional<Livro> buscarLivroMaisBarato(List<Livro> livros) {
        return livros.stream()
                .min(Comparator.comparingDouble(l -> l.getPreco()));
    }

    public List<Livro> filtrarPorAutor(List<Livro> livros, String autor) {
        return livros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .sorted(Comparator.comparingInt(Livro::getAnoPublicacao))
                .collect(Collectors.toList());
    }

    public List<Livro> filtrarPorIntervaloAnos(List<Livro> livros, int anoInicio, int anoFim) {
        return livros.stream()
                .filter(l -> l.getAnoPublicacao() >= anoInicio && l.getAnoPublicacao() <= anoFim)
                .sorted(Comparator.comparingInt(Livro::getAnoPublicacao))
                .collect(Collectors.toList());
    }

    public double calcularValorTotalEstoque(List<Livro> livros) {
        return livros.stream()
                .map(Livro::getPreco)
                .reduce(0.0, (subtotal, preco) -> subtotal + preco);
    }

    public Map<String, Long> contarLivrosPorCategoria(List<Livro> livros) {
        return livros.stream()
                .collect(Collectors.groupingBy(Livro::getCategoria, Collectors.counting()));
    }

    public void exportarRelatorioCompleto(List<Livro> livros, String caminhoArquivo) {
        DoubleSummaryStatistics stats = calcularEstatisticasPreco(livros);
        Optional<Livro> maisCaro = buscarLivroMaisCaro(livros);
        Optional<Livro> maisBarato = buscarLivroMaisBarato(livros);
        double valorTotalReduce = calcularValorTotalEstoque(livros);
        Map<String, Long> contagemPorCategoria = contarLivrosPorCategoria(livros);

        String dataGeracao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================\n");
        sb.append("           RELATORIO ANALITICO DO CATALOGO DE LIVROS (JAVA)         \n");
        sb.append("Gerado em: ").append(dataGeracao).append("\n");
        sb.append("====================================================================\n\n");

        sb.append("1. ESTATISTICAS GERAIS DO ACERVO\n");
        sb.append("--------------------------------------------------------------------\n");
        sb.append(String.format("Total de livros catalogados : %d\n", stats.getCount()));
        sb.append(String.format("Valor total do acervo (reduce): R$ %.2f\n", valorTotalReduce));
        sb.append(String.format("Media de precos               : R$ %.2f\n", stats.getAverage()));
        maisCaro.ifPresent(l -> sb.append("Livro mais caro               : ").append(l).append("\n"));
        maisBarato.ifPresent(l -> sb.append("Livro menos caro              : ").append(l).append("\n"));
        sb.append("\n");

        sb.append("2. DISTRIBUICAO DE LIVROS POR CATEGORIA\n");
        sb.append("--------------------------------------------------------------------\n");
        contagemPorCategoria.forEach((categoria, qtd) ->
                sb.append(String.format("- %-22s: %d livro(s)\n", categoria, qtd)));
        sb.append("\n");

        sb.append("3. LISTAGEM COMPLETA ORDENADA POR TITULO\n");
        sb.append("--------------------------------------------------------------------\n");
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(l -> sb.append(l).append("\n"));
        sb.append("====================================================================\n");

        String relatorioFinal = sb.toString();
        System.out.println(relatorioFinal);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write(relatorioFinal);
            System.out.println("[Exportacao] Relatorio salvo com sucesso em: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("[Erro de I/O] Falha ao exportar o relatorio: " + e.getMessage());
        }
    }
}
