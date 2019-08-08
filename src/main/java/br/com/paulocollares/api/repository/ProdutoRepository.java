/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api.repository;

import br.com.paulocollares.api.dominio.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Reṕsitório de produtos
 *
 * @author Paulo Collares
 */
@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

    public Page<Produto> findAll(Pageable pageable);

    @Query("SELECT p FROM Produto p "
            + "WHERE lower(nome) like %:busca% ")
    public Page<Produto> busca(@Param("busca") String busca, Pageable pageable);

}
