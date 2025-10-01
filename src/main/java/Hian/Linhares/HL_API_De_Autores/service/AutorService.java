package Hian.Linhares.HL_API_De_Autores.service;

import Hian.Linhares.HL_API_De_Autores.model.Autor;
import Hian.Linhares.HL_API_De_Autores.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }


}
