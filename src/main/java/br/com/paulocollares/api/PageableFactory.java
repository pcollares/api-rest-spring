/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Fábrica de Pageable para preparar esse objeto para ser repassado ao
 * repositório
 *
 * @author Paulo Collares
 */
public class PageableFactory {

    private final int page;
    private final int size;
    private final String sort;

    public PageableFactory(int page, int size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public Pageable getPageable() {
        Pageable pageable = PageRequest.of(page, size);

        if (sort != null) {
            if (sort.contains(",")) {
                String p[] = sort.split(",");
                String sort_ = p[0];
                String sortDirection = p[1];
                if (!sortDirection.equals("asc") && !sortDirection.equals("desc")) {
                    sortDirection = "asc";
                }
                if (sortDirection.equalsIgnoreCase("asc")) {
                    pageable = PageRequest.of(page, size, Sort.by(sort_).ascending());
                } else {
                    pageable = PageRequest.of(page, size, Sort.by(sort_).descending());
                }
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            }

        }
        return pageable;
    }

}
