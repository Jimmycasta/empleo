package com.jimmycasta.empleo;

import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmpleoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpleoApplication.class, args);
    }



}
