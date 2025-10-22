package Hian.Linhares.HL_API_De_Autores.service;

import Hian.Linhares.HL_API_De_Autores.dto.UsuarioDTO;
import Hian.Linhares.HL_API_De_Autores.model.Usuario;
import Hian.Linhares.HL_API_De_Autores.repository.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data

public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    //criptografia de senha
    public void salvar (Usuario usuario){
        repository.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return repository.findAll();
    }
    
    public Optional<Usuario> buscaPorId(Usuario usuario){
        return repository.findById(usuario.getId());
    }

    public Usuario obterPorLogin(String login){
        return repository.findByLogin(login);
    }

}
