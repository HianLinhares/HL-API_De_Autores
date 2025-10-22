package Hian.Linhares.HL_API_De_Autores.controller;


import Hian.Linhares.HL_API_De_Autores.dto.UsuarioDTO;
import Hian.Linhares.HL_API_De_Autores.model.Usuario;
import Hian.Linhares.HL_API_De_Autores.repository.UsuarioRepository;
import Hian.Linhares.HL_API_De_Autores.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService service;

@Operation(summary = "Salvar usuário", description = "Salvar no banco de dados usuários com acesso")
    @PostMapping
    public void salvar(@RequestBody Usuario usuario){
        service.salvar(usuario);
    }



}
