/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api.controladores.rest;

import br.com.paulocollares.api.ApiError;
import br.com.paulocollares.api.PageableFactory;
import br.com.paulocollares.api.dominio.Produto;
import br.com.paulocollares.api.dto.ProdutoDTO;
import br.com.paulocollares.api.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controlador de Produtos
 *
 * @author Paulo Collares
 */
@RestController
@CrossOrigin
@RequestMapping("/produtos")
@Api(tags = "Produtos", description = "API de produtos")
public class ProdutoController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProdutoRepository produtoRepository;

    @ApiOperation(value = "Lista os produtos")
    @GetMapping()
    public Page<Produto> listar(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size,
            @RequestParam(
                    value = "sort",
                    required = false) String sort,
            @RequestParam(
                    value = "q",
                    required = false) String q
    ) {
        Pageable pageable = new PageableFactory(page, size, sort).getPageable();

        Page<Produto> resultPage;

        if (q == null) {
            resultPage = produtoRepository.findAll(pageable);
        } else {
            resultPage = produtoRepository.busca(q.toLowerCase(), pageable);
        }

        return resultPage;
    }

    @ApiOperation(value = "Busca um produto pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> listar(@PathVariable Integer id) {
        Optional<Produto> rastreador = produtoRepository.findById(id);

        if (!rastreador.isPresent()) {
            return ApiError.notFound("Produto não encontrado");
        }

        return new ResponseEntity<>(rastreador.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria um novo Produto")
    @PostMapping()
    public ResponseEntity<Produto> criar(@RequestBody ProdutoDTO dto, UriComponentsBuilder ucBuilder) {
        try {
            //Crio um objeto da entidade preenchendo com os valores do DTO e validando
            Produto produto = new Produto();

            if (dto.getNome() == null || dto.getNome().length() < 2) {
                return ApiError.badRequest("Informe o nome do produto");
            }
            produto.setNome(dto.getNome());

            if (dto.getValor() == null || dto.getValor() <= 0) {
                return ApiError.badRequest("Valor do produto inválido");
            }
            produto.setValor(dto.getValor());

            Produto novo = produtoRepository.save(produto);

            //Se ocorreu algum erro, retorno esse erro para a API
            if (novo == null) {
                return ApiError.badRequest("Ocorreu algum erro na criação do produto");
            }

            //Se foi criado com sucesso, retorno o objeto criado
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Erro ao criar um produto", e);
            return ApiError.internalServerError("Ocorreu algum erro na criação do produto");
        }
    }

    @ApiOperation(value = "Atualiza um produto")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable("id") int id, @RequestBody ProdutoDTO dto) {
        try {
            Optional<Produto> produtoAtual = produtoRepository.findById(id);

            if (!produtoAtual.isPresent()) {
                return ApiError.notFound("Produto não encontrado");
            }

            if (dto.getNome() != null) {
                if (dto.getNome().length() < 2) {
                    return ApiError.badRequest("Nome do produto inválido");
                }
                produtoAtual.get().setNome(dto.getNome());
            }

            if (dto.getValor() != null) {
                if (dto.getValor() <= 0) {
                    return ApiError.badRequest("Valor do produto inválido");
                }
                produtoAtual.get().setValor(dto.getValor());
            }

            //Atualizo o objeto utilizando o repositório
            Produto atualizado = produtoRepository.save(produtoAtual.get());

            //Se ocorreu algum erro, retorno esse erro para a API
            if (atualizado == null) {
                return ApiError.internalServerError("Erro na atualização do produto");
            }

            //Se foi criado com sucesso, retorno o objeto atualizado
            return new ResponseEntity<>(atualizado, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar um produto", e);
            return ApiError.internalServerError("Erro na atualização do produto");
        }
    }

    @ApiOperation(value = "Remove um produto")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Produto> deletar(@PathVariable Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (!produto.isPresent()) {
            return ApiError.notFound("Produto não encontrado");
        } else {
            produtoRepository.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
