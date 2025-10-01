package Hian.Linhares.HL_API_De_Autores.dto;


import Hian.Linhares.HL_API_De_Autores.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

//DTO -> Data Transfer Object
public record AutorDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {

public Autor mapearParaAutor(){
    Autor autor = new Autor();
    autor.setNome(this.nome);
    autor.setDataNacimento(this.dataNascimento);
    autor.setNacionalidade(this.nacionalidade);
    return autor;
}



}
