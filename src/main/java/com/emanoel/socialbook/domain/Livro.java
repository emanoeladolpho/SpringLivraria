package com.emanoel.socialbook.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "O campo nome é não pode ser vazio!")
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne // tipo de relacionamento
    @JoinColumn(name = "AUTOR_ID")
    private Autor autor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy") // setar o formato de exibicao da data
    @NotNull(message = "Campo publicação é de preenchimento obrigatório")
    private Date publicacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Campo editora é de preenchimento obrigatório!")
    private String editora;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "O resumento deve ser preenchido")
    @Size(max = 1500, message = "O resumo não pode conter mais de 1500 caracteres!")
    private String resumo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "livro") // tipo de relacionamento
    private List<Comentario> comentarios;

    public Livro(){

    }

    public Livro (String nome){
        this.nome = nome;
    }

    public Livro(Long id, String nome, Autor autor, Date publicacao, String editora, String resumo, List<Comentario> comentarios) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.publicacao = publicacao;
        this.editora = editora;
        this.resumo = resumo;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Autor getAutor() {
         return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Date getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Date publicacao) {
        this.publicacao = publicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
