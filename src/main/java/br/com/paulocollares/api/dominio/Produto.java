/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api.dominio;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidade produto
 *
 * @author Paulo Collares
 */
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
    @GeneratedValue(generator = "produto_seq", strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    private double valor;

    @ApiModelProperty(notes = "Identificador do produto")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiModelProperty(notes = "Nome do produto")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ApiModelProperty(notes = "Valor do produto")
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
