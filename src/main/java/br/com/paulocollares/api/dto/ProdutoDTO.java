/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * DTO de produto
 *
 * @author Paulo Collares
 */
public class ProdutoDTO {

    private String nome;
    private Double valor;

    @ApiModelProperty(notes = "Nome do produto")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    @ApiModelProperty(notes = "Valor do produto")
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
