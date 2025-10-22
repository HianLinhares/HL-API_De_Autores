package Hian.Linhares.HL_API_De_Autores.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public record UsuarioDTO(String login, String senha, List<String> roles) {

}
