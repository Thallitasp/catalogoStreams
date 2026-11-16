package model;

public class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private int anoPublicacao;
    private double preco;

    public Livro(String isbn, String titulo, String autor, String categoria, int anoPublicacao, double preco) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format("[%s] '%s' - %s | Categoria: %s | Ano: %d | R$ %.2f",
                isbn, titulo, autor, categoria, anoPublicacao, preco);
    }
}
