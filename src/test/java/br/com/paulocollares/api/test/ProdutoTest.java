/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api.test;

import br.com.paulocollares.api.controladores.rest.ProdutoController;
import br.com.paulocollares.api.dominio.Produto;
import br.com.paulocollares.api.dto.ProdutoDTO;
import br.com.paulocollares.api.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Classe de teste do endpoint de produtos
 * @author Paulo Collares
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    ProdutoController.class
})
public class ProdutoTest {

    //URL base para acesso desse controlador
    private final String BASE_URL = "/produtos";

    //Instância do ObjectMapper para trabalhar com JSON
    private ObjectMapper objectMapper;

    //Controlador REST tratado por meio de injeção de dependências
    @Autowired
    private ProdutoController restController;

    //Instância do MockMVC
    private MockMvc mockMvc;

    //Instância do mock repository
    @MockBean
    private ProdutoRepository mockRepository;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    public void buscar_id_200() throws Exception {

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Teste");
        produto.setValor(10.0);

        when(mockRepository.findById(1)).thenReturn(Optional.of(produto));

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Teste")))
                .andExpect(jsonPath("$.valor", is(10.0)));

        verify(mockRepository, times(1)).findById(1);
    }

    @Test
    public void buscar_id_404() throws Exception {
        mockMvc.perform(get(BASE_URL + "/2")).andExpect(status().isNotFound());
    }

    @Test
    public void criar_200() throws Exception {

        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("Teste");
        dto.setValor(11.0);

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());

        when(mockRepository.save(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post(BASE_URL)
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Teste")))
                .andExpect(jsonPath("$.valor", is(11.0)));

        verify(mockRepository, times(1)).save(any(Produto.class));

    }

    @Test
    public void atualizar_200() throws Exception {

        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("Teste");
        dto.setValor(11.0);

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());

        when(mockRepository.findById(1)).thenReturn(Optional.of(produto));
        when(mockRepository.save(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(put(BASE_URL + "/1")
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void deletar_200() throws Exception {

        Produto produto = new Produto();
        produto.setId(1);

        when(mockRepository.findById(1)).thenReturn(Optional.of(produto));

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isOk());

        verify(mockRepository, times(1)).deleteById(1);
    }

}
