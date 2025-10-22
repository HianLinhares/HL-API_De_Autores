package Hian.Linhares.HL_API_De_Autores.controller;


import Hian.Linhares.HL_API_De_Autores.dto.UsuarioDTO;
import Hian.Linhares.HL_API_De_Autores.model.Usuario;
import Hian.Linhares.HL_API_De_Autores.repository.UsuarioRepository;
import Hian.Linhares.HL_API_De_Autores.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;


    @PostMapping
    public void salvar(@RequestBody Usuario usuario){
        service.salvar(usuario);
    }



}
