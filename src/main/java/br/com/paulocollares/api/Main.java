/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal do spring boot
 * @author Paulo Collares
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
        System.out.println("\n\n\n\n\n-----------------------------------------------------------------------------------");
        System.out.println("API REST INICIADA");
        System.out.println("www.paulocollares.com.br");
        System.out.println("-----------------------------------------------------------------------------------\n\n");
    }
}
