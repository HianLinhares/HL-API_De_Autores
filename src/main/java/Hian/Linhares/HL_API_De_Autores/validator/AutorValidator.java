package Hian.Linhares.HL_API_De_Autores.validator;

import Hian.Linhares.HL_API_De_Autores.exceptions.RegistroDuplicados;
import Hian.Linhares.HL_API_De_Autores.model.Autor;
import Hian.Linhares.HL_API_De_Autores.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {


    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar (Autor autor){
        if(existeAutorCadasrado(autor)){
            throw new RegistroDuplicados("Autor já cadastrado");
        }

    }

    private boolean existeAutorCadasrado(Autor autor){
        Optional<Autor> autorEncontrado = repository.existsByNomeAndDataNascimentoAndNacionalidade(autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade());

        if (autor.getId() == null){
            return autorEncontrado.isPresent();
        }

        return autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();

    }




}
