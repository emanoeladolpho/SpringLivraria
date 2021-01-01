package com.emanoel.socialbook.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O comentário deve ser preenchido!")
    @Size(max = 1500, message = "O comentário não pode conter mais de 1500 caracteres.")
    @JsonProperty("comentario") //  serve para mudar o nome do atributo que vai ser exibido para o cliente
    private String texto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usuario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIVRO_ID")
    @JsonIgnore // para evitar estouro de pilha. A partir de um livro e possível chegar nos comentarios, mas nao o contrario
    private Livro livro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
