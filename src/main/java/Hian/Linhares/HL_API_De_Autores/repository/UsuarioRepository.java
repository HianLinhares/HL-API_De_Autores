package Hian.Linhares.HL_API_De_Autores.repository;

import Hian.Linhares.HL_API_De_Autores.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository <Usuario, UUID>{

        Usuario findByLogin(String login);

}
