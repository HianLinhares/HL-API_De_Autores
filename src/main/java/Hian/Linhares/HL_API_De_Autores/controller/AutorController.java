package Hian.Linhares.HL_API_De_Autores.controller;

import Hian.Linhares.HL_API_De_Autores.dto.AutorDTO;
import Hian.Linhares.HL_API_De_Autores.model.Autor;
import Hian.Linhares.HL_API_De_Autores.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/autores")
//http://localhost:8080/autores
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }


    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity <Void> salvar(@RequestBody AutorDTO autor) {
        var autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);
        //http://localhost:8080/autores/"f22fd387-54de-4943-bcc7-3776ab571964"
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }























}
