/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Cotrolador principal responsável por redirecionar o acesso a documentação do
 * swagger
 *
 * @author Paulo Collares
 */
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        //Redireciona o acesso da raiz para a documentação
        return new RedirectView("swagger-ui.html");
    }
}
